import React, { useState, useEffect } from "react";
import axios from "axios";

export default function Reports() {
  const [reportType, setReportType] = useState("Overdue Books");
  const [data, setData] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    fetchReport(reportType);
  }, [reportType]);

  const fetchReport = async (type) => {
    try {
      const url = `http://localhost:8080/reports?reportType=${encodeURIComponent(type)}`;
      const res = await axios.get(url);

      if (res.data.error) {
        setError(res.data.error);
        setData([]);
      } else {
        if (type === "Books by Category") {
          const categoryData = Object.entries(res.data.data).map(([key, value]) => ({
            category: key,
            count: value,
          }));
          setData(categoryData);
        } else {
          setData(res.data.data);
        }
        setError("");
      }
    } catch {
      setError("Failed to load report data.");
      setData([]);
    }
  };

  return (
    <div className="container mt-4">
      <div className="card shadow p-4">
        <h2 className="card-title mb-3">Library Management System - Reports</h2>

        <div className="mb-3">
          <label className="form-label">Select Report:</label>
          <select
            value={reportType}
            onChange={(e) => setReportType(e.target.value)}
            className="form-select"
          >
            <option value="Overdue Books">Overdue Books</option>
            <option value="Books by Category">Books by Category</option>
            <option value="Active Members">Active Members</option>
          </select>
        </div>

        {error && <div className="alert alert-danger">{error}</div>}

        {/* Overdue Books */}
        {reportType === "Overdue Books" && data.length > 0 && (
          <div className="table-responsive mt-3">
            <h4>Overdue Books</h4>
            <table className="table table-bordered table-striped">
              <thead className="table-light">
                <tr>
                  <th>ID</th><th>Title</th><th>Author</th><th>Category</th>
                </tr>
              </thead>
              <tbody>
                {data.map((book) => (
                  <tr key={book.id}>
                    <td>{book.id}</td>
                    <td>{book.title}</td>
                    <td>{book.author}</td>
                    <td>{book.category}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}

        {/* Books by Category */}
        {reportType === "Books by Category" && data.length > 0 && (
          <div className="table-responsive mt-3">
            <h4>Book Count by Category</h4>
            <table className="table table-bordered table-striped">
              <thead className="table-light">
                <tr>
                  <th>Category</th><th>Count</th>
                </tr>
              </thead>
              <tbody>
                {data.map((entry, idx) => (
                  <tr key={idx}>
                    <td>{entry.category}</td>
                    <td>{entry.count}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}

        {/* Active Members */}
        {reportType === "Active Members" && data.length > 0 && (
          <div className="table-responsive mt-3">
            <h4>Members with Active Issued Books</h4>
            <table className="table table-bordered table-striped">
              <thead className="table-light">
                <tr>
                  <th>ID</th><th>Name</th><th>Email</th>
                  <th>Mobile</th><th>Gender</th><th>Address</th>
                </tr>
              </thead>
              <tbody>
                {data.map((member) => (
                  <tr key={member.id}>
                    <td>{member.id}</td>
                    <td>{member.name}</td>
                    <td>{member.email}</td>
                    <td>{member.mobile}</td>
                    <td>{member.gender}</td>
                    <td>{member.address}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
}
