import React from 'react';
import { Route, Routes, Link, Navigate } from 'react-router-dom';
import './App.css';

// Import components
import AddBook from './components/books/AddBook';
import ViewBooks from './components/books/ViewBooks';
import AddMember from './components/members/AddMember';
import ViewMembers from './components/members/ViewMembers';
import IssueBook from './components/circulation/IssueBook';
import ReturnBook from './components/circulation/ReturnBook';
import IssueRecords from './components/circulation/IssueRecords';
import Analytics from './components/reports/Analytics';
import MembersBooks from './components/reports/MembersBooks';
import BooksMembers from './components/reports/BooksMembers';

function App() {
  return (
    
      <div className="App">
        <header className="lms-header">
          <div className="logo-section">
            <img src="/Library.png" alt="LMS Logo" className="logo" />
            <h1>Library Management System</h1>
          </div>
        </header>
        
        <div className="main-container">
          <aside className="sidebar">
            <div className="sidebar-section">
              <h3>Book Management</h3>
              <ul>
                <li><Link to="/add-book" className="sidebar-btn">Add Book</Link></li>
                <li><Link to="/view-books" className="sidebar-btn">View Books</Link></li>
              </ul>
            </div>

            <div className="sidebar-section">
              <h3>Members</h3>
              <ul>
                <li><Link to="/add-member" className="sidebar-btn">Add Member</Link></li>
                <li><Link to="/view-members" className="sidebar-btn">View Members</Link></li>
              </ul>
            </div>

            <div className="sidebar-section">
              <h3>Circulation</h3>
              <ul>
                <li><Link to="/issue-book" className="sidebar-btn">Issue Book</Link></li>
                <li><Link to="/return-book" className="sidebar-btn">Return Book</Link></li>
                <li><Link to="/issue-records" className="sidebar-btn">Issue Records</Link></li>
              </ul>
            </div>

            <div className="sidebar-section">
              <h3>Reports</h3>
              <ul>
                <li><Link to="/analytics" className="sidebar-btn">Analytics</Link></li>
                <li><Link to="/members-books" className="sidebar-btn">Members Books</Link></li>
                <li><Link to="/books-members" className="sidebar-btn">Books Members</Link></li>
              </ul>
            </div>
          </aside>

          <main className="main-content">
            
            <Routes>
              <Route path="/" element={
                <div className="welcome-section">
                  <h2>Welcome to Library Management System</h2>
                  <p>Select an option from the sidebar to get started</p>
                </div>
              } />
              <Route path="/add-book" element={<AddBook />} />
              <Route path="/view-books" element={<ViewBooks />} />
              <Route path="/add-member" element={<AddMember />} />
              <Route path="/view-members" element={<ViewMembers />} />
              <Route path="/issue-book" element={<IssueBook />} />
              <Route path="/return-book" element={<ReturnBook />} />
              <Route path="/issue-records" element={<IssueRecords />} />
              <Route path="/analytics" element={<Analytics />} />
              <Route path="/members-books" element={<MembersBooks />} />
              <Route path="/books-members" element={<BooksMembers />} />
              <Route path="*" element={<Navigate to="/" />} />
            </Routes>
          </main>
        </div>
      </div>
   
  );
}

export default App;
