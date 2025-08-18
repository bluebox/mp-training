import React, { useState, useEffect } from "react";
import {
  getOverdueBooks,
  getBooksPerCategory,
  getActiveMembers,
} from "../../api/reportService";
import "./Reports.css"; // make sure to import your CSS

function Reports() {
  const [reportType, setReportType] = useState("");
  const [overdueBooks, setOverdueBooks] = useState([]);
  const [booksPerCategory, setBooksPerCategory] = useState({});
  const [activeMembers, setActiveMembers] = useState([]);

  useEffect(() => {
    if (reportType === "overdue") {
      getOverdueBooks()
        .then((res) => setOverdueBooks(res.data))
        .catch(console.error);
    } else if (reportType === "booksPerCategory") {
      getBooksPerCategory()
        .then((res) => setBooksPerCategory(res.data))
        .catch(console.error);
    } else if (reportType === "activeMembers") {
      getActiveMembers()
        .then((res) => setActiveMembers(res.data))
        .catch(console.error);
    }
  }, [reportType]);

  return (
    <div className="Reports">
      <div className="Reports-header">
        <h1>📊 Library Reports</h1>
        <p>Select a report to view insights</p>
      </div>

      {/* Button Selection */}
      <div className="button-container">
        <button className="main-button" onClick={() => setReportType("overdue")}>
          Overdue Books
        </button>
        <button
          className="main-button"
          onClick={() => setReportType("booksPerCategory")}
        >
          Books per Category
        </button>
        <button
          className="main-button"
          onClick={() => setReportType("activeMembers")}
        >
          Active Members
        </button>
      </div>

      {/* Report Tables */}
      <div className="report-results">
        {reportType === "overdue" && (
          <>
            <h2>Overdue Books</h2>
            <table className="report-table">
              <thead>
                <tr>
                  <th>Issue ID</th>
                  <th>Book ID</th>
                  <th>Member ID</th>
                  <th>Issue Date</th>
                </tr>
              </thead>
              <tbody>
                {overdueBooks.length > 0 ? (
                  overdueBooks.map((r) => (
                    <tr key={r.issueId}>
                      <td>{r.issueId}</td>
                      <td>{r.bookId}</td>
                      <td>{r.memberId}</td>
                      <td>{r.issueDate}</td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan="4"> No overdue books</td>
                  </tr>
                )}
              </tbody>
            </table>
          </>
        )}

        {reportType === "booksPerCategory" && (
          <>
            <h2> Books per Category</h2>
            <table className="report-table">
              <thead>
                <tr>
                  <th>Category</th>
                  <th>Count</th>
                </tr>
              </thead>
              <tbody>
                {Object.keys(booksPerCategory).length > 0 ? (
                  Object.entries(booksPerCategory).map(([category, count]) => (
                    <tr key={category}>
                      <td>{category}</td>
                      <td>{count}</td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan="2">No data available</td>
                  </tr>
                )}
              </tbody>
            </table>
          </>
        )}

        {reportType === "activeMembers" && (
          <>
            <h2>👥 Active Members</h2>
            <table className="report-table">
              <thead>
                <tr>
                  <th>Member ID</th>
                  <th>Name</th>
                  <th>Email</th>
                  <th>Mobile</th>
                </tr>
              </thead>
              <tbody>
                {activeMembers.length > 0 ? (
                  activeMembers.map((m) => (
                    <tr key={m.memberId}>
                      <td>{m.memberId}</td>
                      <td>{m.name}</td>
                      <td>{m.email}</td>
                      <td>{m.mobile}</td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan="4">No active members found</td>
                  </tr>
                )}
              </tbody>
            </table>
          </>
        )}

        {!reportType && <p>Please select a report above </p>}
      </div>

      {/* Back button */}
      <button className="back-button" onClick={() => setReportType("")}>
        🔙 Back
      </button>
    </div>
  );
}

export default Reports;
