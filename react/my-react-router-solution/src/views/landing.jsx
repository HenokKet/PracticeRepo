import React from 'react';

const Landing = () => {
  return (
    <div className="min-h-screen bg-green-50">
      {/* Hero Section */}
      <div className="relative py-24 px-6 bg-green-800 text-center">
        <div className="relative z-10">
          <h1 className="text-4xl font-bold tracking-tight text-white sm:text-5xl mb-4">
            <a href= "http://google.com" target="_blank">Green Horizons Landscaping</a>
          </h1>
          <p className="text-xl text-green-100 mb-8">
            Transform your outdoor space into a beautiful paradise
          </p>
          <button className="bg-green-500 hover:bg-green-600 text-white font-bold py-3 px-8 rounded-lg transition duration-300">
            Get a Free Quote
          </button>
        </div>
      </div>

      {/* Services Section */}
      <div className="max-w-7xl mx-auto py-16 px-4 sm:px-6 lg:px-8">
        <h2 className="text-3xl font-bold text-gray-900 text-center mb-12">
          Our Services
        </h2>
        <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
          <div className="bg-white p-6 rounded-lg shadow-lg">
            <h3 className="text-xl font-semibold text-gray-900 mb-4">Lawn Maintenance</h3>
            <p className="text-gray-600">Professional mowing, edging, and maintenance services to keep your lawn looking its best.</p>
          </div>
          <div className="bg-white p-6 rounded-lg shadow-lg">
            <h3 className="text-xl font-semibold text-gray-900 mb-4">Landscape Design</h3>
            <p className="text-gray-600">Custom landscape design and installation to create your dream outdoor space.</p>
          </div>
          <div className="bg-white p-6 rounded-lg shadow-lg">
            <h3 className="text-xl font-semibold text-gray-900 mb-4">Garden Care</h3>
            <p className="text-gray-600">Expert garden maintenance, planting, and seasonal clean-up services.</p>
          </div>
        </div>
      </div>

      {/* Contact Strip */}
      <div className="bg-green-700 py-8">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
          <p className="text-xl text-white mb-4">Ready to transform your outdoor space?</p>
          <p className="text-2xl font-bold text-white">Call us today at (555) 123-4567</p>
        </div>
      </div>
    </div>
  );
};

export default Landing;