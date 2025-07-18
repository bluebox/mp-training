import React from "react";
import { Outlet } from "react-router-dom";

const BookLayout = () => {
  return (
    <div className="flex gap-4">
      <div className="w-1/4 p-4">
        <h2 className="text-2xl font-bold mb-4">Books</h2>
        <p>
          <a href="/books" className="text-blue-500 hover:underline">
            View All Books
          </a>
        </p>

        <p>
          <a href="/books/add" className="text-blue-500 hover:underline">
            Add Book
          </a>
        </p>

        <p>
          <a href="/books/update" className="text-blue-500 hover:underline">
            Update Book Availability
          </a>
        </p>

        <p>
          <a href="/books/category" className="text-blue-500 hover:underline">
            Books Category
          </a>
        </p>
      </div>
      <Outlet />
    </div>
  );
};

export default BookLayout;
