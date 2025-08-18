import { useState } from 'react'
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import LibraryManagementSystem from './LibraryManagementSystem'
import Books from './Books';
import Members from './Members';
import Reports from './Reports';
import IssueReturn from './IssueReturn';
import AddBook from './AddBook';
import ViewBooks from './ViewBooks';
import UpdateBook from './UpdateBook';
import UpdateAvail from './UpdateAvail';
import AddMember from './AddMember';
import ViewMembers from './ViewMembers';
import UpdateMember from './UpdateMember';
import IssueBook from './IssueBook';
import ReturnBook from './ReturnBook';
import ViewAllIssues from './ViewAllIssues';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LibraryManagementSystem />} />
        <Route path="/books" element={<Books />} />
        <Route path="/books/add" element={<AddBook />} />
        <Route path="/books/view" element={<ViewBooks />} />
        <Route path="/books/update" element={<UpdateBook/>}/>
        <Route path="/books/updateAvailability" element={<UpdateAvail/>}/>
        <Route path="/members" element={<Members />} />
        <Route path="/members/add" element={<AddMember/>}/>
        <Route path="/members/view" element={<ViewMembers/>}/>
        <Route path="/members/update" element={<UpdateMember/>}/>
        <Route path="/issue-return" element={<IssueReturn />} />
        <Route path="/issue-return/issue" element={<IssueBook />} />
        <Route path="/issue-return/return" element={<ReturnBook/>} />
        <Route path="/issue-return/view" element={<ViewAllIssues />} />
        <Route path="/reports" element={<Reports />} />
      </Routes>
    </Router>
  )
}

export default App
