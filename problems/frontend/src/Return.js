import React, { useState } from 'react';
import './App.css';

function Return() {
  const [formData, setFormData] = useState({
    bookId: '',
    memberId: '',
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


    fetch(`http://localhost:8070/Book/returnissue?bookid=${formData.bookId}&memberid=${formData.memberId}`, {
      method: 'POST',
    })
      .then((response) => response.text())
      .then((data) => setMessage(data))
      .catch((error) => setMessage(error));
  };

  return (
    <div className="Container">
      <h2>Return Book</h2>
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
          Member ID:
          <input
            type="number"
            name="memberId"
            value={formData.memberId}
            onChange={handleChange}
            required
          />
        </label>

        <button type="submit">Return Book</button>
      </form>

      {message!=null && <p>{message}</p>}
    </div>
  );
}

export default Return;
