import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { getBooks } from "../services/booksService"; 
import "./HomePage.css";

export default function BooksPage() {
  const navigate = useNavigate();
  const [books, setBooks] = useState([]);
  const [showTable, setShowTable] = useState(false);

  const statusMap = { A: "Active", I: "Inactive" };
  const availabilityMap = { A: "Available", I: "Issued" };

  const handleViewBooks = async () => {
    try {
      const response = await getBooks(); 
      setBooks(response.data);
      setShowTable(true);
    } catch (error) {
      console.error(error);
      alert("Failed to fetch books");
    }
  };

  return (
    <div className="content">
      <h2>Books</h2>

      <div className="button-group">
        <button onClick={() => navigate("/books/add")}>Add Book</button>
        <button onClick={handleViewBooks}>View All Books</button>
      </div>

      {showTable && books.length > 0 && (
        <table border="1" style={{ marginTop: "20px", width: "100%" }}>
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
            {books.map((book) => {
              const statusCode =
                book.status?.type?.substring(0, 1) || book.status || "";
              const availabilityCode =
                book.availability?.type?.substring(0, 1) || book.availability || "";

              return (
                <tr key={book.bookid}>
                  <td>{book.bookid}</td>
                  <td>{book.title}</td>
                  <td>{book.author}</td>
                  <td>{book.category}</td>
                  <td>{statusMap[statusCode] || statusCode}</td>
                  <td>{availabilityMap[availabilityCode] || availabilityCode}</td>
                  <td>
                    <button
                      onClick={() =>
                        navigate(`/books/update/${book.bookid}`)
                      }
                    >
                      Update
                    </button>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
      )}

      {showTable && books.length === 0 && <p>No books available.</p>}

      <div style={{ marginTop: "40px" }}>
        <button onClick={() => navigate("/")}>Back</button>
      </div>
    </div>
  );
}
