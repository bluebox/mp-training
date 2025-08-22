import React, { useState } from "react";
import { BrowserRouter as Router, Routes, Route, useNavigate } from "react-router-dom";
import "./App.css";

// Import all pages
import Books from "./components/Books/Books";
import Members from "./components/Members/Members";
import IssueReturn from "./components/IssueReturn/IssueReturn";
import Reports from "./components/Reports/Reports";

function MainPage() {
  const navigate = useNavigate();

  const handleNavigation = (page) => {
    navigate(`/${page.toLowerCase()}`);
  };

  return (
    <div className="App">
      <header className="App-header">
        <h1>Library Management System</h1>
        <p>Welcome! Choose an option below:</p>

        <div className="button-container">
          <button className="main-button" onClick={() => handleNavigation("Books")}>
            Books
          </button>
          <button className="main-button" onClick={() => handleNavigation("Members")}>
            Members
          </button>
          <button className="main-button" onClick={() => handleNavigation("Issuereturn")}>
            Issue & Return
          </button>
          <button className="main-button" onClick={() => handleNavigation("Reports")}>
            Reports
          </button>
        </div>
      </header>
    </div>
  );
}

function App() {
  // Centralized state for books, members, and issue records
  const [books, setBooks] = useState([
    { id: 1, title: "Book A", author: "Author A", category: "Fiction", status: "A", availability: "A" },
    { id: 2, title: "Book B", author: "Author B", category: "Science", status: "I", availability: "I" },
  ]);

  const [members, setMembers] = useState([
    { id: 1, name: "Alice", email: "alice@example.com", mobile: "1234567890", gender: "F", address: "Street 1" },
    { id: 2, name: "Bob", email: "bob@example.com", mobile: "9876543210", gender: "M", address: "Street 2" },
  ]);

  const [records, setRecords] = useState([
    { id: 1, bookId: 1, memberId: 1, status: "I", issueDate: "2025-08-16", returnDate: null },
  ]);

  return (
    <Router>
      <Routes>
        <Route path="/" element={<MainPage />} />

        <Route 
          path="/books/*" 
          element={<Books books={books} setBooks={setBooks} />} 
        />

        <Route 
          path="/members/*" 
          element={<Members members={members} setMembers={setMembers} />} 
        />

        <Route 
          path="/issuereturn/*" 
          element={
            <IssueReturn 
              books={books} 
              members={members} 
              records={records} 
              setRecords={setRecords} 
            />
          } 
        />

        <Route 
          path="/reports/*" 
          element={<Reports books={books} members={members} records={records} />} 
        />
      </Routes>
    </Router>
  );
}

export default App;
