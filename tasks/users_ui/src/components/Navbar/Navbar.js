import React, { useEffect, useRef, useState } from 'react';
import './Navbar.css';
import profileImage from './profile_img.png';

const Navbar = ({ onNavigate, onLogout, onhandlechangepassword, auth }) => {
  const token = auth?.userName || null;
  const role = auth?.role || '';
  const userId = auth?.userCode || 'User';
  const profile = '';

  const [open, setOpen] = useState(false);
  const dropdown = useRef(null);
  const profilelogo = useRef(null);
  
  const rolesAdminManager = ['ROLE_ADMIN','ROLE_MANAGER'];

  useEffect(() => {
    function handleClickOutside(e) {
      if (
        dropdown.current &&
        !dropdown.current.contains(e.target) &&
        profilelogo.current &&
        !profilelogo.current.contains(e.target)
      ) {
        setOpen(false);
      }
    }
    function handleEsc(e) {
      if (e.key === 'Escape') setOpen(false);
    }
    document.addEventListener('mousedown', handleClickOutside);
    document.addEventListener('keydown', handleEsc);
    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
      document.removeEventListener('keydown', handleEsc);
    };
  }, []);

  if (!token) return null;

  const initials = token
    .split(' ')
    .map(s => s[0])
    .join('')
    .toUpperCase()
    .slice(0, 2);

  const handlechangepassword = async(userId) =>{
    onhandlechangepassword(userId);
  }

  return (
    <div className="nav-root">
      <nav className="nav-sidebar">
        <div
          className="nav-brand"
          onClick={() => onNavigate && onNavigate('home')}
          role="button"
          tabIndex={0}
        >
        </div>
		

        <ul className="nav-list">
          <li>
            <button className="nav-btn" onClick={() => onNavigate && onNavigate('home')}>Home</button>
          </li>
          {role.includes('ROLE_ADMIN') && (
            <li>
              <button className="nav-btn" onClick={() => onNavigate && onNavigate('adduser')}>Create User</button>
            </li>
          )}

          {role.some(r => rolesAdminManager.includes(r)) && (
            <li>
              <button className="nav-btn" onClick={() => onNavigate && onNavigate('viewUserRequests')}>
                View User Requests
              </button>
            </li>
          )}

          {role.includes('ROLE_ADMIN') && (
            <li>
              <button className="nav-btn" onClick={() => onNavigate && onNavigate('mainUsers')}>
                View Main Users
              </button>
            </li>
          )}

          {role.includes('ROLE_ADMIN') && (
            <li>
              <button className="nav-btn" onClick={() => onNavigate && onNavigate('viewroles')}>
                ViewRoles
              </button>
            </li>
          )}
        </ul>
      </nav>

      <div className="nav-top-right">
        <button
          ref={profilelogo}
          className="nav-avatar-btn"
          aria-haspopup="true"
          aria-expanded={open}
          onClick={() => setOpen(v => !v)}
          type="button"
        >
          {profile ? (
            <img src={initials} alt="Profile" className="nav-avatar-img" />
          ) : (
            <img src={profileImage} alt="Default Profile" className="nav-avatar-img" />
          )}
        </button>

        <div className="nav-username" title={token}>{token}</div>

        {open && (
          <div className="nav-dropdown" ref={dropdown} role="menu">
            <button
              className="nav-dropdown-item"
              type="button"
              onClick={() => {
                setOpen(false);
                onLogout && onLogout();
              }}
            >
              Logout
            </button>

            <button
              type="button"
              className="nav-dropdown-item"
              onClick={() => {
                handlechangepassword(userId);
              }}
            >
              Change password
            </button>
          </div>
        )}
      </div>
    </div>
  );
};

export default Navbar;
