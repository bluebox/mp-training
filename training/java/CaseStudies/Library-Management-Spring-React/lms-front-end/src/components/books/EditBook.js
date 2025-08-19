import React, { useState } from 'react';
import './EditBook.css';

const EditBook = ({ book, onClose, onUpdate }) => {
  const [bookData, setBookData] = useState({
    book_Id: book.book_Id,
    book_Title: book.book_Title,
    book_Author: book.book_Author,
    book_Category: book.book_Category,
    book_Status: book.book_Status,
    book_Availability: book.book_Availability
  });
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setBookData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    setError(null);

    try {
      const response = await fetch('http://localhost:8080/updatebook', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(bookData)
      });
      const data = await response.json();
      if (!response.ok) {
        throw new Error(data.message);
      }

      const updatedBook = await response.json();
      onUpdate(updatedBook);
      onClose();
    } catch (err) {
      setError(err.message);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="edit-book-overlay">
      <div className="edit-book-modal">
        <h2>Edit Book</h2>
        {error && <div className="error-message">{error}</div>}
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="book_Title">Book Title</label>
            <input
              type="text"
              id="book_Title"
              name="book_Title"
              value={bookData.book_Title}
              onChange={handleChange}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="book_Author">Author</label>
            <input
              type="text"
              id="book_Author"
              name="book_Author"
              value={bookData.book_Author}
              onChange={handleChange}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="book_Category">Category</label>
            <select
              id="book_Category"
              name="book_Category"
              value={bookData.book_Category}
              onChange={handleChange}
              required
            >
              <option value="Fiction">Fiction</option>
              <option value="Non-Fiction">Non-Fiction</option>
              <option value="Science">Science</option>
              <option value="Technology">Technology</option>
              <option value="History">History</option>
              <option value="Biography">Biography</option>
            </select>
          </div>

          <div className="form-group">
            <label htmlFor="book_Status">Status</label>
            <select
              id="book_Status"
              name="book_Status"
              value={bookData.book_Status}
              onChange={handleChange}
              required
            >
              <option value="I">In-Active</option>
              <option value="A">Active</option>
            </select>
          </div>

          <div className="button-group">
            <button type="submit" disabled={isLoading}>
              {isLoading ? 'Updating...' : 'Update Book'}
            </button>
            <button type="button" onClick={onClose}>Cancel</button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default EditBook;