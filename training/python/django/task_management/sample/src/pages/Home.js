import React from "react";
import { Link } from "react-router-dom";

const Home = () => {
  return (
    <div className="container text-center mt-5">
      <h1>Welcome to Task Management System</h1>
      <p className="lead">Organize your team and projects efficiently.</p>
      <div className="mt-4">
        <Link to="/login" className="btn btn-primary me-2">
          Login
        </Link>
        <Link to="/register" className="btn btn-secondary">
          Register
        </Link>
      </div>
    </div>
  );
};

export default Home;
