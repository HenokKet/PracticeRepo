
import React, { createContext, useContext, useState } from 'react';
import { API_BASE } from '../lib/api';

export const AuthContext = createContext();
export const useAuth = () => useContext(AuthContext);

export const AuthProvider = ({ children }) => {
  const [authToken, setAuthToken] = useState(localStorage.getItem('authToken') || null);
  const [user, setUser] = useState(JSON.parse(localStorage.getItem('user')) || null);

  const login = async (username, password) => {
    const loginUrl = `${API_BASE}/api/user/login`;
    try {
      const response = await fetch(loginUrl, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password }),
      });

      if (!response.ok) {
        throw new Error('Login failed. Check username and password.');
      }

      const data = await response.json();

      // 🔹 Ensure these fields match the backend JwtResponse
      const userObj = {
        username: data.userName ?? data.username,
        firstName: data.firstName ?? '',
        lastName: data.lastName ?? '',
      };

      setAuthToken(data.jwt);
      setUser(userObj);

      localStorage.setItem('authToken', data.jwt);
      localStorage.setItem('user', JSON.stringify(userObj));

      return true;
    } catch (error) {
      console.error('Login API Error:', error);
      logout();
      throw error;
    }
  };

  const logout = () => {
    setAuthToken(null);
    setUser(null);
    localStorage.removeItem('authToken');
    localStorage.removeItem('user');
  };

  return (
    <AuthContext.Provider
      value={{
        authToken,
        user,
        isLoggedIn: !!authToken,
        login,
        logout,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};
