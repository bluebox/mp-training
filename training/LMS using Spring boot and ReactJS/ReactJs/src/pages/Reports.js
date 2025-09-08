import React, { useState } from "react";

function Reports() {
  const [reportData, setReportData] = useState([]);
  const [reportType, setReportType] = useState("");
  const capitalizeFirst = (str) => {
    if (!str) return "";
    return str.charAt(0).toUpperCase() + str.slice(1).toLowerCase();
  };
  const fetchReport = async (type) => {
    let url = "";

    if (type === "overdue") {
      url = "http://localhost:8080/api/issues/reports/overdueBooks";
    } else if (type === "membersWithActiveBooks") {
      url = "http://localhost:8080/api/issues/reports/membersWithActiveBooks";
    } else if (type === "booksByCategory") {
      url = "http://localhost:8080/api/books/viewBooks";
    }

    try {
      const res = await fetch(url);
      const data = await res.json();

      if (type === "booksByCategory") {
        const grouped = data.reduce((acc, book) => {
          const category = book.category || "Uncategorized";
          acc[category] = (acc[category] || 0) + 1;
          return acc;
        }, {});

        const formatted = Object.entries(grouped).map(([category, count]) => ({
          category,
          count,
        }));

        setReportData(formatted);
      } else {
        setReportData(data);
      }

      setReportType(type);
    } catch (err) {
      console.error("Error fetching report:", err);
      setReportData([]);
    }
  };

  const renderTable = () => {
    if (reportType === "overdue") {
      if (reportData.length === 0) {
        return (
          <p>No overdue books found</p>
        );
      }
      return (
        <table className="report-table">
          <thead>
            <tr>
              <th>Issue ID</th>
              <th>Book Name</th>
              <th>Member Name</th>
              <th>Due Date</th>
            </tr>
          </thead>
          <tbody>
            {reportData.map((rec) => (
              <tr key={rec.issueId}>
                <td>{rec.issueId}</td>
                <td>{rec.book?.title}</td>
                <td>{rec.member?.name}</td>
                <td>{rec.dueDate}</td>
              </tr>
            ))}
          </tbody>
        </table>
      );
    }

    if (reportType === "membersWithActiveBooks") {
      if (reportData.length === 0) {
        return <p>No members with active books</p>;
      }
      return (
        <table className="report-table">
          <thead>
            <tr>
              <th>Member ID</th>
              <th>Name</th>
            </tr>
          </thead>
          <tbody>
            {reportData.map((m) => (
              <tr key={m.memberID}>
                <td>{m.memberID}</td>
                <td>{m.name}</td>
              </tr>
            ))}
          </tbody>
        </table>
      );
    }

    if (reportType === "booksByCategory") {
      return (
        <table className="report-table">
          <thead>
            <tr>
              <th>Category</th>
              <th>Book Count</th>
            </tr>
          </thead>
          <tbody>
            {reportData.map((c, idx) => (
              <tr key={idx}>
                <td>{capitalizeFirst(c.category)}</td>
                <td>{c.count}</td>
              </tr>
            ))}
          </tbody>
        </table>
      );
    }

    return <p>Please select a report.</p>;
  };

  return (
    <div className="reports-container">
      <h2>Library Reports</h2>
      <div className="button-row">
        <button onClick={() => fetchReport("overdue")}>Overdue Books</button>
        <button onClick={() => fetchReport("booksByCategory")}>Books Per Category</button>
        <button onClick={() => fetchReport("membersWithActiveBooks")}>
          Members with Active Books
        </button>

      </div>
      <div className="report-display">{renderTable()}</div>
      <button type="button" onClick={() => (window.location.href = "/")}>
        Back to Main Menu
      </button>
    </div>
  );
}

export default Reports;
