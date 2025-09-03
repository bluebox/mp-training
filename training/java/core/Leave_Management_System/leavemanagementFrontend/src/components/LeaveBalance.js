import React, { useEffect, useState } from "react";
import axios from "axios";
import api from "../api/api";

const LeaveBalance = ({ employeeId, refreshTrigger }) => {
  const [balance, setBalance] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchLeaveBalance();
  }, [employeeId, refreshTrigger]); 

  const fetchLeaveBalance = async () => {
    setLoading(true);
    try {
      const res = await api.get(
        `/leaves/leaveBalance?employeeId=${employeeId}`,
        { withCredentials: true }
      );
      setBalance(res.data);
    } catch (err) {
      console.error("Error fetching leave balance:", err);
    } finally {
      setLoading(false);
    }
  };

  if (loading) return <p>Loading leave balance...</p>;
  if (!balance) return <p>No leave balance found.</p>;

  return (
    <div>
      <h3 className="text-sm font-semibold mb-2 text-center">Leave Balance</h3>
      <table className="w-full text-sm text-center border-collapse">
        <thead>
          <tr className="bg-gray-100">
            <th className="border px-2 py-1">Leave Type</th>
            <th className="border px-2 py-1">Balance</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td className="border px-2 py-1">Casual</td>
            <td className="border px-2 py-1 text-blue-600">{balance.casual_LEAVE}</td>
          </tr>
          <tr>
            <td className="border px-2 py-1">Earned</td>
            <td className="border px-2 py-1 text-green-600">{balance.earned_LEAVE}</td>
          </tr>
          <tr>
            <td className="border px-2 py-1">Sick</td>
            <td className="border px-2 py-1 text-red-600">{balance.sick_LEAVE}</td>
          </tr>
          <tr>
            <td className="border px-2 py-1">Optional</td>
            <td className="border px-2 py-1 text-purple-600">{balance.optional_LEAVE}</td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

export default LeaveBalance;
