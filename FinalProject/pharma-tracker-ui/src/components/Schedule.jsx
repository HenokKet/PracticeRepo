import React, { useEffect, useMemo, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext.jsx';
import { API_BASE } from '../lib/api';
import '../App.css';

/**
 * Schedule Component
 * 
 * Displays upcoming medication doses with calculated next dose times.
 * Users can mark doses as taken, which decrements quantity and logs the dose.
 * Automatically removes medications when quantity reaches zero.
 * Shows only active medications (qty > 0 and not completed).
 */
export default function Schedule() {
  const { isLoggedIn, authToken, user } = useAuth();
  const navigate = useNavigate();

  // Medication list and UI state
  const [meds, setMeds] = useState([]);           // Raw medication data from API
  const [loading, setLoading] = useState(true);   // Initial load state
  const [err, setErr] = useState('');             // Global error message
  const [saving, setSaving] = useState({});       // Track which medications are being saved (by ID)

  /**
   * Redirect unauthenticated users to login page
   */
  useEffect(() => {
    if (!isLoggedIn) navigate('/login', { replace: true });
  }, [isLoggedIn, navigate]);

  /**
   * Fetch all medications from API on mount
   */
  useEffect(() => {
    if (!isLoggedIn) return;

    const fetchMeds = async () => {
      setLoading(true);
      setErr('');
      try {
        const res = await fetch(`${API_BASE}/api/pharma`, {
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${authToken}`,
          },
        });
        if (!res.ok) throw new Error(`Fetch failed with status ${res.status}`);
        const data = await res.json();
        setMeds(Array.isArray(data) ? data : []);
      } catch (e) {
        setErr(e.message || 'Failed to load schedule');
      } finally {
        setLoading(false);
      }
    };

    fetchMeds();
  }, [isLoggedIn, authToken]);

  /**
   * Helper to get field value from object with multiple possible key names
   * Handles API responses with different naming conventions
   * @param {Object} obj - Source object
   * @param {...string} keys - Possible key names to check
   * @returns {*} First found value or undefined
   */
  const getField = (obj, ...keys) => {
    for (const k of keys) if (obj[k] !== undefined) return obj[k];
    return undefined;
  };

  /**
   * Normalize medication object to consistent camelCase format
   * @param {Object} m - Raw medication object from API
   * @returns {Object} Normalized medication object
   */
  const toCamelMedication = (m) => ({
    applicationNo: getField(m, 'applicationNo', 'ApplicationNo'),
    medicationName: getField(m, 'medicationName', 'medication_name'),
    qty: getField(m, 'qty', 'Qty') ?? 0,
    firstDose: getField(m, 'firstDose', 'firstDosage'),
    lastDose: getField(m, 'lastDose', 'lastDosage'),
    doseIntervalHours: getField(m, 'doseIntervalHours', 'dose_interval_hours'),
  });

  /**
   * Compute display rows with calculated next dose times and status
   * Memoized to avoid recalculation on every render
   */
  const rows = useMemo(() => {
    /**
     * Convert value to Date object
     * @param {string|Date} v - Date value
     * @returns {Date|null} Date object or null if invalid
     */
    const toDate = (v) => (v ? new Date(v) : null);

    /**
     * Calculate the next scheduled dose time based on interval
     * @param {string|Date} firstDosage - First dose timestamp
     * @param {string|Date} lastDosage - Last dose timestamp
     * @param {number} intervalHours - Hours between doses
     * @returns {Date|null} Next dose time or null if medication is completed/invalid
     */
    const computeNextDose = (firstDosage, lastDosage, intervalHours) => {
      const now = new Date();
      const start = toDate(firstDosage);
      const end = toDate(lastDosage);
      if (!start || !end || !intervalHours || intervalHours <= 0) return null;

      // Medication period has ended
      if (now > end) return null;
      
      // Medication hasn't started yet - first dose is next
      if (now <= start) return start;

      // Calculate next dose based on interval from start time
      const ms = (d) => d.getTime();
      const intervalMs = intervalHours * 60 * 60 * 1000;
      const elapsed = ms(now) - ms(start);
      const k = Math.ceil(elapsed / intervalMs); // Number of intervals passed
      const candidate = new Date(ms(start) + k * intervalMs);
      
      // Only return if next dose is within the medication period
      return candidate <= end ? candidate : null;
    };

    /**
     * Format Date object for display in table
     * @param {Date} d - Date to format
     * @returns {string} Formatted date string or '—' if null
     */
    const formatDT = (d) =>
      d
        ? `${d.toLocaleDateString()} ${d.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })}`
        : '—';

    /**
     * Determine medication status based on current time and dose schedule
     * @param {string|Date} firstDosage - First dose timestamp
     * @param {string|Date} lastDosage - Last dose timestamp
     * @returns {string} Status: 'Scheduled', 'Active', 'Completed', or 'Unknown'
     */
    const statusFor = (firstDosage, lastDosage) => {
      const now = new Date();
      const start = toDate(firstDosage);
      const end = toDate(lastDosage);
      if (!start || !end) return 'Unknown';
      if (now < start) return 'Scheduled';     // Not started yet
      if (now > end) return 'Completed';       // Past end date
      return 'Active';                         // Currently active
    };

    // Transform each medication into display row format
    return meds.map((m) => {
      const camel = toCamelMedication(m);
      const id = camel.applicationNo ?? getField(m, 'id', 'medication_name');
      const next = computeNextDose(camel.firstDose, camel.lastDose, camel.doseIntervalHours);
      const status = statusFor(camel.firstDose, camel.lastDose);

      return {
        id,
        ...camel,
        nextDose: next,                    // Date object for logic
        nextDoseDisplay: formatDT(next),   // Formatted string for display
        status
      };
    });
  }, [meds]);

  /**
   * Filter to show only active medications
   * Excludes medications with zero quantity or completed status
   */
  const activeRows = useMemo(
    () => rows.filter((r) => (r.qty ?? 0) > 0 && r.status !== 'Completed'),
    [rows]
  );

  /**
   * Log a dose to localStorage for activity tracking
   * These logs are read by the Activity component
   * @param {number} applicationNo - Medication ID
   * @param {number} qty - Quantity taken (default 1)
   */
  const addDoseLog = (applicationNo, qty = 1) => {
    const log = { t: new Date().toISOString(), applicationNo, qty };
    const prev = JSON.parse(localStorage.getItem('doseLogs') || '[]');
    prev.push(log);
    localStorage.setItem('doseLogs', JSON.stringify(prev));
  };

  /**
   * Handle "Take Dose" button click
   * Decrements quantity, updates API, and logs the dose.
   * If quantity reaches zero, deletes the medication from the server.
   * Uses optimistic updates - immediately updates UI, reverts on error.
   * 
   * @param {Object} row - Medication row data
   */
  const handleTakeDose = async (row) => {
    const id = row.id;
    if (!id) return;

    // Prevent action on completed medications or those without a next dose
    const disable = row.status === 'Completed' || !row.nextDose;
    if (disable) return;

    // Store previous state for potential rollback
    const prevMeds = meds;

    const newList = [];
    let updatedCamel = null;

    // Build new medication list with decremented quantity
    for (const m of meds) {
      const camel = toCamelMedication(m);
      const mid = camel.applicationNo ?? getField(m, 'id', 'medication_name');

      // Keep other medications unchanged
      if (mid !== id) {
        newList.push(m);
        continue;
      }

      // Decrement quantity, minimum 0
      const currentQty = camel.qty ?? 0;
      const decQty = Math.max(0, currentQty - 1);

      if (decQty === 0) {
        // Remove from list (don't push) - will be deleted from server
      } else {
        // Update quantity in original format (preserve field name style)
        const updated =
          'qty' in m
            ? { ...m, qty: decQty }
            : 'Qty' in m
            ? { ...m, Qty: decQty }
            : { ...m, qty: decQty };
        newList.push(updated);
      }

      // Prepare payload for API update
      updatedCamel = {
        applicationNo: camel.applicationNo,
        medicationName: camel.medicationName,
        qty: decQty,
        firstDose: camel.firstDose,
        lastDose: camel.lastDose,
        doseIntervalHours: camel.doseIntervalHours,
      };
    }

    // Optimistic update - show change immediately
    setSaving((s) => ({ ...s, [id]: true }));
    setMeds(newList);

    try {
      if (updatedCamel && updatedCamel.qty > 0) {
        // Update medication with new quantity
        const res = await fetch(`${API_BASE}/api/pharma`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${authToken}`,
          },
          body: JSON.stringify(updatedCamel),
        });
        if (!res.ok) {
          setMeds(prevMeds); // Rollback on error
          const text = await res.text().catch(() => '');
          throw new Error(text || `Update failed with status ${res.status}`);
        }
        // Log dose after successful update
        addDoseLog(id, 1);
      } else {
        // Quantity hit 0 → delete medication from server
        const res = await fetch(`${API_BASE}/api/pharma/${encodeURIComponent(id)}`, {
          method: 'DELETE',
          headers: { Authorization: `Bearer ${authToken}` },
        });
        if (!res.ok && res.status !== 404) {
          setMeds(prevMeds); // Rollback on error
          const text = await res.text().catch(() => '');
          throw new Error(text || `Delete failed with status ${res.status}`);
        }
        // Log dose after successful delete
        addDoseLog(id, 1);
      }
    } catch (e) {
      setErr(e.message || 'Failed to save dose update');
    } finally {
      setSaving((s) => ({ ...s, [id]: false }));
    }
  };

  return (
    <div className="container py-4">
      {/* Page Heading */}
      <div className="d-flex flex-column align-items-stretch mb-3">
        <h2 className="mb-2 text-center w-100">Schedule</h2>
        <p className="text-secondary m-0 text-center">{user.firstName}'s upcoming doses</p>
      </div>

      {/* Loading and Error States */}
      {loading && <div className="alert alert-info">Loading schedule…</div>}
      {!!err && <div className="alert alert-danger">Error: {err}</div>}

      {/* Schedule Table */}
      {!loading && !err && (
        <div className="table-responsive">
          <table className="table table-hover align-middle">
            <thead className="table-light">
              <tr>
                <th>Medication</th>
                <th className="text-nowrap">Next Dose</th>
                <th className="text-nowrap">Interval (hrs)</th>
                <th>Qty</th>
                <th>Status</th>
                <th className="text-end">Action</th>
              </tr>
            </thead>
            <tbody>
              {/* Empty state when no active medications */}
              {activeRows.length === 0 ? (
                <tr>
                  <td colSpan={6} className="text-center text-secondary py-4">
                    No active medications scheduled.
                  </td>
                </tr>
              ) : (
                // Render each active medication row
                activeRows.map((r) => {
                  // Disable "Take Dose" button if completed, no next dose, or out of stock
                  const disable =
                    r.status === 'Completed' || !r.nextDose || (Number.isFinite(r.qty) && r.qty <= 0);

                  return (
                    <tr key={r.id}>
                      <td className="fw-medium">{r.medicationName ?? r.name}</td>
                      <td>{r.nextDoseDisplay}</td>
                      <td>{r.doseIntervalHours ?? r.interval}</td>
                      <td>{r.qty}</td>
                      {/* Status badge with color coding */}
                      <td>
                        <span
                          className={
                            r.status === 'Active'
                              ? 'badge bg-success'
                              : r.status === 'Scheduled'
                              ? 'badge bg-primary'
                              : 'badge bg-secondary'
                          }
                        >
                          {r.status}
                        </span>
                      </td>
                      {/* Take Dose action button */}
                      <td className="text-end">
                        <button
                          className="btn btn-sm btn-outline-success"
                          disabled={disable || saving[r.id]}
                          onClick={() => handleTakeDose(r)}
                        >
                          {saving[r.id] ? (
                            <>
                              <span className="spinner-border spinner-border-sm me-2" />
                              Saving…
                            </>
                          ) : (
                            'Take Dose'
                          )}
                        </button>
                      </td>
                    </tr>
                  );
                })
              )}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}