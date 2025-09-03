import React, { useEffect, useState } from "react";
import axios from "axios";
import api from "../api/api";
export default function AttendanceTable({ managerId }) {
  const [attendance, setAttendance] = useState([]);
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");

  useEffect(() => {
    const fetchAttendance = async () => {
      setLoading(true);
      setMessage("");
      try {
        const res = await api.get(
          `/manager/totalAttendence?managerId=${managerId}`,
          { withCredentials: true } 
        );
        setAttendance(res.data || []);
      } catch (err) {
        console.error("Error fetching attendance:", err);
        setMessage("Failed to fetch attendance records.");
      } finally {
        setLoading(false);
      }
    };

    if (managerId) fetchAttendance();
  }, [managerId]);

  return (
    <div className="p-4">
      <h2 className="text-lg font-semibold mb-3">Team Attendance</h2>

      {loading && <p>Loading attendance records...</p>}
      {message && <p className="text-red-500">{message}</p>}

      {!loading && attendance.length === 0 && (
        <p>No attendance records found.</p>
      )}

      {attendance.length > 0 && (
        <table className="w-full border-collapse border border-gray-300 text-sm">
          <thead className="bg-gray-100">
            <tr>
              <th className="border p-2">Employee</th>
              <th className="border p-2">Date</th>
              <th className="border p-2">Check In</th>
              <th className="border p-2">Check Out</th>
              <th className="border p-2">Status</th>
              <th className="border p-2">Working Hours</th>
            </tr>
          </thead>
          <tbody>
            {attendance.map((record) => (
              <tr key={record.id} className="text-center">
                <td className="border p-2">{record.employeeName}</td>
                <td className="border p-2">
                  {new Date(record.date).toLocaleDateString()}
                </td>
                <td className="border p-2">{record.checkIn || "-"}</td>
                <td className="border p-2">{record.checkOut || "-"}</td>
                <td
                  className={`border p-2 ${
                    record.attendenceStatus === "PRESENT"
                      ? "text-green-600"
                      : record.attendenceStatus === "ABSENT"
                      ? "text-red-600"
                      : "text-yellow-600"
                  }`}
                >
                  {record.attendenceStatus}
                </td>
                <td className="border p-2">
                  {record.workingHrs !== null ? record.workingHrs : "-"}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}