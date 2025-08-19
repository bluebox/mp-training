import React, { useState, useEffect } from "react";
import axios from "axios";

function OverdueBooks() {
  const [overdueBooks, setOverdueBooks] = useState([]);
  const [message, setMessage] = useState("");

  useEffect(() => {
    fetchOverdueBooks();
  }, []);

  const fetchOverdueBooks = async () => {
    try {
      const res = await axios.get("http://localhost:8081/reports/overdue?days=14");
      setOverdueBooks(res.data);
      setMessage(`Overdue Books (past 14 days)`);
    } catch (err) {
      console.error(err);
      setMessage("Failed to fetch overdue books");
      setOverdueBooks([]);
    }
  };

  return (
    <div style={{ padding: "20px", textAlign: "center" }}>
      <h2>📚 Overdue Books</h2>
      {message && <h3>{message}</h3>}

      <table
        style={{
          width: "90%",
          margin: "auto",
          borderCollapse: "collapse",
          boxShadow: "0px 4px 10px rgba(0,0,0,0.1)",
        }}
      >
        <thead>
          <tr style={{ background: "#FF6B6B", color: "white" }}>
            <th style={{ padding: "12px" }}>Issue ID</th>
            <th style={{ padding: "12px" }}>Book ID</th>
            <th style={{ padding: "12px" }}>Member ID</th>
            <th style={{ padding: "12px" }}>Status</th>
            <th style={{ padding: "12px" }}>Issue Date</th>
            <th style={{ padding: "12px" }}>Return Date</th>
          </tr>
        </thead>
        <tbody>
          {overdueBooks.length > 0 ? (
            overdueBooks.map((record, index) => (
              <tr
                key={record.issueId}
                style={{
                  background: index % 2 === 0 ? "#f9f9f9" : "white",
                  textAlign: "center",
                }}
              >
                <td style={{ padding: "10px" }}>{record.issueId}</td>
                <td style={{ padding: "10px" }}>{record.bookId}</td>
                <td style={{ padding: "10px" }}>{record.memberId}</td>
                <td
                  style={{
                    padding: "10px",
                    color: record.status === "ISSUED" ? "red" : "green",
                    fontWeight: "bold",
                  }}
                >
                  {record.status}
                </td>
                <td style={{ padding: "10px" }}>{record.issueDate}</td>
                <td style={{ padding: "10px" }}>{record.returnDate || "-"}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="6" style={{ padding: "15px" }}>
                No overdue books found.
              </td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}

export default OverdueBooks;