import React, { useEffect, useState } from "react";
import axios from "axios";

function OverdueBooks() {
  const [issues, setIssues] = useState([]);
  const [message, setMessage] = useState("");

  useEffect(() => {
    fetchIssues();
  }, []);

  const fetchIssues = async () => {
    try {
      const res = await axios.get("http://localhost:8080/reports/overdue");
      setIssues(res.data);
    } catch (err) {
      setMessage("Failed to fetch issues");
    }
  };

  return (
    <div style={{ textAlign: "center" }}>
      <h2>OverdueBooks</h2>
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
                <td>{i.returnDate || "Not Returned"}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="7">No OverDues Found.</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}

export default OverdueBooks;
