
import React, { useEffect, useState } from "react";
import axios from "axios";
import api from "../api/api";

const PendingLeaves = ({ managerId }) => {
  const [leaves, setLeaves] = useState([]);
  const [loading, setLoading] = useState(true);
  const [rejectionReasons, setRejectionReasons] = useState({});

  useEffect(() => {
    fetchLeaves();
  }, []);

  const fetchLeaves = async () => {
    try {
      const res = await api.get(`/manager/leaves/pending?managerId=${managerId}`, {
        withCredentials: true, 
      });
      setLeaves(res.data);
    } catch (err) {
      console.error("Error fetching pending leaves:", err);
    } finally {
      setLoading(false);
    }
  };

  const approveLeave = async (id) => {
    try {
      await api.post(`/leaves/approve/${id}`, {}, { withCredentials: true });
      alert("Leave approved successfully");
      fetchLeaves();
    } catch (err) {
      console.error("Error approving leave:", err);
    }
  };

  const rejectLeave = async (id) => {
    const reason = rejectionReasons[id];
    if (!reason || reason.trim() === "") {
      alert("Please enter a rejection reason!");
      return;
    }
    try {
      await api.post(
        `/leaves/reject/${id}?reason=${encodeURIComponent(reason)}`,
        {},
        { withCredentials: true }
      );
      alert("Leave rejected successfully");
      fetchLeaves();
    } catch (err) {
      console.error("Error rejecting leave:", err);
    }
  };

  if (loading) return <p>Loading pending leaves...</p>;

  return (
    <div className="p-4">
      <h2 className="text-xl font-bold mb-4">Pending Leave Requests</h2>
  
        
        <table className="w-full border-collapse border border-gray-300">
          <thead>
            <tr className="bg-gray-200">
              <th className="border p-2">Employee</th>
              <th className="border p-2">Leave Type</th>
              <th className="border p-2">Start Date</th>
              <th className="border p-2">End Date</th>
              <th className="border p-2">Remarks</th>
              <th className="border p-2">Actions</th>
            </tr>
          </thead>
          {leaves.length === 0 ? (
           <tr>
            <td colSpan={6} style={{textAlign: 'center',color:'#ff0000'}}>No pending leave requests</td>
           </tr>
                  ) : (

          <tbody>
            {leaves.map((leave) => (
              <tr key={leave.requestId} className="text-center">
                <td className="border p-2">{leave.employeeName}</td>
                <td className="border p-2">{leave.leaveType}</td>
                <td className="border p-2">{leave.startDate}</td>
                <td className="border p-2">{leave.endDate}</td>
                <td className="border p-2">{leave.remarks}</td>
                <td className="border p-2">
                  <button
                  
                    onClick={() => approveLeave(leave.requestId)}
                    className="buttoncss"
                  >
                    Approve
                  </button>
                  <button
                    onClick={() => rejectLeave(leave.requestId)}
                    className="buttoncss"
                  >
                    Reject
                  </button>
                  <input
                    type="text"
                    placeholder="Rejection reason"
                    value={rejectionReasons[leave.requestId] || ""}
                    onChange={(e) =>
                      setRejectionReasons({
                        ...rejectionReasons,
                        [leave.requestId]: e.target.value,
                      })
                    }
                    className="ml-2 border px-2 py-1 rounded"
                  />
                </td>
              </tr>
            ))}
          </tbody>
           )}
        </table>
     
    </div>
  );
};

export default PendingLeaves;