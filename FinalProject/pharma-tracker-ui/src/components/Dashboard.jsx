import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext.jsx';
import '../App.css';

export default function Dashboard() {
  const { isLoggedIn, user } = useAuth();
  const navigate = useNavigate();

  // Redirect if not authenticated
  useEffect(() => {
    if (!isLoggedIn) {
      navigate('/login', { replace: true });
    }
  }, [isLoggedIn, navigate]);

  return (
    <div className="container py-4">
      {/* Heading */}
      <div className="d-flex justify-content-between align-items-center mb-3">
        <h2 className="m-0">Dashboard</h2>
        <span className="text-secondary">
          Welcome, <strong>{user?.username ?? 'User'}</strong>
          {user?.role ? ` (${user.role})` : ''}
        </span>
      </div>

      {/* Info banner */}
      <div className="alert alert-info">
        You’re signed in. Use the cards below to jump into common tasks.
      </div>

      {/* Cards */}
      <div className="row g-3">
        <div className="col-12 col-md-4">
          <div className="card h-100 shadow-sm">
            <div className="card-body">
              <h5 className="card-title mb-2">Next Dose</h5>
              <p className="text-secondary mb-3">
                See your upcoming medication and schedule.
              </p>
              <button className="btn btn-primary" disabled>
                View Schedule
              </button>
            </div>
          </div>
        </div>

        <div className="col-12 col-md-4">
          <div className="card h-100 shadow-sm">
            <div className="card-body">
              <h5 className="card-title mb-2">Medication List</h5>
              <p className="text-secondary mb-3">
                Browse, add, or edit your medications.
              </p>
              <button className="btn btn-outline-primary" disabled>
                Open List
              </button>
            </div>
          </div>
        </div>

        <div className="col-12 col-md-4">
          <div className="card h-100 shadow-sm">
            <div className="card-body">
              <h5 className="card-title mb-2">Recent Activity</h5>
              <p className="text-secondary mb-3">
                Review your recent doses and updates.
              </p>
              <button className="btn btn-outline-secondary" disabled>
                View Activity
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
