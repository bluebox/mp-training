import React, { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';

function AddBookForm() {
  const location = useLocation();
  const editData = location.state?.book;
  const isEdit = location.state?.isEdit;

  const [book, setBook] = useState({
    title: '',
    author: '',
    category: '',
    cost: '',
    quantity: '',
    published: ''
  });

  useEffect(() => {
    if (isEdit && editData) {
      setBook({
        title: editData.title,
        author: editData.author,
        category: editData.categroy,
        cost: editData.cost,
        quantity: editData.quantity,
        published: editData.published
      });
    }
  }, [editData, isEdit]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setBook((prev) => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const payload = {
      ...book,
      cost: parseFloat(book.cost),
      quantity: parseInt(book.quantity),
      published: book.published
    };

    const url = isEdit
      ? `http://localhost:8080/books/${editData.bookId}`
      : 'http://localhost:8080/books/add';

    const method = isEdit ? 'PUT' : 'POST';

    try {
      const response = await fetch(url, {
        method,
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
      });

      if (response.ok) {
        alert(isEdit ? 'Book updated successfully!' : 'Book added successfully!');
        setBook({
          title: '',
          author: '',
          category: '',
          cost: '',
          quantity: '',
          published: ''
        });
      } else {
        const errorData = await response.json();
        alert(`Failed: ${errorData.message || 'Unknown error'}`);
      }
    } catch (error) {
      console.error('Error:', error);
      alert('Error saving book.');
    }
  };

  return (
    <div className="form-container">
      <h2>{isEdit ? 'Edit Book' : 'Add Book'}</h2>
      <form onSubmit={handleSubmit}>
        <label htmlFor="title">Title:</label><br />
        <input
          type="text"
          name="title"
          value={book.title}
          onChange={handleChange}
          required
        /><br />

        <label htmlFor="author">Author:</label><br />
        <input
          type="text"
          name="author"
          value={book.author}
          onChange={handleChange}
          required
        /><br />

        <label htmlFor="category">Category:</label><br />
        <input
          type="text"
          name="category"
          value={book.category}
          onChange={handleChange}
          required
        /><br />

        <label htmlFor="cost">Cost:</label><br />
        <input
          type="number"
          name="cost"
          value={book.cost}
          onChange={handleChange}
          required
        /><br />

        <label htmlFor="quantity">Quantity:</label><br />
        <input
          type="number"
          name="quantity"
          value={book.quantity}
          onChange={handleChange}
          required
        /><br />

        <label htmlFor="published">Published Date:</label><br />
        <input
          type="date"
          name="published"
          value={book.published}
          onChange={handleChange}
          required
        /><br />

        <button type="submit">{isEdit ? 'Update' : 'Submit'}</button>
      </form>
    </div>
  );
}

export default AddBookForm;
