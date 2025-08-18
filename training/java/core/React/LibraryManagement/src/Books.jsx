import React from 'react'
import { Link } from "react-router-dom";
export default function Books() {
  return (
    <div
      className="d-flex align-items-center justify-content-center vh-100"
      style={{
        backgroundImage: "url('/library.jpg')",
        backgroundSize: "cover",
        backgroundPosition: "center",
      }}
    >
      <div
        className="bg-white p-4 rounded shadow text-center"
        style={{ width: "450px", height:"350px"}}
      >
        <h3 className="fw-bold mt-2 mb-5">
          Books Section <br /> 
        </h3>

        <div className="d-grid gap-4">
          <Link to="/books/add" className="btn btn-primary btn-lg fs-5">Add a Book</Link>
          <Link to="/books/view" className="btn btn-primary btn-lg fs-5">View Books</Link>
          <Link to="/" className="btn btn-danger btn-lg fs-5">Back to Main Menu</Link>
        </div>
      </div>
    </div>
  );
}
