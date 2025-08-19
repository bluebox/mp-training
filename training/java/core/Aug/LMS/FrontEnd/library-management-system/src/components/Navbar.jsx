import { Link } from "react-router-dom";

function Navbar() {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
      <div className="container-fluid">
        <Link className="navbar-brand" to="/">Library Dashboard</Link>
        <button 
          className="navbar-toggler" 
          type="button" 
          data-bs-toggle="collapse" 
          data-bs-target="#navbarNav"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav ms-auto">
            <li className="nav-item">
              <Link className="nav-link" to="/books">Books</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/members">Members</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/issue-return">Issue & Return</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/reports">Reports</Link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;