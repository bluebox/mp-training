import React, { useState } from 'react';
import { Link, NavLink } from 'react-router-dom';

const Header = () => {
  const [isOpen, setIsOpen] = useState(false);

  const toggleNavbar = () => setIsOpen(!isOpen);
  const closeNavbar = () => setIsOpen(false);

  return (
    <nav className="navbar navbar-expand-lg navbar-dark">
      <div className="container-fluid">
        <Link className="navbar-brand text-white" to="/" onClick={closeNavbar}>
          📚 Library Management
        </Link>
        
        <button
          className="navbar-toggler"
          type="button"
          onClick={toggleNavbar}
          aria-controls="navbarNav"
          aria-expanded={isOpen}
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        
        <div className={`collapse navbar-collapse ${isOpen ? 'show' : ''}`} id="navbarNav">
          <ul className="navbar-nav me-auto">
            <li className="nav-item">
              <NavLink 
                className={({ isActive }) => `nav-link text-white ${isActive ? 'active' : ''}`}
                to="/"
                end
                onClick={closeNavbar}
              >
                📊 Dashboard
              </NavLink>
            </li>
            
            <li className="nav-item">
              <NavLink 
                className={({ isActive }) => `nav-link text-white ${isActive ? 'active' : ''}`}
                to="/books"
                onClick={closeNavbar}
              >
                📚 Books
              </NavLink>
            </li>
            
            <li className="nav-item">
              <NavLink 
                className={({ isActive }) => `nav-link text-white ${isActive ? 'active' : ''}`}
                to="/members"
                onClick={closeNavbar}
              >
                👥 Members
              </NavLink>
            </li>
            
            <li className="nav-item">
              <NavLink 
                className={({ isActive }) => `nav-link text-white ${isActive ? 'active' : ''}`}
                to="/transactions"
                onClick={closeNavbar}
              >
                🔄 Issue / Returns
              </NavLink>
            </li>
            
            <li className="nav-item">
              <NavLink 
                className={({ isActive }) => `nav-link text-white ${isActive ? 'active' : ''}`}
                to="/reports"
                onClick={closeNavbar}
              >

                📊 Reports
              </NavLink>
              <div>
                
              </div>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default Header;
