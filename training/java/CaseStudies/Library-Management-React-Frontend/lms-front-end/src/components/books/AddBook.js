import React, { useState } from 'react';
import './AddBook.css';

const AddBook = () => {
  const [bookData, setBookData] = useState({
    book_Title: '',
    book_Author: '',
    book_Category: '',
    book_Status: 'A',
    book_Avaliability: 'A'
  });
  const [isLoading, setIsLoading] = useState(false);
  const [message, setMessage] = useState({ type: '', text: '' });

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
    setMessage({ type: '', text: '' });


    try {
      const response = await fetch('http://localhost:8080/addbook', {
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

      
      setMessage({ type: 'success', text: 'Book added successfully!' });
      setBookData({ 
        book_Title: '', 
        book_Author: '', 
        book_Category: '',
        book_Status: 'A',
        book_Avaliability: 'A'
      });
    } catch (error) {
      console.log(error);
      setMessage({ type: 'error', text: error.message });
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="book-form-container">
      <h2>Add New Book</h2>
      {message.text && (
        <div className={`message ${message.type}`}>
          {message.text}
        </div>
      )}
      <form onSubmit={handleSubmit} className="book-form">
        <div className="form-group">
          <label htmlFor="book_Title">Enter book title</label>
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
          <label htmlFor="book_Author">Enter book's Author</label>
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
          <label htmlFor="book_Category">Select book category</label>
          <select
            id="book_Category"
            name="book_Category"
            value={bookData.book_Category}
            onChange={handleChange}
            required
          >
            <option value="" disabled>Choose a category</option>
            <option value="Fiction">Fiction</option>
            <option value="Non-Fiction">Non-Fiction</option>
            <option value="Science">Science</option>
            <option value="Technology">Technology</option>
            <option value="History">History</option>
            <option value="Biography">Biography</option>
          </select>
        </div>

        <button type="submit" disabled={isLoading}>
          {isLoading ? 'Adding Book...' : 'Add Book'}
        </button>
      </form>
    </div>
  );
};

export default AddBook;
