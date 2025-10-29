import React, { useState } from 'react';
import Swal from 'sweetalert2';

const Add = ({employees, setEmployees, setIsAdding }) => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [salary, setSalary] = useState('');
    const [date, setDate] = useState('');

    const handleAdd = e => {
        e.preventDefault();

        if (!firstName || !lastName || !email || !salary || !date){
            return Swal.fire({
                icon: 'error',
                title: 'Error!',
                text: 'All fields are required.',
                showConfirmButton: true,
            });
        }

        const id = employees.length + 1;
        const newEmployee = {
            id,
            firstName,
            lastName,
            email,
            salary,
            date,
        };

        employees.push(newEmployee);
        localStorage.setItem('employees_data', JSON.stringify(employees));
        setEmployees(employees);
        setIsAdding(false);

        Swal.fire({
            icon: 'success',
            title: 'Added!',
            text: `${firstName} ${lastName}'s Data has been added`,
            showConfirmButton: false,
            timer: 1500
        });
    };


    return (
        <div className = "small-container">
            <form onSubmit={handleAdd}>
                <label htmlFor="firstName">First Name</label>
                <input 
                    id="firstName"
                    text="text"
                    name="firstName"
                    value={firstName}
                    onChange={e => setFirstName(e.target.value)}
                />
                <label htmlFor="lastName">Last Name</label>
                <input 
                    id="lastName"
                    text="text"
                    name="lastName"
                    value={lastName}
                    onChange={e => setLastName(e.target.value)}
                />
                <label htmlFor="email">Email</label>
                <input 
                    id="email"
                    text="text"
                    name="email"
                    value={email}
                    onChange={e => setEmail(e.target.value)}
                />
                <label htmlFor="salary">Salary</label>
                <input 
                    id="salary"
                    text="text"
                    name="salary"
                    value={salary}
                    onChange={e => setSalary(e.target.value)}
                />
                 <label htmlFor="date">Date</label>
                <input 
                    id="date"
                    text="text"
                    name="date"
                    value={date}
                    onChange={e => setDate(e.target.value)}
                />
                <div style={{marginTop: '30px'}}>
                    <input type = "submit" value="Add" /> 
                    <input 
                        style={{marginLeft: '12px'}}
                        className="muted-button"
                        type="button"
                        value="Cancel"
                        onClick={() => setIsAdding(false)}
                    />
                </div>
            </form>
        </div>
    );
};

export default Add;