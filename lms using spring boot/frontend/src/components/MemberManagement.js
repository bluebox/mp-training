import React from "react";
import { Link } from "react-router-dom";

function MemberManagement() {
  return (
    <div style={{ textAlign: "center", marginTop: "50px" }}>
      <h2 style={{ color: "#8e44ad" }}>Member Management</h2>

      <div style={{ display: "flex", flexDirection: "column", gap: "15px", alignItems: "center", marginTop: "30px" }}>
        <Link to="/members/add">
          <button style={{ backgroundColor: "#28a745", color: "white", padding: "10px 20px", border: "none", borderRadius: "8px", cursor: "pointer" }}>
            Add Member
          </button>
        </Link>

        <Link to="/members/view">
          <button style={{ backgroundColor: "#007bff", color: "white", padding: "10px 20px", border: "none", borderRadius: "8px", cursor: "pointer" }}>
            View Members
          </button>
        </Link>

        <Link to="/members/update/:id">
          <button style={{ backgroundColor: "#ffc107", color: "black", padding: "10px 20px", border: "none", borderRadius: "8px", cursor: "pointer" }}>
            Update Member
          </button>
        </Link>
      </div>
    </div>
  );
}

export default MemberManagement;