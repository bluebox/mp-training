import React, { useState, useEffect } from 'react';
import { bookService } from '../../services/bookService';

const BookForm = ({ bookToEdit, onBookSaved, onCancel }) => {
  const [formData, setFormData] = useState({
    title: '',
    author: '',
    category: '',
    status: 'A',
    availability: 'A'
  });
  const [categories, setCategories] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const isEditMode = !!bookToEdit;

  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const response = await bookService.getCategories();
        const categoryList = response.data.data || [];
        setCategories(categoryList);

        if (bookToEdit) {
          setFormData({
            title: bookToEdit.title || '',
            author: bookToEdit.author || '',
            category: bookToEdit.category || categoryList[0] || '',
            status: bookToEdit.status || 'A',
            availability: bookToEdit.availability || 'A'
          });
        } else if (categoryList.length > 0 && !formData.category) {
          setFormData(prev => ({ ...prev, category: categoryList[0] }));
        }
      } catch (err) {
        console.error('Failed to fetch categories:', err);
        setCategories(['FICTION']);
        setFormData(prev => ({ 
          ...prev, 
          category: bookToEdit?.category || 'FICTION' 
        }));
      }
    };

    fetchCategories();
  }, [bookToEdit]);

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    if (!formData.title || !formData.author) {
      setError('Title and Author are required');
      return;
    }

    setLoading(true);
    setError('');

    try {
      if (isEditMode) {
        await bookService.updateBook(bookToEdit.bookId, formData);
        alert('Book updated successfully!');
      } else {
        await bookService.createBook(formData);
        alert('Book added successfully!');
      }

      if (onBookSaved) onBookSaved();
      
      if (!isEditMode) {
        setFormData({ 
          title: '', 
          author: '', 
          category: categories[0] || 'FICTION',
          status: 'A',
          availability: 'A'
        });
      }
    } catch (err) {
      console.error('Error saving book:', err.response?.data);
      setError(`Failed to ${isEditMode ? 'update' : 'add'} book: ${err.response?.data?.message || err.message}`);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="card">
      <div className="card-header">
        <h5>{isEditMode ? 'Edit Book' : 'Add New Book'}</h5>
      </div>
      <div className="card-body">
        {error && <div className="alert alert-danger">{error}</div>}
        
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label className="form-label">Title *</label>
            <input
              type="text"
              className="form-control"
              name="title"
              value={formData.title}
              onChange={handleChange}
              pattern="[A-Za-z\s]+"
              title="Please enter a valid title (letters and spaces only)"
              required
            />
          </div>
          
          <div className="mb-3">
            <label className="form-label">Author *</label>
            <input
              type="text"
              className="form-control"
              name="author"
              value={formData.author}
              onChange={handleChange}
              pattern="[A-Za-z\s]+"
              title="Please enter a valid author name (letters and spaces only)"
              required
            />
          </div>
          
          <div className="mb-3">
            <label className="form-label">Category</label>
            <select
              className="form-control"
              name="category"
              value={formData.category}
              onChange={handleChange}
            >
              {categories.map(cat => (
                <option key={cat} value={cat}>{cat}</option>
              ))}
            </select>
          </div>

          <div className="d-flex gap-2">
            <button 
              type="submit" 
              className="btn btn-primary"
              disabled={loading}
            >
              {loading ? 'Saving...' : (isEditMode ? 'Update Book' : 'Add Book')}
            </button>
            
            {isEditMode && (
              <button 
                type="button" 
                className="btn btn-secondary"
                onClick={onCancel}
                disabled={loading}
              >
                Cancel
              </button>
            )}
          </div>
        </form>
      </div>
    </div>
  );
};

export default BookForm;
