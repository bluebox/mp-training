import React from "react";
import { NavLink } from "react-router-dom";

const Header = () => {
  const linkStyle = {
    padding: "12px 20px",
    fontSize: "16px",
    fontWeight: "bold",
    borderRadius: "8px",
    border: "none",
    textDecoration: "none",
    color: "white",
    backgroundColor: "#3498db",
    marginRight: "10px",
    transition: "0.3s",
  };

  const activeStyle = {
    backgroundColor: "#2c3e50",
  };

  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        padding: "15px",
        backgroundColor: "#ecf0f1",
        boxShadow: "0px 4px 15px rgba(0,0,0,0.2)",
      }}
    >
      <NavLink to="/library/books/view" style={({ isActive }) => isActive ? { ...linkStyle, ...activeStyle } : linkStyle}>
        Books
      </NavLink>
      <NavLink to="/library/members/view" style={({ isActive }) => isActive ? { ...linkStyle, ...activeStyle } : linkStyle}>
        Members
      </NavLink>
      <NavLink to="/library/issues/allIssues" style={({ isActive }) => isActive ? { ...linkStyle, ...activeStyle } : linkStyle}>
        Issues
      </NavLink>
      <NavLink to="/library/reports" style={({ isActive }) => isActive ? { ...linkStyle, ...activeStyle } : linkStyle}>
        Reports
      </NavLink>
    </div>
  );
};

export default Header;
