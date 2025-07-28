import React, { useContext, useEffect, useState } from 'react';
import { NavLink, useNavigate } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext.jsx';
import { UserContext } from '../context/UserContext.jsx';
import axios from 'axios';

const Navbar = () => {
  const { isLoggined, setIsLoggined } = useContext(AuthContext);
  const { user, setUser } = useContext(UserContext);
  const navigate = useNavigate();
  console.log(user);
  
  const navClass = ({ isActive }) =>
    isActive
      ? 'text-white bg-blue-600 px-3 py-2 rounded-md font-semibold'
      : 'text-gray-700 hover:text-blue-600 px-3 py-2';

  const HandleLogout = () => {
    if (!window.confirm('Do you want to Logout?')) {
      return;
    }
    localStorage.removeItem('access_token');
    localStorage.removeItem('refresh_token');
    setIsLoggined(false);
    setUser(null);
    navigate('/login');
  };

  return (
    <>
      {isLoggined && user && (
        <nav className="bg-white shadow-md px-6 py-3 flex items-center justify-between">
          <div className="text-xl font-bold text-blue-700">
            {user ? (user.is_admin?'Admin':"User") : 'Library Management'}
          </div>
          <div className="space-x-4">
            <NavLink to="/" className={navClass}>
              Profile
            </NavLink>
            <NavLink to="/books" className={navClass}>
              Books
            </NavLink>
            
              <NavLink to="/members" className={navClass}>
                Members
              </NavLink>
            <NavLink to="/issues" className={navClass}>
              Issues
            </NavLink>
            <button className="hover:text-blue-600" onClick={HandleLogout}>
              Logout
            </button>
          </div>
        </nav>
      )}
    </>
  );
};

export default Navbar;
