import React, { useState, useEffect } from "react";
import { Link, Routes, Route } from "react-router-dom";
import ApplyLeave from "./ApplyLeave";
import Attendance from "./Attendence";
import ExitRequests from "./ExitRequests"; 
import api from "../api/api";
import { useMessage } from "../context/MessageContext";
import PendingLeaves  from "./PendingLeaves";
import { useAuth } from "../context/AuthContext";
import TeamMembers from "./TeamMembers";
import AttendanceTable from "./AttendanceTable";
import YearlyManagerReports from "./YearlyManagerReports";
import EmployeeLeaveHistory from "./EmployeeLeaveHistory";
import EmployeeAttendence from "./EmployeeAttendence";
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
             Manager Dashboard
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
export default function ManagerDashboard() {
  const { showMessage } = useMessage();

  const [leaveRequests, setLeaveRequests] = useState([]);
  const [loadingLeaves, setLoadingLeaves] = useState(true);
   const { user,logout } = useAuth(); 
  useEffect(() => {
    const fetchLeaves = async () => {
      setLoadingLeaves(true);
      try {
        const leaveRes = await api.get(`manager/leaves/pending?managerId=${user.empId}`,{withCredentials: true});
        setLeaveRequests(leaveRes.data);
      } catch (err) {
        console.error("Error fetching leave requests:", err);
      } finally {
        setLoadingLeaves(false);
      }
    };

    fetchLeaves();
  }, []);

  const handleLeaveAction = async (id, action) => {
    try {
      await api.post(`/leaves/${id}/${action.toLowerCase()}`, {withCredentials: true});
      setLeaveRequests((prev) =>
        prev.map((req) => (req.id === id ? { ...req, status: action } : req))
      );
      showMessage(
        `Leave request ${action.toLowerCase()}!`,
        action === "Approved" ? "success" : "error"
      );
    } catch (err) {
      console.error("Error updating leave:", err);
    }
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
	
	  <div style={{ display: "flex", gap: "20px" }}>
      <Link style={linkStyle} to="/managerdashboard/">Home</Link>
	    <Link style={linkStyle} to="/managerdashboard/leave-requests">Leave Requests</Link>
	    <Link style={linkStyle} to="/managerdashboard/exit-requests">Exit Requests</Link>
	    <Link style={linkStyle} to="/managerdashboard/attendance">Attendance</Link>
	    <Link style={linkStyle} to="/managerdashboard/apply-leave">Apply Leave</Link>
      <Link style={linkStyle} to="/managerdashboard/leave-history">Leave History</Link>

	    <Link style={linkStyle} to="/managerdashboard/Team-members">Team Members</Link>
		<Link style={linkStyle} to="/managerdashboard/attedance-table">Team Attendence report</Link>
		<Link style={linkStyle} to="/managerdashboard/yearly-attendance">Yearly Attendance</Link>
	  </div>

	
	        <div style={{ display: "flex", alignItems: "center", gap: 20 }}>
          {user && <span style={{ fontWeight: "500" }}></span>}
          <button onClick={logout} style={buttonStyle}>
            Logout
          </button>
        </div>
	</nav>


      <div className="p-4">
        <Routes>
          {/* <Route
            path="leave-requests"
            element={
              <LeaveRequests
                leaveRequests={leaveRequests}
                handleLeaveAction={handleLeaveAction}
                loading={loadingLeaves}
              />
            }
          /> */}
          <Route
            path="leave-requests"
            element={<PendingLeaves managerId={user.empId}/>}></Route>
              <Route
            path="Team-members"
            element={<TeamMembers managerId={user.empId}/>}></Route>
            <Route path="/" element={<Home user={user} />} />
          <Route path="exit-requests" element={<ExitRequests managerId={user.empId}/>} />
          <Route path="attendance" element={<Attendance />} />
          <Route path="apply-leave" element={<ApplyLeave />} />
		  <Route path="attedance-table" element={<AttendanceTable managerId={user.empId}/>} />
		  <Route path="yearly-attendance" element={<YearlyManagerReports managerId={user.empId}/>} />
          <Route path="leave-history" element={<EmployeeLeaveHistory employeeId={user.empId}/>} />
          <Route
            path="*"
            element={<div>Welcome Manager! Choose a section from the navbar.</div>}
          />
          <Route path="employee-attendence/:id" element={<EmployeeAttendence/>}/>
        </Routes>
      </div>
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

export const getuserDetails = (email) => {
  return api.get(`/employee/getUserdetails?email=${email}`);
};
