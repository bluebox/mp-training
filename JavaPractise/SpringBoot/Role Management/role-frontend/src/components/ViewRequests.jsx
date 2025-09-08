import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import "../styles/ActiveUsers.css";

export default function UserRequests() {
  const [requests, setRequests] = useState([]);
  const [search, setSearch] = useState("");
  const [searchId, setSearchId] = useState("");
  const navigate = useNavigate();
  const { getAuthHeader } = useAuth();

  const fetchRequests = async () => {
    try {
      const res = await fetch("http://localhost:8080/api/enrollments/requests", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          ...getAuthHeader(),
        },
      });

      const body = await res.json();

      if (!res.ok || !body.success) {
        alert(body.message || `Failed (${res.status})`);
        if (res.status === 401 || res.status === 403) navigate("/login");
        return;
      }

      setRequests(Array.isArray(body.data) ? body.data : []);
    } catch (err) {
      console.error("Error fetching requests:", err);
      alert("Error fetching requests");
    }
  };
  const handleAction = async (url, action) => {
    try {
      const res = await fetch(url, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          ...getAuthHeader(),
        },
      });

      const body = await res.json();

      if (!res.ok || !body.success) {
        alert(body.message || `Failed to ${action}`);
        return;
      }

      alert(body.message || `${action} successful`);
      fetchRequests();
    } catch (err) {
      console.error(`Error while ${action}:`, err);
      alert(`Error while ${action}`);
    }
  };

  const handleAccept = (id) =>
    handleAction(
      `http://localhost:8080/api/enrollments/requests/${id}/accept`,
      "accept request"
    );

  const handleReject = (id) =>
    handleAction(
      `http://localhost:8080/api/enrollments/requests/${id}/reject`,
      "reject request"
    );

  const handleActive = (id) =>
    handleAction(
      `http://localhost:8080/api/enrollments/requests/${id}/active`,
      "activate request"
    );

  const handleInactive = (id) =>
    handleAction(
      `http://localhost:8080/api/enrollments/requests/${id}/inactive`,
      "inactivate request"
    );

  useEffect(() => {
    fetchRequests();
  }, []);

  const filteredUsers = requests
    .filter((u) =>
      Object.values(u).some(
        (val) =>
          val &&
          val.toString().trim().toLowerCase().includes(search.toString().trim().toLowerCase())
      )
    )
    .filter((u) =>
      searchId.trim() === ""
        ? true
        : u.reqId?.toString().trim().toLowerCase().includes(searchId.trim().toLowerCase())
    );

  return (
    <div>
      <h2>User Requests</h2>
      <input
        type="text"
        placeholder="Search by ID"
        value={searchId}
        onChange={(e) => setSearchId(e.target.value)}
        style={{
          marginLeft: "10px",
          marginBottom: "10px",
          padding: "5px",
          width: "150px",
        }}
      />
      <input
        type="text"
        placeholder="Search by any field..."
        value={search}
        onChange={(e) => setSearch(e.target.value)}
        style={{
          marginLeft: "10px",
          marginBottom: "10px",
          padding: "5px",
          width: "300px",
        }}
      />
      <table border="1" cellPadding="5" className="user-table">
        <thead>
          <tr className="table-header">
            <th>Request ID</th> <th>First Name</th> <th>Last Name</th><th>Username</th>
            <th>Age</th> <th>Gender</th> <th>EmpID</th> <th>Mobile</th><th>Email</th>
            <th>Country</th><th>State</th> <th>City</th><th>User Status</th>
            <th>Approval Status</th><th>Created Time</th> <th>Updated Time</th>
            <th>Status Actions</th><th>Approve Actions</th>
          </tr>
        </thead>
        <tbody>
          {filteredUsers.length === 0 ? (
            <tr>
              <td colSpan="17" style={{ textAlign: "center" }}>
                No data found
              </td>
            </tr>
          ) : (
            filteredUsers.map((req) => (
              <tr key={req.reqId}>
                <td>{req.reqId}</td>
                <td>{req.firstName}</td>
                <td>{req.lastName}</td>
                <td>{req.username}</td>
                <td>{req.age}</td>
                <td>{req.gender.charAt(0).toUpperCase() + req.gender.slice(1).toLowerCase()}</td>
                <td>{req.empId}</td>
                <td>{req.mobile}</td>
                <td>{req.email}</td>
                <td>{req.country}</td>
                <td>{req.state}</td>
                <td>{req.city}</td>
                <td>{req.activeStatus.charAt(0).toUpperCase() + req.activeStatus.slice(1).toLowerCase()}</td>
                <td>{req.approvalStatus.charAt(0).toUpperCase() + req.approvalStatus.slice(1).toLowerCase()}</td>
                <td>{req.createdAt}</td>
                <td>{req.updatedAt}</td>
                <td>
                  {req.activeStatus === "ACTIVE" ? (
                    <button onClick={() => handleInactive(req.reqId)} 
                    className="action-btn danger">
                      Inactivate
                    </button>
                  ) : (
                    <button onClick={() => handleActive(req.reqId)} className="action-btn">
                      Activate
                    </button>
                  )}
                </td>
                <td>
                  {req.approvalStatus === "PENDING" ? (
                    <>
                      <button onClick={() => handleAccept(req.reqId)} 
                      disabled={req.activeStatus === "INACTIVE"}
                      className="action-btn">
                        Accept
                      </button>
                      <button onClick={() => handleReject(req.reqId)}
                       disabled={req.activeStatus === "INACTIVE"}
                       className="action-btn danger">
                        Reject
                      </button>
                    </>
                  ) : (
                    <b>{req.approvalStatus}</b>
                  )}
                </td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
}
