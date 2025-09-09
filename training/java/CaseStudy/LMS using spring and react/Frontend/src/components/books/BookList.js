import React, { useState, useEffect } from 'react';
import { bookService } from '../../services/bookService';

const BookList = ({ searchTerm, categoryFilter }) => {
  const [books, setBooks] = useState([]);
  const [filteredBooks, setFilteredBooks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    fetchBooks();
  }, []);

  useEffect(() => {
    let filtered = books;

    if (searchTerm) {
      filtered = filtered.filter(book =>
        book.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
        book.author.toLowerCase().includes(searchTerm.toLowerCase()) ||
        book.bookId.toLowerCase().includes(searchTerm.toLowerCase())
      );
    }

    if (categoryFilter && categoryFilter !== 'ALL') {
      filtered = filtered.filter(book => book.category === categoryFilter);
    }

    setFilteredBooks(filtered);
  }, [books, searchTerm, categoryFilter]);

  const fetchBooks = async () => {
    try {
      const response = await bookService.getAllBooks();
      const bookData = Array.isArray(response.data.data) ? response.data.data : [];
      setBooks(bookData);
      setFilteredBooks(bookData);
      setLoading(false);
    } catch (err) {
      setError('Failed to fetch books');
      setBooks([]);
      setFilteredBooks([]);
      setLoading(false);
    }
  };

  const handleDelete = async (bookId) => {
    if (window.confirm('Are you sure you want to delete this book?')) {
      try {
        await bookService.deleteBook(bookId);
        setBooks(books.filter(book => book.bookId !== bookId));
        alert('Book deleted successfully!');
      } catch (err) {
        if (err.response?.status === 500) {
          alert('Cannot delete this book. It appears to be currently issued to a member.');
        } else {
          alert('Failed to delete book. Please try again.');
        }
      }
    }
  };

  const handleEdit = (book) => {
    if (window.onEditBook) {
      window.onEditBook(book);
    }
  };

  if (loading) return <div className="text-center">Loading...</div>;
  if (error) return <div className="alert alert-danger">{error}</div>;

  return (
    <div className="container mt-4">
      <h2>All Books ({filteredBooks.length})</h2>
      
      {filteredBooks.length === 0 ? (
        <div className="text-center mt-4">
          <p>No books found matching your criteria.</p>
        </div>
      ) : (
        <div className="table-responsive">
          <table className="table table-striped">
            <thead>
              <tr>
                <th>Book ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>Category</th>
                <th>Status</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {filteredBooks.map(book => (
                <tr key={book.id}>
                  <td>{book.bookId}</td>
                  <td>{book.title}</td>
                  <td>{book.author}</td>
                  <td>{book.category}</td>
                  <td>
                    <span className={`badge ${book.available ? 'bg-success' : 'bg-danger'}`}>
                      {book.available ? 'Available' : 'Issued'}
                    </span>
                  </td>
                  <td>
                    <button
                      className="btn btn-sm btn-outline-primary me-2"
                      onClick={() => handleEdit(book)}
                      title="Edit Book"
                    >
                      Edit
                    </button>
                    {book.available ? (
                      <button
                        className="btn btn-sm btn-outline-danger"
                        onClick={() => handleDelete(book.bookId)}
                        title="Delete Book"
                      >
                        Delete
                      </button>
                    ) : (
                      <button
                        className="btn btn-sm btn-secondary"
                        disabled
                        title="Cannot delete issued book"
                      >
                        Cannot Delete
                      </button>
                    )}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
};

export default BookList;
