
import React, { useEffect, useState } from "react";
import axios from "axios";
import api from "../api/api";
import { useNavigate } from "react-router-dom";



const TeamMembers = ({ managerId }) => {
  const [employees, setEmployees] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchTeamMembers();
  }, [managerId]);
  const navigate = useNavigate();
    const handleAction = async (employeeid) => {
   navigate(`/managerdashboard/employee-attendence/${employeeid}`);
  };

  const fetchTeamMembers = async () => {
    try {
      const res = await api.get(
        `/manager/team-members?managerId=${managerId}`,
        { withCredentials: true }
      );
      setEmployees(res.data);
    } catch (err) {
      console.error("Error fetching team members:", err);
    } finally {
      setLoading(false);
    }
  };

  if (loading) return <p>Loading team members...</p>;

  if (!employees || employees.length === 0) {
    return <p>No team members found.</p>;
  }

  return (
    <div className="p-4">
      <h2 className="text-lg font-semibold mb-3">Team Members</h2>
      <table className="w-full border-collapse border border-gray-300 text-sm">
        <thead className="bg-gray-100">
          <tr>
            <th className="border p-2">ID</th>
            <th className="border p-2">Name</th>
            <th className="border p-2">Role</th>
            <th className="border p-2">Email</th>
            <th className="border p-2">Mobile</th>
            <th>View Attendence</th>
          </tr>
        </thead>
        <tbody>
          {employees.map((emp) => (
            <tr key={emp.employeeId} className="text-center">
              <td className="border p-2">{emp.employeeId}</td>
              <td className="border p-2">{emp.employeeName}</td>
              <td className="border p-2">{emp.employeeRole}</td>
              <td className="border p-2">{emp.employeeEmail}</td>
              <td className="border p-2">{emp.employeeMobileNo}</td>
              <td><button style={{ backgroundColor: "#088220ea", color: "white", border: "none", fontWeight: "bold" }} onClick={() => handleAction(emp.employeeId)} >View Attendence</button></td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default TeamMembers;