import React, { useState, useEffect, useCallback } from "react";
import { useAuth } from "../context/AuthContext";
import api from "../api/api"; 
import "./Attendance.css";

export default function Attendance() {
  const { user } = useAuth();
  const [attendance, setAttendance] = useState(null);
  const [status, setStatus] = useState("idle"); 
  const [message, setMessage] = useState("");

  const employeeId = user?.empId;

  const getAuthHeaders = () => {
    if (!user || !user.email || !user.password) return {};
    return {
      auth: { username: user.email, password: user.password },
      headers: { "Content-Type": "application/json" },
    };
  };

  const fetchAttendance = useCallback(async () => {
    if (!employeeId) return;

    setStatus("loading");
    setMessage("");

    const today = new Date().toISOString().split("T")[0];

    try {
      const res = await api.get(`/attendance/${employeeId}/${today}`,{withCredentials: true});
      setAttendance(res.data);
      setStatus("success");
    } catch (err) {
      console.warn("No attendance found for today", err);
      setAttendance(null);
      setMessage("No attendance marked today.");
      setStatus("error");
    }
  }, [employeeId, user]);

  useEffect(() => {
    fetchAttendance();
  }, [fetchAttendance]);

  const handlePunch = async () => {
    if (!employeeId) return;

    setStatus("loading");
    setMessage("");

    try {
      console.log(typeof user?.empId);
      console.log(user?.empId);
      await api.post(`/attendance/mark?employeeId=${user?.empId}`, {withCredentials: true});
      await fetchAttendance();
      setMessage("Attendance marked successfully.");
      setStatus("success");
    } catch (err) {
      console.error("Error marking attendance:", err);
      setMessage("Failed to mark attendance. Please try again.");
      setStatus("error");
    }
  };

  if (!user) return <p>Loading user data...</p>;

  return (
    <div className="attendance-container">
      <h2 className="attendance-title">Attendance</h2>

      {status === "loading" && <p>Loading attendance...</p>}
      {message && status === "error" && <p className="status-message error">{message}</p>}

      {attendance && status === "success" && (
        <div className="attendance-info">
          <p><strong>Date:</strong> {attendance.date}</p>
          <p><strong>Check-In:</strong> {attendance.checkIn || "-"}</p>
          <p><strong>Check-Out:</strong> {attendance.checkOut || "-"}</p>
        </div>
      )}

      <button
        onClick={handlePunch}
        disabled={status === "loading" || !employeeId}
        className="attendance-button"
      >
        {status === "loading" ? "Saving..." : "Mark Attendance"}
      </button>
    </div>
  );
}
