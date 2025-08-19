import React, { useState, useEffect } from "react";
import axios from "axios";

function BookCountPerCategory() {
  const [bookCounts, setBookCounts] = useState([]);
  const [message, setMessage] = useState("");

  useEffect(() => {
    fetchBookCount();
  }, []);

  const fetchBookCount = async () => {
    try {
      const res = await axios.get("http://localhost:8081/reports/book-count");
      const arr = Object.entries(res.data).map(([category, count]) => ({
        category,
        count,
      }));
      setBookCounts(arr);
      setMessage("Book Count per Category");
    } catch (err) {
      console.error(err);
      setBookCounts([]);
      setMessage("Failed to fetch book count per category");
    }
  };

  return (
    <div style={{ padding: "20px", textAlign: "center" }}>
      <h2>📚 Book Count per Category</h2>
      {message && <h3>{message}</h3>}

      <table
        style={{
          width: "60%",
          margin: "auto",
          borderCollapse: "collapse",
          boxShadow: "0px 4px 10px rgba(0,0,0,0.1)",
        }}
      >
        <thead>
          <tr style={{ background: "#4CAF50", color: "white" }}>
            <th style={{ padding: "12px" }}>Category</th>
            <th style={{ padding: "12px" }}>Count</th>
          </tr>
        </thead>
        <tbody>
          {bookCounts.length > 0 ? (
            bookCounts.map((row, index) => (
              <tr
                key={index}
                style={{
                  background: index % 2 === 0 ? "#f9f9f9" : "white",
                  textAlign: "center",
                }}
              >
                <td style={{ padding: "10px" }}>{row.category}</td>
                <td style={{ padding: "10px" }}>{row.count}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="2" style={{ padding: "15px" }}>
                No data to display
              </td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}

export default BookCountPerCategory;