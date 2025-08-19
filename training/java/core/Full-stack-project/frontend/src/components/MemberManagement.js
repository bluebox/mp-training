import React from "react";
import { Link, Outlet } from "react-router-dom";

function MemberManagement() {
  return (
    <>
      <div style={{ textAlign: "center", marginTop: "-10px" }}>
        <h2>Member Management</h2>
        <Link to="/members/add">
          <button>Add Member</button>
        </Link>
        <Link to="/members/view" style={{ marginLeft: "10px" }}>
          <button>View Members</button>
        </Link>

        <Link to="/members/update" style={{ marginLeft: "10px" }}>
          <button>Update Member</button>
        </Link>
      </div>
      <Outlet />
    </>
  );
}

export default MemberManagement;
