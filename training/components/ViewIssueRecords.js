import React, { useEffect, useState } from "react";
import axios from "axios";

const ViewIssueRecords = () => {
  const [records, setRecords] = useState([]);

  useEffect(() => {
    fetchRecords();
  }, []);

  const fetchRecords = async () => {
    try {
      const response = await axios.get("http://localhost:8080/issues");
      setRecords(response.data);
    } catch (error) {
      console.error("Error fetching issue records:", error);
    }
  };

  return (
    <div>
      <h2>All Issue Records</h2>
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
          {records.map((r) => (
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
  );
};

export default ViewIssueRecords;
