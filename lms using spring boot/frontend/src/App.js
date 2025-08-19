import React from "react";
import { BrowserRouter as Router, Route, Routes} from "react-router-dom";
import Home from "./components/Home";
import MemberManagement from "./components/MemberManagement";
import AddMember from "./components/AddMember";
import UpdateMember from "./components/UpdateMember";
import ViewMembers from "./components/ViewMember";
import BookManagement from "./components/BookManagement";
import AddBook from "./components/Addbook";
import ViewAllBooks from "./components/ViewAllBooks";
import UpdateBook from "./components/UpdateBook";
import IssueAndReturn from "./components/IssueAndReturn";
import IssueBook from "./components/IssueBook";
import ReturnBook from "./components/ReturnBook";
import ViewAllIssues from "./components/ViewIssues";
import Reports from "./components/Reports";
import OverdueBooks from "./components/OverDueBooks";
import BookCountPerCategory from "./components/BooksCountPerCategory";
import ActiveMembers from "./components/ActiveMembers";

function App() {
  return (
    <Router>
      <div className="container">
        
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/members" element={<MemberManagement/>} />
          <Route path="/members/add" element={<AddMember />} />
          <Route path="/members/update/:id" element={<UpdateMember />} />
          <Route path="/members/view" element={<ViewMembers />} />

           <Route path="/books" element={<BookManagement/>} />
          <Route path="/books/add" element={<AddBook />} />
          <Route path="/books/view" element={<ViewAllBooks />} />
          <Route path="/books/update/:id" element={<UpdateBook/>} />

          <Route path="/issues" element={<IssueAndReturn/>} />
          <Route path="/issues/issue" element={<IssueBook />} />
          <Route path="/issues/return" element={<ReturnBook />} />
          <Route path="/issues/view" element={<ViewAllIssues />} />

          <Route path="/reports" element={<Reports/>} />
          <Route path="/reports/OverdueBooks" element={<OverdueBooks />} />
          <Route path="/reports/BookCountPerCategory" element={<BookCountPerCategory />} />
          <Route path="/reports/ActiveMembers" element={<ActiveMembers />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
