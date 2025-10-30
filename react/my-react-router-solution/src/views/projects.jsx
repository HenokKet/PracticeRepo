import React from 'react';
import { useNavigate } from 'react-router-dom';

const Projects = () => {
  const navigate = useNavigate();

  const projects = [
    {
      id: 1,
      title: "Modern Zen Garden Transformation",
      location: "Bellevue, WA",
      duration: "6 weeks",
      description: "A complete backyard renovation featuring a modern zen garden design with a water feature, meditation area, and low-maintenance plantings.",
      services: ["Landscape Design", "Hardscaping", "Water Features", "Native Plants"],
      results: "Created a peaceful retreat that requires minimal maintenance while providing year-round beauty."
    },
    {
      id: 2,
      title: "Sustainable Family Garden",
      location: "Kirkland, WA",
      duration: "8 weeks",
      description: "Integration of edible landscaping with traditional garden design, including raised vegetable beds, fruit trees, and a natural play area for children.",
      services: ["Garden Installation", "Irrigation Systems", "Organic Soil Management", "Kid-Friendly Design"],
      results: "A beautiful and functional space that provides fresh produce and outdoor entertainment for the whole family."
    },
    {
      id: 3,
      title: "Hillside Haven",
      location: "Mercer Island, WA",
      duration: "12 weeks",
      description: "Complex terrain transformation with terraced gardens, stone retaining walls, and strategic drainage solutions.",
      services: ["Terrain Management", "Stone Work", "Native Plant Installation", "Drainage Solutions"],
      results: "Converted a challenging slope into a stunning multi-level garden with easy maintenance access."
    }
  ];

  return (
    <div className="min-h-screen bg-green-50">
      {/* Header Section */}
      <div className="bg-green-800 py-16">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
          <h1 className="text-4xl font-bold text-white mb-4">Our Projects</h1>
          <p className="text-xl text-green-100">
            Transforming outdoor spaces into beautiful, functional environments
          </p>
        </div>
      </div>

      {/* Projects Grid */}
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-16">
        <div className="grid grid-cols-1 gap-12">
          {projects.map((project) => (
            <div key={project.id} className="bg-white rounded-lg shadow-lg overflow-hidden">
              <div className="p-8">
                <div className="flex justify-between items-start">
                  <div>
                    <h2 className="text-2xl font-bold text-gray-900 mb-2">{project.title}</h2>
                    <p className="text-green-600 mb-4">{project.location} • {project.duration}</p>
                  </div>
                </div>

                <p className="text-gray-600 mb-6 leading-relaxed">
                  {project.description}
                </p>

                <div className="mb-6">
                  <h3 className="text-lg font-semibold text-gray-900 mb-3">Services Provided:</h3>
                  <div className="flex flex-wrap gap-2">
                    {project.services.map((service, index) => (
                      <span
                        key={index}
                        className="bg-green-100 text-green-800 px-3 py-1 rounded-full text-sm"
                      >
                        {service}
                      </span>
                    ))}
                  </div>
                </div>

                <div className="border-t border-gray-200 pt-6">
                  <h3 className="text-lg font-semibold text-gray-900 mb-3">Results:</h3>
                  <p className="text-gray-600 italic">"{project.results}"</p>
                </div>
              </div>
            </div>
          ))}
        </div>

        {/* Call to Action */}
        <div className="text-center mt-16">
          <h2 className="text-3xl font-bold text-gray-900 mb-6">
            Ready to Start Your Own Project?
          </h2>
          <p className="text-xl text-gray-600 mb-8">
            Let us help you create your perfect outdoor space
          </p>
          <button
            onClick={() => navigate('/contact')}
            className="bg-green-600 text-white px-8 py-3 rounded-lg hover:bg-green-700 transition duration-300"
          >
            Request a Consultation
          </button>
        </div>
      </div>

      {/* Additional Info Section */}
      <div className="bg-green-700 py-12">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="grid grid-cols-1 md:grid-cols-3 gap-8 text-white">
            <div className="text-center">
              <div className="font-bold text-3xl mb-2">50+</div>
              <div className="text-green-100">Projects Completed</div>
            </div>
            <div className="text-center">
              <div className="font-bold text-3xl mb-2">98%</div>
              <div className="text-green-100">Client Satisfaction</div>
            </div>
            <div className="text-center">
              <div className="font-bold text-3xl mb-2">15+</div>
              <div className="text-green-100">Design Awards</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Projects;