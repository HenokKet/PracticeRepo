import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext.jsx';
import '../App.css';

const Home = () => {
  const navigate = useNavigate();
  const { isLoggedIn } = useAuth();

  const handleGetStarted = () => {
    navigate(isLoggedIn ? '/dashboard' : '/login');
  };

  return (
    <>
      <header className="text-center py-5">
        <h1 className="display-5 fw-semibold mb-3">Welcome to Medic Alert Tracker</h1>
        <p className="lead text-secondary mb-4 px-3">
          Welcome to Medic Alert Tracker, your personal companion for managing daily medications with
          confidence and ease. Our goal is to help you stay consistent, informed, and stress-free when
          it comes to your health routine. Whether you're tracking multiple prescriptions, setting
          reminders for your next dose, or reviewing your medication history, Medic Alert Tracker
          keeps everything organized in one place—simple, secure, and always accessible. Take control
          of your health journey today.
        </p>
        <div className="d-flex justify-content-center gap-2">
          <button className="btn btn-primary" onClick={handleGetStarted}>
            Get Started Today
          </button>
        </div>
      </header>
    </>
  );
};

export default Home;







