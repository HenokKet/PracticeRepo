// Application entry point
// This file wires React into the DOM. In a demo you can point out that
// createRoot attaches the React tree to the <div id="root"> in index.html.
import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
  </StrictMode>,
)
