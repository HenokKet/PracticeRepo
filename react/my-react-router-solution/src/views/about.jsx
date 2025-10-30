import React from 'react';
import { useNavigate } from 'react-router-dom';

const About = () => {
  const navigate = useNavigate();
  return (
    <div className="min-h-screen bg-green-50">
      {/* Header Section */}
      <div className="bg-green-800 py-16">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <h1 className="text-4xl font-bold text-white text-center">Our Story</h1>
        </div>
      </div>

      {/* Story Section */}
      <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 py-16">
        <div className="bg-white rounded-lg shadow-lg p-8 mb-12">
          <h2 className="text-3xl font-semibold text-gray-900 mb-6">Growing Together Since 1995</h2>
          <p className="text-gray-600 mb-6 leading-relaxed">
            Green Horizons Landscaping began as a family dream in the summer of 1995. Founded by Sarah and James Chen, 
            our journey started with a single lawn mower, an old pickup truck, and an unwavering passion for transforming 
            outdoor spaces into natural sanctuaries.
          </p>
          <p className="text-gray-600 mb-6 leading-relaxed">
            What began as a small, local operation has blossomed into one of the region's most trusted landscaping companies. 
            Our growth wasn't just about expanding our business—it was about growing deep roots in our community and fostering 
            relationships that have lasted generations.
          </p>
          <p className="text-gray-600 mb-6 leading-relaxed">
            Today, with over 25 years of experience and a team of 50 dedicated professionals, we continue to bring creative 
            vision and sustainable practices to every project we undertake.
          </p>
        </div>

        {/* Values Section */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-8 mb-12">
          <div className="bg-white p-6 rounded-lg shadow-lg">
            <h3 className="text-xl font-semibold text-green-800 mb-4">Sustainability</h3>
            <p className="text-gray-600">
              We're committed to eco-friendly practices and sustainable landscaping solutions that benefit both our clients 
              and the environment.
            </p>
          </div>
          <div className="bg-white p-6 rounded-lg shadow-lg">
            <h3 className="text-xl font-semibold text-green-800 mb-4">Excellence</h3>
            <p className="text-gray-600">
              Every project receives our complete dedication to quality, from the smallest garden to the largest commercial landscape.
            </p>
          </div>
          <div className="bg-white p-6 rounded-lg shadow-lg">
            <h3 className="text-xl font-semibold text-green-800 mb-4">Community</h3>
            <p className="text-gray-600">
              We take pride in beautifying our community and contributing to local environmental initiatives.
            </p>
          </div>
        </div>

        {/* Team Section */}
        <div className="bg-white rounded-lg shadow-lg p-8">
          <h2 className="text-3xl font-semibold text-gray-900 mb-6">Our Team</h2>
          <p className="text-gray-600 mb-6 leading-relaxed">
            Our diverse team includes certified landscape architects, experienced horticulturists, and skilled craftsmen who 
            share our passion for creating beautiful, sustainable outdoor spaces. Many of our team members have been with us 
            for over a decade, bringing unmatched expertise and dedication to every project.
          </p>
          <div className="text-green-700 font-semibold text-center mt-8">
            "Creating beauty in harmony with nature"
            <div className="text-gray-600 font-normal mt-2">- Sarah Chen, Founder</div>
          </div>
        </div>
      </div>

      {/* Call to Action */}
      <div className="bg-green-700 py-12">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
          <h3 className="text-2xl font-bold text-white mb-4">
            Ready to Start Your Landscape Journey?
          </h3>
          <p className="text-xl text-green-100 mb-8">
            Let's create something beautiful together
          </p>
          <button 
            onClick={() => navigate('/contact')}
            className="bg-white text-green-700 hover:bg-green-50 font-bold py-3 px-8 rounded-lg transition duration-300">
            Schedule a Consultation
          </button>
        </div>
      </div>
    </div>
  );
};

export default About;