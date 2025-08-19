import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

function ViewBooksPage() {
  const nav = useNavigate();

  const [books,setBooks]=useState([]);
  useEffect(() => {
    fetchBooks();
  }, []);

  const fetchBooks = async () => {
    try {
       
            const response =await axios.get("http://localhost:8082/books");
            const data=response.data;
            console.log(data);
      setBooks(data);
    } catch {
      alert("Failed to fetch books");
    }
  };

  return (
    <div style={{ padding: "20px", maxWidth: "800px", margin: "auto" }}>
      <h2 style={{ textAlign: "center", marginBottom: "20px" }}> Book List</h2>

      <table
        border="1"
        width="100%"
        cellPadding="10"
        style={{ borderCollapse: "collapse" }}
      >
        <thead>
          <tr style={{ backgroundColor: "#f2f2f2" }}>
            <th>Book ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Category</th>
            <th>Status</th>
            <th>Availability</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {books.map((b) => (
            <tr key={b.bookId}>
              <td>{b.bookId}</td>
              <td>{b.title}</td>
              <td>{b.author}</td>
              <td>{b.category}</td>
              <td>{b.status}</td>
              <td>{b.availability}</td>
              <td>
  <button onClick={() => nav(`/update-book/${b.bookId}`)}> Update Details</button>
  <button
    style={{ marginLeft: "10px" }}
    onClick={() => nav(`/update-availability/${b.bookId}`)}
  >
     Update Availability
  </button>
</td>

            </tr>
          ))}
        </tbody>
      </table>

      <div style={{ marginTop: "20px", textAlign: "center" }}>
        <button onClick={() => nav("/books")} style={{ color: "red" }}>
           Back
        </button>
      </div>
    </div>
  );
}

export default ViewBooksPage;
