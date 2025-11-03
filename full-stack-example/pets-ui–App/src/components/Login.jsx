// src/components/Login.jsx
import React, { useState } from 'react';
import { useAuth } from '../context/AuthContext';
import { API_BASE } from '../lib/api'; 

function Login() {
    const { login } = useAuth();
    const [isLoginView, setIsLoginView] = useState(true); // Toggle between Login and Register
    const [formData, setFormData] = useState({ 
        username: '', 
        password: '',
        email: '',
        role: 'user' // Default role for registration
    });
    const [status, setStatus] = useState({ loading: false, error: '', success: '' });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    };

    const handleLoginSubmit = async (e) => {
        e.preventDefault();
        setStatus({ loading: true, error: '', success: '' });
        try {
            await login(formData.username, formData.password);
            // AuthContext handles success state update (setting token/user)
        } catch (err) {
            setStatus({ 
                loading: false, 
                error: err.message || 'Login failed. Please try again.', 
                success: '' 
            });
        } finally {
            setStatus(prev => ({ ...prev, loading: false }));
        }
    };
    
    const handleRegisterSubmit = async (e) => {
        e.preventDefault();
        setStatus({ loading: true, error: '', success: '' });

        try {
            const response = await fetch(`${API_BASE}/api/user/register`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ 
                    userName: formData.username, 
                    password: formData.password,
                    userEmail: formData.email,
                    userRole: formData.role
                }),
            });

            if (!response.ok) {
                throw new Error(`Registration failed with status ${response.status}`);
            }

            // Registration successful, now attempt to log in automatically
            setStatus({ loading: false, error: '', success: 'Registration successful! Logging you in...' });
            await login(formData.username, formData.password);

        } catch (err) {
            setStatus({ 
                loading: false, 
                error: err.message || 'Registration failed. Please try again.', 
                success: '' 
            });
        }
    };

    return (
        <div className="d-flex justify-content-center align-items-center" style={{ minHeight: '100vh', background: '#f8f9fa' }}>
            <div className="card shadow" style={{ width: '400px' }}>
                <div className="card-header bg-primary text-white text-center">
                    <h2>{isLoginView ? 'Login' : 'Register'}</h2>
                </div>
                <div className="card-body">
                    <form onSubmit={isLoginView ? handleLoginSubmit : handleRegisterSubmit}>
                        <div className="mb-3">
                            <label htmlFor="username" className="form-label">Username</label>
                            <input
                                type="text"
                                className="form-control"
                                id="username"
                                name="username"
                                value={formData.username}
                                onChange={handleChange}
                                required
                                disabled={status.loading}
                            />
                        </div>
                        
                        {!isLoginView && (
                             <div className="mb-3">
                                <label htmlFor="email" className="form-label">Email</label>
                                <input
                                    type="email"
                                    className="form-control"
                                    id="email"
                                    name="email"
                                    value={formData.email}
                                    onChange={handleChange}
                                    required
                                    disabled={status.loading}
                                />
                            </div>
                        )}

                        <div className="mb-3">
                            <label htmlFor="password" className="form-label">Password</label>
                            <input
                                type="password"
                                className="form-control"
                                id="password"
                                name="password"
                                value={formData.password}
                                onChange={handleChange}
                                required
                                disabled={status.loading}
                            />
                        </div>

                        {status.error && (
                            <div className="alert alert-danger">{status.error}</div>
                        )}
                        {status.success && (
                            <div className="alert alert-success">{status.success}</div>
                        )}

                        <div className="d-grid gap-2">
                            <button
                                type="submit"
                                className="btn btn-primary"
                                disabled={status.loading || (isLoginView && (!formData.username || !formData.password))}
                            >
                                {status.loading ? (
                                    <>
                                        <span className="spinner-border spinner-border-sm me-2"></span>
                                        {isLoginView ? 'Logging In...' : 'Registering...'}
                                    </>
                                ) : (
                                    isLoginView ? 'Login' : 'Register'
                                )}
                            </button>
                        </div>
                    </form>
                </div>
                <div className="card-footer text-center">
                    <button 
                        className="btn btn-link" 
                        onClick={() => { setIsLoginView(prev => !prev); setStatus({ loading: false, error: '', success: '' }); }}
                        disabled={status.loading}
                    >
                        {isLoginView ? 'Need an account? Register here.' : 'Already have an account? Login here.'}
                    </button>
                </div>
            </div>
        </div>
    );
}

export default Login;