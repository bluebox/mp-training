import React, { useState, useEffect } from "react";
import { Routes, Route, Link } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import ApplyLeave from "./ApplyLeave";
import Attendance from "./Attendence";
import ExitRequestForm from "./ExitRequestForm";
import ExitRequestList from "./ExitRequestList";
import api, { getuserDetails } from "../api/api";
import EmployeeLeaveHistory from "./EmployeeLeaveHistory";

export default function EmployeeDashboard() {
  const { user, logout } = useAuth();

  const Home = ({ user }) => {
    const [employee, setEmployee] = useState([]);

    useEffect(() => {
      if (user?.email) {
        getuserDetails(user?.email)
          .then((response) => setEmployee(response.data))
          .catch((error) =>
            console.error("Error fetching employee details:", error)
          );
      }
    }, [user]);

    const labelStyle = { fontWeight: "600", color: "#4a5568" };
    const valueStyle = { fontWeight: "500", color: "#2d3748" };

    return (
      <div
        style={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          justifyContent: "center",
          padding: "50px",
          backgroundColor: "#eef2f6",
          fontFamily:
            "'Segoe UI', 'Roboto', 'Helvetica Neue', Arial, sans-serif",
        }}
      >
        <div
          style={{
            backgroundColor: "#ffffff",
            border: "1px solid #d3dce0",
            borderRadius: "8px",
            padding: "40px",
            width: "100%",
            height: "80vh",
            maxWidth: "700px",
            boxShadow: "0 4px 12px rgba(0, 0, 0, 0.05)",
            overflowY: "auto",
          }}
        >
          <h1
            style={{
              fontSize: "2rem",
              color: "#007bff",
              fontWeight: "600",
              textAlign: "center",
              marginBottom: "30px",
              borderBottom: "2px solid #e1e7f0",
              paddingBottom: "15px",
            }}
          >
             Employee Dashboard
          </h1>
          <p
            style={{
              fontSize: "1.2rem",
              color: "#007bff",
              textAlign: "center",
              lineHeight: "1.6",
              marginBottom: "30px",
            }}
          >
            Welcome, {employee.employeeName}  !!!
          </p>

          <div
            style={{
              display: "grid",
              gridTemplateColumns: "1fr 2fr",
              gap: "20px",
              padding: "20px",
              backgroundColor: "#f4f7f9",
              borderRadius: "6px",
              overflowY: "auto",
            }}
          >
            <p style={labelStyle}>Employee ID:</p>
            <p style={valueStyle}>{employee.employeeId}</p>

            <p style={labelStyle}>Employee Name:</p>
            <p style={valueStyle}>{employee.employeeName}</p>

            <p style={labelStyle}>Email Address:</p>
            <p style={valueStyle}>{employee.employeeEmail}</p>

            <p style={labelStyle}>Account Role:</p>
            <p style={valueStyle}>{employee.employeeRole}</p>

            <p style={labelStyle}>Contact No:</p>
            <p style={valueStyle}>{employee.employeeMobileNo}</p>

            <p style={labelStyle}> Date Of Birth:</p>
            <p style={valueStyle}>{new Date(employee.employeeDOB).toLocaleDateString("en-GB").replace(/\//g, "-")}</p>

            <p style={labelStyle}> Date Of Join:</p>
            <p style={valueStyle}>{new Date(employee.employeeDOJ).toLocaleDateString("en-GB").replace(/\//g, "-")}</p>


            <p style={labelStyle}>Gender:</p>
            <p style={valueStyle}>{employee.employeeGender}</p>

            <p style={labelStyle}>Address:</p>
            <p style={valueStyle}>{employee.employeeAddress}</p>
          </div>

          <p
            style={{
              marginTop: "40px",
              fontSize: "1rem",
              color: "#718096",
              textAlign: "center",
            }}
          >
            Please use the navigation bar to manage employees, leave requests,
            and more.
          </p>
        </div>
      </div>
    );
  };

  const linkStyle = {
    color: "#fff",
    textDecoration: "none",
    fontWeight: "500",
  };

  const buttonStyle = {
    padding: "8px 16px",
    backgroundColor: "#1e40af",
    color: "#fff",
    border: "none",
    borderRadius: "5px",
    cursor: "pointer",
    fontWeight: "500",
  };

  return (
    <div style={{ display: "flex", flexDirection: "column", height: "100vh" }}>
      
      <nav
        style={{
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
          padding: "12px 20px",
          background: "#2563eb",
          color: "#fff",
        }}
      >
        <div style={{ display: "flex", gap: 20 }}>
          <Link style={linkStyle} to="/employeedashboard">
            Home
          </Link>
          <Link style={linkStyle} to="/employeedashboard/apply-leave">
            Apply Leave
          </Link>
          <Link style={linkStyle} to="/employeedashboard/leave-history">
            Leave History
          </Link>
          <Link style={linkStyle} to="/employeedashboard/attendance">
            Attendance
          </Link>
          <Link style={linkStyle} to="/employeedashboard/exit-request">
            Exit Request
          </Link>
          <Link style={linkStyle} to="/employeedashboard/exit-history">
            My Exit Requests
          </Link>
        </div>

        <div style={{ display: "flex", alignItems: "center", gap: 20 }}>
          {user && <span style={{ fontWeight: "500" }}></span>}
          <button onClick={logout} style={buttonStyle}>
            Logout
          </button>
        </div>
      </nav>

      {/*  */}
      <main style={{ flex: 1, padding: 20, background: "#f9fafb" }}>
        <Routes>
          <Route path="/" element={<Home user={user} />} />
          <Route path="apply-leave" element={<ApplyLeave />} />
          <Route path="attendance" element={<Attendance />} />
          <Route path="exit-request" element={<ExitRequestForm />} />
          <Route
            path="exit-history"
            element={<ExitRequestList employeeId={user.empId} />}
          />
          <Route
            path="leave-history"
            element={<EmployeeLeaveHistory employeeId={user.empId} />}
          />
        </Routes>
      </main>
    </div>
  );
}
