// src/components/Activity.jsx
import React, { useEffect, useMemo, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext.jsx';
import { API_BASE } from '../lib/api';
import '../App.css';

export default function Activity() {
  const { isLoggedIn, authToken,user } = useAuth();
  const navigate = useNavigate();

  const [medsMap, setMedsMap] = useState(new Map());  // applicationNo -> medicationName
  const [logs, setLogs] = useState([]);               // local dose logs
  const [loading, setLoading] = useState(true);
  const [err, setErr] = useState('');

  // Redirect if not authenticated
  useEffect(() => {
    if (!isLoggedIn) {
      navigate('/login', { replace: true });
    }
  }, [isLoggedIn, navigate]);

  // Fetch medications (for name lookup)
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

        const map = new Map();
        (Array.isArray(data) ? data : []).forEach((m) => {
          const applicationNo = m.applicationNo ?? m.ApplicationNo;
          const name = m.medicationName ?? m.medication_name ?? '—';
          if (applicationNo != null) map.set(applicationNo, name);
        });
        setMedsMap(map);
      } catch (e) {
        // Not fatal — we can still show logs without names
        console.warn(e);
      }
    };

    fetchMeds();
  }, [isLoggedIn, authToken]);

  // Load client-side dose logs from localStorage
  useEffect(() => {
    // Expecting an array of records like:
    // { t: ISOString, applicationNo: number, qty: number }
    // These are written in Schedule.jsx when "Take Dose" is pressed.
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

  // Filter last 24 hours and format rows
  const rows = useMemo(() => {
    const cutoff = Date.now() - 24 * 60 * 60 * 1000;

    const recent = logs
      .filter((l) => {
        const ts = Date.parse(l.t ?? l.time ?? l.timestamp ?? '');
        return Number.isFinite(ts) && ts >= cutoff;
      })
      .sort((a, b) => Date.parse(b.t) - Date.parse(a.t)) // newest first
      .map((l, idx) => {
        const when = l.t
          ? new Date(l.t).toLocaleString([], {
              year: 'numeric',
              month: 'short',
              day: '2-digit',
              hour: '2-digit',
              minute: '2-digit',
            })
          : '—';
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
      {/* Heading */}
      <div className="d-flex flex-column align-items-stretch mb-3">
        <h2 className="mb-2 text-center w-100"> {user.firstName}'s Recent Activity</h2>
        <p className="text-secondary m-0 text-center">
          Doses taken in the last 24 hours
        </p>
      </div>

      {/* Load/Error */}
      {loading && <div className="alert alert-info">Loading activity…</div>}
      {!!err && <div className="alert alert-danger">Error: {err}</div>}

      {/* Table */}
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
              {rows.length === 0 ? (
                <tr>
                  <td colSpan={3} className="text-center text-secondary py-4">
                    No doses recorded in the last 24 hours.
                  </td>
                </tr>
              ) : (
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
