import React, { useState, useEffect } from "react";
import axios from "axios";

const OverdueBooks = () => {
  const [days, setDays] = useState(14); // default overdue period
  const [records, setRecords] = useState([]);

  useEffect(() => {
    fetchOverdueBooks();
  }, [days]);

  const fetchOverdueBooks = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/reports/overdue?days=${days}`);
      setRecords(response.data);
    } catch (error) {
      console.error("Error fetching overdue books:", error);
      alert("Failed to fetch overdue books");
    }
  };

  return (
    <div>
      <h2> Overdue Books</h2>

      <label>
        Overdue Days: 
        <input 
          type="number" 
          value={days} 
          onChange={(e) => setDays(Number(e.target.value))} 
          min="1"
        />
      </label>

      <button onClick={fetchOverdueBooks}>Refresh</button>

      <table border="1" cellPadding="10" style={{ marginTop: "20px" }}>
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

export default OverdueBooks;
