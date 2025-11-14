import React, { useEffect, useMemo, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext.jsx';
import { API_BASE } from '../lib/api';
import '../App.css';

/**
 * Activity Component
 * 
 * Displays a user's medication dose history for the last 24 hours.
 * Reads dose logs from localStorage (written by Schedule.jsx) and enriches
 * them with medication names fetched from the API.
 */
export default function Activity() {
  // Extract authentication state and user info from context
  const { isLoggedIn, authToken, user } = useAuth();
  const navigate = useNavigate();

  // Map of applicationNo -> medicationName for display purposes
  const [medsMap, setMedsMap] = useState(new Map());
  
  // Array of dose log entries from localStorage
  const [logs, setLogs] = useState([]);
  
  // Loading state for initial data fetch
  const [loading, setLoading] = useState(true);
  
  // Error message state
  const [err, setErr] = useState('');

  /**
   * Redirect unauthenticated users to login page
   */
  useEffect(() => {
    if (!isLoggedIn) {
      navigate('/login', { replace: true });
    }
  }, [isLoggedIn, navigate]);

  /**
   * Fetch medications from API to build applicationNo -> name mapping
   * This allows us to display medication names instead of just IDs
   */
  useEffect(() => {
    if (!isLoggedIn) return;

    const fetchMeds = async () => {
      try {
        const res = await fetch(`${API_BASE}/api/pharma`, {
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${authToken}`,
          },
        });
        if (!res.ok) throw new Error(`Meds fetch failed: ${res.status}`);
        const data = await res.json();

        // Build map handling both camelCase and snake_case field names
        const map = new Map();
        (Array.isArray(data) ? data : []).forEach((m) => {
          const applicationNo = m.applicationNo ?? m.ApplicationNo;
          const name = m.medicationName ?? m.medication_name ?? '—';
          if (applicationNo != null) map.set(applicationNo, name);
        });
        setMedsMap(map);
      } catch (e) {
        // Non-fatal error - we can still display logs with applicationNo fallback
        console.warn(e);
      }
    };

    fetchMeds();
  }, [isLoggedIn, authToken]);

  /**
   * Load dose logs from localStorage
   * Expected format: Array of { t: ISOString, applicationNo: number, qty: number }
   * These logs are written by Schedule.jsx when user clicks "Take Dose"
   */
  useEffect(() => {
    try {
      const raw = localStorage.getItem('doseLogs');
      const list = raw ? JSON.parse(raw) : [];
      setLogs(Array.isArray(list) ? list : []);
    } catch (e) {
      console.warn('Failed to parse local doseLogs', e);
      setLogs([]);
    } finally {
      setLoading(false);
    }
  }, []);

  /**
   * Filter and format logs for display
   * - Only shows doses from last 24 hours
   * - Sorts newest first
   * - Enriches with medication names from medsMap
   * - Formats timestamps for display
   */
  const rows = useMemo(() => {
    // Calculate 24-hour cutoff timestamp
    const cutoff = Date.now() - 24 * 60 * 60 * 1000;

    const recent = logs
      // Filter for entries within last 24 hours
      .filter((l) => {
        const ts = Date.parse(l.t ?? l.time ?? l.timestamp ?? '');
        return Number.isFinite(ts) && ts >= cutoff;
      })
      // Sort by timestamp descending (newest first)
      .sort((a, b) => Date.parse(b.t) - Date.parse(a.t))
      // Transform into display format
      .map((l, idx) => {
        // Format timestamp as human-readable date/time
        const when = l.t
          ? new Date(l.t).toLocaleString([], {
              year: 'numeric',
              month: 'short',
              day: '2-digit',
              hour: '2-digit',
              minute: '2-digit',
            })
          : '—';
        
        // Get medication name from map, fallback to applicationNo
        const medName =
          medsMap.get(l.applicationNo) ?? `#${l.applicationNo ?? '—'}`;
        
        return {
          id: `${l.applicationNo}-${l.t}-${idx}`,
          medName,
          qty: l.qty ?? 1,
          when,
        };
      });

    return recent;
  }, [logs, medsMap]);

  return (
    <div className="container py-4">
      {/* Page heading with personalized title */}
      <div className="d-flex flex-column align-items-stretch mb-3">
        <h2 className="mb-2 text-center w-100">
          {user?.firstName ? `${user.firstName}'s Recent Activity` : 'Recent Activity'}
        </h2>
        <p className="text-secondary m-0 text-center">
          Doses taken in the last 24 hours
        </p>
      </div>

      {/* Loading indicator */}
      {loading && <div className="alert alert-info">Loading activity…</div>}
      
      {/* Error message */}
      {!!err && <div className="alert alert-danger">Error: {err}</div>}

      {/* Activity table */}
      {!loading && !err && (
        <div className="table-responsive">
          <table className="table table-hover align-middle">
            <thead className="table-light">
              <tr>
                <th className="text-nowrap">Time</th>
                <th>Medication</th>
                <th className="text-nowrap">Qty</th>
              </tr>
            </thead>
            <tbody>
              {/* Empty state when no doses recorded */}
              {rows.length === 0 ? (
                <tr>
                  <td colSpan={3} className="text-center text-secondary py-4">
                    No doses recorded in the last 24 hours.
                  </td>
                </tr>
              ) : (
                // Render each dose log entry
                rows.map((r) => (
                  <tr key={r.id}>
                    <td className="text-nowrap">{r.when}</td>
                    <td className="fw-medium">{r.medName}</td>
                    <td>{r.qty}</td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}