import React, { useState, useEffect } from 'react';
import { bookService } from '../services/bookService';
import { authService } from '../services/authService';

const UserDashboard = () => {
  const [books, setBooks] = useState([]);
  const [loading, setLoading] = useState(true);
  const currentUser = authService.getCurrentUser();

  useEffect(() => {
    fetchAvailableBooks();
  }, []);

  const fetchAvailableBooks = async () => {
    try {
      const response = await bookService.getAllBooks();
      const availableBooks = (response.data.data || []).filter(book => book.available);
      setBooks(availableBooks.slice(0, 6));
    } catch (error) {
      console.error('Failed to fetch books:', error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container-fluid">
      <div className="page-header text-center">
        <h1 className="page-title">Welcome, {currentUser?.name}! 📚</h1>
        <p className="page-subtitle">Explore our library collection</p>
      </div>

      <div className="row">
        <div className="col-12">
          <div className="card shadow">
            <div className="card-header">
              <h5 className="m-0">📚 Available Books</h5>
            </div>
            <div className="card-body">
              {loading ? (
                <div className="text-center py-4">
                  <div className="spinner-border" role="status">
                    <span className="visually-hidden">Loading...</span>
                  </div>
                </div>
              ) : (
                <div className="row">
                  {books.map(book => (
                    <div key={book.bookId} className="col-md-4 mb-3">
                      <div className="card h-100">
                        <div className="card-body">
                          <h6 className="card-title">{book.title}</h6>
                          <p className="card-text text-muted">
                            <strong>Author:</strong> {book.author}<br/>
                            <strong>Category:</strong> {book.category}
                          </p>
                          <span className="badge bg-success">Available</span>
                        </div>
                      </div>
                    </div>
                  ))}
                </div>
              )}
              
              <div className="text-center mt-4">
                <a href="/books" className="btn btn-primary">
                  View All Books →
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UserDashboard;
