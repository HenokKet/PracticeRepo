import React, { useEffect, useState, useRef } from "react";
import { API_BASE } from '../lib/api';
import { useAuth } from '../context/AuthContext'; // Import useAuth

/*
  PetTable component
  - Responsible for fetching and displaying the pets list from the API
  - Provides Edit and Delete actions per row
  - Accepts callbacks from the parent: onShowAdd (open Add form), onShowEdit (open Edit form)
  - refreshTrigger prop is observed to re-fetch the list when parent requests
*/
function PetTable({ onShowAdd, onShowEdit, refreshTrigger }) {
	// Component state
	const [pets, setPets] = useState([]);
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState('');
	const [deletingId, setDeletingId] = useState(null);
	// delete modal state: holds the pet the user intends to delete
	const [showDeleteModal, setShowDeleteModal] = useState(false);
	const [deleteCandidate, setDeleteCandidate] = useState(null);
	const [successMessage, setSuccessMessage] = useState('');
	const { authToken, logout } = useAuth(); // Get the token and logout function
	// mountedRef prevents state updates after unmount
	const mountedRef = useRef(true);

	// fetchPets: encapsulates the GET /api/pet call and basic validation
	const fetchPets = async () => {
		setLoading(true);
		setError('');
		try{
			const res = await fetch(`${API_BASE}/api/pet`, {
                // Add Authorization Header for GET request
                headers: {
                    'Authorization': `Bearer ${authToken}`
                }
            });

			if(!mountedRef.current) return;
			
			if (res.status === 401) { // Check for Unauthorized
                logout(); // Log out if the token is invalid/expired
                return;
            }

			if(!res.ok) {
				setError(`Could not load Pets: API returned status ${res.status}`);
				setPets([]);
				return;
			}

			const data = await res.json();

			const isValid = Array.isArray(data) && data.every(item => (
				item && Object.prototype.hasOwnProperty.call(item, 'petId') &&
				Object.prototype.hasOwnProperty.call(item, 'name') && 
				Object.prototype.hasOwnProperty.call(item, 'type')
			));

			if(isValid) {
				setPets(data);
			} else {
				setError('API response was not in the expected format');
				setPets([]);
			}
		} catch {
			if (mountedRef.current) {
				setPets([]);
				setError('Could not reach the API. Please Try Again Later');
			}
		}
		if(mountedRef.current) {
			setLoading(false);
		} 
	};

	// Re-fetch when the parent increments refreshTrigger (e.g. after add/edit/delete)
	useEffect(() => {
		mountedRef.current = true;
		fetchPets();
		return () => { mountedRef.current = false; };
	}, [refreshTrigger]); // Reload when refreshTrigger changes

	/*
	  handleDelete: prompt the user, call DELETE /api/pet/{id},
	  then refresh the list and show a short success message.
	*/
	// When user clicks Delete, we open a React-controlled modal instead
	// of using the native window.confirm. This gives a nicer demo UI and
	// is easier to style for students.
	const handleDelete = (pet) => {
		setDeleteCandidate(pet);
		setShowDeleteModal(true);
	};

	// Called when the user confirms deletion in the modal
	const confirmDelete = async () => {
		const pet = deleteCandidate;
		if (!pet) return;
		setShowDeleteModal(false);
		setDeletingId(pet.petId);
		setError('');
		try {
			const res = await fetch(`${API_BASE}/api/pet/${pet.petId}`,
                { 
                    method: 'DELETE',
                    // Add Authorization Header for DELETE request
                    headers: {
                        'Authorization': `Bearer ${authToken}`
                    }
                });
			if (res.status === 401) { logout(); return; }
			
			if(!res.ok) {
				setError(`Delete failed: API returned status ${res.status}`);
			} else {
				setSuccessMessage(`Delete ${pet.name}`);
				await fetchPets();
				setTimeout(() => setSuccessMessage(''), 3000);
			}
		} catch {
			setError('Network Error while deleting pet');
		} finally {
			setDeletingId(null);
			setDeleteCandidate(null);
		}
	};

	const cancelDelete = () => {
		setShowDeleteModal(false);
		setDeleteCandidate(null);
	};

	return (
		<div className="pet-table-wrapper">
			<div className="d-flex justify-content-end mb-3">
				<button
					className="btn btn-primary"
					onClick={onShowAdd}
				>
					Add Pet
				</button>
			</div>

			{successMessage && (
				<div className="alert alert-success text-center" role="alert">{successMessage}</div>
			)}
			{loading ? (
				<div className="d-flex justify-content-center align-items-center mb-2">
					<div className="spinner-border" role="status">
						<span className="visually-hidden">Loading...</span>
					</div>
					<span className="ms-2">Loading pets from API...</span>
				</div>
			) : (
				error && (
					<div className="alert alert-danger text-center" role="alert">
						{error}
					</div>
				)
			)}

			{!loading && !error && (
					<table className="table table-striped">
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Type</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						{pets.map((pet) => (
							<tr key={pet.petId}>
								<td>{pet.petId}</td>
								<td>{pet.name}</td>
								<td>{pet.type}</td>
								<td>
									<div className="d-flex gap-2">
										<button
											className="btn btn-sm btn-outline-secondary"
											onClick={() => onShowEdit && onShowEdit(pet)}
										>
											Edit
										</button>

										<button
											className="btn btn-sm btn-outline-danger"
											onClick={() => handleDelete(pet)}
											disabled={deletingId !== null}
										>
											{deletingId === pet.petId ? (
												<span className="spinner-border spinner-border-sm" aria-hidden="true"></span>
											) : 'Delete'}
										</button>
									</div>
								</td>
							</tr>
						))}
					</tbody>
				</table>
			)}
			
			{showDeleteModal && deleteCandidate && (
				<>
					<div className="modal fade show d-block" tabIndex="-1" role="dialog" aria-modal="true" style={{ backgroundColor: 'rgba(0,0,0,0.5)' }}>
						<div className="modal-dialog modal-dialog-centered" role="document">
							<div className="modal-content">
								<div className="modal-header">
									<h5 className="modal-title">Confirm Delete</h5>
									<button type="button" className="btn-close" aria-label="Close" onClick={cancelDelete}></button>
								</div>
								<div className="modal-body">
									<p>Delete pet "{deleteCandidate.name}" (ID: {deleteCandidate.petId})? This cannot be undone.</p>
									{error && <div className="alert alert-danger">{error}</div>}
								</div>
								<div className="modal-footer">
									<button type="button" className="btn btn-secondary" onClick={cancelDelete} disabled={deletingId !== null}>Cancel</button>
									<button type="button" className="btn btn-danger" onClick={confirmDelete} disabled={deletingId !== null}>
										{deletingId === deleteCandidate.petId ? <span className="spinner-border spinner-border-sm" aria-hidden="true"></span> : 'Delete'}
									</button>
								</div>
							</div>
						</div>
					</div>
					<div className="modal-backdrop fade show"></div>
				</>
			)}
		</div>
	);
}

export default PetTable;
