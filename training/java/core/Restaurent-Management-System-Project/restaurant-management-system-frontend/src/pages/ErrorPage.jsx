import React from "react";
import { useRouteError, Link } from "react-router-dom";

const ErrorPage = () => {
  const errorResponse = useRouteError();
    const role=localStorage.getItem("role");

  return (
    <div className="container d-flex flex-column justify-content-center align-items-center text-center vh-100">
      <h1 className="display-4 text-danger fw-bold mb-3">
        Error {errorResponse?.status || "Unknown"}
      </h1>

      <p className="lead text-muted mb-4">
        {errorResponse?.statusText || "Something went wrong"}
      </p>

      <img
        src="/images/errorAnimation.gif"
        alt="Error"
        className="img-fluid rounded shadow mb-4"
        style={{ maxWidth: "350px" }}
      />

        <Link to={role==="ROLE_ADMIN" ? "/admin" : "/staff"} className="btn btn-primary px-4 py-2 rounded-pill shadow-sm">
          Go Back Home
        </Link>
    </div>
  );
};

export default ErrorPage;