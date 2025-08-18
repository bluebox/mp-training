import React, { useState } from "react";
import axios from "axios";
import AddBook from "./AddBook";

const UpdateBook = () => {
  const [bookId, setBookId] = useState("");
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
    try {
      await axios.put(`http://localhost:8080/books/${bookId}`, book);
      alert("Book updated successfully!");
    } catch (error) {
      console.error("Error updating book:", error);
      alert("Failed to update book");
    }
  };

  return (
    <div>
      <h2>Update Book</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="number"
          placeholder="Book ID"
          value={bookId}
          onChange={(e) => setBookId(e.target.value)}
          required
        />
        <br />
        <input
          type="text"
          name="title"
          placeholder="Title"
          value={book.title}
          onChange={handleChange}
        />
        <br />
        <input
          type="text"
          name="author"
          placeholder="Author"
          value={book.author}
          onChange={handleChange}
        />
        <br />
        <input
          type="text"
          name="category"
          placeholder="Category"
          value={book.category}
          onChange={handleChange}
        />
        <br />
        <select name="status" value={book.status} onChange={handleChange}>
          <option value="ACTIVE">ACTIVE</option>
          <option value="INACTIVE">INACTIVE</option>
        </select>
        <br />
        <select
          name="availability"
          value={book.availability}
          onChange={handleChange}
        >
          <option value="AVAILABLE">AVAILABLE</option>
          <option value="ISSUED">ISSUED</option>
        </select>
        <br />
        <button type="submit">Update Book</button>
      </form>
    </div>
  );
};

export default UpdateBook;
