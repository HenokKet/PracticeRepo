import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext.jsx';
import { API_BASE, FDA_BASE } from '../lib/api';
import '../App.css';

/**
 * AddMedication Component
 * 
 * Form for adding a new medication to the user's medication list.
 * Features FDA drug database search to auto-populate medication details
 * and manual entry for custom medications.
 */
export default function AddMedication() {
  const { isLoggedIn, authToken } = useAuth();
  const navigate = useNavigate();

  // Main form state for medication details
  const [form, setForm] = useState({
    medicationName: '',
    ndc: '',              // National Drug Code, hidden field populated from FDA search
    firstDose: '',        // datetime-local input value
    lastDose: '',         // datetime-local input value
    doseIntervalHours: '', // Hours between doses
    qty: '',              // Remaining quantity
  });

  // Form submission status tracking
  const [status, setStatus] = useState({ saving: false, error: '', success: '' });

  // FDA search state
  const [fdaQuery, setFdaQuery] = useState('');       // User's search input
  const [fdaLoading, setFdaLoading] = useState(false); // Loading indicator for FDA API
  const [fdaErr, setFdaErr] = useState('');           // FDA search error message
  const [fdaResults, setFdaResults] = useState([]);   // Array of search results

  /**
   * Redirect unauthenticated users to login page
   */
  useEffect(() => {
    if (!isLoggedIn) navigate('/login', { replace: true });
  }, [isLoggedIn, navigate]);

  // ---- Helper Functions ----

  /**
   * Convert datetime-local input value to ISO string
   * @param {string} val - Value from datetime-local input
   * @returns {string|null} ISO date string or null if invalid
   */
  const fromLocalInput = (val) => {
    if (!val) return null;
    const d = new Date(val);
    return Number.isNaN(d.getTime()) ? null : d.toISOString();
  };

  /**
   * Generate a stable integer application number from NDC
   * Extracts digits from NDC (e.g., "12345-6789-00") and creates a 9-digit number
   * @param {string} ndc - National Drug Code
   * @returns {number|null} 9-digit application number or null if invalid
   */
  const appNoFromNdc = (ndc) => {
    if (!ndc) return null;
    const digits = String(ndc).replace(/\D/g, '');
    if (!digits) return null;
    const trimmed = digits.slice(-9);
    return Number(trimmed) || null;
  };

  // ---- FDA Search Functions ----

  /**
   * Search FDA drug database by brand or generic name
   * Queries the OpenFDA drug API and displays results for user selection
   */
  const searchFda = async (e) => {
    e?.preventDefault?.();
    
    // Validate search query
    if (!fdaQuery.trim()) {
      setFdaResults([]);
      setFdaErr('Enter a drug name to search');
      return;
    }

    setFdaErr('');
    setFdaLoading(true);
    
    try {
      // Search both brand_name and generic_name fields
      const url = `${FDA_BASE}?search=brand_name:${encodeURIComponent(
        fdaQuery
      )}+OR+generic_name:${encodeURIComponent(fdaQuery)}&limit=10`;

      const res = await fetch(url);
      if (!res.ok) throw new Error(`FDA search failed: ${res.status}`);
      const data = await res.json();

      // Transform FDA results into displayable format
      const items = (data?.results ?? []).map((r) => {
        // Extract NDC from various possible locations in response
        const ndc = r?.packaging?.[0]?.package_ndc || r?.product_ndc || r?.productid || '';
        const brand = r?.brand_name || '';
        const generic = r?.generic_name || '';
        
        // Build strength info from active ingredients
        const strength =
          r?.active_ingredients
            ?.map((ai) => `${ai?.name ?? ''} ${ai?.strength ?? ''}`)
            .join(', ') || '';
        
        return {
          ndc,
          label: brand || generic || ndc || 'Unknown',
          subLabel: [generic && generic !== brand ? generic : '', strength]
            .filter(Boolean)
            .join(' • '),
        };
      });

      setFdaResults(items);
    } catch (e2) {
      setFdaErr(e2.message || 'FDA search failed');
      setFdaResults([]);
    } finally {
      setFdaLoading(false);
    }
  };

  /**
   * Handle user selection of an FDA search result
   * Auto-populates form with selected medication name and NDC
   */
  const pickFda = (item) => {
    setForm((f) => ({
      ...f,
      ndc: item.ndc || '',
      medicationName: item.label || item.ndc || '',
    }));
  };

  // ---- Form Handlers ----

  /**
   * Handle form input changes
   */
  const onChange = (e) => {
    const { name, value } = e.target;
    setForm((f) => ({ ...f, [name]: value }));
  };

  /**
   * Validate form inputs before submission
   * @returns {string} Error message if validation fails, empty string if valid
   */
  const validate = () => {
    // Check required fields
    if (!form.medicationName.trim()) return 'Medication name is required.';
    if (!form.firstDose) return 'First dose date/time is required.';
    if (!form.lastDose) return 'Last dose date/time is required.';

    // Validate date logic - first dose must be before or equal to last dose
    const fd = new Date(form.firstDose);
    const ld = new Date(form.lastDose);
    if (fd > ld) return 'First dose must be before (or equal to) last dose.';

    // Validate dose interval is positive number
    if (
      !form.doseIntervalHours ||
      isNaN(Number(form.doseIntervalHours)) ||
      Number(form.doseIntervalHours) <= 0
    ) {
      return 'Dose interval (hours) must be a positive number.';
    }

    // Validate quantity is non-negative
    if (form.qty === '' || isNaN(Number(form.qty)) || Number(form.qty) < 0) {
      return 'Quantity must be a non-negative number.';
    }

    return '';
  };

  /**
   * Handle form submission
   * Validates input, derives application number from NDC, and POSTs to API
   */
  const onSubmit = async (e) => {
    e.preventDefault();
    
    // Run validation
    const v = validate();
    if (v) {
      setStatus({ saving: false, error: v, success: '' });
      return;
    }

    setStatus({ saving: true, error: '', success: '' });

    // Derive ApplicationNo from NDC, fallback to timestamp-based number
    const applicationNo =
      appNoFromNdc(form.ndc) ?? Math.floor(Date.now() % 2_000_000_000);

    // Build payload with converted types
    const payload = {
      applicationNo,
      medicationName: form.medicationName.trim(),
      firstDose: fromLocalInput(form.firstDose),
      lastDose: fromLocalInput(form.lastDose),
      doseIntervalHours: Number(form.doseIntervalHours),
      qty: Number(form.qty),
    };

    try {
      // POST new medication to API
      const res = await fetch(`${API_BASE}/api/pharma`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${authToken}`,
        },
        body: JSON.stringify(payload),
      });

      if (!res.ok) {
        const text = await res.text().catch(() => '');
        throw new Error(text || `Add failed (${res.status})`);
      }

      // Show success message
      setStatus({
        saving: false,
        error: '',
        success: 'Medication added successfully.',
      });

      // Navigate back after brief delay to show success message
      setTimeout(() => {
        navigate(-1); 
      }, 500);
    } catch (e2) {
      setStatus({ saving: false, error: e2.message || 'Save failed', success: '' });
    }
  };

  // ---- Render ----
  return (
    <div className="container py-4">
      {/* Page header with back button */}
      <div className="d-flex justify-content-between align-items-center mb-3">
        <h2 className="m-0">Add Medication</h2>
        <button
          type="button"
          className="btn btn-outline-secondary"
          onClick={() => navigate(-1)}
          disabled={status.saving}
        >
          Back
        </button>
      </div>

      {/* Status messages */}
      {status.error && <div className="alert alert-danger">Error: {status.error}</div>}
      {status.success && <div className="alert alert-success">{status.success}</div>}

      <div className="card shadow-sm">
        <div className="card-body">
          {/* FDA Drug Search Section */}
          <div className="mb-4">
            <label className="form-label">Search FDA (brand or generic)</label>
            <div className="input-group">
              <input
                type="text"
                className="form-control"
                placeholder="e.g., ibuprofen, amoxicillin"
                value={fdaQuery}
                onChange={(e) => setFdaQuery(e.target.value)}
                disabled={status.saving}
              />
              <button
                className="btn btn-outline-primary"
                onClick={searchFda}
                disabled={status.saving || fdaLoading}
              >
                {fdaLoading ? (
                  <>
                    <span className="spinner-border spinner-border-sm me-2" />
                    Searching…
                  </>
                ) : (
                  'Search'
                )}
              </button>
            </div>
            {fdaErr && <div className="text-danger small mt-1">{fdaErr}</div>}

            {/* FDA Search Results List */}
            {fdaResults.length > 0 && (
              <div
                className="list-group mt-2"
                style={{ maxHeight: 220, overflowY: 'auto' }}
              >
                {fdaResults.map((it, idx) => (
                  <button
                    key={`${it.ndc}-${idx}`}
                    type="button"
                    className="list-group-item list-group-item-action"
                    onClick={() => pickFda(it)}
                    disabled={status.saving}
                  >
                    {/* Primary medication info */}
                    <div className="d-flex justify-content-between">
                      <span className="fw-medium">{it.label}</span>
                      <span className="text-muted small">{it.ndc}</span>
                    </div>
                    {/* Secondary info: generic name and strength */}
                    {it.subLabel && (
                      <div className="small text-secondary">{it.subLabel}</div>
                    )}
                  </button>
                ))}
              </div>
            )}
          </div>

          {/* Hidden field to store NDC for ApplicationNo derivation */}
          <input type="hidden" name="ndc" value={form.ndc} readOnly />

          {/* Main Medication Form */}
          <form onSubmit={onSubmit}>
            <div className="row g-3">
              {/* Medication Name Field */}
              <div className="col-12">
                <label className="form-label">Medication Name</label>
                <input
                  type="text"
                  className="form-control"
                  name="medicationName"
                  value={form.medicationName}
                  onChange={onChange}
                  placeholder="Select from FDA search above or type a name"
                  disabled={status.saving}
                  required
                />
              </div>

              {/* First Dose DateTime */}
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

              {/* Last Dose DateTime */}
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

              {/* Dose Interval in Hours */}
              <div className="col-12 col-md-6">
                <label className="form-label">Dose Interval (hours)</label>
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

              {/* Remaining Quantity */}
              <div className="col-12 col-md-6">
                <label className="form-label">Quantity (remaining)</label>
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

            {/* Form Action Buttons */}
            <div className="mt-4 d-flex justify-content-end gap-2">
              <button
                type="button"
                className="btn btn-outline-secondary"
                onClick={() => navigate(-1)}
                disabled={status.saving}
              >
                Cancel
              </button>
              <button type="submit" className="btn btn-primary" disabled={status.saving}>
                {status.saving ? (
                  <>
                    <span className="spinner-border spinner-border-sm me-2" />
                    Saving…
                  </>
                ) : (
                  'Add Medication'
                )}
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}