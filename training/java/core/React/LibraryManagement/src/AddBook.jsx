import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

export default function AddBook() {
  const [title, setTitle] = useState("");
  const [author, setAuthor] = useState("");
  const [category, setCategory] = useState("");
  const [errors, setErrors] = useState({});
  const [successMessage, setSuccessMessage] = useState("");

  const navigate = useNavigate();

  const validateInputs = () => {
    const nameRegex = /^[A-Za-z\s]+$/;
    const newErrors = {};

    if (!title.trim()) {
      newErrors.title = "Book title cannot be empty.";
    } else if (!nameRegex.test(title)) {
      newErrors.title = "Book title must contain only letters and spaces.";
    }

    if (!author.trim()) {
      newErrors.author = "Author name cannot be empty.";
    } else if (!nameRegex.test(author)) {
      newErrors.author = "Author name must contain only letters and spaces.";
    }

    if (!category) {
      newErrors.category = "Please select a category.";
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!validateInputs()) return;

    const newBook = { title, author, category };

    try {
      const response = await axios.post("http://localhost:8080/books", newBook);
      const savedBook = response.data;

      if (savedBook.bookId) {
        setSuccessMessage(`Book added successfully! ID: ${savedBook.bookId}`);
        setTitle("");
        setAuthor("");
        setCategory("");
        setErrors({});
        setTimeout(() => navigate("/books"), 1500);
      }
    } catch (error) {
      console.error("Error adding book:", error);
      setErrors({ form: "Failed to add book. Please try again." });
    }
  };

  return (
    <div
      className="d-flex align-items-center justify-content-center vh-100"
      style={{
        backgroundImage: "url('/library.jpg')",
        backgroundSize: "cover",
        backgroundPosition: "center",
      }}
    >
      <div className="bg-white p-5 rounded shadow" style={{ width: "400px" }}>
        <h3 className="fw-bold text-center mb-4">Add a Book</h3>

        {errors.form && <div className="text-danger text-center mb-3">{errors.form}</div>}
        {successMessage && <div className="text-success text-center mb-3">{successMessage}</div>}

        <form onSubmit={handleSubmit}>
          <div className="mb-3 text-start">
            <label className="form-label">Enter Book Title:</label>
            <input
              type="text"
              className={`form-control ${errors.title ? "is-invalid" : ""}`}
              value={title}
              onChange={(e) => setTitle(e.target.value)}
            />
            {errors.title && <div className="text-danger small">{errors.title}</div>}
          </div>
          <div className="mb-3 text-start">
            <label className="form-label">Enter Book Author:</label>
            <input
              type="text"
              className={`form-control ${errors.author ? "is-invalid" : ""}`}
              value={author}
              onChange={(e) => setAuthor(e.target.value)}
            />
            {errors.author && <div className="text-danger small">{errors.author}</div>}
          </div>
          <div className="mb-4 text-start">
            <label className="form-label">Select the Category:</label>
            <select
              className={`form-select ${errors.category ? "is-invalid" : ""}`}
              value={category}
              onChange={(e) => setCategory(e.target.value)}
            >
              <option value="">Category</option>
              <option value="Fiction">Fiction</option>
              <option value="Mystery">Mystery</option>
              <option value="Story">Story</option>
              <option value="Thriller">Thriller</option>
              <option value="Adventure">Adventure</option>
            </select>
            {errors.category && <div className="text-danger small">{errors.category}</div>}
          </div>
          <button type="submit" className="btn btn-success w-100 mb-3">
            Add
          </button>
          <Link to="/books" className="btn btn-danger w-100">
            Back to Dashboard
          </Link>
        </form>
      </div>
    </div>
  );
}
