import { useState, useEffect } from "react";
import axios from "axios";

export default function AddBook() {
  const [title, setTitle] = useState("");
  const [author, setAuthor] = useState("");
  const [category, setCategory] = useState("");
  const [categories, setCategories] = useState([]);
  const [message, setMessage] = useState("");
  const [error, setError] = useState("");

  // Load categories
  useEffect(() => {
    axios.get("http://localhost:8080/api/books/categories")
      .then(res => setCategories(res.data))
      .catch(() => setError("Failed to load categories"));
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage("");
    setError("");

    try {
      const response = await axios.post("http://localhost:8080/api/books", {
        bookTitle: title,
        bookAuthor: author,
        bookCategory: category
      });

      setMessage("✅ Book added successfully!");
      setTitle("");
      setAuthor("");
      setCategory("");

    } catch (err) {
      setError(err.response?.data?.message || err.response?.data || "❌ Failed to add book");
    }
  };

  return (
    <div className="container my-5">
      <div className="card shadow">
        <div className="card-body">
          <h2 className="card-title text-center mb-4">Add New Book</h2>

          {message && <div className="alert alert-success">{message}</div>}
          {error && <div className="alert alert-danger">{error}</div>}

          <form onSubmit={handleSubmit}>
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

            <div className="d-flex gap-2">
              <button type="submit" className="btn btn-primary">
                Add Book
              </button>
              <button
                type="reset"
                onClick={() => { setTitle(""); setAuthor(""); setCategory(""); }}
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
