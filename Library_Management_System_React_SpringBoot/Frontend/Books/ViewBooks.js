import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getAllBooks } from "../../api/bookService";
import "./Books.css";

function ViewBooks() {
  const navigate = useNavigate();
  const [books, setBooks] = useState([]);

  useEffect(() => {
    getAllBooks()
      .then((res) => setBooks(res.data))
      .catch((err) => alert("Failed to load books."));
  }, []);

  return (
    <div className="Books">
      <header className="Books-header">
        <h1>View Books</h1>
        {books.length === 0 ? (
          <p>No books found.</p>
        ) : (
          <table className="book-table">
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
              {books.map((book) => (
                <tr key={book.id}>
                  <td>{book.id}</td>
                  <td>{book.title}</td>
                  <td>{book.author}</td>
                  <td>{book.category}</td>
                  <td>{book.status === "A" ? "Active" : "Inactive"}</td>
                  <td>{book.availability === "A" ? "Available" : "Issued"}</td>
                  <td>
                    <button
                      className="main-button"
                      onClick={() =>
                        navigate(`/books/update-book/${book.id}`)
                      }
                    >
                      Update
                    </button>
                    <button
                      className="main-button"
                      onClick={() =>
                        navigate(`/books/update-availability/${book.id}`)
                      }
                    >
                      Availability
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
        <button className="back-button" onClick={() => navigate("/books")}>
          ← Back
        </button>
      </header>
    </div>
  );
}

export default ViewBooks;
