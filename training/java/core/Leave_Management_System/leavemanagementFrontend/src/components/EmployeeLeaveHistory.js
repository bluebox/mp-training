import React, { useEffect, useState } from "react";
import axios from "axios";
import "./EmployeeLeaveHistory.css";

export default function EmployeeLeaveHistory({ employeeId }) {
  const [leaves, setLeaves] = useState([]);
  const [filteredLeaves, setFilteredLeaves] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const [searchStatus, setSearchStatus] = useState("");
  const [searchLeaveType, setSearchLeaveType] = useState("");
  const[message,setMessage]=useState("");
  const[msgtype,setmsgtype]=useState("");

  const fetchLeaves = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/leaves/${employeeId}`,
        { withCredentials: true }
      );
      setLeaves(response.data);
      setFilteredLeaves(response.data);
    } catch (err) {
      setError("Failed to fetch leave history");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchLeaves();
  }, [employeeId]);


  const handleCancel = async (requestId) => {
    if (!window.confirm("Are you sure you want to cancel this leave?")) return;

    try {
      await axios.post(
        `http://localhost:8080/api/leaves/cancel/${requestId}`,
        {},
        { withCredentials: true }
      );
      setMessage("Leave cancelled successfully.");
      setmsgtype("sucess");
      fetchLeaves();
    } catch (err) {
      console.error("Error cancelling leave:", err);
      setMessage("Leave cancelled successfully.");
      setmsgtype("error");
    }
  };


  useEffect(() => {
    let filtered = leaves;
    if (searchStatus) {
      filtered = filtered.filter(
        (l) => l.leaveStatus.toLowerCase() === searchStatus.toLowerCase()
      );
    }
    if (searchLeaveType) {
      filtered = filtered.filter(
        (l) => l.leaveType.toLowerCase() === searchLeaveType.toLowerCase()
      );
    }
    setFilteredLeaves(filtered);
  }, [searchStatus, searchLeaveType, leaves]);

  const formatDate = (date) => {
    if (!date) return "-";
    const d = new Date(date);
    return d.toLocaleDateString("en-GB").replace(/\//g, "-");
  };

  if (loading) return <p>Loading leave history...</p>;
  if (error) return <p className="error-message">{error}</p>;

  const  messagecolour={ sucess:{color:"green"}, error:{color:"red"}, info:{color:"blue"}  };


  return (
    <div className="leave-history-container">
      <h2 className="leave-history-title">Leave History </h2>

      <center><strong><p style={msgtype?messagecolour[msgtype]:{}} >{message}</p></strong></center>
      <div className="leave-filters">
        <select
          value={searchStatus}
          onChange={(e) => setSearchStatus(e.target.value)}
        >
          <option value="">Filter by Status</option>
          <option value="PENDING">Pending</option>
          <option value="ACCEPTED">Accepted</option>
          <option value="REJECTED">Rejected</option>
		  <option value="CANCELLED">Cancelled</option>
        </select>

        <select
          value={searchLeaveType}
          onChange={(e) => setSearchLeaveType(e.target.value)}
        >
          <option value="">Filter by Leave Type</option>
          <option value="CASUAL_LEAVE">Casual Leave</option>
          <option value="SICK_LEAVE">Sick Leave</option>
          <option value="OPTIONAL_LEAVE">Optional Leave</option>
          <option value="EARNED_LEAVE">Earned Leave</option>
        </select>
      </div>

      <div className="table-wrapper">
        <table className="leave-table">
          <thead>
            <tr>
              <th>Request ID</th>
              <th>Leave Type</th>
              <th>Start Date</th>
              <th>End Date</th>
              <th>Remarks</th>
              <th>Status</th>
              <th>Rejection Reason</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {filteredLeaves.length === 0 ? (
              <tr>
                <td colSpan="8">No leave records found.</td>
              </tr>
            ) : (
              filteredLeaves.map((leave) => (
                <tr key={leave.requestId}>
                  <td>{leave.requestId}</td>
                  <td>{leave.leaveType.replace("_", " ")}</td>
                  <td>{formatDate(leave.startDate)}</td>
                  <td>{formatDate(leave.endDate)}</td>
                  <td>{leave.remarks}</td>
                  <td className={`status-${leave.leaveStatus.toLowerCase()}`}>
                    {leave.leaveStatus}
                  </td>
                  <td>{leave.rejectionReason || "-"}</td>
                  <td>
                    {leave.leaveStatus === "PENDING" && (
                      <button
                        onClick={() => handleCancel(leave.requestId)}
                        className="cancel-button"
                      >
                        Cancel
                      </button>
                    )}
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}
