import React, { useEffect, useState } from "react";

const BooksCategory = () => {
  const [categoryBookData, setCategoryBookData] = useState([]);

  useEffect(() => {
    const fetchBooks = async () => {
      try {
        const response = await fetch(
          "http://localhost:8080/api/books/category-count",
          {
            method: "GET",
            credentials: "include",
          }
        );
        if (!response.ok) {
          throw new Error("Failed to fetch books");
        }
        const responseData = await response.json();
        setCategoryBookData(responseData.data);
      } catch (error) {
        console.error("Error fetching books:", error);
      }
    };

    fetchBooks();
  }, []);

  return (
    <div className="flex flex-col gap-4">
      <p>BooksCategory</p>
      <div>
        <table>
          <thead>
            <tr>
              <th className="px-4 py-3">Category</th>
              <th className="px-4 py-3">Count</th>
            </tr>
          </thead>
          <tbody>
            {categoryBookData.map((book) => (
              <tr key={book.category}>
                <td className="px-4 py-3">{book.category}</td>
                <td className="px-4 py-3">{book.count}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default BooksCategory;
