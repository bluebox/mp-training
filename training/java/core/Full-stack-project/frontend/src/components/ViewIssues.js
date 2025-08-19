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
      const res = await axios.get("http://localhost:8080/issues");
      setIssues(res.data);
    } catch (err) {
      setMessage("Failed to fetch issues");
    }
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Are you sure you want to delete this issue?")) return;

    try {
      await axios.delete(`http://localhost:8080/issues/${id}`);
      setMessage("Issue deleted successfully!");
      fetchIssues();
    } catch (err) {
      setMessage("Error deleting issue");
    }

    setTimeout(() => setMessage(""), 3000);
  };

  return (
    <div style={{ textAlign: "center" }}>
      <h2>All Issues</h2>
      {message && <p>{message}</p>}

      <table border="1" style={{ margin: "auto", width: "80%" }}>
        <thead>
          <tr>
            <th>Issue ID</th>
            <th>Book ID</th>
            <th>Member ID</th>
            <th>Status</th>
            <th>Issue Date</th>
            <th>Return Date</th>
          </tr>
        </thead>
        <tbody>
          {issues.length > 0 ? (
            issues.map((i) => (
              <tr key={i.issueId}>
                <td>{i.issueId}</td>
                <td>{i.bookId}</td>
                <td>{i.memberId}</td>
                <td>{i.status}</td>
                <td>{i.issueDate}</td>
                <td>{i.returnDate || "-"}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="7">No issues found.</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}

export default ViewIssues;
