import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import MainPage from "./MainPage";
import BooksPage from "./BooksPage";
import AddBookPage from "./AddBookPage";
import ViewBooksPage from "./ViewBooksPage";
import UpdateAvailabilityPage from "./UpdateAvailabilityPage";
import UpdateBookDetailsPage from "./UpdateBookDetailsPage";
import MembersPage from "./MembersPage";
import AddMemberPage from "./AddMemberPage";
import ViewMembersPage from "./ViewMembersPage";
import UpdateMemberPage from "./UpdateMemberPage";
import IssueManagementPage from "./IssueManagementPage";
import IssueBookPage from "./IssueBookPage";
import ReturnBookPage from "./ReturnBookPage";
import ViewAllIssuesPage from "./ViewAllIssuesPage";
import ReportsPage from "./ReportsPage";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<MainPage />} />
        <Route path="/books" element={<BooksPage/>} />
         <Route path="/add-book" element={<AddBookPage />} /> 
         <Route path="/view-books" element={<ViewBooksPage />} />
         <Route path="/update-book/:id" element={<UpdateBookDetailsPage />} />
        <Route path="/update-availability/:id" element={<UpdateAvailabilityPage />} />
        <Route path="/members" element={<MembersPage />} />
        <Route path="/add-member" element={<AddMemberPage />} />
        <Route path="/view-members" element={<ViewMembersPage />} />
        <Route path="/update-member/:id" element={<UpdateMemberPage />} />
        {/* <Route path="/members/update" element={<UpdateMembersPage />} /> */}

        <Route path="/issue-return" element={<IssueManagementPage/>} />
        
<Route path="/issue-book" element={<IssueBookPage/>} />
<Route path="/return-book" element={<ReturnBookPage/>} />
<Route path="/view-issues" element={<ViewAllIssuesPage/>} />

        <Route path="/reports" element={<ReportsPage/>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
