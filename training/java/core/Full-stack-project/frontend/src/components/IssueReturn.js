import React from "react";
import { Link, Outlet } from "react-router-dom";

function IssueReturn() {
  return (
    <>
      <div style={{ textAlign: "center", marginTop: "-10px" }}>
        <h2>Issue / Return Book</h2>

        <Link to="/issues/issue">
          <button>Issue Book</button>
        </Link>

        <Link to="/issues/return" style={{ marginLeft: "10px" }}>
          <button>Return Book</button>
        </Link>

        <Link to="/issues/view" style={{ marginLeft: "10px" }}>
          <button>View All Issues</button>
        </Link>
      </div>
      <Outlet />
    </>
  );
}

export default IssueReturn;
