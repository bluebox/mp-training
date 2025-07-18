import React from "react";
import { Navigate, Outlet } from "react-router";
import { useAuth } from "../context/AuthContext";

const ProtectedRoute = ({ allowedRoles = [] }) => {
    const { currentUser, isAuthenticated, isLoading } = useAuth();

    // Show loading while checking authentication status
    if (isLoading) {
        return <div className="text-center my-5">Loading...</div>;
    }

    // If not authenticated, redirect to login
    if (!isAuthenticated) {
        return <Navigate to="/login" replace />;
    }

    // If roles are specified and user doesn't have any required role, redirect to home
    if (
        allowedRoles.length > 0 &&
        !allowedRoles.some(role => currentUser.role === role)
    ) {
        return <Navigate to="/" replace />;
    }

    // If authenticated and has permissions, render the child routes
    return <Outlet />;
};

export default ProtectedRoute;
