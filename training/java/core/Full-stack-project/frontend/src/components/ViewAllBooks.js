import React, { useEffect, useState } from "react";
import axios from "axios";

function ViewAllBooks() {
  const [books, setBooks] = useState([]);
  const [message, setMessage] = useState("");

  useEffect(() => {
    fetchBooks();
  }, []);

  const fetchBooks = async () => {
    const res = await axios.get("http://localhost:8080/books");
    setBooks(res.data);
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Are you sure you want to delete this book?")) return;

    try {
      await axios.delete(`http://localhost:8080/books/${id}`);
      setMessage("Book Deleted Successfully!");
      fetchBooks();
    } catch (err) {
      setMessage("Error Deleting Book");
    }

    setTimeout(() => setMessage(""), 3000);
  };

  return (
    <div style={{ textAlign: "center" }}>
      <h2>All Books</h2>
      {message && <p>{message}</p>}

      <table border="1" style={{ margin: "auto", width: "80%" }}>
        <thead>
          <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Category</th>
            <th>Status</th>
            <th>Availability</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {books.length > 0 ? (
            books.map((b) => (
              <tr key={b.bookId}>
                <td>{b.bookId}</td>
                <td>{b.title}</td>
                <td>{b.author}</td>
                <td>{b.category}</td>
                <td>{b.status}</td>
                <td>{b.availability}</td>
                <td>
                  <button onClick={() => handleDelete(b.bookId)}>Delete</button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="7">No books found.</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}

export default ViewAllBooks;
