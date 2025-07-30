import React, { useContext } from 'react';
import { NavLink, useNavigate } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext.jsx';
import { UserContext } from '../context/UserContext.jsx';

const Navbar = () => {
  const { isLoggined, setIsLoggined } = useContext(AuthContext);
  const { user, setUser } = useContext(UserContext);
  const navigate = useNavigate();

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

  if (!isLoggined || !user) {
    return null; 
  }
  const isAdmin = user?.is_admin;
  return (
    <div className="bg-cyan-200 shadow-md px-6 py-3 flex items-center justify-between">
      <div className="text-xl font-bold text-blue-700">
        {user ? (isAdmin ? 'Admin' : 'User') : 'Library Management'}
      </div>
      <div className="space-x-4">
        <NavLink to="/" className={navClass}>
          Profile
        </NavLink>  
        {isAdmin ? (
          <>
            <NavLink to="/books" className={navClass}>
              Books
            </NavLink>
            <NavLink to="/members" className={navClass}>
              Members
            </NavLink>
            <NavLink to="/issues" className={navClass}>
              Issues
            </NavLink>
          </>
        ):(
           <>
           <NavLink to="/viewbooks" className={navClass}>
              View Books
            </NavLink>
            <NavLink to='/borrowedbooks' className={navClass}>
              Borrowed Books
            </NavLink>
           </>
        )}
        <button className="hover:text-blue-600" onClick={HandleLogout}>
          Logout
        </button>
      </div>
    </div>
  );
};

export default Navbar;
