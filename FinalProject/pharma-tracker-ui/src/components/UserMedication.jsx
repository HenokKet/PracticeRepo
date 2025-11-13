import React, { useEffect, useMemo, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext.jsx';
import { API_BASE,FDA_BASE } from '../lib/api';
import '../App.css';

export default function UserMedication() {
  const { isLoggedIn, authToken } = useAuth();
  const navigate = useNavigate();

  const [rows, setRows] = useState([]);
  const [loading, setLoading] = useState(true);
  const [err, setErr] = useState('');
  const [busyId, setBusyId] = useState(null);

  const [showForm, setShowForm] = useState(false);
  const [status, setStatus] = useState({ saving: false, error: '' });

  const [form, setForm] = useState({
    applicationNo: '',
    medicationName: '',
    firstDose: '',
    lastDose: '',
    doseIntervalHours: '',
    qty: '',
  });

  useEffect(() => {
    if (!isLoggedIn) navigate('/login', { replace: true });
  }, [isLoggedIn, navigate]);

  const getField = (obj, ...keys) => {
    for (const k of keys) if (obj?.[k] !== undefined) return obj[k];
    return undefined;
  };

  const toCamelMedication = (m) => ({
    applicationNo: getField(m, 'applicationNo', 'ApplicationNo'),
    medicationName: getField(m, 'medicationName', 'medication_name'),
    firstDose: getField(m, 'firstDose', 'firstDosage'),
    lastDose: getField(m, 'lastDose', 'lastDosage'),
    doseIntervalHours: getField(m, 'doseIntervalHours', 'dose_interval_hours'),
    qty: getField(m, 'qty', 'Qty'),
  });

  const formatDisplayDT = (val) => {
    if (!val) return '—';
    const d = new Date(val);
    if (Number.isNaN(d.getTime())) return '—';
    return `${d.toLocaleDateString()} ${d.toLocaleTimeString([], {
      hour: '2-digit',
      minute: '2-digit',
    })}`;
  };

  const toLocalInput = (val) => {
    if (!val) return '';
    const d = new Date(val);
    if (Number.isNaN(d.getTime())) return '';
    const pad = (n) => String(n).padStart(2, '0');
    return `${d.getFullYear()}-${pad(d.getMonth() + 1)}-${pad(
      d.getDate()
    )}T${pad(d.getHours())}:${pad(d.getMinutes())}`;
  };

  const fromLocalInput = (val) => {
    if (!val) return null;
    const d = new Date(val);
    return Number.isNaN(d.getTime()) ? null : d.toISOString();
  };

  const fetchAll = async () => {
    setLoading(true);
    setErr('');
    try {
      const res = await fetch(`${API_BASE}/api/pharma`, {
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${authToken}`,
        },
      });
      if (!res.ok) throw new Error(`Load failed: ${res.status}`);
      const data = await res.json();
      setRows((Array.isArray(data) ? data : []).map(toCamelMedication));
    } catch (e) {
      setErr(e.message || 'Failed to load medications');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    if (isLoggedIn) fetchAll();
  }, [isLoggedIn, authToken]);

  const nothing = useMemo(
    () => !loading && !err && rows.length === 0,
    [loading, err, rows]
  );

  const openEdit = (row) => {
    setStatus({ saving: false, error: '' });
    setShowForm(true);
    setForm({
      applicationNo: String(row.applicationNo ?? ''),
      medicationName: row.medicationName ?? '',
      firstDose: toLocalInput(row.firstDose),
      lastDose: toLocalInput(row.lastDose),
      doseIntervalHours: row.doseIntervalHours ?? '',
      qty: row.qty ?? '',
    });
  };

  const onChange = (e) => {
    const { name, value } = e.target;
    setForm((f) => ({ ...f, [name]: value }));
  };

  const validate = () => {
    if (!form.medicationName.trim())
      return 'Medication name is required.';
    if (!form.firstDose) return 'First dose date/time is required.';
    if (!form.lastDose) return 'Last dose date/time is required.';
    const fd = new Date(form.firstDose);
    const ld = new Date(form.lastDose);
    if (fd > ld) return 'First dose must be before (or equal to) last dose.';
    if (
      !form.doseIntervalHours ||
      isNaN(Number(form.doseIntervalHours)) ||
      Number(form.doseIntervalHours) <= 0
    ) {
      return 'Dose interval (hours) must be a positive number.';
    }
    if (form.qty === '' || isNaN(Number(form.qty)) || Number(form.qty) < 0) {
      return 'Quantity must be a non-negative number.';
    }
    return '';
  };

  const save = async (e) => {
    e.preventDefault();
    const v = validate();
    if (v) {
      setStatus({ saving: false, error: v });
      return;
    }
    setStatus({ saving: true, error: '' });

    const applicationNoNum = Number(form.applicationNo);
    if (applicationNoNum <= 0) {
      setStatus({
        saving: false,
        error: 'Invalid medication (missing application number).',
      });
      return;
    }

    const payload = {
      applicationNo: applicationNoNum,
      medicationName: form.medicationName.trim(),
      firstDose: fromLocalInput(form.firstDose),
      lastDose: fromLocalInput(form.lastDose),
      doseIntervalHours: Number(form.doseIntervalHours),
      qty: Number(form.qty),
    };

    try {
      const res = await fetch(`${API_BASE}/api/pharma`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${authToken}`,
        },
        body: JSON.stringify(payload),
      });

      if (!res.ok) {
        const text = await res.text().catch(() => '');
        throw new Error(text || `Update failed (${res.status})`);
      }

      await fetchAll();
      setShowForm(false);
    } catch (e2) {
      setStatus({ saving: false, error: e2.message || 'Save failed' });
      return;
    } finally {
      setStatus((s) => ({ ...s, saving: false }));
    }
  };

  // ---------- Remove ----------
  const remove = async (applicationNo) => {
    const id = Number(applicationNo);
    setBusyId(id);
    try {
      const res = await fetch(`${API_BASE}/api/pharma/${id}`, {
        method: 'DELETE',
        headers: {
          Authorization: `Bearer ${authToken}`,
        },
      });

      if (res.ok || res.status === 404) {
        // update UI
        setRows((list) =>
          list.filter(
            (m) =>
              Number(m.applicationNo ?? m.ApplicationNo) !== id
          )
        );
      } else {
        const text = await res.text().catch(() => '');
        throw new Error(text || `Delete failed (${res.status})`);
      }
    } catch (e) {
      setErr(e.message || 'Delete failed');
    } finally {
      setBusyId(null);
    }
  };

  // ---------- Render ----------
  return (
    <div className="container py-4">
      {/* Header */}
      <div className="d-flex justify-content-between align-items-center mb-3">
        <h2 className="m-0">Your Medications</h2>
        <button
          className="btn btn-primary"
          type="button"
          disabled
          title="Add medication coming soon"
        >
          Add Medication
        </button>
      </div>

      {/* Status */}
      {err && <div className="alert alert-danger">Error: {err}</div>}
      {loading && (
        <div className="alert alert-info">Loading medications…</div>
      )}
      {nothing && (
        <div className="alert alert-secondary">
          No medications found.
        </div>
      )}

      {!loading && !err && rows.length > 0 && (
        <div className="table-responsive">
          <table className="table table-hover align-middle">
            <thead className="table-light">
              <tr>
                <th>Medication</th>
                <th className="text-nowrap">First Dose</th>
                <th className="text-nowrap">Last Dose</th>
                <th className="text-nowrap">Interval (hrs)</th>
                <th>Qty</th>
                <th className="text-end">Actions</th>
              </tr>
            </thead>
            <tbody>
              {rows.map((r) => (
                <tr key={r.applicationNo}>
                  <td className="fw-medium">{r.medicationName}</td>
                  <td className="text-nowrap">
                    {formatDisplayDT(r.firstDose)}
                  </td>
                  <td className="text-nowrap">
                    {formatDisplayDT(r.lastDose)}
                  </td>
                  <td>{r.doseIntervalHours}</td>
                  <td>{r.qty}</td>
                  <td className="text-end">
                    <div className="btn-group">
                      <button
                        className="btn btn-sm btn-outline-secondary"
                        type="button"
                        onClick={() => openEdit(r)}
                      >
                        Edit
                      </button>
                      <button
                        className="btn btn-sm btn-outline-danger"
                        type="button"
                        onClick={() => remove(r.applicationNo)}
                        disabled={busyId === r.applicationNo}
                      >
                        {busyId === r.applicationNo ? (
                          <>
                            <span className="spinner-border spinner-border-sm me-2" />
                            Deleting…
                          </>
                        ) : (
                          'Remove'
                        )}
                      </button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}

      {/* Edit */}
      {showForm && (
        <div
          className="position-fixed top-0 start-0 w-100 h-100 bg-dark bg-opacity-50 d-flex align-items-center justify-content-center"
          style={{ zIndex: 1050 }}
        >
          <div
            className="card shadow"
            style={{ width: '820px', maxWidth: '95vw' }}
          >
            <div className="card-header d-flex justify-content-between align-items-center">
              <h5 className="m-0">Edit Medication</h5>
              <button
                type="button"
                className="btn-close"
                aria-label="Close"
                onClick={() => setShowForm(false)}
                disabled={status.saving}
              />
            </div>

            <form onSubmit={save}>
              <div className="card-body">
                {status.error && (
                  <div className="alert alert-danger">
                    {status.error}
                  </div>
                )}

                <input
                  type="hidden"
                  name="applicationNo"
                  value={form.applicationNo}
                  readOnly
                />

                <div className="row g-3">
                  <div className="col-12">
                    <label className="form-label">
                      Medication Name
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      name="medicationName"
                      value={form.medicationName}
                      onChange={onChange}
                      disabled={status.saving}
                      required
                    />
                  </div>

                  <div className="col-12 col-md-6">
                    <label className="form-label">First Dose</label>
                    <input
                      type="datetime-local"
                      className="form-control"
                      name="firstDose"
                      value={form.firstDose}
                      onChange={onChange}
                      disabled={status.saving}
                      required
                    />
                  </div>

                  <div className="col-12 col-md-6">
                    <label className="form-label">Last Dose</label>
                    <input
                      type="datetime-local"
                      className="form-control"
                      name="lastDose"
                      value={form.lastDose}
                      onChange={onChange}
                      disabled={status.saving}
                      required
                    />
                  </div>

                  <div className="col-12 col-md-6">
                    <label className="form-label">
                      Dose Interval (hours)
                    </label>
                    <input
                      type="number"
                      className="form-control"
                      name="doseIntervalHours"
                      value={form.doseIntervalHours}
                      onChange={onChange}
                      min="1"
                      step="1"
                      disabled={status.saving}
                      required
                    />
                  </div>

                  <div className="col-12 col-md-6">
                    <label className="form-label">
                      Quantity (remaining)
                    </label>
                    <input
                      type="number"
                      className="form-control"
                      name="qty"
                      value={form.qty}
                      onChange={onChange}
                      min="0"
                      step="1"
                      disabled={status.saving}
                      required
                    />
                  </div>
                </div>
              </div>

              <div className="card-footer d-flex justify-content-end gap-2">
                <button
                  type="button"
                  className="btn btn-outline-secondary"
                  onClick={() => setShowForm(false)}
                  disabled={status.saving}
                >
                  Cancel
                </button>
                <button
                  type="submit"
                  className="btn btn-primary"
                  disabled={status.saving}
                >
                  {status.saving ? (
                    <>
                      <span className="spinner-border spinner-border-sm me-2" />
                      Saving…
                    </>
                  ) : (
                    'Save Changes'
                  )}
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}
