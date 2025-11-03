// Centralized API base for the app.
// Use Vite env var VITE_API_BASE to override in development or production builds.
// Example: create a file named `.env` with `VITE_API_BASE=http://localhost:8080`
export const API_BASE = import.meta.env.VITE_API_BASE || 'http://localhost:8080';
