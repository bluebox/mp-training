import React from "react";
import { NavLink } from "react-router-dom";

const ReportsHeader = () => {
  const linkStyle = {
    padding: "10px 16px",
    fontSize: "15px",
    fontWeight: "bold",
    borderRadius: "6px",
    border: "none",
    textDecoration: "none",
	backgroundColor: "#2ecc71",
			color: "white",
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
        padding: "10px",
		
        boxShadow: "0 3px 8px rgba(0,0,0,0.2)",
        marginBottom: "20px",
      }}
    >
      <NavLink
        to="overdueRecords"
        style={({ isActive }) => (isActive ? { ...linkStyle, ...activeStyle } : linkStyle)}
      >
        Overdue Books
      </NavLink>
      <NavLink
        to="categoryCount"
        style={({ isActive }) => (isActive ? { ...linkStyle, ...activeStyle } : linkStyle)}
      >
        Category Count
      </NavLink>
      <NavLink
        to="activeIssuedRecords"
        style={({ isActive }) => (isActive ? { ...linkStyle, ...activeStyle } : linkStyle)}
      >
        Active Issued
      </NavLink>
    </div>
  );
};

export default ReportsHeader;
