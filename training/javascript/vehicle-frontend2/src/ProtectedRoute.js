import React from "react";
import { Navigate } from "react-router-dom";

export default function ProtectedRoute({ children, roleRequired }) {
    const role = localStorage.getItem("role");
    console.log(role);
    if (!role) {
        return <Navigate to="/login" />;
    }
    if (role !== roleRequired) {
        return <Navigate to="/not-authorized" />;
    }
    return children;
}
