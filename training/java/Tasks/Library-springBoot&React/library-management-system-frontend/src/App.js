import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";

import LibraryHome from "./components/LibraryHome";

import BookManagement from "./components/BookManagement";
import AddBook from "./components/AddBook";
import BookList from "./components/BookList";
import UpdateBook from "./components/UpdateBook";

import MemberManagement from "./components/MemberManagement";
import AddMember from "./components/AddMember";
import MemberList from "./components/MemberList";
import UpdateMember from "./components/UpdateMember";

import IssueReturn from "./components/IssueReturn";
import IssueRecord from "./components/IssueRecord";
import ReturnRecord from "./components/ReturnRecord";
import IssueList from "./components/IssueList";

import ReportsHome from "./components/Reports";
import CategoryCountTable from "./components/CategoryCountTable";
import OverdueBooksTable from "./components/OverdueBooksTable";
import ActiveIssuedBooksTable from "./components/ActiveIssuedBooksTable";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Navigate to="/library" replace />} />

      <Route path="/library" element={<LibraryHome />} />

      <Route path="/library/books" element={<BookManagement />} />
      <Route path="/library/books/add" element={<AddBook />} />
      <Route path="/library/books/view" element={<BookList />} />
      <Route path="/library/books/update" element={<UpdateBook />} />

      <Route path="/library/members" element={<MemberManagement />} />
      <Route path="/library/members/add" element={<AddMember />} />
      <Route path="/library/members/view" element={<MemberList />} />
      <Route path="/library/members/update" element={<UpdateMember />} />

	  <Route path="/library/issues" element={<IssueReturn />} />
	  <Route path="/library/issues/issue" element={<IssueRecord />} />
	  <Route path="/library/issues/return" element={<ReturnRecord />} />
	  <Route path="/library/issues/allIssues" element={<IssueList />} />
	  
      <Route path="/library/reports" element={<ReportsHome />} />
      <Route path="/library/reports/categoryCount" element={<CategoryCountTable />} />
      <Route path="/library/reports/overdueRecords" element={<OverdueBooksTable />} />
      <Route path="/library/reports/activeIssuedRecords" element={<ActiveIssuedBooksTable />} />
    </Routes>
  );
}

export default App;
