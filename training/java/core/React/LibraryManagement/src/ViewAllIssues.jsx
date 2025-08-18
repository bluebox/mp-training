import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";

export default function ViewAllIssues() {
  const [issues, setIssues] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    fetchIssues();
  }, []);

  const fetchIssues = async () => {
    try {
      const response = await axios.get("http://localhost:8080/issueReturn");
      setIssues(response.data);
    } catch (error) {
      alert("Failed to fetch Issues.");
    }
  };

  const getStatusLabel = (status) => {
    if (status === "R") return "Returned";
    if (status === "I") return "Issued";
    return status;
  };

  return (
    <div
      className="d-flex align-items-center justify-content-center"
      style={{
        minHeight: "100vh",
        backgroundImage: "url('/library.jpg')",
        backgroundSize: "cover",
        backgroundPosition: "center",
        backgroundAttachment: "fixed",
      }}
    >
      <div className="bg-white p-4 rounded shadow" style={{ width: "70%" }}>
        <Link to="/issue-return" className="btn btn-danger mb-3">
          Back to Dashboard
        </Link>

        <h3 className="fw-bold text-center mb-4">All Issue Records</h3>

        <table className="table table-bordered table-striped">
          <thead className="table-light">
            <tr>
              <th>Issue ID</th>
              <th>Book Id</th>
              <th>Member Id</th>
              <th>Issue Date</th>
              <th>Return Date</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {issues.length > 0 ? (
              issues.map((issue) => (
                <tr key={issue.issueId}>
                  <td>{issue.issueId}</td>
                  <td>{issue.bookId}</td>
                  <td>{issue.memberId}</td>
                  <td>{issue.issueDate}</td>
                  <td>{issue.returnDate || "-"}</td>
                  <td>{getStatusLabel(issue.status)}</td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="6" className="text-center">
                  No Issues Data.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}
