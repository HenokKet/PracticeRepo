// src/App.jsx
// ... (existing imports)
import './App.css'

import PetTable from './components/PetTable.jsx'
import Add from './components/Add.jsx'
import Edit from './components/Edit.jsx'
import Login from './components/Login.jsx' // Import the new Login component
import { useAuth } from './context/AuthContext.jsx' // Import the Auth hook

import { useState } from 'react'

function App() {
  const { isLoggedIn, logout, user } = useAuth(); // Get auth state and methods

  // Local UI state (stays the same)
  const [showAddForm, setShowAddForm] = useState(false);
  const [showEditForm, setShowEditForm] = useState(false);
  const [editPet, setEditPet] = useState(null);
  const [refreshTrigger, setRefreshTrigger] = useState(0);

  // ... (handleAddSuccess, handleEditShow, handleEditSuccess functions remain the same)
  const handleAddSuccess = () => {
    setShowAddForm(false);
    setRefreshTrigger(prev => prev + 1);
  };
  const handleEditShow = (pet) => {
    setEditPet(pet);
    setShowEditForm(true);
  };
  const handleEditSuccess = () => {
    setShowEditForm(false);
    setEditPet(null);
    setRefreshTrigger(prev => prev + 1);
  };

  // --- Conditional Rendering ---
  if (!isLoggedIn) {
    // If not logged in, show the login screen only
    return <Login />;
  }

  // If logged in, show the main application content
  return (
    <>
      <div className="d-flex justify-content-between align-items-center p-3 border-bottom">
        <h1 className="app-title m-0">Pets</h1>
        <div className="d-flex align-items-center gap-3">
          <span className="text-secondary">Logged in as: **{user.username}** ({user.role})</span>
          <button className="btn btn-sm btn-outline-danger" onClick={logout}>
            Logout
          </button>
        </div>
      </div>
      
      <div className="container mt-4">
        <PetTable 
          onShowAdd={() => setShowAddForm(true)}
          onShowEdit={handleEditShow}
          refreshTrigger={refreshTrigger}
        />

        {/* Forms rendered conditionally based on local state */}
        {showAddForm && (
          <Add 
            onCancel={() => setShowAddForm(false)}
            onSuccess={handleAddSuccess}
          />
        )}

        {showEditForm && editPet && (
          <Edit
            pet={editPet}
            onCancel={() => { setShowEditForm(false); setEditPet(null); }}
            onSuccess={handleEditSuccess}
          />
        )}
      </div>
    </>
  )
}

export default App