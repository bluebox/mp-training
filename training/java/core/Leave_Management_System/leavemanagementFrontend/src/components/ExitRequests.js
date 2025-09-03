import React, { useEffect, useState } from "react";
import api from "../api/api"; 

export default function ExitRequests({ managerId }) {
  const [requests, setRequests] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchRequests = async () => {
      try {
        const response = await api.get(
          `/manager/exit-requests?managerId=${managerId}`,
          { withCredentials: true }
        );
        setRequests(response.data);
      } catch (err) {
        console.error("Error fetching exit requests:", err);
        setError("Failed to fetch exit requests.");
      } finally {
        setLoading(false);
      }
    };

    fetchRequests();
  }, [managerId]);

  if (loading) return <div>Loading exit requests...</div>;
  if (error) return <div style={{ color: "red" }}>{error}</div>;

  return (
    <div style={containerStyle}>
      <h2 style={headerStyle}>Employee Exit Requests</h2>
      <table style={tableStyle}>
        <thead>
          <tr>
            <th style={thStyle}>Request ID</th>
            <th style={thStyle}>Employee Name</th>
            <th style={thStyle}>Exit Date</th>
            <th style={thStyle}>Status</th>
            <th style={thStyle}>Remarks</th>
          </tr>
        </thead>
        <tbody>
          {requests.map((req) => (
            <tr key={req.requestId} style={rowStyle}>
              <td style={tdStyle}>{req.requestId}</td>
              <td style={tdStyle}>{req.employeeName}</td>
              <td style={tdStyle}>{req.exitDate}</td>
              <td style={{ ...tdStyle, ...statusStyle(req.requestStatus) }}>
                {req.requestStatus}
              </td>
              <td style={tdStyle}>{req.requestRemarks}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

const containerStyle = {
  padding: "20px",
  background: "#f9fafb",
  borderRadius: "10px",
  boxShadow: "0 2px 8px rgba(0,0,0,0.05)",
};

const headerStyle = {
  fontSize: "20px",
  fontWeight: "600",
  marginBottom: "15px",
};

const tableStyle = {
  width: "100%",
  borderCollapse: "collapse",
};

const thStyle = {
  borderBottom: "2px solid #e5e7eb",
  padding: "10px",
  textAlign: "left",
  fontWeight: "600",
};

const tdStyle = {
  borderBottom: "1px solid #e5e7eb",
  padding: "10px",
};


const statusStyle = (status) => {
  switch (status.toUpperCase()) {
    case "ACCEPTED":
      return { color: "green", fontWeight: "600" };
    case "REJECTED":
    case "CANCELLED":
      return { color: "red", fontWeight: "600" };
    case "PENDING":
      return { color: "#f59e0b", fontWeight: "600" };
    default:
      return {};
  }
};

const rowStyle = {
  backgroundColor: "#ffffff",
};
