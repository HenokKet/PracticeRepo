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
        <div className="d-flex flex-column align-items-stretch mb-3">
            <h2 className="mb-2 text-center w-100">
                Welcome, {user.firstName}!
            </h2>
            <h4 className="mb-2 text-start">Dashboard</h4>
        </div>

      {/* Info banner */}
      <div className="alert alert-info">
        You’re signed in. Scroll down to jump into common tasks.
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
              <button className="btn btn-primary" onClick={() => navigate('/schedule')}>
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
              <button className="btn btn-outline-primary" onClick={() => navigate('/medications')}>
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
                Review your recent doses.
              </p>
              <button className="btn btn-outline-secondary" onClick={() => navigate('/activity')}>
                View Activity
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
