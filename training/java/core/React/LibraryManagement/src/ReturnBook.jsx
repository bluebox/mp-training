import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

export default function ReturnBook() {
  const [bookId, setBookId] = useState("");
  const [books, setBooks] = useState([]);
  const navigate = useNavigate();

  const fetchBooks = async () => {
    try {
      const res = await axios.get("http://localhost:8080/issueReturn/issued");
      setBooks(res.data);
    } catch (error) {
      console.error("Error fetching issued books:", error);
      alert("Failed to fetch issued books.");
    }
  };

  useEffect(() => {
    fetchBooks();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!bookId) {
      alert("Please select a book!");
      return;
    }
    try {
      await axios.post("http://localhost:8080/issueReturn/return", {
        bookId: bookId,
      });
      alert("Book returned successfully!");
      setBookId("");
      fetchBooks();
    } catch (error) {
      console.error("Error returning book:", error);
      alert("Failed to return book.");
    }
  };

  return (
    <div
      className="d-flex align-items-center justify-content-center"
      style={{
        minHeight: "100vh",
        backgroundImage: "url('/library.jpg')",
        backgroundSize: "cover",
        backgroundPosition: "center",
        backgroundAttachment: "fixed",
      }}
    >
      <div
        className="bg-white p-5 rounded shadow-lg"
        style={{ width: "450px", maxWidth: "90%" }}
      >
        <h3 className="fw-bold text-center mb-4 ">Return Book</h3>
        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <label className="form-label fw-semibold">Select Issued Book:</label>
            <select
              className="form-select form-select-lg"
              value={bookId}
              onChange={(e) => setBookId(e.target.value)}
              required
            >
              <option value="">Choose a Book</option>
              {books.map((b) => (
                <option key={b.bookId} value={b.bookId}>
                  {b.bookId} - {b.title}
                </option>
              ))}
            </select>
          </div>
          <div className="d-flex gap-3">
            <button type="submit" className="btn btn-success flex-fill">
              Return
            </button>
            <button
              type="button"
              className="btn btn-danger flex-fill"
              onClick={() => navigate("/issue-return")}
            >
              Back
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
