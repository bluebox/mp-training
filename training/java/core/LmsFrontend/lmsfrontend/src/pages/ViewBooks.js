import React, { useEffect, useState } from "react";
export default function ViewBooks() {
  const [books, setBooks] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    fetch("http://localhost:8080/api/books")
      .then((res) => {
        if (!res.ok) throw new Error("Failed to fetch books");
        return res.json();
      })
      .then((data) => setBooks(data))
      .catch((err) => setError(err.message));
  }, []);

  return (
    <div className="container mt-4">
      <h2 className="mb-3">All Books</h2>

      {error && <div className="alert alert-danger">{error}</div>}

      {books.length > 0 ? (
        <div className="table-responsive">
          <table className="table table-bordered table-striped">
            <thead className="table-light">
              <tr>
                <th>Book ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>Category</th>
                <th>Status</th>
                <th>Availability</th>
              </tr>
            </thead>
            <tbody>
              {books.map((book) => (
                <tr key={book.bookId}>
                  <td>{book.bookId}</td>
                  <td>{book.bookTitle}</td>
                  <td>{book.bookAuthor}</td>
                  <td>{book.bookCategory}</td>
                  <td>{book.status}</td>
                  <td>{book.availability}</td>
                </tr>
              ))}
            </tbody>
          </table>
         
        </div>
      ) : (
        !error && <p>No books found.</p>
      )}
    </div>
  );
}
