# Pets UI — Quick Demo README

This small React + Vite demo app shows a simple CRUD UI for pets using Bootstrap styling.

Purpose
- Provide a compact app you can use in a classroom demo showing parent/child state, controlled forms, async data loading, and graceful degradation.

Run the app
1. Ensure the backend API is running (default expects a server at http://localhost:8080).
2. Install and run the Vite dev server:

```powershell
npm install
npm run dev
```

3. Open the Vite URL (usually http://localhost:5173) in your browser.

Configuring the API base
- The app reads the API base from the Vite environment variable `VITE_API_BASE`.
- If not set, it defaults to `http://localhost:8080`.
- To override for a demo, create a `.env` file at the repo root with:

```text
VITE_API_BASE=http://localhost:8080
```

or run the dev server with an env variable set in your shell.

What changed in this commit
1. Centralized API base
   - `src/lib/api.js` exports `API_BASE` which components import.
   - Allows switching backend targets without changing multiple files.

2. Delete confirmation modal
   - `src/components/PetTable.jsx` uses a React-controlled Bootstrap-styled modal to confirm deletes instead of `window.confirm`.
   - The modal is accessible and styled with Bootstrap CSS (no Bootstrap JS dependency required).

3. README added
   - Quick start, environment configuration, and demo talking points.

API endpoints used by the UI
- GET `${API_BASE}/api/pet` — list pets
- POST `${API_BASE}/api/pet` — create pet (body: { name, type })
- PUT `${API_BASE}/api/pet` — update pet (body: { petId, name, type })
- DELETE `${API_BASE}/api/pet/{id}` — delete by id
- GET `${API_BASE}/api/pet/types` — list of pet types (array of strings)

Demo talking points and verification
- Show how `import { API_BASE } from './lib/api'` centralizes the API target; change `.env` to point the UI to another backend.
- Point out the graceful fallback in forms: when `/api/pet/types` fails, the form falls back to a text input so users can continue working.
- Show the delete modal as a teachable moment for controlled UI vs native dialog.

Troubleshooting
- If the list is empty or an error appears, open the browser devtools network tab and verify the requests hit the expected `VITE_API_BASE` + `/api/pet` endpoints.
- If you change `VITE_API_BASE`, restart the dev server.

Notes
- Bootstrap CSS is imported from `src/App.css` as the single global source for styling.
- The modal uses Bootstrap CSS classes but is controlled by React; no Bootstrap JS is required.

## For Instructors

This app is designed to demonstrate several key React patterns in a compact, real-world-like setting:

1. Component Patterns
   - Parent/child state management (`App.jsx` controls visibility and refresh triggers).
   - Controlled forms with validation (`Add.jsx`, `Edit.jsx`).
   - Loading/error/success states in components.
   - Graceful degradation (types dropdown → text input fallback).

2. Data Flow Concepts
   - Async data fetching with useEffect.
   - API integration with fetch.
   - Error handling and validation.
   - Optimistic UI updates (success messages before re-fetch).

3. UI/UX Teaching Points
   - Bootstrap styling without Bootstrap JS.
   - Accessible modals and loading states.
   - Form validation and feedback.
   - Visual consistency across components.

Suggested 45-minute lesson plan:

1. Overview (5 min)
   - Show the running app
   - Point out the main components
   - Explain the API contract

2. Deep Dive (25 min)
   - Walk through `PetTable.jsx` list/refresh flow
   - Show controlled form pattern in `Add.jsx`
   - Demonstrate the types dropdown and fallback
   - Explore the delete confirmation modal

3. Exercise Ideas (15 min)
   - Add field validation
   - Implement optimistic updates
   - Add a search/filter feature
   - Style the "loading" state

See `.env.example` for configuration options. Consider running a local API mock during class to avoid connectivity issues.