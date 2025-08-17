import React, { useState } from "react";
import axios from "axios";

export default function ReturnBook() {
  const [mobile, setMobile] = useState("");
  const [memberName, setMemberName] = useState("");
  const [books, setBooks] = useState([]);
  const [selectedBook, setSelectedBook] = useState("");

  const fetchIssuedBooks = async () => {
    if (!mobile) {
      alert("Please enter a mobile number");
      return;
    }
    try {
      const response = await axios.get(`http://localhost:8080/api/return-books/${mobile}`);
      setMemberName(response.data.memberName);
      setBooks(response.data.books || []);
    } catch {
      alert("Could not fetch issued books. Please check mobile number.");
    }
  };

  const handleReturnBook = async () => {
    if (!selectedBook) {
      alert("Please select a book to return");
      return;
    }
    try {
      await axios.post("http://localhost:8080/api/return-books", null, {
        params: { mobile, bookName: selectedBook, status: "active" },
      });
      alert("Book returned successfully!");
      fetchIssuedBooks();
    } catch {
      alert("Failed to return book.");
    }
  };

  return (
    <div className="container mt-4">
      <div className="card shadow p-4">
        <h2 className="card-title mb-3">Return Book</h2>

        <div className="mb-3">
          <label className="form-label">Mobile Number:</label>
          <input
            type="text"
            value={mobile}
            onChange={(e) => setMobile(e.target.value)}
            className="form-control"
            placeholder="Enter Mobile Number"
          />
          <button onClick={fetchIssuedBooks} className="btn btn-primary mt-2">Fetch Issued Books</button>
        </div>

        {memberName && (
          <p><strong>Member Name:</strong> {memberName}</p>
        )}

        {books.length > 0 && (
          <div className="mb-3">
            <label className="form-label">Select Book:</label>
            <select
              value={selectedBook}
              onChange={(e) => setSelectedBook(e.target.value)}
              className="form-select"
            >
              <option value="">Select Book</option>
              {books.map((book, index) => (
                <option key={index} value={book}>{book}</option>
              ))}
            </select>
          </div>
        )}

        {books.length > 0 && (
          <button onClick={handleReturnBook} className="btn btn-success">Return Book</button>
        )}
      </div>
    </div>
  );
}
