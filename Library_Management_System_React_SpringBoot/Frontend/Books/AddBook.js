import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { addBook, getAllBooks } from "../../api/bookService";
import "../../App.css";

function AddBook({ books, setBooks }) {
  const navigate = useNavigate();
  const [book, setBook] = useState({
    title: "",
    author: "",
    category: "",
    status: "A",
    availability: "A",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setBook({ ...book, [name]: value });
  };

  const handleSubmit = async (e) => {
  e.preventDefault();
  try {
    await addBook(book);
    alert("Book added successfully!");

    try {
      const response = await getAllBooks();
      setBooks(response.data);
    } catch (fetchErr) {
      console.warn("Book added, but failed to refresh list:", fetchErr);
      
    }

    navigate("/books/viewbooks");
  } catch (err) {
    console.error(err);
    alert("Failed to add book.");
  }
};


  const goBack = () => navigate("/books");

  return (
    <div className="Books">
      <header className="Books-header">
        <h1>Add Book</h1>
        <form className="form-container" onSubmit={handleSubmit}>
          <input type="text" name="title" placeholder="Title" value={book.title} onChange={handleChange} required />
          <input type="text" name="author" placeholder="Author" value={book.author} onChange={handleChange} required />
          <input type="text" name="category" placeholder="Category" value={book.category} onChange={handleChange} required />
          
          <select name="status" value={book.status} onChange={handleChange} required>
            <option value="A">Active</option>
            <option value="I">Inactive</option>
          </select>

          <select name="availability" value={book.availability} onChange={handleChange} required>
            <option value="A">Available</option>
            <option value="I">Issued</option>
          </select>

          <div className="form-buttons">
            <button type="submit" className="main-button">Add Book</button>
            <button type="button" className="back-button" onClick={goBack}>← Back</button>
          </div>
        </form>
      </header>
    </div>
  );
}

export default AddBook;
