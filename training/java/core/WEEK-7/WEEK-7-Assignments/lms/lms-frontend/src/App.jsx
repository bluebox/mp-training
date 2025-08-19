import { BrowserRouter as Router, Routes, Route } from "react-router-dom"
import BooksPage from "./pages/BooksPage";
import MembersPage from "./pages/MembersPage"
import IssueReturnPage from "./pages/IssueReturnPage"
import ReportsPage from "./pages/ReportsPage"
import Dashboard from "./pages/Dashboard"

export default function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Dashboard />} />
        <Route path="/books" element={<BooksPage />} />
        <Route path="/members" element={<MembersPage />} />
        <Route path="/issues" element={<IssueReturnPage />} />
        <Route path="/reports" element={<ReportsPage />} />
      </Routes>
    </Router>
  )
}
