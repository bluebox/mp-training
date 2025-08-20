import { useState, useEffect } from "react";
import axios from "axios";

export default function UpdateBook() {
  const [bookId, setBookId] = useState("");
  const [title, setTitle] = useState("");
  const [author, setAuthor] = useState("");
  const [category, setCategory] = useState("");
  const [status, setStatus] = useState(""); 
  const [availability, setAvailability] = useState("");
  const [categories, setCategories] = useState([]);
  const [message, setMessage] = useState("");
  const [error, setError] = useState("");

  // Load categories
  useEffect(() => {
    axios.get("http://localhost:8080/api/books/categories")
      .then(res => setCategories(res.data))
      .catch(() => setError("Failed to load categories"));
  }, []);

  const validateInputs = () => {
    if (!bookId.trim()) {
      setError("❌ Book ID is required.");
      return false;
    }
    if (title.trim().length < 3) {
      setError("❌ Title must be at least 3 characters long.");
      return false;
    }
    if (!/^[A-Za-z\s]+$/.test(author) || author.trim().length < 3) {
      setError("❌ Author name must contain only letters & spaces (min 3 chars).");
      return false;
    }
    if (!category) {
      setError("❌ Please select a category.");
      return false;
    }
    return true;
  };

  // Fetch book details by ID to prefill form
  const fetchBookDetails = async () => {
    setMessage("");
    setError("");
    if (!bookId.trim()) {
      setError("❌ Enter Book ID to fetch details.");
      return;
    }

    try {
      const res = await axios.get(`http://localhost:8080/api/books/${bookId}`);
      setTitle(res.data.bookTitle || "");
      setAuthor(res.data.bookAuthor || "");
      setCategory(res.data.bookCategory || "");
      setStatus(res.data.status || "A");
      setAvailability(res.data.availability || "A");
      setMessage("✅ Book details loaded.");
    } catch (err) {
      setError("❌ " + (err.response?.data || "Book not found"));
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage("");
    setError("");

    if (!validateInputs()) return;

    try {
      await axios.put(`http://localhost:8080/api/books/${bookId}`, {
        bookId: bookId.trim(),
        bookTitle: title.trim(),
        bookAuthor: author.trim(),
        bookCategory: category,
        status,
        availability
      });

      setMessage("✅ Book updated successfully!");
    } catch (err) {
      if (err.response) {
        setError("❌ " + err.response.data);
      } else {
        setError("❌ Something went wrong!");
      }
    }
  };

  return (
    <div className="container my-5">
      <div className="card shadow">
        <div className="card-body">
          <h2 className="card-title text-center mb-4">Update Book</h2>

          {message && <div className="alert alert-success">{message}</div>}
          {error && <div className="alert alert-danger">{error}</div>}

          <form onSubmit={handleSubmit}>
            {/* Book ID */}
            <div className="form-group mb-3">
              <label>Book ID:</label>
              <div className="d-flex gap-2">
                <input
                  type="text"
                  value={bookId}
                  onChange={e => setBookId(e.target.value)}
                  required
                  className="form-control"
                />
                <button
                  type="button"
                  onClick={fetchBookDetails}
                  className="btn btn-info"
                >
                  Load
                </button>
              </div>
            </div>

            {/* Title */}
            <div className="form-group mb-3">
              <label>Title:</label>
              <input
                type="text"
                value={title}
                onChange={e => setTitle(e.target.value)}
                required
                className="form-control"
              />
            </div>

            {/* Author */}
            <div className="form-group mb-3">
              <label>Author:</label>
              <input
                type="text"
                value={author}
                onChange={e => setAuthor(e.target.value)}
                required
                className="form-control"
              />
            </div>

            {/* Category */}
            <div className="form-group mb-4">
              <label>Category:</label>
              <select
                value={category}
                onChange={e => setCategory(e.target.value)}
                required
                className="form-control"
              >
                <option value="">--Select Category--</option>
                {categories.map(cat => (
                  <option key={cat} value={cat}>{cat}</option>
                ))}
              </select>
            </div>

            {/* Status */}
            <div className="form-group mb-3">
              <label>Status:</label>
              <div>
                <label className="me-3">
                  <input
                    type="radio"
                    value="A"
                    checked={status === "A"}
                    onChange={e => setStatus(e.target.value)}
                  /> Active
                </label>
                <label>
                  <input
                    type="radio"
                    value="I"
                    checked={status === "I"}
                    onChange={e => setStatus(e.target.value)}
                  /> Inactive
                </label>
              </div>
            </div>

            {/* Availability */}
            <div className="form-group mb-4">
              <label>Availability:</label>
              <div>
                <label className="me-3">
                  <input
                    type="radio"
                    value="A"
                    checked={availability === "A"}
                    onChange={e => setAvailability(e.target.value)}
                  /> Available
                </label>
                <label>
                  <input
                    type="radio"
                    value="U"
                    checked={availability === "U"}
                    onChange={e => setAvailability(e.target.value)}
                  /> Unavailable
                </label>
              </div>
            </div>

            {/* Buttons */}
            <div className="d-flex gap-2">
              <button type="submit" className="btn btn-success">
                Update Book
              </button>
              <button
                type="reset"
                onClick={() => { 
                  setBookId(""); 
                  setTitle(""); 
                  setAuthor(""); 
                  setCategory(""); 
                  setStatus("A"); 
                  setAvailability("A"); 
                }}
                className="btn btn-secondary"
              >
                Clear
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
