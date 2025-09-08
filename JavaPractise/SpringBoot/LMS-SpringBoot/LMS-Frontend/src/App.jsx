import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Sidebar from "./components/Sidebar";
import "./App.css";
import Dashboard from "./pages/Dashboard";
import AddBook from "./pages/books/AddBook";
import UpdateBook from "./pages/books/UpdateBook";
import UpdateAvailability from "./pages/books/UpdateAvailability";
import ViewBooks from "./pages/books/ViewBooks";
import AddMember from "./pages/members/AddMember";
import UpdateMember from "./pages/members/UpdateMember";
import ViewMembers from "./pages/members/ViewMembers";
import IssueBook from "./pages/issuereturn/IssueBook";
import ReturnBook from "./pages/issuereturn/ReturnBook";
import ViewIssuedRecords from "./pages/issuereturn/ViewIssuedRecords";
import OverdueBooks from "./pages/reports/OverdueBooks";
import BooksPerCategory from "./pages/reports/BooksPerCategory";
import ActiveIssuedMembers from "./pages/reports/ActiveIssuedMembers";

export default function App() {
  return (
    <Router>
      <div className="app-layout">
        <Sidebar />
        <div className="main-content">
          <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="/books/add" element={<AddBook />} />
            <Route path="/books/update" element={<UpdateBook />} />
            <Route path="/books/availability" element={<UpdateAvailability />} />
            <Route path="/updatebook/:id" element={<UpdateBook />} />
            <Route path="/updateavailability/:id" element={<UpdateAvailability />} />
            <Route path="/books" element={<ViewBooks />} />
            <Route path="/members/add" element={<AddMember />} />
            <Route path="/members/update" element={<UpdateMember />} />
            <Route path="/updatemember/:id" element={<UpdateMember />} />
            <Route path="/members" element={<ViewMembers />} />
            <Route path="/issues/issue" element={<IssueBook />} />
            <Route path="/issues/return" element={<ReturnBook />} />
            <Route path="/issues" element={<ViewIssuedRecords />} />
            <Route path="/reports/overdue" element={<OverdueBooks />} />
            <Route path="/reports/category" element={<BooksPerCategory />} />
            <Route path="/reports" element={<ActiveIssuedMembers />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
}
