import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

function ReportsPage() {
  const nav = useNavigate();
  const [reportType, setReportType] = useState(null);
  const [data, setData] = useState([]);

  
  useEffect(() => {
    if (!reportType) return;

    let url = "";
    if (reportType === "overdue") url = "http://localhost:8082/reports/overdue";
    else if (reportType === "category") url = "http://localhost:8082/reports/count";
    else if (reportType === "active") url = "http://localhost:8082/reports/members";

    fetch(url)
      .then((res) => res.json())
      .then((json) => setData(json))
      .catch((err) => console.error("Error fetching report:", err));
  }, [reportType]);
console.log(data);
  
  const renderTable = () => {
    if (!reportType) return <p style={msg}>Select a report to view data</p>;

    if (reportType === "overdue") {
      return (
        <table style={table}>
          <thead>
            <tr>
              <th style={th}>Title</th>
              <th style={th}>Member</th>
              <th style={th}>Due Date</th>
            </tr>
          </thead>
          <tbody>
            {data.length > 0 ? (
              data.map((row, idx) => (
                <tr key={idx} style={tr}>
                  <td style={td}>{row.title}</td>
                  <td style={td}>{row.member}</td>
                  <td style={td}>{row.dueDate}</td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="3" style={tdCenter}>No overdue books</td>
              </tr>
            )}
          </tbody>
        </table>
      );
    }

    if (reportType === "category") {
      return (
        <table style={table}>
          <thead>
            <tr>
              <th style={th}>Category</th>
              <th style={th}>Count</th>
            </tr>
          </thead>
          <tbody>
            {data.length > 0 ? (
              data.map((row, idx) => (
                <tr key={idx} style={tr}>
                  <td style={td}>{row.category}</td>
                  <td style={td}>{row.count}</td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="2" style={tdCenter}>No data</td>
              </tr>
            )}
          </tbody>
        </table>
      );
    }

    if (reportType === "active") {
      return (
        <table style={table}>
          <thead>
            <tr>
              <th style={th}>Member Name</th>
              <th style={th}>Books Issued</th>
            </tr>
          </thead>
          <tbody>
            {data.length > 0 ? (
              data.map((row, idx) => (
                <tr key={idx} style={tr}>
                  <td style={td}>{row.name}</td>
                  <td style={td}>{row.booksIssued}</td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="2" style={tdCenter}>No active members</td>
              </tr>
            )}
          </tbody>
        </table>
      );
    }
  };

  return (
    <div style={container}>
     
      <h2 style={title}>Reports</h2>

      <div style={layout}>
       
        <div style={sidebar}>
          <button style={btn} onClick={() => setReportType("overdue")}>
            Overdue Books
          </button>
          <button style={btn} onClick={() => setReportType("category")}>
            Count of Books per Category
          </button>
          <button style={btn} onClick={() => setReportType("active")}>
            Active Issued Members
          </button>
          <button style={btnRed} onClick={() => nav("/")}>
            Back
          </button>
        </div>

      
        <div style={content}>{renderTable()}</div>
      </div>
    </div>
  );
}


const container = {
  padding: "20px",
  width: "90%",
  margin: "0 auto",
};
const title = { textAlign: "center", fontSize: "22px", fontWeight: "bold", marginBottom: "20px" };
const layout = { display: "flex", gap: "20px" };
const sidebar = { display: "flex", flexDirection: "column", gap: "15px", width: "220px" };
const content = { flex: 1, padding: "15px", border: "1px solid #ddd", borderRadius: "8px" };
const table = { borderCollapse: "collapse", width: "100%" };
const th = { border: "1px solid #ddd", padding: "10px", background: "#f4f4f4", fontWeight: "bold" };
const tr = { borderBottom: "1px solid #ddd" };
const td = { border: "1px solid #ddd", padding: "10px", textAlign: "center" };
const tdCenter = { ...td, textAlign: "center", fontStyle: "italic" };
const msg = { textAlign: "center", fontStyle: "italic", color: "#555" };
const btn = {
  padding: "10px",
  background: "#3498db",
  color: "#fff",
  border: "none",
  borderRadius: "8px",
  cursor: "pointer",
};
const btnRed = { ...btn, background: "#e74c3c" };

export default ReportsPage;
