import React from "react";
import { Link } from "react-router-dom";

function IssueReturn() {
  return (
    <div style={{ textAlign: "center", marginTop: "50px" }}>
      <h2 style={{ marginBottom: "20px" }}>Issue / Return Book</h2>

      <div
        style={{
          display: "flex",
          flexDirection: "column", 
          alignItems: "center",
          gap: "15px",
        }}
      >
        <Link to="/issues/issue">
          <button
            style={{
              width: "200px",
              padding: "10px 20px",
              borderRadius: "8px",
              border: "none",
              background: "linear-gradient(135deg, #ff6a00, #ee0979)",
              color: "#fff",
              fontWeight: "bold",
              cursor: "pointer",
              transition: "0.25s",
            }}
          >
            Issue Book
          </button>
        </Link>

        <Link to="/issues/return">
          <button
            style={{
              width: "200px",
              padding: "10px 20px",
              borderRadius: "8px",
              border: "none",
              background: "linear-gradient(135deg, #11998e, #38ef7d)",
              color: "#fff",
              fontWeight: "bold",
              cursor: "pointer",
              transition: "0.25s",
            }}
          >
            Return Book
          </button>
        </Link>

        <Link to="/issues/view">
          <button
            style={{
              width: "200px",
              padding: "10px 20px",
              borderRadius: "8px",
              border: "none",
              background: "linear-gradient(135deg, #667eea, #764ba2)",
              color: "#fff",
              fontWeight: "bold",
              cursor: "pointer",
              transition: "0.25s",
            }}
          >
            View All Issues
          </button>
        </Link>
      </div>
    </div>
  );
}

export default IssueReturn;