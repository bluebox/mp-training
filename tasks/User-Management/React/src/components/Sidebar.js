import React from 'react';
import { useNavigate, useLocation } from 'react-router-dom';

function Sidebar() {
  const navigate = useNavigate();
  const location = useLocation();

  const isActive = (path) => {
    return location.pathname === path;
  };

  return (
    <div className="sidebar">
      <button
        onClick={() => navigate('/home')}
        className={`sidebar-nav-button ${isActive('/home') ? 'active' : ''}`}
        title="Home"
      >
        Home
      </button>

      <button
        onClick={() => navigate('/register')}
        className={`sidebar-nav-button ${isActive('/register') ? 'active' : ''}`}
        title="Create User"
      >
        Create User
      </button>

      <button
        onClick={() => navigate('/requests')}
        className={`sidebar-nav-button ${isActive('/requests') ? 'active' : ''}`}
        title="View User Requests"
      >
        View User Requests
      </button>

      <button
        onClick={() => navigate('/mainUsers')}
        className={`sidebar-nav-button ${isActive('/mainUsers') ? 'active' : ''}`}
        title="View Main Users"
      >
        View Main Users
      </button>
      <button
        onClick={()=>navigate('/viewRoles')}
        className={`sidebar-nav-button ${isActive('/viewRoles') ? 'active' : ''}`}
      >
        View Roles
      </button>
      <button
        onClick={() => navigate('/login')}
        className="sidebar-logout"
        title="Logout"
      >
        Logout
      </button>

    </div>
  );
}

export default Sidebar;
