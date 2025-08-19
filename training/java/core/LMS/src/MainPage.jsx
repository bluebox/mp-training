import React from "react";
import { useNavigate } from "react-router-dom";

export default function MainPage() {
  const navigate = useNavigate();

  return (
    <div className="page">
      <div className="card">
        
        <h1 className="title">Library Management System</h1>

        <button onClick={() => navigate("/books")} className="btn blue">Books</button>
        <button onClick={() => navigate("/members")} className="btn green">Members</button>
        <button onClick={() => navigate("/issue-return")} className="btn yellow">Issue & Return</button>
        <button onClick={() => navigate("/reports")} className="btn purple">Reports</button>
      </div>
    </div>
  );
}
