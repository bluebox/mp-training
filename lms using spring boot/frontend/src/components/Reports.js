import React, { useState } from "react";
import axios from "axios";

function Reports() {
  const [data, setData] = useState([]);
  const [message, setMessage] = useState("");

  const fetchOverdue = async () => {
    try {
      const res = await axios.get("http://localhost:8081/reports/overdue?days=14");
      setData(res.data);
      setMessage("Overdue Books Report");
    } catch (err) {
      console.error(err);
      setData([]);
      setMessage("Failed to fetch overdue books");
    }
  };

  const fetchBookCount = async () => {
    try {
      const res = await axios.get("http://localhost:8081/reports/book-count");
      // Convert map to array
      const arr = Object.entries(res.data).map(([category, count]) => ({ category, count }));
      setData(arr);
      setMessage("Book Count per Category Report");
    } catch (err) {
      console.error(err);
      setData([]);
      setMessage("Failed to fetch book count per category");
    }
  };

  const fetchActiveMembers = async () => {
    try {
      const res = await axios.get("http://localhost:8081/reports/active-members");
      setData(res.data);
      setMessage("Members with Active Issued Books");
    } catch (err) {
      console.error(err);
      setData([]);
      setMessage("Failed to fetch active members");
    }
  };

  return (
    <div style={{ padding: "20px", textAlign: "center" }}>
      <h2>📊 Library Reports</h2>

      <div style={{ display: "flex", flexDirection: "column", alignItems: "center", gap: "15px", marginBottom: "20px" }}>
        <button
          onClick={fetchOverdue}
          style={{
            padding: "12px 24px",
            borderRadius: "8px",
            border: "none",
            background: "#FF6B6B",
            color: "white",
            cursor: "pointer",
            width: "250px"
          }}
        >
          Overdue Books
        </button>

        <button
          onClick={fetchBookCount}
          style={{
            padding: "12px 24px",
            borderRadius: "8px",
            border: "none",
            background: "#4CAF50",
            color: "white",
            cursor: "pointer",
            width: "250px"
          }}
        >
          Book Count per Category
        </button>

        <button
          onClick={fetchActiveMembers}
          style={{
            padding: "12px 24px",
            borderRadius: "8px",
            border: "none",
            background: "#007BFF",
            color: "white",
            cursor: "pointer",
            width: "250px"
          }}
        >
          Members with Active Issued Books
        </button>
      </div>

      {message && <h3>{message}</h3>}

      {/* Table */}
      <table style={{ width: "90%", margin: "auto", borderCollapse: "collapse" }}>
        <thead>
          <tr style={{ background: "#007BFF", color: "white" }}>
            {data[0] &&
              Object.keys(data[0]).map((key) => (
                <th key={key} style={{ padding: "8px" }}>{key}</th>
              ))}
          </tr>
        </thead>
        <tbody>
          {data.length > 0 ? (
            data.map((row, i) => (
              <tr key={i} style={{ background: i % 2 === 0 ? "#f9f9f9" : "white", textAlign: "center" }}>
                {Object.values(row).map((val, j) => (
                  <td key={j} style={{ padding: "8px" }}>{val}</td>
                ))}
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="10" style={{ padding: "15px" }}>No data to display</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}

export default Reports;
