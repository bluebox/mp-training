import React, { useState, useEffect } from 'react';
import './ViewBooks.css';
import EditBook from './EditBook';

const ViewBooks = () => {
  const [books, setBooks] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
  const [editingBook, setEditingBook] = useState(null);

  useEffect(() => {
    fetchBooks();
  }, []);

  const fetchBooks = async () => {
    try {
      const response = await fetch('http://localhost:8080/getallbooks');
      if (!response.ok) {
        throw new Error('Failed to fetch books');
      }
      const data = await response.json();
      setBooks(data);
    } catch (error) {
      setError(error.message);
    } finally {
      setIsLoading(false);
    }
  };

  const filteredBooks = books.filter(book =>
    book.book_Title.toLowerCase().includes(searchTerm.toLowerCase()) ||
    book.book_Author.toLowerCase().includes(searchTerm.toLowerCase()) ||
    book.book_Category.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const handleEdit = (book) => {
    setEditingBook(book);
  };

  const handleUpdate = async (updatedBook) => {
    try {
      setBooks(prevBooks => 
        prevBooks.map(book => 
          book.book_Id === updatedBook.book_Id ? updatedBook : book
        )
      );
      await fetchBooks();
    } catch (error) {
      setError('Failed to update book list');
    }
  };

  if (isLoading) return <div className="loading">Loading books...</div>;
  if (error) return <div className="error">Error: {error}</div>;

  return (
    <div className="content-page">
      <h2>View All Books</h2>
      
      <div className="search-container">
        <input
          type="text"
          placeholder="Search by title, author, or category..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          className="search-input"
        />
      </div>

      <div className="table-container">
        <table className="data-table">
          <thead>
            <tr>
                <th>ID</th>
              <th>Title</th>
              <th>Author</th>
              <th>Category</th>
              <th>Status</th>
              <th>Availability</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {filteredBooks.map(book => (
              <tr key={book.book_Id}>
                <td>{book.book_Id}</td>
                <td>{book.book_Title}</td>
                <td>{book.book_Author}</td>
                <td>{book.book_Category}</td>
                <td>{book.book_Status}</td>
                <td>{book.book_Availability}</td>
                <td>
                  <button 
                    className="action-btn edit"
                    onClick={() => handleEdit(book)}
                  >
                    Update
                  </button>
                 
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {editingBook && (
        <EditBook
          book={editingBook}
          onClose={() => setEditingBook(null)}
          onUpdate={handleUpdate}
        />
      )}
    </div>
  );
};

export default ViewBooks;
