import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Contact = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: '',
    phone: '',
    address: '',
    projectType: 'lawn-maintenance',
    message: '',
    preferredContact: 'email',
    budget: 'under-5000'
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('Consultation request submitted:', formData);
    // In a real application, you would send this data to your backend
    // For now, we'll simulate success and redirect to home
    alert('Thank you for your interest! We will contact you soon.');
    navigate('/');
  };

  return (
    <div className="min-h-screen bg-green-50">
      {/* Header Section */}
      <div className="bg-green-800 py-16">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
          <h1 className="text-4xl font-bold text-white">Request a Consultation</h1>
          <p className="mt-4 text-xl text-green-100">
            Transform your outdoor space with Green Horizons
          </p>
        </div>
      </div>

      {/* Form Section */}
      <div className="max-w-3xl mx-auto px-4 sm:px-6 lg:px-8 py-16">
        <div className="bg-white rounded-lg shadow-lg p-8">
          <form onSubmit={handleSubmit} className="space-y-6">
            {/* Name Fields */}
            <div className="grid grid-cols-1 gap-6 sm:grid-cols-2">
              <div>
                <label htmlFor="firstName" className="block text-sm font-medium text-gray-700">
                  First Name
                </label>
                <input
                  type="text"
                  name="firstName"
                  id="firstName"
                  required
                  value={formData.firstName}
                  onChange={handleChange}
                  className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-green-500 focus:ring-green-500"
                />
              </div>
              <div>
                <label htmlFor="lastName" className="block text-sm font-medium text-gray-700">
                  Last Name
                </label>
                <input
                  type="text"
                  name="lastName"
                  id="lastName"
                  required
                  value={formData.lastName}
                  onChange={handleChange}
                  className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-green-500 focus:ring-green-500"
                />
              </div>
            </div>

            {/* Contact Information */}
            <div className="grid grid-cols-1 gap-6 sm:grid-cols-2">
              <div>
                <label htmlFor="email" className="block text-sm font-medium text-gray-700">
                  Email
                </label>
                <input
                  type="email"
                  name="email"
                  id="email"
                  required
                  value={formData.email}
                  onChange={handleChange}
                  className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-green-500 focus:ring-green-500"
                />
              </div>
              <div>
                <label htmlFor="phone" className="block text-sm font-medium text-gray-700">
                  Phone Number
                </label>
                <input
                  type="tel"
                  name="phone"
                  id="phone"
                  required
                  value={formData.phone}
                  onChange={handleChange}
                  className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-green-500 focus:ring-green-500"
                />
              </div>
            </div>

            {/* Address */}
            <div>
              <label htmlFor="address" className="block text-sm font-medium text-gray-700">
                Property Address
              </label>
              <input
                type="text"
                name="address"
                id="address"
                required
                value={formData.address}
                onChange={handleChange}
                className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-green-500 focus:ring-green-500"
              />
            </div>

            {/* Project Type */}
            <div>
              <label htmlFor="projectType" className="block text-sm font-medium text-gray-700">
                Project Type
              </label>
              <select
                name="projectType"
                id="projectType"
                value={formData.projectType}
                onChange={handleChange}
                className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-green-500 focus:ring-green-500"
              >
                <option value="lawn-maintenance">Lawn Maintenance</option>
                <option value="landscape-design">Landscape Design</option>
                <option value="garden-installation">Garden Installation</option>
                <option value="hardscaping">Hardscaping</option>
                <option value="irrigation">Irrigation Systems</option>
                <option value="other">Other</option>
              </select>
            </div>

            {/* Budget Range */}
            <div>
              <label htmlFor="budget" className="block text-sm font-medium text-gray-700">
                Estimated Budget
              </label>
              <select
                name="budget"
                id="budget"
                value={formData.budget}
                onChange={handleChange}
                className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-green-500 focus:ring-green-500"
              >
                <option value="under-5000">Under $5,000</option>
                <option value="5000-10000">$5,000 - $10,000</option>
                <option value="10000-25000">$10,000 - $25,000</option>
                <option value="25000-plus">$25,000+</option>
              </select>
            </div>

            {/* Preferred Contact Method */}
            <div>
              <label className="block text-sm font-medium text-gray-700">
                Preferred Contact Method
              </label>
              <div className="mt-2 space-x-4">
                <label className="inline-flex items-center">
                  <input
                    type="radio"
                    name="preferredContact"
                    value="email"
                    checked={formData.preferredContact === 'email'}
                    onChange={handleChange}
                    className="form-radio text-green-600 focus:ring-green-500"
                  />
                  <span className="ml-2">Email</span>
                </label>
                <label className="inline-flex items-center">
                  <input
                    type="radio"
                    name="preferredContact"
                    value="phone"
                    checked={formData.preferredContact === 'phone'}
                    onChange={handleChange}
                    className="form-radio text-green-600 focus:ring-green-500"
                  />
                  <span className="ml-2">Phone</span>
                </label>
              </div>
            </div>

            {/* Message */}
            <div>
              <label htmlFor="message" className="block text-sm font-medium text-gray-700">
                Project Details
              </label>
              <textarea
                name="message"
                id="message"
                rows={4}
                value={formData.message}
                onChange={handleChange}
                className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-green-500 focus:ring-green-500"
                placeholder="Please describe your project and any specific requirements..."
              />
            </div>

            {/* Submit Button */}
            <div className="text-center">
              <button
                type="submit"
                className="bg-green-600 text-white px-8 py-3 rounded-lg hover:bg-green-700 transition duration-300"
              >
                Submit Consultation Request
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Contact;