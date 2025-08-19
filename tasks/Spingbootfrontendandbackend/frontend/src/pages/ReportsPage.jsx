import React, { useState } from "react";
import { getBooksPerCategory, getActiveMembers, getOverdueBooks } from "../services/ReportsService"; // destructured import

export default function ReportsPage() {
  const [reportType, setReportType] = useState(""); 
  const [reportData, setReportData] = useState(null);
  const [loading, setLoading] = useState(false);

  const fetchReport = async (type) => {
    setLoading(true);
    setReportType(type);
    setReportData(null);

    try {
      let res;
      if (type === "books") res = await getBooksPerCategory(); 
      else if (type === "members") res = await getActiveMembers();
      else if (type === "overdue") res = await getOverdueBooks();

      console.log("API response:", res);
      setReportData(res.data);
    } catch (error) {
      console.error("Error fetching report:", error.response || error);
      alert("Failed to fetch report. Check console for details.");
    } finally {
      setLoading(false);
    }
  };

  const renderReport = () => {
    if (!reportData) return null;

    if (reportType === "books") {
      return (
        <table border="1" style={{ marginTop: "20px", width: "100%" }}>
          <thead>
            <tr>
              <th>Category</th>
              <th>Count</th>
            </tr>
          </thead>
          <tbody>
            {Object.entries(reportData).map(([category, count]) => (
              <tr key={category}>
                <td>{category}</td>
                <td>{count}</td>
              </tr>
            ))}
          </tbody>
        </table>
      );
    } else if (reportType === "members") {
      return (
        <table border="1" style={{ marginTop: "20px", width: "100%" }}>
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
            {reportData.map((m) => (
              <tr key={m.memberid}>
                <td>{m.memberid}</td>
                <td>{m.name}</td>
                <td>{m.email}</td>
                <td>{m.mobile}</td>
                <td>{m.gender}</td>
                <td>{m.address}</td>
              </tr>
            ))}
          </tbody>
        </table>
      );
    } else if (reportType === "overdue") {
      return (
        <table border="1" style={{ marginTop: "20px", width: "100%" }}>
          <thead>
            <tr>
              <th>Book ID</th>
              <th>Title</th>
              <th>Author</th>
              <th>Category</th>
              <th>Status</th>
              <th>Availability</th>
            </tr>
          </thead>
          <tbody>
            {reportData.map((b) => (
              <tr key={b.bookid}>
                <td>{b.bookid}</td>
                <td>{b.title}</td>
                <td>{b.author}</td>
                <td>{b.category}</td>
                <td>{b.status}</td>
                <td>{b.availability}</td>
              </tr>
            ))}
          </tbody>
        </table>
      );
    }
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Library Reports</h2>

      <div style={{ marginBottom: "20px" }}>
        <button onClick={() => fetchReport("books")}>Books Per Category</button>
        <button onClick={() => fetchReport("members")} style={{ marginLeft: "10px" }}>
          Active Members with Issued Books
        </button>
        <button onClick={() => fetchReport("overdue")} style={{ marginLeft: "10px" }}>
          Overdue Books
        </button>
      </div>

      {loading && <p>Loading report...</p>}
      {renderReport()}
    </div>
  );
}
