import React from "react";
import Home from "./components/Home";
import { Route, Routes } from "react-router-dom";
import Navbar from "./components/Navbar";
import BookLayout from "./components/books/BookLayout";
import ViewAllBooks from "./components/books/ViewAllBooks";
import { AddBook } from "./components/books/AddBook";
import UpdateBookAvailability from "./components/books/UpdateBookAvailability";
import BooksCategory from "./components/books/BooksCategory";
import MemberLayout from "./components/member/MemberLayout";
import AddMember from "./components/member/AddMember";
import ViewAllMembers from "./components/member/ViewAllMembers";
import UpdateMember from "./components/member/UpdateMember";
import MembersWithBooks from "./components/member/MembersWithBooks";
import IssueLayout from "./components/issues/IssueLayout";
import ViewAllIssues from "./components/issues/ViewAllIssues";
import IssueBook from "./components/issues/IssueBook";
import ReturnBook from "./components/issues/ReturnBook";
import OverdueRecords from "./components/issues/OverdueRecords";
import Login from "./components/auth/Login";
import { PrivateRoutes } from "./components/auth/PrivateRoutes";

const App = () => {
  return (
    <>
      <Navbar />
      <Routes>
        <Route element={<PrivateRoutes />}>
          <Route path="/" element={<Home />} />
          <Route path="/books" element={<BookLayout />}>
            <Route path="" element={<ViewAllBooks />} />
            <Route path="add" element={<AddBook />} />
            <Route path="update" element={<UpdateBookAvailability />} />
            <Route path="category" element={<BooksCategory />} />
          </Route>
          <Route path="/members" element={<MemberLayout />}>
            <Route path="" element={<ViewAllMembers />} />
            <Route path="add" element={<AddMember />} />
            <Route path="update" element={<UpdateMember />} />
            <Route path="books" element={<MembersWithBooks />} />
          </Route>
          <Route path="/issues" element={<IssueLayout />}>
            <Route path="" element={<ViewAllIssues />} />
            <Route path="issue" element={<IssueBook />} />
            <Route path="return" element={<ReturnBook />} />
            <Route path="overdue" element={<OverdueRecords />} />
          </Route>
        </Route>

        <Route path="/login" element={<Login />} />
      </Routes>
    </>
  );
};

export default App;
