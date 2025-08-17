import React, { useState } from "react";
import axios from "axios";

export default function IssueBook() {
  const [mobile, setMobile] = useState("");
  const [member, setMember] = useState(null);
  const [categories, setCategories] = useState([]);
  const [category, setCategory] = useState("");
  const [books, setBooks] = useState([]);
  const [selectedBook, setSelectedBook] = useState("");
  const [dueDate, setDueDate] = useState("");
  const [message, setMessage] = useState("");
  const [error, setError] = useState("");

  const fetchMember = async () => {
    try {
      const res = await axios.get(`/api/members/mobile/${mobile}`);
      setMember(res.data);
      setError("");
      setMessage("");

      const catRes = await axios.get(`/api/issue-books/categories`);
      setCategories(catRes.data);
    } catch {
      setError("Member not found");
      setMember(null);
      setCategories([]);
      setBooks([]);
    }
  };

  const fetchBooks = async () => {
    try {
      const res = await axios.get(`/api/issue-books/books/${category}`);
      setBooks(res.data);
      setError("");
    } catch {
      setError("No books found for this category");
      setBooks([]);
    }
  };

  const issueBook = async () => {
    try {
      await axios.post(`/api/issue-books`, null, {
        params: { mobile: member.mobile, category, bookTitle: selectedBook, dueDate },
      });
      setMessage("✅ Book issued successfully!");
      setError("");
    } catch {
      setError("❌ Failed to issue book");
    }
  };

  return (
    <div className="container mt-4">
      <div className="card shadow p-4">
        <h2 className="card-title mb-3">Issue a Book</h2>

        {message && <div className="alert alert-success">{message}</div>}
        {error && <div className="alert alert-danger">{error}</div>}

        <div className="mb-3">
          <label className="form-label">Mobile:</label>
          <input
            type="text"
            value={mobile}
            onChange={(e) => setMobile(e.target.value)}
            className="form-control"
          />
          <button onClick={fetchMember} className="btn btn-primary mt-2">Fetch Member</button>
        </div>

        {member && <p><strong>Member:</strong> {member.name}</p>}

        {categories.length > 0 && (
          <div className="mb-3">
            <label className="form-label">Category:</label>
            <select
              value={category}
              onChange={(e) => setCategory(e.target.value)}
              className="form-select"
            >
              <option value="">-- Select Category --</option>
              {categories.map((cat) => <option key={cat}>{cat}</option>)}
            </select>
            <button onClick={fetchBooks} className="btn btn-secondary mt-2">Search Books</button>
          </div>
        )}

        {books.length > 0 && (
          <div className="mb-3">
            <label className="form-label">Select Book:</label>
            <select
              value={selectedBook}
              onChange={(e) => setSelectedBook(e.target.value)}
              className="form-select"
            >
              <option value="">-- Select Book --</option>
              {books.map((book, idx) => <option key={idx}>{book}</option>)}
            </select>
          </div>
        )}

        {member && (
          <div className="mb-3">
            <label className="form-label">Due Date:</label>
            <input
              type="date"
              value={dueDate}
              onChange={(e) => setDueDate(e.target.value)}
              className="form-control"
              min={new Date().toISOString().split("T")[0]}
            />
          </div>
        )}

        <button
          onClick={issueBook}
          disabled={!selectedBook || !dueDate}
          className="btn btn-success"
        >
          Issue Book
        </button>
      </div>
    </div>
  );
}
