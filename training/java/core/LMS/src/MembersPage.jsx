import React from "react";
import { useNavigate } from "react-router-dom";

function MembersPage() {
  const nav = useNavigate();

  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        height: "100vh",
      }}
    >
      <div
        style={{
          textAlign: "center",
          padding: "30px",
          border: "1px solid #ccc",
          borderRadius: "12px",
          boxShadow: "0 4px 8px rgba(0,0,0,0.1)",
          width: "400px",
        }}
      >
        <h2 style={{ fontSize: "22px", marginBottom: "40px" }}>
          Library Management System
        </h2>

        <button
          style={{
            display: "block",
            width: "100%",
            padding: "12px",
            marginBottom: "20px",
            backgroundColor: "#3498db",
            color: "white",
            border: "none",
            borderRadius: "8px",
            fontSize: "16px",
            cursor: "pointer",
          }}
          onClick={() => nav("/add-member")}
        >
           Add a Member
        </button>

        <button
          style={{
            display: "block",
            width: "100%",
            padding: "12px",
            marginBottom: "20px",
            backgroundColor: "#2ecc71",
            color: "white",
            border: "none",
            borderRadius: "8px",
            fontSize: "16px",
            cursor: "pointer",
          }}
          onClick={() => nav("/view-members")}
        >
           View All Members
        </button>

        <button
          style={{
            display: "block",
            width: "100%",
            padding: "12px",
            backgroundColor: "#e74c3c",
            color: "white",
            border: "none",
            borderRadius: "8px",
            fontSize: "16px",
            cursor: "pointer",
          }}
          onClick={() => nav("/")}
        >
           Back
        </button>
      </div>
    </div>
  );
}

export default MembersPage;
