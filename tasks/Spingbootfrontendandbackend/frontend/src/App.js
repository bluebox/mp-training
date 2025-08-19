import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import HomePage from "./pages/HomePage";
import MembersPage from "./pages/MembersPage";
import BooksPage from "./pages/BooksPage";
import ReportsPage from "./pages/ReportsPage";
import AddBookPage from "./pages/AddBookPage";
import UpdateBookPage from "./pages/UpdateBookPage";
import AddMemberPage from "./pages/AddMemberPage";
import UpdateMemberPage from "./pages/UpdateMemberPage";
import IssueRecordsPage from "./pages/IssueRecordsPage";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/books" element={<BooksPage />} />
        <Route path="/books/add" element={<AddBookPage />} />
        <Route path="/books/update/:bookid" element={<UpdateBookPage />} />

        <Route path="/members" element={<MembersPage />} />
        <Route path="/members/add" element={<AddMemberPage />} />
        <Route path="/members/update/:id" element={<UpdateMemberPage />} />

        <Route path="/issues" element={<IssueRecordsPage />} />
        <Route path="/reports" element={<ReportsPage />} />
      </Routes>
    </Router>
  );
}

export default App;
