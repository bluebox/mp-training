import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import { CgProfile } from "react-icons/cg";
import "../styles/Navbar.css";

export default function Navbar() {
  const { user, logout } = useAuth();
  const navigate = useNavigate();
  const [dropdownOpen, setDropdownOpen] = useState(false);

  const handleLogout = () => {
    const ok = window.confirm("Are you sure you want to logout ?");
    if (ok) {
      logout();
      alert("Logged out");
      navigate("/");
    }
  };

  const roles = user?.roles || [];

  return (
    <>
      {user && (
        <div className="topbar">
          <div
            className="user-info"
            onClick={() => setDropdownOpen(!dropdownOpen)}
          >
            <CgProfile size={20} />
            <span>{user.username}</span>
          </div>

          {dropdownOpen && (
            <div className="dropdown-menu">
              <Link
                to="/profile"
                className="dropdown-link"
                onClick={() => setDropdownOpen(false)}
              >
                Profile
              </Link>
              <Link
                to="/change-password"
                className="dropdown-link"
                onClick={() => setDropdownOpen(false)}
              >
                Change Password
              </Link>
              <button
                className="dropdown-link logout"
                onClick={() => {
                  setDropdownOpen(false);
                  handleLogout();
                }}
              >
                Logout
              </button>
            </div>
          )}
        </div>
      )}

      <div className="sidebar">
        <h2 className="sidebar-title">Role-Based Application</h2>

        {!user && (
          <>
            <Link to="/login" className="nav-link">
              Login
            </Link>
          </>
        )}

        {user && (
          <>
            <Link to="/" className="nav-link">Home</Link>

            {roles.includes("ADMIN") && (
              <>
                <Link to="/enroll" className="nav-link">Create User</Link>
                <Link to="/viewallactivemembers" className="nav-link">
                   Active Users
                </Link>
                <Link to="/requests" className="nav-link">User Requests</Link>
              </>
            )}
          </>
        )}
      </div>
    </>
  );
}
