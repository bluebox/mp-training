import React, { useState, useEffect } from 'react';
import BookForm from '../components/books/BookForm';
import BookList from '../components/books/BookList';
import { bookService } from '../services/bookService';

const Books = () => {
  const [showForm, setShowForm] = useState(false);
  const [bookToEdit, setBookToEdit] = useState(null);
  const [refreshKey, setRefreshKey] = useState(0);
  const [searchTerm, setSearchTerm] = useState('');
  const [categoryFilter, setCategoryFilter] = useState('ALL');
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const response = await bookService.getCategories();
        setCategories(response.data.data || []);
      } catch (err) {
        setCategories([]);
      }
    };
    fetchCategories();
  }, []);

  const handleBookSaved = () => {
    setShowForm(false);
    setBookToEdit(null);
    setRefreshKey(prev => prev + 1);
  };

  const handleEditBook = (book) => {
    setBookToEdit(book);
    setShowForm(true);
  };

  const handleCancelEdit = () => {
    setShowForm(false);
    setBookToEdit(null);
  };

  window.onEditBook = handleEditBook;

  return (
    <div className="container mt-4">
      <div className="d-flex justify-content-between align-items-center mb-4">
        <h1>Books Management</h1>
        <button
          className="btn btn-primary"
          onClick={() => setShowForm(true)}
          disabled={showForm}
        >
          Add New Book
        </button>
      </div>

      <div className="row mb-4">
        <div className="col-md-8">
          <input
            type="text"
            className="form-control"
            placeholder="Search by title, author, or book ID..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />
        </div>
        <div className="col-md-4">
          <select
            className="form-control"
            value={categoryFilter}
            onChange={(e) => setCategoryFilter(e.target.value)}
          >
            <option value="ALL">All Categories</option>
            {categories.map(category => (
              <option key={category} value={category}>
                {category}
              </option>
            ))}
          </select>
        </div>
      </div>

      {showForm && (
        <div className="mb-4">
          <BookForm 
            bookToEdit={bookToEdit}
            onBookSaved={handleBookSaved}
            onCancel={handleCancelEdit}
          />
        </div>
      )}

      <BookList 
        key={refreshKey}
        searchTerm={searchTerm}
        categoryFilter={categoryFilter}
      />
    </div>
  );
};

export default Books;
