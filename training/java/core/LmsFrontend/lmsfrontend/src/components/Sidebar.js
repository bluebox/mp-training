import { Link } from "react-router-dom";

export default function Sidebar() {
  return (
    <div className="bg-light vh-100 p-3" style={{ minWidth: "250px" }}>
      <h3 className="mb-4">📚 Library Menu</h3>
      <nav className="nav flex-column">
        <Link to="/add-book" className="nav-link">Add Book</Link>
        <Link to="/update-book" className="nav-link">Update Book</Link>
        <Link to="/books" className="nav-link">View All Books</Link>
        <hr />
        <Link to="/add-member" className="nav-link">Add Member</Link>
        <Link to="/update-member" className="nav-link">Update Member</Link>
        <Link to="/members" className="nav-link">View All Members</Link>
        <hr />
        <Link to="/issue-book" className="nav-link">Issue Book</Link>
        <Link to="/return-book" className="nav-link">Return Book</Link>
        <hr />
        <Link to="/reports" className="nav-link">Reports</Link>
      </nav>
    </div>
  );
}
