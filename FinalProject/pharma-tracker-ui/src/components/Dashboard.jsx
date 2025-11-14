import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext.jsx';
import '../App.css';

/**
 * Dashboard Component
 * 
 * Main landing page for authenticated users.
 * Displays a personalized welcome message and provides quick navigation
 * to key features: medication schedule, medication list, and activity history.
 */
export default function Dashboard() {
  const { isLoggedIn, user } = useAuth();
  const navigate = useNavigate();

  /**
   * Redirect unauthenticated users to login page
   * Ensures dashboard is only accessible to logged-in users
   */
  useEffect(() => {
    if (!isLoggedIn) {
      navigate('/login', { replace: true });
    }
  }, [isLoggedIn, navigate]);

  return (
    <div className="container py-4">
      {/* Page Heading with personalized greeting */}
      <div className="d-flex flex-column align-items-stretch mb-3">
        <h2 className="mb-2 text-center w-100">
          {user?.firstName ? `Welcome, ${user.firstName}!` : 'Welcome!'}
        </h2>
        <h4 className="mb-2 text-start">Dashboard</h4>
      </div>

      {/* Info banner confirming authentication status */}
      <div className="alert alert-info">
        You're signed in. Scroll down to jump into common tasks.
      </div>

      {/* Navigation Cards - Quick access to main features */}
      <div className="row g-3">
        {/* Schedule Card - View upcoming medication doses */}
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

        {/* Medications Card - Manage medication list */}
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

        {/* Activity Card - View dose history */}
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