import React, { useEffect, useState } from "react";
import axios from "axios";

function ViewIssues() {
  const [issues, setIssues] = useState([]);
  const [message, setMessage] = useState("");

  useEffect(() => {
    fetchIssues();
  }, []);

  const fetchIssues = async () => {
    try {
      const res = await axios.get("http://localhost:8081/issues");
      setIssues(res.data);
    } catch (err) {
      setMessage("Failed to fetch issues");
    }
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Are you sure you want to delete this issue?")) return;

    try {
      await axios.delete(`http://localhost:8081/issues/${id}`);
      setMessage("Issue deleted successfully!");
      fetchIssues();
    } catch (err) {
      setMessage("Error deleting issue");
    }

    setTimeout(() => setMessage(""), 3000);
  };

  return (
    <div style={{ padding: "20px", textAlign: "center" }}>
      <h2 style={{ color: "#333", marginBottom: "20px" }}>📚 All Issues</h2>
      {message && <p style={{ color: "green" }}>{message}</p>}

      <table
        style={{
          width: "90%",
          margin: "auto",
          borderCollapse: "collapse",
          boxShadow: "0px 4px 10px rgba(0,0,0,0.1)",
        }}
      >
        <thead>
          <tr style={{ background: "#007BFF", color: "white" }}>
            <th style={{ padding: "12px" }}>Issue ID</th>
            <th style={{ padding: "12px" }}>Book ID</th>
            <th style={{ padding: "12px" }}>Member ID</th>
            <th style={{ padding: "12px" }}>Status</th>
            <th style={{ padding: "12px" }}>Issue Date</th>
            <th style={{ padding: "12px" }}>Return Date</th>
            {/* <th style={{ padding: "12px" }}>Action</th> */}
          </tr>
        </thead>
        <tbody>
          {issues.length > 0 ? (
            issues.map((i, index) => (
              <tr
                key={i.issueId}
                style={{
                  background: index % 2 === 0 ? "#f9f9f9" : "white",
                  textAlign: "center",
                }}
              >
                <td style={{ padding: "10px" }}>{i.issueId}</td>
                <td style={{ padding: "10px" }}>{i.bookId}</td>
                <td style={{ padding: "10px" }}>{i.memberId}</td>
                <td
                  style={{
                    padding: "10px",
                    color: i.status === "ISSUED" ? "red" : "green",
                    fontWeight: "bold",
                  }}
                >
                  {i.status}
                </td>
                <td style={{ padding: "10px" }}>{i.issueDate}</td>
                <td style={{ padding: "10px" }}>{i.returnDate || "-"}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="6" style={{ padding: "15px" }}>
                No issues found.
              </td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}

export default ViewIssues;