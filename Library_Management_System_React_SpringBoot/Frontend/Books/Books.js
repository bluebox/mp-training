import React from "react";
import { Routes, Route, useNavigate } from "react-router-dom";
import AddBook from "./AddBook";
import ViewBooks from "./ViewBooks";
import UpdateBook from "./UpdateBook";
import UpdateAvailability from "./UpdateAvailability";
import "./Books.css";

function Books() {
  const navigate = useNavigate();

  return (
    <div className="Books">
      <header className="Books-header">
        <h1>Books Management</h1>

        <Routes>
          {/* Default route: action selection */}
          <Route
            path="/"
            element={
              <>
                <p>Choose an action:</p>
                <div className="button-container">
                  <button
                    className="main-button"
                    onClick={() => navigate("/books/addbook")}
                  >
                    Add Book
                  </button>
                  <button
                    className="main-button"
                    onClick={() => navigate("/books/viewbooks")}
                  >
                    View Books
                  </button>
                </div>
                <button
                  className="back-button"
                  onClick={() => navigate("/")}
                >
                  ← Back
                </button>
              </>
            }
          />

          {/* Routes for books management */}
          <Route path="addbook" element={<AddBook />} />
          <Route path="viewbooks" element={<ViewBooks />} />
          <Route path="update-book/:id" element={<UpdateBook />} />
          <Route path="update-availability/:id" element={<UpdateAvailability />} />
        </Routes>
      </header>
    </div>
  );
}

export default Books;
