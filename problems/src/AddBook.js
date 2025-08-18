import React, { useState } from 'react';
import './App.css';

function AddBook() {
  const [formData, setFormData] = useState({
    bookId:0,
    title: '',
    author: '',
    category: '',
    status: 'Select Below',
    availability: 'Select Below',
  });

  const [message,setMessage]=useState("");

   const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

 const handleSubmit = (e) => {
    e.preventDefault();
    fetch('http://localhost:8070/Book/addBook', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formData),
    })
    .then(response=>response.text())
    .then(data=>setMessage(data))
     .catch((error) => setMessage(error));
 };

  return (
   <div className="Container">
      <h2>Add New Book</h2>
      <form onSubmit={handleSubmit} className="Container">
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
          <option >--Select Below--</option>
            <option value="ACTIVE">Active</option>
            <option value="INACTIVE">Inactive</option>
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
          <option >--Select Below--</option>
            <option value="AVAILABLE">Available</option>
            <option value="ISSUED">Issued</option>
          </select>
        </label>

        <button type="submit">Add Book</button>
      </form>

      {message && <p>{message}</p>}
    </div>
  );
}

export default AddBook;
