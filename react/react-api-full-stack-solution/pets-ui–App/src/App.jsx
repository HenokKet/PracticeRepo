// Global styles for the app (includes Bootstrap via App.css)
import './App.css'

// Import the main UI pieces used by this top-level component
import PetTable from './components/PetTable.jsx'
import Add from './components/Add.jsx'
import Edit from './components/Edit.jsx'

// React hook for local state
import { useState } from 'react'

function App() {
  // Local UI state
  // showAddForm: whether the Add form is visible
  const [showAddForm, setShowAddForm] = useState(false);
  // showEditForm & editPet: control showing the Edit form and which pet to edit
  const [showEditForm, setShowEditForm] = useState(false);
  const [editPet, setEditPet] = useState(null);
  // refreshTrigger: simple counter used to tell PetTable to re-fetch data
  const [refreshTrigger, setRefreshTrigger] = useState(0);

  // Called when Add finishes successfully. We hide the form and bump the
  // refreshTrigger so the PetTable knows to reload its data from the API.
  const handleAddSuccess = () => {
    setShowAddForm(false);
    setRefreshTrigger(prev => prev + 1); // Trigger table refresh
  };

  // Open the Edit form and pass in the pet to edit
  const handleEditShow = (pet) => {
    setEditPet(pet);
    setShowEditForm(true);
  };

  // Called after a successful edit -> hide the edit form and refresh the table
  const handleEditSuccess = () => {
    setShowEditForm(false);
    setEditPet(null);
    setRefreshTrigger(prev => prev + 1);
  };

  return (
    <>
      {/* App title / logo - this H1 is styled in App.css */}
      <div>
        <h1 className="app-title">
          Pets
        </h1>
      </div>

      {/* Main container: holds the table and any visible forms. */}
      <div className="container">
        {/* PetTable displays the list and provides callbacks to show forms */}
        <PetTable 
          onShowAdd={() => setShowAddForm(true)}
          onShowEdit={handleEditShow}
          refreshTrigger={refreshTrigger}
        />

        {/* The Add form is rendered conditionally. Parent handles hiding and refreshing. */}
        {showAddForm && (
          <Add 
            onCancel={() => setShowAddForm(false)}
            onSuccess={handleAddSuccess}
          />
        )}

        {/* The Edit form is rendered when a pet has been selected for editing. */}
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
