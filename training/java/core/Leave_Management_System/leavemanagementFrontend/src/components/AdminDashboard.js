import React, { useState, useEffect } from "react";
import { Routes, Route, Link } from "react-router-dom";
import api from "../api/api";
import Employees from "./Employees";
import Managers from "./Managers";
import { getuserDetails } from "./scripts/AdminService";
import ManagerLeaveRequests from "./ManagerLeaveRequests";
import ExitRequestsReport from "./exitRequestsReport";
import { useAuth } from "../context/AuthContext";
import YearlyAttendance from "./YearlyAttendance";
import AssignedMembers from "./AssignedMembers";
import EmployeeAttendence from "./EmployeeAttendence";

const Home = ({ user }) => {
  const [employee, setEmployee] = useState([]);
  
  useEffect(() => {
    if (user?.email) {
      getuserDetails(user?.email)
        .then((response) => {
          setEmployee(response.data);
        })
        .catch((error) => {
          console.error("Error fetching employee details:", error);
        });
    }
  }, [user]);


  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
        padding: "50px",
        backgroundColor: "#eef2f6",
        fontFamily: "'Segoe UI', 'Roboto', 'Helvetica Neue', Arial, sans-serif",
        
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
             overflowY :  "auto"
          }}
        >
          📜Administrator Dashboard
        </h1>
        <p
          style={{
            fontSize: "1.2rem",
            color: "#007bff", 
            textAlign: "center",
            lineHeight: "1.6",
            marginBottom: "30px",
             overflowY :  "auto"
          }}
        >
          Welcome, {employee.employeeRole} 🧑‍💻 !!!
        </p>

        <div
          style={{
            display: "grid",
            gridTemplateColumns: "1fr 2fr",
            gap: "20px",
            padding: "20px",
            backgroundColor: "#f4f7f9", 
            borderRadius: "6px",
            overflowY :  "auto"
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

          <p style={labelStyle}>🎊Date Of Birth:</p>
          <p style={valueStyle}>{employee.employeeDOB}</p>

          <p style={labelStyle}>🧑‍💼Date Of Join:</p>
          <p style={valueStyle}>{employee.employeeDOJ}</p>

          <p style={labelStyle}>Gender :</p>
          <p style={valueStyle}>{employee.employeeGender}</p>

          <p style={labelStyle}>Address :</p>
          <p style={valueStyle}>{employee.employeeAddress}</p>
        </div>
        <p
          style={{
            marginTop: "40px",
            fontSize: "1rem",
            color: "#718096", 
            textAlign: "center"
          }}
        >
          Please use the navigation bar to manage employees, leave requests, and more.
        </p>
      </div >
    </div >
  );
};

export default function AdminDashboard() {

  const [employees, setEmployees] = useState([]);
  const [managers, setManagers] = useState([]);
  const [managerLeaveRequests, setManagerLeaveRequests] = useState([]);
  const [exitRequests, setExitRequests] = useState([]);
  const { user,logout } = useAuth();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const empRes = await api.get("/employees");
        setEmployees(empRes.data);

        const mgrRes = await api.get("/managers");
        setManagers(mgrRes.data);

        const leaveRes = await api.get("/leaves/pending");
        setManagerLeaveRequests(leaveRes.data);

        const exitRes = await api.get("/exits/pending");
        setExitRequests(exitRes.data);

      } catch (err) {
        console.error("Error fetching admin data:", err);
      }
    };
    fetchData();
  }, []);

  const handleExitAction = async (id, action) => {
    try {
      await api.post(`/exits/${id}/${action.toLowerCase()}`);
      setExitRequests((prev) =>
        prev.map((req) =>
          req.id === id ? { ...req, status: action } : req
        )
      );
    } catch (err) {
      console.error("Error updating exit:", err);
    }
  };

  const handleManagerLeaveAction = async (id, action) => {
    try {
      await api.post(`/leaves/${id}/${action.toLowerCase()}`);
      setManagerLeaveRequests((prev) =>
        prev.map((req) =>
          req.id === id ? { ...req, status: action } : req
        )
      );
    } catch (err) {
      console.error("Error updating leave:", err);
    }
  };

  return (
    <div style={{ position: "fixed", top: 0, left: 0, width: "100%", height: "100%", "background-color": "#f8f9fa" }}>
      <nav style={{
        display: "flex",
        gap: 20,
        padding: "12px 20px",
        background: "#1e40af",
        color: "#fff",
        justifyContent: "space-between",
        alignItems: "center"
      }}>
        <div style={{
          display: "flex",
          justifyContent: "flex-start",
          fontSize: "1.2rem",
          fontWeight: "bold"
        }}>
          🧑‍💻 Admin Dashboard
        </div>
        <div style={{ display: "flex", gap: 20 }}>
          <Link style={linkStyle} to="/admindashboard/">Home</Link>
          <Link style={linkStyle} to="/admindashboard/employees">Employees</Link>
          <Link style={linkStyle} to="/admindashboard/managers">Managers</Link>
          <Link style={linkStyle} to="/admindashboard/leave-requests">Leave Requests</Link>
          <Link style={linkStyle} to="/admindashboard/exit-requests">Exit Requests</Link>
          <Link style={linkStyle} to="/admindashboard/yearly-attendance">Yearly Attendance</Link>
		      <Link style={linkStyle} to="/">Logout</Link>
        </div>
		

      </nav>
      <main style={{ flex: 1, padding: 20, background: "#f9fafb", width: "100%", overflow: "auto" }}>
        <div style={{ overflow: "auto" }}>
          <Routes>
            <Route path="employees" element={<Employees employees={employees} />} />
            <Route path="managers" element={<Managers managers={managers} />} />
            <Route path="leave-requests" element={<ManagerLeaveRequests requests={managerLeaveRequests} onAction={handleManagerLeaveAction} />} />
            <Route path="exit-requests" element={<ExitRequestsReport />} />
            <Route path="yearly-attendance" element={<YearlyAttendance />} />
            <Route path="*" element={<Home user={user} />} />
            <Route path="assigned-employees/:id" element={<AssignedMembers/>}/>
            <Route path="employee-attendence/:id" element={<EmployeeAttendence/>}/>
            {/* <Route path="*" element={<div>Welcome Admin! Choose a section from the navbar.</div>} /> */}
          </Routes>
        </div>
      </main>
    </div>
  );
}

const linkStyle = {
  color: "#fff",
  textDecoration: "none",
  fontWeight: "500"
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
 
const labelStyle = {
  fontWeight: "600",
  color: "#2d3748",
  fontSize: "1rem"
};

const valueStyle = {
  fontSize: "1rem",
  color: "#4a5568"
};