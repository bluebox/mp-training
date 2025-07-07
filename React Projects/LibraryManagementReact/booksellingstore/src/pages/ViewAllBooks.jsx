import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

function ViewAllBooks() {
  const [books, setBooks] = useState([]);
  const [search, setSearch] = useState('');
  const navigate = useNavigate();

  // Debounce mechanism (optional but useful)
  useEffect(() => {
    const delayDebounce = setTimeout(() => {
      if (search.trim() === '') {
        fetchBooks();
      } else {
        fetchSearchBooks(search);
      }
    }, 300); // 300ms delay

    return () => clearTimeout(delayDebounce);
  }, [search]);

  const fetchBooks = () => {
    fetch('http://localhost:8080/books/list')
      .then((res) => res.json())
      .then((data) => setBooks(data))
      .catch((err) => console.error('Error fetching books:', err));
  };

  const fetchSearchBooks = (query) => {
    fetch(`http://localhost:8080/books/search?title=${encodeURIComponent(query)}`)
      .then((res) => res.json())
      .then((data) => setBooks(data))
      .catch((err) => console.error('Error searching books:', err));
  };

  const handleEdit = (book) => {
    navigate('/add-book', { state: { book, isEdit: true } });
  };

  return (
    <div style={{ padding: '20px' }}>
      <h2>All Books</h2>

      <input
        type="text"
        placeholder="Search by Title..."
        value={search}
        onChange={(e) => setSearch(e.target.value)}
        style={{ padding: '8px', width: '300px', marginBottom: '20px' }}
      />

      <table border="1" cellPadding="10" cellSpacing="0">
        <thead>
          <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Category</th>
            <th>Cost</th>
            <th>Quantity</th>
            <th>Published</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {books.length > 0 ? (
            books.map((book) => (
              <tr key={book.bookId}>
                <td>{book.bookId}</td>
                <td>{book.title}</td>
                <td>{book.author}</td>
                <td>{book.categroy}</td>
                <td>{book.cost}</td>
                <td>{book.quantity}</td>
                <td>{book.published}</td>
                <td>
                  <button onClick={() => handleEdit(book)}>Edit</button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="8" style={{ textAlign: 'center' }}>No books found</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}

export default ViewAllBooks;
