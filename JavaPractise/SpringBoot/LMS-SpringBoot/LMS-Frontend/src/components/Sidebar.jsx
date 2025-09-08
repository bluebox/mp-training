import { useState } from "react";
import { Link } from "react-router-dom";
import "./Sidebar.css";

export default function Sidebar() {
  const [openMenu, setOpenMenu] = useState(null);

  const toggleMenu = (menu) => {
    setOpenMenu(openMenu === menu ? null : menu);
  };

  return (
    <div className="sidebar">
      <h2 className="sidebar-title">LMS</h2>
      <div>
        <button onClick={() => toggleMenu("books")} className="menu-btn">
          Book Management 
        </button>
        {openMenu === "books" && (
          <div className="submenu">
            <Link to="/books/add" className="submenu-link">Add Book</Link>
            <Link to="/books" className="submenu-link">View Books</Link>
            {/* <Link to="/books/update" className="submenu-link">Update Book</Link>
            <Link to="/books/availability" className="submenu-link">Update Availability</Link> */}
          </div>
        )}
      </div>

      <hr className="divider" />
      <div>
        <button onClick={() => toggleMenu("members")} className="menu-btn">
          Member Management 
        </button>
        {openMenu === "members" && (
          <div className="submenu">
            <Link to="/members/add" className="submenu-link">Add Member</Link>
            <Link to="/members" className="submenu-link">View Members</Link>
            {/* <Link to="/members/update" className="submenu-link">Update Member</Link> */}
          </div>
        )}
      </div>

      <hr className="divider" />

      <div>
        <button onClick={() => toggleMenu("issues")} className="menu-btn">
          Issue & Return 
        </button>
        {openMenu === "issues" && (
          <div className="submenu">
            <Link to="/issues/issue" className="submenu-link">Issue Book</Link>
            <Link to="/issues/return" className="submenu-link">Return Book</Link>
            <Link to="/issues" className="submenu-link">View Issued Records</Link>
          </div>
        )}
      </div>

      <hr className="divider" />

      <div>
        <button onClick={() => toggleMenu("reports")} className="menu-btn">
          Reports 
        </button>
        {openMenu === "reports" && (
          <div className="submenu">
            <Link to="/reports/overdue" className="submenu-link"> Overdue Books</Link>
            <Link to="/reports/category" className="submenu-link">Books per Category</Link>
            <Link to="/reports" className="submenu-link">Active Issued Members</Link>
          </div>
        )}
      </div>
    </div>
  );
}
