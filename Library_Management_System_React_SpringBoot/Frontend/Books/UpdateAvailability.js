import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { updateAvailability, getAllBooks } from "../../api/bookService";
import "./Books.css";

function UpdateAvailability() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [book, setBook] = useState(null);

  useEffect(() => {
    getAllBooks()
      .then((res) => {
        const currentBook = res.data.find((b) => b.id === parseInt(id));
        if (currentBook) setBook(currentBook);
      })
      .catch(() => alert("Failed to load book."));
  }, [id]);

  if (!book) return <p>Loading book...</p>;

  const handleChange = (e) => {
    setBook({ ...book, availability: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    updateAvailability(book.id, book.availability)
      .then(() => {
        alert("Availability updated!");
        navigate("/books/viewbooks");
      })
      .catch(() => alert("Failed to update availability."));
  };

  return (
    <div className="Books">
      <header className="Books-header">
        <h1>Update Availability</h1>
        <form className="form-container" onSubmit={handleSubmit}>
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

export default UpdateAvailability;
