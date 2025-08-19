import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios"; 

function ViewAllIssuesPage() {
  const nav = useNavigate();
  const [issues, setIssues] = useState([]);

  useEffect(() => {
   
    axios
      .get("http://localhost:8082/issueReturn/issued") 
      .then((res) => setIssues(res.data))
      .catch((err) => console.error("Error fetching issues:", err));
  }, []);

  return (
    <div style={container}>
      <h2 style={title}>All Issues</h2>

      <table style={table}>
        <thead>
          <tr>
            <th style={th}>Issue ID</th>
            <th style={th}>Book ID</th>
            <th style={th}>Member ID</th>
            <th style={th}>Availability</th>
            <th style={th}>Issue Date</th>
            <th style={th}>Return Date</th>
          </tr>
        </thead>
        <tbody>
          {issues.length > 0 ? (
            issues.map((issue, idx) => (
              <tr key={idx} style={tr}>
                <td style={td}>{issue.issueId}</td>
                <td style={td}>{issue.bookId}</td>
                <td style={td}>{issue.memberId}</td>
                <td style={td}>{issue.availability ? "Yes" : "No"}</td>
                <td style={td}>{issue.issueDate}</td>
                <td style={td}>{issue.returnDate || "Not Returned"}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="6" style={tdCenter}>
                No issues found
              </td>
            </tr>
          )}
        </tbody>
      </table>

      <button style={btnRed} onClick={() => nav("/issue-return")}>
        Back
      </button>
    </div>
  );
}

const container = {
  display: "flex",
  flexDirection: "column",
  alignItems: "center",
  margin: "30px auto",
  padding: "20px",
  width: "90%",
  maxWidth: "800px",
  background: "#fff",
  borderRadius: "10px",
  boxShadow: "0 4px 10px rgba(0,0,0,0.1)",
};
const title = { fontSize: "24px", fontWeight: "bold", marginBottom: "15px" };
const table = {
  borderCollapse: "collapse",
  width: "100%",
  marginBottom: "20px",
};
const th = {
  border: "1px solid #ddd",
  padding: "10px",
  background: "#f4f4f4",
  fontWeight: "bold",
  textAlign: "center",
};
const tr = { borderBottom: "1px solid #ddd" };
const td = { border: "1px solid #ddd", padding: "10px", textAlign: "center" };
const tdCenter = { ...td, textAlign: "center", fontStyle: "italic" };
const btnRed = {
  background: "#e74c3c",
  color: "white",
  border: "none",
  padding: "10px 20px",
  borderRadius: "8px",
  cursor: "pointer",
};

export default ViewAllIssuesPage;
