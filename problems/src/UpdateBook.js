import React, { useState } from 'react';
import './App.css';

function UpdateBook() {
  const [formData, setFormData] = useState({
    bookId: 0,
    title: '',
    author: '',
    category: '',
    status: '',
    availability: '',
  });

  const [message, setMessage] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    fetch('http://localhost:8070/Book/updateBook', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formData),
    })
      .then((response) => response.text())
      .then((data) => setMessage(data))
      .catch((error) => setMessage(error));
  };

  return (
    <div className="Container">
      <h2>Update Book Details</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Book ID:
          <input
            type="number"
            name="bookId"
            value={formData.bookId}
            onChange={handleChange}
            required
          />
        </label>

        <label>
          Title:
          <input
            type="text"
            name="title"
            value={formData.title}
            onChange={handleChange}
            required
          />
        </label>

        <label>
          Author:
          <input
            type="text"
            name="author"
            value={formData.author}
            onChange={handleChange}
            required
          />
        </label>

        <label>
          Category:
          <input
            type="text"
            name="category"
            value={formData.category}
            onChange={handleChange}
            required
          />
        </label>

        <label>
          Status:
          <select
            name="status"
            value={formData.status}
            onChange={handleChange}
            required
          >
            <option value="">--Select Status--</option>
            <option value="Active">Active</option>
            <option value="Inactive">Inactive</option>
          </select>
        </label>

        <label>
          Availability:
          <select
            name="availability"
            value={formData.availability}
            onChange={handleChange}
            required
          >
            <option value="">--Select Availability--</option>
            <option value="Available">Available</option>
            <option value="Issued">Issued</option>
          </select>
        </label>

        <button type="submit">Update Book</button>
      </form>

      {message && <p>{message}</p>}
    </div>
  );
}

export default UpdateBook;
