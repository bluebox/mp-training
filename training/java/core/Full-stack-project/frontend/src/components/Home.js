import React from "react";
import { Link } from "react-router-dom";

function Home() {
  return (
    <div style={{ textAlign: "center", marginTop: "20px" }}>
      <h1>Library Management System</h1>
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          gap: "14px",
          marginTop: "-10px",
        }}
      >
        <Link to="/members">
          <button>Member Management</button>
        </Link>
        <br />
        <br />
        <Link to="/books">
          <button>Book Management</button>
        </Link>

        <br />
        <br />
        <Link to="/issues">
          <button>Issue and Return</button>
        </Link>

        <br />
        <br />
        <Link to="/reports">
          <button>Reports</button>
        </Link>
      </div>
    </div>
  );
}

export default Home;
