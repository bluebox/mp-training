import React from "react";
import { Link } from "react-router-dom";

function Home() {
  return (
    <div style={{ textAlign: "center", marginTop: "50px" }}>
      <h1 style={{ color: "#2E86C1" }}>Welcome to Library Management System</h1>

      <div style={{ display: "flex", flexDirection: "column", gap: "15px", alignItems: "center", marginTop: "30px" }}>
        <Link to="/members">
          <button style={{ backgroundColor: "#28a745", color: "white", padding: "10px 20px", border: "none", borderRadius: "8px", cursor: "pointer" }}>
            Member Management
          </button>
        </Link>

        <Link to="/books">
          <button style={{ backgroundColor: "#007bff", color: "white", padding: "10px 20px", border: "none", borderRadius: "8px", cursor: "pointer" }}>
            Book Management
          </button>
        </Link>

        <Link to="/issues">
          <button style={{ backgroundColor: "#ffc107", color: "black", padding: "10px 20px", border: "none", borderRadius: "8px", cursor: "pointer" }}>
            Issue And Return
          </button>
        </Link>

        <Link to="/reports">
          <button style={{ backgroundColor: "#c42692ff", color: "white", padding: "10px 20px", border: "none", borderRadius: "8px", cursor: "pointer" }}>
            View Reports
          </button>
        </Link>
      </div>
    </div>
  );
}

export default Home;