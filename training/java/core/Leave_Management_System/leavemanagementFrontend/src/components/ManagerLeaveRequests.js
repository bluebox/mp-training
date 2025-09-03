import React, { useState, useEffect } from "react";
import { listEmployeesLeaveRequests } from "./scripts/AdminService";
import '../styles/LeaveRequests.css';
import api from '../api/api';
import "../styles/ExitRequestsReport.css";

export default function ManagerLeaveRequests() {
  const [employeesLeaveRequests, setEmployeesLeaveRequests] = useState([]);
  const [filteredLeaves, setFilteredLeaves] = useState([]);
  const [refreshTrigger, setRefreshTrigger] = useState(0);
  const [searchStatus, setSearchStatus] = useState("");
  const [searchLeaveType, setSearchLeaveType] = useState("");

  useEffect(() => {
    listEmployeesLeaveRequests()
      .then((response) => {
        setEmployeesLeaveRequests(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, [refreshTrigger]);

  useEffect(() => {
    const filteredRequests = Object.entries(employeesLeaveRequests).reduce((acc, [employee, requests]) => {
      const matchingRequests = requests.filter(request => {
        const statusMatch = searchStatus ? request.leaveStatus.toLowerCase() === searchStatus.toLowerCase() : true;
        const typeMatch = searchLeaveType ? request.leaveType.toLowerCase() === searchLeaveType.toLowerCase() : true;
        return statusMatch && typeMatch;
      });

      if (matchingRequests.length > 0) {
        acc.push([employee, matchingRequests]);
      }
      return acc;
    }, []);

    setFilteredLeaves(filteredRequests);
  }, [searchStatus, searchLeaveType, employeesLeaveRequests]);

  const getStatusClass = (status) => {
    switch (status) {
      case "Pending":
      case "PENDING":
        return "status-pending";
      case "Accepted":
      case "ACCEPTED":
        return "status-accepted";
      case "Rejected":
      case "REJECTED":
        return "status-rejected";
      case "Cancelled":
      case "CANCELLED":
        return "status-cancelled";
      default:
        return "";
    }
  };

  const handleAction = async (id, action) => {
    try {
      if (action === "ACCEPTED") {
        await api.post(`/leaves/approve/${id}`, { withCredentials: true });
      } else if (action === "REJECTED") {
        const reason = prompt("Enter rejection reason:");
        if (!reason) return;
        await api.post(`/leaves/reject/${id}?reason=${reason}`, { withCredentials: true });
      }
      setRefreshTrigger(prev => prev + 1);
    } catch (err) {
      console.error("Error updating leave:", err);
    }
  };

  return (
    <div className="leave-requests-container">
      <h2 className="leave-requests-heading">Leave Requests</h2>

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
      <div className="table-responsive">
        <table className="leave-requests-table">
          <thead>
            <tr>
              <th>Emp Id</th>
              <th>Request Id</th>
              <th>Leave Type</th>
              <th>Leave Start Date</th>
              <th>Leave End Date</th>
              <th>Reason</th>
              <th>Leave Status</th>
              <th>Reason On Rejection</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {filteredLeaves.length === 0 ? (
              <tr>
                <td colSpan={9} style={{ textAlign: 'center' }}>No leave requests</td>
              </tr>
            ) : (filteredLeaves.map(([employee, requests]) =>
              requests.map((leaveRequest, index) => (
                <tr key={leaveRequest.requestId}>
                  {index === 0 && (
                    <td rowSpan={requests.length}>{leaveRequest.employeeId}</td>
                  )}
                  <td>{leaveRequest.requestId}</td>
                  <td>{leaveRequest.leaveType}</td>
                  <td>{leaveRequest.startDate}</td>
                  <td>{leaveRequest.endDate}</td>
                  <td>{leaveRequest.remarks}</td>
                  <td className={getStatusClass(leaveRequest.leaveStatus)}>
                    {leaveRequest.leaveStatus}
                  </td>
                  <td>
                    {leaveRequest.rejectionReason ? leaveRequest.rejectionReason : '-'}
                  </td>
                  {(leaveRequest.leaveStatus === 'PENDING') ?
                    <td>
                      <button className="buttoncss"
                        onClick={() => handleAction(leaveRequest.requestId, "ACCEPTED")}
                      >
                        Approve
                      </button>
                      <button className="buttoncss"
                        onClick={() => handleAction(leaveRequest.requestId, "REJECTED")}
                      >
                        Reject
                      </button>
                    </td>
                    : <td></td>}
                </tr>
              ))))}
          </tbody>
        </table>
      </div>
    </div>
  );
}