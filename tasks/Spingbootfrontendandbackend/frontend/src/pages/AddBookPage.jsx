import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { addBook } from "../services/booksService";
import "./HomePage.css";

export default function AddBookPage() {
  const navigate = useNavigate();

  const [book, setBook] = useState({
    title: "",
    author: "",
    category: "",
    status: "ACTIVE",
    availability: "AVAILABLE",
  });

  const handleChange = (e) => {
    setBook({ ...book, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!book.title || !book.author || !book.category) {
      alert("Please fill all fields");
      return;
    }

    try {
      await addBook(book); 
      alert("Book added successfully!");
      navigate("/books");
    } catch (error) {
      console.error(error);
      alert("Failed to add book");
    }
  };

  return (
    <div className="content">
      <h2>Add Book</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="title"
          placeholder="Title"
          value={book.title}
          onChange={handleChange}
        />
        <input
          type="text"
          name="author"
          placeholder="Author"
          value={book.author}
          onChange={handleChange}
        />
        <input
          type="text"
          name="category"
          placeholder="Category"
          value={book.category}
          onChange={handleChange}
        />

        <label>Status:</label>
        <select name="status" value={book.status} onChange={handleChange}>
          <option value="ACTIVE">Active</option>
          <option value="INACTIVE">Inactive</option>
        </select>

        <label>Availability:</label>
        <select
          name="availability"
          value={book.availability}
          onChange={handleChange}
        >
          <option value="AVAILABLE">Available</option>
          <option value="ISSUED">Issued</option>
        </select>

        <button type="submit">Add Book</button>
      </form>

      <div style={{ marginTop: "20px" }}>
        <button onClick={() => navigate("/books")}>Back</button>
      </div>
    </div>
  );
}
