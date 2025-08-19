import React from "react";
import { Link, Outlet } from "react-router-dom";

function BookManagement() {
  return (
    <>
      <div style={{ textAlign: "center", marginTop: "-10px" }}>
        <h2>Book Management</h2>
        <Link to="/books/add">
          <button>Add Book</button>
        </Link>
        <Link to="/books/view" style={{ marginLeft: "10px" }}>
          <button>View Books</button>
        </Link>
        <Link to="/books/update" style={{ marginLeft: "10px" }}>
          <button>Update Book</button>
        </Link>
      </div>
      <Outlet />
    </>
  );
}

export default BookManagement;
