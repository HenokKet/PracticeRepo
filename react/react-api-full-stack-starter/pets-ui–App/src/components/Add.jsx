import React, { useState, useEffect } from 'react';
import { API_BASE } from '../lib/api';

/*
    Add component (demo-friendly comments)

    Responsibilities & behavior:
    - Renders a controlled form to create a new pet and POSTs it to /api/pet.
    - Fetches available pet types from GET /api/pet/types and uses them to populate
        a <select> control for the "Type" field. This demonstrates async
        data-loading in a form and graceful degradation when the types endpoint
        is unavailable.
    - If the types request fails or returns unexpected data, the component
        falls back to a free-text input so users can still add pets.
    - onSuccess() is called after a successful POST so the parent can hide
        the form and refresh the list (separation of concerns: form reports
        results to the container, it doesn't mutate the table directly).
    - onCancel() allows the parent to hide the form without submitting.

    Demo talking points:
    - Controlled inputs (value + onChange) keep React state as the single
        source of truth for the form.
    - useEffect is used to fetch dropdown options once on mount.
    - The UI shows loading, success and error states to keep the user informed.
*/
function Add({ onCancel, onSuccess }) {
    // Local form state for controlled inputs
    const [formData, setFormData] = useState({
        name: '',
        type: ''
    });
    // Status tracks loading & error/success messages for the UI
    const [status, setStatus] = useState({
        loading: false,
        error: '',
        success: false
    });
    // Types fetched from the API (e.g. ["Dog","Cat","Snake"])
    const [petTypes, setPetTypes] = useState([]);
    const [loadingTypes, setLoadingTypes] = useState(true);
    const [typesError, setTypesError] = useState('');

    const handleSubmit = async (e) => {
        alert("Call the API");
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    // Load pet types once when component mounts
    // Expected shape: an array of strings, e.g. ["Dog","Cat","Snake"].
    // We handle three states: loading, success (populate dropdown), and
    // failure (fall back to a free-text input). This pattern is common when
    // forms depend on auxiliary data from the server.
    useEffect(() => {
        let mounted = true;
        async function loadTypes() {
            setLoadingTypes(true);
            setTypesError('');
            try {
                const res = await fetch(`${API_BASE}/api/pet/types`);
                if (!mounted) return;
                if (!res.ok) {
                    setTypesError(`Could not load types: ${res.status}`);
                    setPetTypes([]);
                    return;
                }
                const data = await res.json();
                // Expecting an array of strings
                if (Array.isArray(data) && data.every(x => typeof x === 'string')) {
                    setPetTypes(data);
                } else {
                    setTypesError('Types response was not an array of strings');
                    setPetTypes([]);
                }
            } catch {
                if (mounted) setTypesError('Network error loading types');
            } finally {
                if (mounted) setLoadingTypes(false);
            }
        }

        loadTypes();
        return () => { mounted = false; };
    }, []);

    return (
        <div className="pet-form-wrapper">
            <h2>Add New Pet</h2>
            <form onSubmit={handleSubmit} className="mb-4">
                <div className="mb-3">
                    <label htmlFor="name" className="form-label">Pet Name</label>
                    <input
                        type="text"
                        className="form-control"
                        id="name"
                        name="name"
                        value={formData.name}
                        onChange={handleChange}
                        required
                        minLength="2"
                        maxLength="50"
                        disabled={status.loading}
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="type" className="form-label">Pet Type</label>
                    {loadingTypes ? (
                        <select className="form-select" id="type" name="type" disabled>
                            <option>Loading types...</option>
                        </select>
                    ) : typesError ? (
                        // If types can't be loaded, fall back to a free text input so the user can still add a pet.
                        // In a demo point out how this keeps the app functional even when auxiliary services fail.
                        <input
                            type="text"
                            className="form-control"
                            id="type"
                            name="type"
                            value={formData.type}
                            onChange={handleChange}
                            required
                            minLength="2"
                            maxLength="50"
                            disabled={status.loading}
                        />
                    ) : (
                        <select
                            className="form-select"
                            id="type"
                            name="type"
                            value={formData.type}
                            onChange={handleChange}
                            required
                            disabled={status.loading}
                        >
                            <option value="" disabled>Choose a type...</option>
                            {petTypes.map(t => (
                                <option key={t} value={t}>{t}</option>
                            ))}
                        </select>
                    )}
                </div>
                <div className="d-flex gap-2">
                    <button
                        type="submit"
                        className="btn btn-primary"
                        disabled={status.loading}
                    >
                        {status.loading ? (
                            <>
                                <span className="spinner-border spinner-border-sm me-2" role="status" aria-hidden="true"></span>
                                Adding Pet...
                            </>
                        ) : 'Add Pet'}
                    </button>
                    <button
                        type="button"
                        className="btn btn-secondary"
                        onClick={onCancel}
                        disabled={status.loading}
                    >
                        Cancel
                    </button>
                </div>
            </form>

            {status.success && (
                <div className="alert alert-success" role="alert">
                    Pet added successfully!
                </div>
            )}

            {status.error && (
                <div className="alert alert-danger" role="alert">
                    {status.error}
                </div>
            )}
        </div>
    );
}

export default Add;
