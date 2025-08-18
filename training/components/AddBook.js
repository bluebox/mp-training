import React, { useState } from "react";
import axios from "axios";

const AddBook = () => {
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
      await axios.post("http://localhost:8080/books", book);
      alert("Book added successfully!");
      setBook({
        title: "",
        author: "",
        category: "",
        status: "ACTIVE",
        availability: "AVAILABLE",
      });
    } catch (error) {
      console.error("Error adding book:", error);
      alert("Failed to add book");
    }
  };

  return (
    <div>
      <h2>Add Book</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="title"
          placeholder="Title"
          value={book.title}
          onChange={handleChange}
          required
        />
        <br />
        <input
          type="text"
          name="author"
          placeholder="Author"
          value={book.author}
          onChange={handleChange}
          required
        />
        <br />
        <input
          type="text"
          name="category"
          placeholder="Category"
          value={book.category}
          onChange={handleChange}
          required
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
        <button type="submit">Add Book</button>
      </form>
    </div>
  );
};

export default AddBook;
