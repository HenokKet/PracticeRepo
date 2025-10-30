import React from "react";
import { NavLink, Routes, Route } from 'react-router-dom';
import { About, Contact, Landing, Login, Projects } from './views';
import "./App.css";

export default function App() {
  return (
    <div className="min-h-screen bg-green-50">
        <nav className="bg-green-800 shadow-lg">
          <div className="max-w-7x1 mx-auto px-4 sm:px-6 lg:px-8">
              <div className="flex justify-between h-16 items-center">
                  <div className="flex-shrink-0">
                      <span className="text-white font-bold text-xl">
                          Green Horizons
                      </span>
                  </div>
                  <div className="flex space-x-8">
                    <NavLink 
                          to="/" 
                          className={({ isActive }) => 
                            `px-3 py-2 rounded-md text-sm font-medium transition-colors duration-200 ${
                              isActive 
                              ? 'text-white bg-green-900' 
                              : 'text-green-100 hover:text-white hover:bg-green-700'
                            }`
                          }
                        >
                      Home
                      </NavLink>
                      <NavLink 
                          to="projects" 
                          className={({ isActive }) => 
                            `px-3 py-2 rounded-md text-sm font-medium transition-colors duration-200 ${
                              isActive 
                              ? 'text-white bg-green-900' 
                              : 'text-green-100 hover:text-white hover:bg-green-700'
                            }`
                          }
                        >
                      Projects
                      </NavLink>
                      <NavLink 
                          to="about" 
                          className={({ isActive }) => 
                            `px-3 py-2 rounded-md text-sm font-medium transition-colors duration-200 ${
                              isActive 
                              ? 'text-white bg-green-900' 
                              : 'text-green-100 hover:text-white hover:bg-green-700'
                            }`
                          }
                        >
                      About
                      </NavLink>
                      <NavLink 
                          to="contact" 
                          className={({ isActive }) => 
                            `px-3 py-2 rounded-md text-sm font-medium transition-colors duration-200 ${
                              isActive 
                              ? 'text-white bg-green-900' 
                              : 'text-green-100 hover:text-white hover:bg-green-700'
                            }`
                          }
                        >
                      Contact
                      </NavLink>
                      <NavLink 
                          to="login" 
                          className={({ isActive }) => 
                            `px-3 py-2 rounded-md text-sm font-medium transition-colors duration-200 ${
                              isActive 
                              ? 'text-white bg-green-900' 
                              : 'text-green-100 hover:text-white hover:bg-green-700'
                            }`
                          }
                        >
                      Log In
                      </NavLink>
                  </div>
              </div>
          </div>
        </nav>
        <Routes>
            <Route path="/" element={<Landing />} />
            <Route path="/about" element={<About />} />
            <Route path="/contact" element={<Contact />} />
            <Route path="/login" element={<Login />} />
            <Route path="/projects" element={<Projects />} />
        </Routes> 
    </div>
  );
}
