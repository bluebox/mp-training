import React from "react";
import { useNavigate } from "react-router-dom";
import './HomePage.css'; 

function HomePage() {
  const navigate = useNavigate();

  const username = "Niha"; 

  return (
    <div className="home-container">
      <div className="content">
        <h1> Library Management System</h1>
        <h2>Welcome, {username}</h2>
        <div className="button-group">
          <button onClick={() => navigate("/books")}>Books</button>
          <button onClick={() => navigate("/members")}>Members</button>
          <button onClick={() => navigate("/issues")}>Issue Records</button>
          <button onClick={() => navigate("/reports")}>Reports</button>
        </div>
      </div>
    </div>
  );
}

export default HomePage;
