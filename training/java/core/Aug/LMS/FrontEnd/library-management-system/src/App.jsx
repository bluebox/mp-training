import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import Navbar from "./components/Navbar";
import Books from "./pages/Books";
import Members from "./pages/Members";
import IssueReturn from "./pages/IssueReturn";
import Reports from "./pages/Reports";
import ActiveIssuedBooks from "./components/ActiveIssuedBooks";
import BookCategoryCount from "./components/BooksCategoryCount";
import OverDueBooks from "./components/OverDueBooks";

function App() {
  return (
    <Router>
      <Navbar />

      <div className="container mt-4">
        <Routes>
          <Route path="/" element={<Books />} />
          <Route path="/books" element={<Books />} />
          <Route path="/members" element={<Members />} />
          <Route path="/issue-return" element={<IssueReturn />} />
          <Route path="/reports" element={<Reports />}>
          <Route index element={<ActiveIssuedBooks />} />  
          <Route path="activeIssuedBooks" element={<ActiveIssuedBooks />} />
          <Route path="bookCategoryCount" element={<BookCategoryCount />} />
          <Route path="overDueBooks" element={<OverDueBooks />} />
        </Route>
        </Routes>
      </div>
    </Router>
  );
}

export default App;