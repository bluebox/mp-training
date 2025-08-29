import React, { useState, useEffect } from "react";
import { listEmployeesLeaveRequests } from "./scripts/AdminService";
import '../styles/LeaveRequests.css';
import api from '../api/api';
import "../styles/ExitRequestsReport.css";

export default function ManagerLeaveRequests() {
  const [employeesLeaveRequests, setEmployeesLeaveRequests] = useState([]);
  const [refreshTrigger, setRefreshTrigger] = useState(0);

  useEffect(() => {
    listEmployeesLeaveRequests()
      .then((response) => {
        setEmployeesLeaveRequests(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, [refreshTrigger]);

  const getStatusClass = (status) => {
    switch (status) {
      case "PENDING":
        return "status-pending";
      case "ACCEPTED":
        return "status-approved";
      case "Rejected":
        return "status-rejected";
      default:
        return "";
    }
  };

  const handleAction = async (id,action) => {
    try {
      if (action === "ACCEPTED") {
        await api.post(`/leaves/approve/${id}`,{withCredentials: true});
      } else if (action === "REJECTED") {
        const reason = prompt("Enter rejection reason:");
        if (!reason) return;
        await api.post(`/leaves/reject/${id}?reason=${reason}`, {withCredentials: true});
      }
      setRefreshTrigger(prev => prev + 1);
    } catch (err) {
      console.error("Error updating leave:", err);
    }
  };

  return (
    <div className="leave-requests-container">
      <h2 className="leave-requests-heading">Leave Requests</h2>
      {employeesLeaveRequests.length === 0 ? (
        <p>No leave requests</p>
      ) : (
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
              {Object.entries(employeesLeaveRequests).map(([employee, requests]) =>
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
                ))
              )}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}