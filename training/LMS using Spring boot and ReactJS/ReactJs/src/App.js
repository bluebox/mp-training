import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";

import HomePage from "./pages/HomePage";
import AddMember from "./pages/addMember";
import ViewMembers from "./pages/ViewMembers";
import AddBook from "./pages/AddBook";
import ViewBooks from "./pages/ViewBooks";
import IssueBook from './pages/IssueBook';
import ViewIssueRecords from "./pages/ViewIssueRecords";
import ReturnBook from "./pages/ReturnBook";
import Reports from "./pages/Reports";
import UpdateMember from "./pages/UpdateMember";
import UpdateBook from "./pages/UpdateBook";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/addMember" element={<AddMember />} />
        <Route path="/viewMembers" element={<ViewMembers />} />
        <Route path="/addBook" element={<AddBook />} />
        <Route path="/viewBooks" element={<ViewBooks />} />
        <Route path="/issueBook" element={<IssueBook />} />
        <Route path="/viewIssuedRecords" element={<ViewIssueRecords />} />
        <Route path="/returnBook" element={<ReturnBook />} />
        <Route path="/reports" element={<Reports />} />
        <Route path="/updateMember/:memberID" element={<UpdateMember />} />
        <Route path="/updateBook/:bookId" element={<UpdateBook />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
