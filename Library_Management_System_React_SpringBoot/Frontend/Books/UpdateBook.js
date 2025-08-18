import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { updateBook, getAllBooks } from "../../api/bookService";
import "./Books.css";

function UpdateBook() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [book, setBook] = useState(null);

  useEffect(() => {
    getAllBooks()
      .then((res) => {
        const currentBook = res.data.find((b) => b.id === parseInt(id));
        if (currentBook) setBook(currentBook);
      })
      .catch((err) => alert("Failed to load book."));
  }, [id]);

  if (!book) return <p>Loading book...</p>;

  const handleChange = (e) => {
    const { name, value } = e.target;
    setBook({ ...book, [name]: value });
  };

  const handleSubmit = async (e) => {
  e.preventDefault();
  try {
    await updateBook(book.id, {
      ...book,
      status: book.status.toUpperCase(),        // must be 'A' or 'I'
      availability: book.availability.toUpperCase(),
    });
    alert("Book updated successfully!");
    navigate("/books/viewbooks");
  } catch (err) {
    console.error(err);
    alert("Failed to update book.");
  }
};


  return (
    <div className="Books">
      <header className="Books-header">
        <h1>Update Book</h1>
        <form className="form-container" onSubmit={handleSubmit}>
          <input
            type="text"
            name="title"
            value={book.title}
            onChange={handleChange}
            required
          />
          <input
            type="text"
            name="author"
            value={book.author}
            onChange={handleChange}
            required
          />
          <input
            type="text"
            name="category"
            value={book.category}
            onChange={handleChange}
            required
          />
          <select name="status" value={book.status} onChange={handleChange}>
            <option value="A">Active</option>
            <option value="I">Inactive</option>
          </select>
          <select
            name="availability"
            value={book.availability}
            onChange={handleChange}
          >
            <option value="A">Available</option>
            <option value="I">Issued</option>
          </select>

          <div className="form-buttons">
            <button type="submit" className="main-button">
              Save
            </button>
            <button
              type="button"
              className="back-button"
              onClick={() => navigate("/books/viewbooks")}
            >
              Cancel
            </button>
          </div>
        </form>
      </header>
    </div>
  );
}

export default UpdateBook;
