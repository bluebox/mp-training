import React from "react";
import { Link } from "react-router-dom";

export default function LibraryManagementSystem() {
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
        style={{ width: "450px", height:"450px"}}
      >
        <h3 className="fw-bold mt-2 mb-4">
          Welcome to Library <br /> Management System
        </h3>

        <div className="d-grid gap-4">
          <Link to="/books" className="btn btn-primary btn-lg">Books</Link>
          <Link to="/members" className="btn btn-primary btn-lg">Members</Link>
          <Link to="/issue-return" className="btn btn-primary btn-lg">Issue and Return</Link>
          <Link to="/reports" className="btn btn-primary btn-lg">Reports</Link>
        </div>
      </div>
    </div>
  );
}
