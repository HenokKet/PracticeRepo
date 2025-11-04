// src/context/AuthContext.jsx
import React, { createContext, useContext, useState } from 'react';
import { API_BASE } from '../lib/api'; // Assuming you have this base URL defined

// 1. Create the Context object
export const AuthContext = createContext();

// 2. Create the Custom Hook for easy access
export const useAuth = () => {
    return useContext(AuthContext);
};

// 3. Create the Provider component
export const AuthProvider = ({ children }) => {
    // Stores the JWT string returned from the backend
    const [authToken, setAuthToken] = useState(localStorage.getItem('authToken') || null);
    // Stores the user details (username, role)
    const [user, setUser] = useState(JSON.parse(localStorage.getItem('user')) || null);

    // Function to handle the login API call
    const login = async (username, password) => {
        const loginUrl = `${API_BASE}/api/user/login`;
        
        try {
            const response = await fetch(loginUrl, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ username, password }),
            });

            if (!response.ok) {
                // Return a specific error if credentials fail (e.g., 401)
                throw new Error('Login failed. Check username and password.');
            }

            const data = await response.json();
            
            // Assuming the JWT response is { jwt: "...", username: "...", role: "..." }
            setAuthToken(data.jwt);
            setUser({ username: data.username, role: data.role });

            // Store token and user data in local storage for persistence
            localStorage.setItem('authToken', data.jwt);
            localStorage.setItem('user', JSON.stringify({ username: data.username, role: data.role }));

            return true; // Login successful
            
        } catch (error) {
            console.error("Login API Error:", error);
            // Clear any old stored data on failure
            logout(); 
            throw error; // Re-throw for the component to handle the UI error message
        }
    };

    // Function to handle logout
    const logout = () => {
        setAuthToken(null);
        setUser(null);
        localStorage.removeItem('authToken');
        localStorage.removeItem('user');
    };

    // The value provided by the context
    const value = {
        authToken,
        user,
        isLoggedIn: !!authToken, // Boolean flag for quick check
        login,
        logout,
    };

    return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};