import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Home from "./components/Home";
import MemberManagement from "./components/MemberManagement";
import AddMember from "./components/AddMember";
import UpdateMember from "./components/UpdateMember";
import ViewMembers from "./components/ViewMembers";
import BookManagement from "./components/BookManagement";
import AddBook from "./components/AddBook";
import UpdateBook from "./components/UpdateBook";
import ViewAllBooks from "./components/ViewAllBooks";
import IssueReturn from "./components/IssueReturn";
import IssueBook from "./components/IssueBook";
import ReturnBook from "./components/ReturnBook";
import ViewIssues from "./components/ViewIssues";
import Reports from "./components/Reports";
import ActiveIssuedBooks from "./components/ActiveIssuedBooks";
import BooksPerCategory from "./components/BooksPerCategory";
import OverdueBooks from "./components/OverdueBooks";

function App() {
  return (
    <>
      <Router>
        <Home />
        <div>
          <Routes>
            {/* <Route path="/" element={<Home />} /> */}
            <Route path="/members" element={<MemberManagement />}>
              <Route path="add" element={<AddMember />} />
              <Route path="update" element={<UpdateMember />} />
              <Route path="view" element={<ViewMembers />} />
            </Route>

            <Route path="/books" element={<BookManagement />}>
              <Route path="add" element={<AddBook />} />
              <Route path="update" element={<UpdateBook />} />
              <Route path="view" element={<ViewAllBooks />} />
            </Route>

            <Route path="/issues" element={<IssueReturn />}>
              <Route path="issue" element={<IssueBook />} />
              <Route path="return" element={<ReturnBook />} />
              <Route path="view" element={<ViewIssues />} />
            </Route>
            <Route path="/reports" element={<Reports />}>
              <Route path="active-members" element={<ActiveIssuedBooks />} />
              <Route path="book-count" element={<BooksPerCategory />} />
              <Route path="overdue" element={<OverdueBooks />} />
            </Route>
          </Routes>
        </div>
      </Router>
    </>
  );
}

export default App;
