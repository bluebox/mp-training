import React from "react";
import { Link, Outlet } from "react-router-dom";

function Reports() {
  return (
    <>
      <div style={{ textAlign: "center", marginTop: "-10px" }}>
        <h2>Reports</h2>
        <Link to="/reports/overdue">
          <button>overdue Books</button>
        </Link>
        <Link to="/reports/book-count" style={{ marginLeft: "10px" }}>
          <button>Books Per Category</button>
        </Link>
        <Link to="/reports/active-members" style={{ marginLeft: "10px" }}>
          <button>Active members with issued books</button>
        </Link>
      </div>
      <Outlet />
    </>
  );
}

export default Reports;
