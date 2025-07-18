import React, { useState } from "react";
import Book from "./Book";
import { useEffect } from "react";

const ViewAllBooks = () => {
  const [books, setBooks] = useState([]);

  useEffect(() => {
    const fetchBooks = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/books",
          {
            method: "GET",
            credentials: "include",
          }
        );
        if (!response.ok) {
          throw new Error("Failed to fetch books");
        }
        const responseData = await response.json();
        setBooks(responseData.data);
      } catch (error) {
        console.error("Error fetching books:", error);
      }
    };

    fetchBooks();
  }, []);
  return (
    <>
      <div>
        <h2 className="mb-5">All Books</h2>
        <div>
          <table>
            <thead>
              <tr>
                <th className="px-4 py-3">Book ID</th>
                <th className="px-4 py-3">Title</th>
                <th className="px-4 py-3">Author</th>
                <th className="px-4 py-3">Category</th>
                <th className="px-4 py-3">Status</th>
                <th className="px-4 py-3">Availability</th>
              </tr>
            </thead>
            <tbody>
              {books.map((book) => (
                <Book key={book.bookId} book={book} />
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </>
  );
};

export default ViewAllBooks;
