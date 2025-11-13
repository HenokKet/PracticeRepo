import React, { useEffect, useMemo, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext.jsx';
import { API_BASE } from '../lib/api';
import '../App.css';

export default function Schedule() {
  const { isLoggedIn, authToken, user } = useAuth();
  const navigate = useNavigate();

  const [meds, setMeds] = useState([]);
  const [loading, setLoading] = useState(true);
  const [err, setErr] = useState('');
  const [saving, setSaving] = useState({}); 

  useEffect(() => {
    if (!isLoggedIn) navigate('/login', { replace: true });
  }, [isLoggedIn, navigate]);

  // --- Load medications ---
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

  const getField = (obj, ...keys) => {
    for (const k of keys) if (obj[k] !== undefined) return obj[k];
    return undefined;
  };

  const toCamelMedication = (m) => ({
    applicationNo: getField(m, 'applicationNo', 'ApplicationNo'),
    medicationName: getField(m, 'medicationName', 'medication_name'),
    qty: getField(m, 'qty', 'Qty') ?? 0,
    firstDose: getField(m, 'firstDose', 'firstDosage'),
    lastDose: getField(m, 'lastDose', 'lastDosage'),
    doseIntervalHours: getField(m, 'doseIntervalHours', 'dose_interval_hours'),
  });

  const rows = useMemo(() => {
    const toDate = (v) => (v ? new Date(v) : null);

    const computeNextDose = (firstDosage, lastDosage, intervalHours) => {
      const now = new Date();
      const start = toDate(firstDosage);
      const end = toDate(lastDosage);
      if (!start || !end || !intervalHours || intervalHours <= 0) return null;

      if (now > end) return null;        
      if (now <= start) return start;    

      const ms = (d) => d.getTime();
      const intervalMs = intervalHours * 60 * 60 * 1000;
      const elapsed = ms(now) - ms(start);
      const k = Math.ceil(elapsed / intervalMs);
      const candidate = new Date(ms(start) + k * intervalMs);
      return candidate <= end ? candidate : null;
    };

    const formatDT = (d) =>
      d
        ? `${d.toLocaleDateString()} ${d.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })}`
        : '—';

    const statusFor = (firstDosage, lastDosage) => {
      const now = new Date();
      const start = toDate(firstDosage);
      const end = toDate(lastDosage);
      if (!start || !end) return 'Unknown';
      if (now < start) return 'Scheduled';
      if (now > end) return 'Completed';
      return 'Active';
    };

    return meds.map((m) => {
      const camel = toCamelMedication(m);
      const id = camel.applicationNo ?? getField(m, 'id', 'medication_name');
      const next = computeNextDose(camel.firstDose, camel.lastDose, camel.doseIntervalHours);
      const status = statusFor(camel.firstDose, camel.lastDose);

      return {
        id,
        ...camel,
        nextDose: next,
        nextDoseDisplay: formatDT(next),
        status
      };
    });
  }, [meds]);

  const activeRows = useMemo(
    () => rows.filter((r) => (r.qty ?? 0) > 0 && r.status !== 'Completed'),
    [rows]
  );

  //helper to log doses locally
  const addDoseLog = (applicationNo, qty = 1) => {
    const log = { t: new Date().toISOString(), applicationNo, qty };
    const prev = JSON.parse(localStorage.getItem('doseLogs') || '[]');
    prev.push(log);
    localStorage.setItem('doseLogs', JSON.stringify(prev));
  };

  const handleTakeDose = async (row) => {
    const id = row.id;
    if (!id) return;

    const disable = row.status === 'Completed' || !row.nextDose;
    if (disable) return;

    const prevMeds = meds;

    const newList = [];
    let updatedCamel = null;

    for (const m of meds) {
      const camel = toCamelMedication(m);
      const mid = camel.applicationNo ?? getField(m, 'id', 'medication_name');

      if (mid !== id) {
        newList.push(m);
        continue;
      }

      const currentQty = camel.qty ?? 0;
      const decQty = Math.max(0, currentQty - 1);

      if (decQty === 0) {
        // drop from list (do not push)
      } else {
        const updated =
          'qty' in m
            ? { ...m, qty: decQty }
            : 'Qty' in m
            ? { ...m, Qty: decQty }
            : { ...m, qty: decQty };
        newList.push(updated);
      }

      updatedCamel = {
        applicationNo: camel.applicationNo,
        medicationName: camel.medicationName,
        qty: decQty,
        firstDose: camel.firstDose,
        lastDose: camel.lastDose,
        doseIntervalHours: camel.doseIntervalHours,
      };
    }

    setSaving((s) => ({ ...s, [id]: true }));
    setMeds(newList);

    try {
      if (updatedCamel && updatedCamel.qty > 0) {
        const res = await fetch(`${API_BASE}/api/pharma`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${authToken}`,
          },
          body: JSON.stringify(updatedCamel),
        });
        if (!res.ok) {
          setMeds(prevMeds); // revert
          const text = await res.text().catch(() => '');
          throw new Error(text || `Update failed with status ${res.status}`);
        }
        addDoseLog(id, 1);
      } else {
        // Qty hit 0 → delete on server
        const res = await fetch(`${API_BASE}/api/pharma/${encodeURIComponent(id)}`, {
          method: 'DELETE',
          headers: { Authorization: `Bearer ${authToken}` },
        });
        if (!res.ok && res.status !== 404) {
          setMeds(prevMeds); // revert
          const text = await res.text().catch(() => '');
          throw new Error(text || `Delete failed with status ${res.status}`);
        }
        // Log after a confirmed successful delete
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
      {/* Heading */}
      <div className="d-flex flex-column align-items-stretch mb-3">
        <h2 className="mb-2 text-center w-100">Schedule</h2>
        <p className="text-secondary m-0 text-center">{user.firstName}'s upcoming doses</p>
      </div>

      {/* Load/Error */}
      {loading && <div className="alert alert-info">Loading schedule…</div>}
      {!!err && <div className="alert alert-danger">Error: {err}</div>}

      {/* Table */}
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
              {activeRows.length === 0 ? (
                <tr>
                  <td colSpan={6} className="text-center text-secondary py-4">
                    No active medications scheduled.
                  </td>
                </tr>
              ) : (
                activeRows.map((r) => {
                  const disable =
                    r.status === 'Completed' || !r.nextDose || (Number.isFinite(r.qty) && r.qty <= 0);

                  return (
                    <tr key={r.id}>
                      <td className="fw-medium">{r.medicationName ?? r.name}</td>
                      <td>{r.nextDoseDisplay}</td>
                      <td>{r.doseIntervalHours ?? r.interval}</td>
                      <td>{r.qty}</td>
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
