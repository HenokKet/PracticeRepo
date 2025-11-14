import React from 'react';
import './App.css'; // imports Bootstrap via @import
import { NavLink, Routes, Route } from 'react-router-dom';
import { useAuth } from './context/AuthContext.jsx';
import Home from './components/Home.jsx';
import Login from './components/Login.jsx';
import Dashboard from './components/Dashboard.jsx';
import Schedule from './components/Schedule.jsx';
import Activity from './components/Activity.Jsx';
import UserMedication from './components/UserMedication.jsx';
import AddMedication from './components/Add.jsx';

/**
 * App Component
 * 
 * Root component that provides application-wide navigation and routing.
 * Features:
 * - Responsive Bootstrap navbar with authentication-aware navigation
 * - Dynamic nav links based on login state
 * - User status display and logout functionality
 * - Route definitions for all application pages
 */
export default function App() {
  const { isLoggedIn, user, logout } = useAuth();

  return (
    <>
      {/* Top Navigation Bar */}
      <nav className="navbar navbar-dark bg-primary">
        <div className="container">
          {/* Brand/Logo */}
          <NavLink to="/" className="navbar-brand fw-semibold mb-0">
            Medic Alert Tracker
          </NavLink>

          {/* Left-side Navigation Links */}
          <ul className="navbar-nav flex-row gap-2">
            {/* Home link - always visible */}
            <li className="nav-item">
              <NavLink
                to="/"
                end
                className={({ isActive }) => `nav-link px-2${isActive ? ' active' : ''}`}
              >
                Home
              </NavLink>
            </li>

            {/* Conditional navigation: Dashboard for logged-in users, Login for guests */}
            {isLoggedIn ? (
              <li className="nav-item">
                <NavLink
                  to="/dashboard"
                  className={({ isActive }) => `nav-link px-2${isActive ? ' active' : ''}`}
                >
                  Dashboard
                </NavLink>
              </li>
            ) : (
              <li className="nav-item">
                <NavLink
                  to="/login"
                  className={({ isActive }) => `nav-link px-2${isActive ? ' active' : ''}`}
                >
                  Login
                </NavLink>
              </li>
            )}
          </ul>

          {/* Right Side: User Info and Logout */}
          <div className="ms-auto d-flex align-items-center gap-2">
            {isLoggedIn && (
              <>
                {/* Display current user's username */}
                <span className="text-white-50 small">
                  Logged in as: <strong>{user?.username ?? 'User'}</strong>
                </span>
                {/* Logout button triggers auth context logout */}
                <button className="btn btn-sm btn-outline-light" onClick={logout}>
                  Logout
                </button>
              </>
            )}
          </div>
        </div>
      </nav>

      {/* Main Content Area with Routes */}
      <div className="container py-4">
        <Routes>
          {/* Public Routes */}
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          
          {/* Protected Routes - Components handle auth redirects internally */}
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/schedule" element={<Schedule />} />
          <Route path="/activity" element={<Activity />} />
          <Route path="/medications" element={<UserMedication />} />
          <Route path="/add-medication" element={<AddMedication />} />
        </Routes>
      </div>
    </>
  );
}