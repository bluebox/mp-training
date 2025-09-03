import React, { useEffect, useState } from "react";
import api from "../api/api";
import "./ExitRequest.css";

export default function ExitRequestList({ employeeId }) {
  const [exitRequests, setExitRequests] = useState([]);
  const [status, setStatus] = useState("idle");
  const [message, setMessage] = useState("");
  const [searchTerm, setSearchTerm] = useState("");
  const [filterStatus, setFilterStatus] = useState("");

  useEffect(() => {
    const fetchMyRequests = async () => {
      setStatus("loading");
      setMessage("");
      try {
        const res = await api.get(`/exit-requests/get/${employeeId}`,{withCredentials: true});
        setExitRequests(res.data.exitRequests || []);
        setStatus("success");
      } catch (err) {
        setMessage(err.response?.data?.message || "Failed to fetch exit requests.");
        setStatus("error");
      }
    };
    if (employeeId) fetchMyRequests();
  }, [employeeId]);

  const handleCancel = async (requestId) => {
    setStatus("loading");
    setMessage("");
    try {
      await api.put(`/exit-requests/cancel/${requestId}`,{withCredentials: true});
      setMessage("Exit request canceled successfully.");
      setStatus("success");
      setExitRequests(exitRequests.filter((req) => req.requestId !== requestId));
    } catch (err) {
      setMessage(err.response?.data?.message || "Failed to cancel exit request.");
      setStatus("error");
    }
  };

  const filteredRequests = exitRequests.filter((req) => {
    const matchesStatus = filterStatus ? req.requestStatus === filterStatus : true;
    const matchesSearch = searchTerm
      ? Object.values(req)
          .join(" ")
          .toLowerCase()
          .includes(searchTerm.toLowerCase())
      : true;
    return matchesStatus && matchesSearch;
  });

  return (
    <div className="exit-request-table-container">
      <h2>My Exit Requests</h2>

      {message && <div className={`status-message ${status}`}>{message}</div>}

      <div style={{ marginBottom: "10px", display: "flex", gap: "10px", flexWrap: "wrap" }}>
        <input
          type="text"
          placeholder="Search..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          style={{
            padding: "6px 10px",
            borderRadius: "5px",
            border: "1px solid #d1d5db",
            flex: "1",
          }}
        />
        <select
          value={filterStatus}
          onChange={(e) => setFilterStatus(e.target.value)}
          style={{
            padding: "6px 10px",
            borderRadius: "5px",
            border: "1px solid #d1d5db",
          }}
        >
          <option value="">All Status</option>
          <option value="PENDING">PENDING</option>
          <option value="ACCEPTED">ACCEPTED</option>
          <option value="REJECTED">REJECTED</option>
          <option value="CANCELLED">CANCELLED</option>
        </select>
      </div>

      <div
        className="exit-request-table-wrapper"
        style={{ maxHeight: "400px", overflow: "auto" }} 
      >
        <table className="exit-request-table">
          <thead>
            <tr>
              <th>Exit Date</th>
              <th>Request Submitted At</th>
              <th>Status</th>
              <th>Request Reason</th>
              <th>Rejection Reason</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {filteredRequests.length === 0 ? (
              <tr>
                <td colSpan="6">No exit requests found.</td>
              </tr>
            ) : (
              filteredRequests.map((req) => (
                <tr key={req.requestId}>
                  <td>{new Date(req.exitDate).toLocaleDateString("en-GB").replace(/\//g, "-")}</td>
                  <td>
                    {new Date(req.requestSubmittedAt)
                      .toLocaleDateString("en-GB")
                      .replace(/\//g, "-")}
                  </td>
                  <td>{req.requestStatus}</td>
                  <td>{req.remarks}</td>
                  <td>{req.adminRemarks}</td>
                  <td>
                    {req.requestStatus === "PENDING" ? (
                      <button
                        className="cancel-button"
                        onClick={() => handleCancel(req.requestId)}
                        disabled={status === "loading"}
                      >
                        Cancel
                      </button>
                    ) : (
                      "-"
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
