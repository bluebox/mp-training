import React, { useEffect, useState } from "react";
import axios from "axios";

function ViewAllBooks() {
  const [books, setBooks] = useState([]);
  const [message, setMessage] = useState("");

  useEffect(() => {
    fetchBooks();
  }, []);

  const fetchBooks = async () => {
    const res = await axios.get("http://localhost:8081/books");
    setBooks(res.data);
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Are you sure you want to delete this book?")) return;

    try {
      await axios.delete(`http://localhost:8081/books/${id}`);
      setMessage("✅ Book Deleted Successfully!");
      fetchBooks();
    } catch (err) {
      setMessage("❌ Error Deleting Book");
    }

    setTimeout(() => setMessage(""), 3000);
  };

  return (
    <div style={{ textAlign: "center", padding: "20px" }}>
      <h2 style={{ color: "#2c3e50", marginBottom: "20px" }}>📚 All Books</h2>
      {message && (
        <p
          style={{
            color: message.includes("Error") ? "red" : "green",
            fontWeight: "bold",
            marginBottom: "15px",
          }}
        >
          {message}
        </p>
      )}

      <table
        style={{
          margin: "auto",
          width: "90%",
          borderCollapse: "collapse",
          boxShadow: "0px 4px 12px rgba(0,0,0,0.1)",
          borderRadius: "10px",
          overflow: "hidden",
        }}
      >
        <thead style={{ backgroundColor: "#4CAF50", color: "white" }}>
          <tr>
            <th style={{ padding: "12px" }}>ID</th>
            <th style={{ padding: "12px" }}>Title</th>
            <th style={{ padding: "12px" }}>Author</th>
            <th style={{ padding: "12px" }}>Category</th>
            <th style={{ padding: "12px" }}>Status</th>
            <th style={{ padding: "12px" }}>Availability</th>
            <th style={{ padding: "12px" }}>Action</th>
          </tr>
        </thead>
        <tbody>
          {books.length > 0 ? (
            books.map((b, index) => (
              <tr
                key={b.bookId}
                style={{
                  backgroundColor: index % 2 === 0 ? "#f9f9f9" : "white",
                  textAlign: "center",
                }}
              >
                <td style={{ padding: "10px" }}>{b.bookId}</td>
                <td style={{ padding: "10px" }}>{b.title}</td>
                <td style={{ padding: "10px" }}>{b.author}</td>
                <td style={{ padding: "10px" }}>{b.category}</td>
                <td style={{ padding: "10px" }}>{b.status}</td>
                <td style={{ padding: "10px" }}>{b.availability}</td>
                <td style={{ padding: "10px" }}>
                  <button
                    onClick={() => handleDelete(b.bookId)}
                    style={{
                      backgroundColor: "#e74c3c",
                      color: "white",
                      border: "none",
                      padding: "6px 12px",
                      borderRadius: "6px",
                      cursor: "pointer",
                    }}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="7" style={{ padding: "15px", color: "gray" }}>
                No books found.
              </td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}

export default ViewAllBooks;