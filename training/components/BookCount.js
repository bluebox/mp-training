import React, { useEffect, useState } from "react";
import axios from "axios";

function BookCount() {
  const [countData, setCountData] = useState({});

  const fetchBookCount = async () => {
    try {
      const response = await axios.get("http://localhost:8080/reports/book-count");
      setCountData(response.data);
    } catch (error) {
      console.error("Error fetching book count:", error);
    }
  };

  useEffect(() => {
    fetchBookCount();
  }, []);

  return (
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
          {Object.entries(countData).map(([category, count]) => (
            <tr key={category}>
              <td>{category}</td>
              <td>{count}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default BookCount;
