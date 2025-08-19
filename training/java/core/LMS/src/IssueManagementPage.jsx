import React from "react";
import { useNavigate } from "react-router-dom";

function IssueManagementPage() {
  const nav = useNavigate();

  return (
    <div style={container}>
      <h2 style={title}>Issue Management</h2>

      <button
        style={btnStyle}
        onClick={() => nav("/issue-book")}
      >
        Issue Book
      </button>

      <button
        style={btnStyle}
        onClick={() => nav("/return-book")}
      >
        Return Book
      </button>

      <button
        style={btnStyle}
        onClick={() => nav("/view-issues")}
      >
        View All Issues
      </button>

      <button
        style={btnStyleRed}
        onClick={() => nav("/")}
      >
        Back
      </button>
    </div>
  );
}


const container = {
  display: "flex",
  flexDirection: "column",
  alignItems: "center",
  justifyContent: "center",
  gap: "15px",
  minHeight: "100vh",
  background: "#f9f9f9",
};
const title = {
  fontSize: "22px",
  fontWeight: "bold",
  marginBottom: "30px",
};
const btnStyle = {
  backgroundColor: "#3498db",
  color: "white",
  padding: "10px 20px",
  border: "none",
  borderRadius: "8px",
  cursor: "pointer",
  minWidth: "200px",
  fontSize: "16px",
};
const btnStyleRed = {
  ...btnStyle,
  backgroundColor: "#e74c3c",
};

export default IssueManagementPage;
