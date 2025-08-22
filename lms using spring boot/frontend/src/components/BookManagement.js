import React from "react";
import { Link } from "react-router-dom";

function BookManagement() {
  return (
    <div style={{ textAlign: "center", marginTop: "50px" }}>
      <h2 style={{ color: "#d35400" }}>Book Management</h2>

      <div style={{ display: "flex", flexDirection: "column", gap: "15px", alignItems: "center", marginTop: "30px" }}>
        <Link to="/books/add">
          <button style={{ backgroundColor: "#28a745", color: "white", padding: "10px 20px", border: "none", borderRadius: "8px", cursor: "pointer" }}>
            Add Book
          </button>
        </Link>

        <Link to="/books/view">
          <button style={{ backgroundColor: "#007bff", color: "white", padding: "10px 20px", border: "none", borderRadius: "8px", cursor: "pointer" }}>
            View Books
          </button>
        </Link>

        <Link to="/books/update/:id">
          <button style={{ backgroundColor: "#ffc107", color: "black", padding: "10px 20px", border: "none", borderRadius: "8px", cursor: "pointer" }}>
            Update Book
          </button>
        </Link>
      </div>
    </div>
  );
}

export default BookManagement;