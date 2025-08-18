import React, { useEffect, useState } from "react";
import axios from "axios";

const ViewBooks = () => {
  const [books, setBooks] = useState([]);

  useEffect(() => {
    fetchBooks();
  }, []);

  const fetchBooks = async () => {
    try {
      const response = await axios.get("http://localhost:8080/books");
      setBooks(response.data);
    } catch (error) {
      console.error("Error fetching books:", error);
    }
  };

  return (
    <div>
      <h2>All Books</h2>
      <table border="1" cellPadding="10">
        <thead>
          <tr>
            <th>ID</th>
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
    </div>
  );
};

export default ViewBooks;
