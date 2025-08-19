import React from "react";
import { useNavigate } from "react-router-dom";

export default function BooksPage() {
  const navigate = useNavigate();

  return (
    <div className="page">
      <div className="card">
        <h1 className="title">Library Management System</h1>

        <button onClick={() => navigate("/add-book")} className="btn blue">
          Add a Book
        </button>

        <button onClick={() => navigate("/view-books")} className="btn green">
          View All Books
        </button>

        <button onClick={() => navigate("/")} className="btn gray">
          Back
        </button>
      </div>
    </div>
  );
}
