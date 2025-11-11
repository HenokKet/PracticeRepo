import React from 'react';
import './App.css'; // imports Bootstrap via @import
import { NavLink, Routes, Route } from 'react-router-dom';
import { useAuth } from './context/AuthContext.jsx';
import Home from './components/Home.jsx';
import Login from './components/Login.jsx';
import Dashboard from './components/Dashboard.jsx';

export default function App() {
  const { isLoggedIn, user, logout } = useAuth();

  return (
    <>
      <nav className="navbar navbar-dark bg-primary">
        <div className="container">
          {/* Brand */}
          <NavLink to="/" className="navbar-brand fw-semibold mb-0">
            Medic Alert Tracker
          </NavLink>

          {/* Left-side nav */}
          <ul className="navbar-nav flex-row gap-2">
            <li className="nav-item">
              <NavLink
                to="/"
                end
                className={({ isActive }) => `nav-link px-2${isActive ? ' active' : ''}`}
              >
                Home
              </NavLink>
            </li>

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

          {/* Right side: auth status/actions */}
          <div className="ms-auto d-flex align-items-center gap-2">
            {isLoggedIn && (
              <>
                <span className="text-white-50 small">
                  Logged in as: <strong>{user?.username ?? 'User'}</strong>
                  {user?.role ? ` (${user.role})` : ''}
                </span>
                <button className="btn btn-sm btn-outline-light" onClick={logout}>
                  Logout
                </button>
              </>
            )}
          </div>
        </div>
      </nav>

      {/* Routed content */}
      <div className="container py-4">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/dashboard" element={<Dashboard />} />
        </Routes>
      </div>
    </>
  );
}

