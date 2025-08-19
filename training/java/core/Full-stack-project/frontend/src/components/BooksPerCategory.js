import React, { useEffect, useState } from "react";
import axios from "axios";

function BooksPerCategory() {
  const [books, setBooks] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/reports/book-count").then((res) => {
      console.log(res.data);
      setBooks(Object.entries(res.data));
    });
  }, []);

  return (
    <div style={{ textAlign: "center" }}>
      <h2>All Books</h2>
      <table border="1" style={{ margin: "auto", width: "80%" }}>
        <thead>
          <tr>
            <th>Category</th>
            <th>Books Count</th>
          </tr>
        </thead>
        <tbody>
          {books.map((m, index) => (
            <tr key={index}>
              <td>{m[0]}</td>
              <td>{m[1]}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default BooksPerCategory;
