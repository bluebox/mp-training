import React, { useState } from "react";
import axios from "axios";

const Reports = () => {
  // State variables for each report
  const [overdueBooks, setOverdueBooks] = useState([]);
  const [bookCount, setBookCount] = useState({});
  const [activeMembers, setActiveMembers] = useState([]);

  // Fetch overdue books
  const fetchOverdueBooks = async () => {
    try {
      const response = await axios.get("http://localhost:8080/reports/overdue?days=14");
      setOverdueBooks(response.data);
      setBookCount({});
      setActiveMembers([]);
    } catch (error) {
      console.error("Error fetching overdue books:", error);
      alert("Failed to fetch overdue books");
    }
  };

  // Fetch book count per category
  const fetchBookCount = async () => {
    try {
      const response = await axios.get("http://localhost:8080/reports/book-count");
      setBookCount(response.data);
      setOverdueBooks([]);
      setActiveMembers([]);
    } catch (error) {
      console.error("Error fetching book count:", error);
      alert("Failed to fetch book count");
    }
  };

  // Fetch members with active issued books
  const fetchActiveMembers = async () => {
    try {
      const response = await axios.get("http://localhost:8080/reports/active-members");
      setActiveMembers(response.data);
      setOverdueBooks([]);
      setBookCount({});
    } catch (error) {
      console.error("Error fetching active members:", error);
      alert("Failed to fetch active members");
    }
  };

  return (
    <div>
      <h2> Reports</h2>

      {/* Buttons to choose report */}
      <div style={{ marginBottom: "20px" }}>
        <button onClick={fetchOverdueBooks}>Overdue Books</button>
        <button onClick={fetchBookCount}>Book Count per Category</button>
        <button onClick={fetchActiveMembers}>Active Members</button>
      </div>

      {/* Overdue Books Table */}
      {overdueBooks.length > 0 && (
        <div>
          <h3> Overdue Books</h3>
          <table border="1" cellPadding="10">
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
              {overdueBooks.map((r) => (
                <tr key={r.issueId}>
                  <td>{r.issueId}</td>
                  <td>{r.bookId}</td>
                  <td>{r.memberId}</td>
                  <td>{r.status}</td>
                  <td>{r.issueDate}</td>
                  <td>{r.returnDate || "-"}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}

      {/* Book Count Table */}
      {Object.keys(bookCount).length > 0 && (
        <div>
          <h3> Book Count per Category</h3>
          <table border="1" cellPadding="10">
            <thead>
              <tr>
                <th>Category</th>
                <th>Count</th>
              </tr>
            </thead>
            <tbody>
              {Object.entries(bookCount).map(([category, count]) => (
                <tr key={category}>
                  <td>{category}</td>
                  <td>{count}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}

      {/* Active Members Table */}
      {activeMembers.length > 0 && (
        <div>
          <h3> Members with Active Issued Books</h3>
          <table border="1" cellPadding="10">
            <thead>
              <tr>
                <th>Member ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Mobile</th>
                <th>Gender</th>
                <th>Address</th>
              </tr>
            </thead>
            <tbody>
              {activeMembers.map((m) => (
                <tr key={m.memberId}>
                  <td>{m.memberId}</td>
                  <td>{m.name}</td>
                  <td>{m.email}</td>
                  <td>{m.mobile}</td>
                  <td>{m.gender}</td>
                  <td>{m.address}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
};

export default Reports;
