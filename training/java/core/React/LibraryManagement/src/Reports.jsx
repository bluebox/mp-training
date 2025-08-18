import React, { useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

export default function Reports() {
  const [reportData, setReportData] = useState([]);
  const [reportType, setReportType] = useState("");

  const fetchReport = async (type) => {
    try {
      let url = "";
      if (type === "overdue") url = "http://localhost:8080/reports/overdue";
      if (type === "count") url = "http://localhost:8080/reports/count";
      if (type === "members") url = "http://localhost:8080/reports/members";

      const res = await axios.get(url);
      setReportType(type);
      setReportData(res.data || []);
    } catch (err) {
      alert("No data available for " + type);
      setReportData([]);
    }
  };

  const renderTable = () => {
    if (reportType === "overdue") {
      return (
        <table className="table table-bordered table-striped">
          <thead className="table-light">
            <tr>
              <th>Book Title</th>
              <th>Member Name</th>
              <th>Issue Date</th>
            </tr>
          </thead>
          <tbody>
            {reportData.map((row, i) => (
              <tr key={i}>
                <td>{row.title}</td>
                <td>{row.memberName}</td>
                <td>{row.issueDate}</td>
              </tr>
            ))}
          </tbody>
        </table>
      );
    }

    if (reportType === "count") {
      return (
        <table className="table table-bordered table-striped">
          <thead className="table-light">
            <tr>
              <th>Category</th>
              <th>Books Count</th>
            </tr>
          </thead>
          <tbody>
            {reportData.map((row, i) => (
              <tr key={i}>
                <td>{row.category}</td>
                <td>{row.count}</td>
              </tr>
            ))}
          </tbody>
        </table>
      );
    }

    if (reportType === "members") {
      return (
        <table className="table table-bordered table-striped">
          <thead className="table-light">
            <tr>
              <th>Member Name</th>
              <th>Books Issued</th>
            </tr>
          </thead>
          <tbody>
            {reportData.map((row, i) => (
              <tr key={i}>
                <td>{row.name}</td>
                <td>{row.booksIssued}</td>
              </tr>
            ))}
          </tbody>
        </table>
      );
    }

    return <p className="text-muted" >Click a button to view a report.</p>;
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
      <div className="bg-white p-4 rounded shadow" style={{ width: "80%" }}>
        <Link to="/" className="btn btn-danger mb-3">
          Back to Dashboard
        </Link>

        <h3 className="fw-bold text-center mb-4">Reports</h3>

        <div className="d-flex gap-3 justify-content-center mb-4">
          <button className="btn btn-primary" onClick={() => fetchReport("overdue")}>
            Overdue Books
          </button>
          <button className="btn btn-success" onClick={() => fetchReport("count")}>
            Books by Category
          </button>
          <button className="btn btn-warning" onClick={() => fetchReport("members")}>
            Active Members
          </button>
        </div>

        {renderTable()}
      </div>
    </div>
  );
}
