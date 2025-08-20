import React from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import AddBook from "./pages/AddBook";
import AddMember from "./pages/AddMember";
import IssueBook from "./pages/IssueBook";
import Reports from "./pages/Reports";
import ReturnBooks from "./pages/ReturnBooks";
import UpdateMember from "./pages/UpdateMember";
import ViewBooks from "./pages/ViewBooks";
import ViewMembers from "./pages/ViewMembers";
import UpdateBook from "./pages/UpdateBook";

function Home() {
  return (
    <div style={{ padding: "20px" }}>
      <h2>Welcome to the Library Management System</h2>
      <p>Select an option from the navigation bar.</p>
    </div>
  );
}

function App() {
  return (
    <Router>
      <div>
        {/* Navigation Bar */}
        <nav style={{ padding: "10px", backgroundColor: "#f2f2f2" }}>
          <Link to="/" style={{ marginRight: "10px" }} className="btn btn-secondary">Home</Link>
          <Link to="/add-book" style={{ marginRight: "10px" }} className="btn btn-secondary">Add Book</Link>
          <Link to="/update-book" style={{ marginRight: "10px" }} className="btn btn-secondary">Update Book</Link>
          <Link to="/view-books" style={{ marginRight: "10px" }} className="btn btn-secondary">View Books</Link>
          <Link to="/add-member" style={{ marginRight: "10px" }} className="btn btn-secondary">Add Member</Link>
          <Link to="/view-members" style={{ marginRight: "10px" }} className="btn btn-secondary">View Members</Link>
          <Link to="/issue-book" style={{ marginRight: "10px" }} className="btn btn-secondary">Issue Book</Link>
          <Link to="/return-books" style={{ marginRight: "10px" }} className="btn btn-secondary">Return Books</Link>
          <Link to="/update-member" style={{ marginRight: "10px" }} className="btn btn-secondary">Update Member</Link>
          <Link to="/reports" style={{ marginRight: "10px" }} className="btn btn-secondary">Reports</Link>
        </nav>

        {/* Routes */}
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/add-book" element={<AddBook />} />
          <Route path="/update-book" element={<UpdateBook />} />
          <Route path="/view-books" element={<ViewBooks />} />
          <Route path="/add-member" element={<AddMember />} />
          <Route path="/view-members" element={<ViewMembers />} />
          <Route path="/issue-book" element={<IssueBook />} />
          <Route path="/return-books" element={<ReturnBooks />} />
          <Route path="/update-member" element={<UpdateMember />} />
          <Route path="/reports" element={<Reports />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
