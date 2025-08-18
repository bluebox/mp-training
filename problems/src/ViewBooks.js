import React, { useEffect, useState } from 'react';
import './App.css';
import { Link } from 'react-router-dom';

function ViewBooks() {
  const [books, setBooks] = useState([]);
  const [message,setMessage]=useState('');

  useEffect(() => {
    fetch('http://localhost:8070/Book/viewBooks')
      .then(response => response.json())
      .then(data => setBooks(data))
      .catch(error => {console.error(error);
        setMessage(error);
      });
  }, []);


console.log(books);



  return (
    <div className="Container">
      <h2>Books Table View</h2>
      {message != null && <p>{message}</p>}
      {books.length === 0 ? (
        <p>No books available yet .</p>
      ) : (
    <table>
  <thead>
    <tr>
      <th>Book ID</th>
      <th>Title</th>
      <th>Author</th>
      <th>Category</th>
      <th>Status</th>
      <th>Availability</th>
    </tr>
  </thead>
  <tbody>
    {books.map((book) => (
      <tr key={book.bookId}>
        <td>{book.bookId}</td>
        <td>{book.title}</td>
        <td>{book.author}</td>
        <td>{book.category}</td>
        <td>{book.status}</td>
        <td>{book.availability}</td>
      </tr>
    ))}
  </tbody>
</table>
    
      )}
       <Link to="/Bookhome/AddBook">Add New Book</Link>
            <br />
            <Link to="/Bookhome/UpdateBook">Update the Book Details</Link>
            <br />
    </div>
  );
}

export default ViewBooks;
