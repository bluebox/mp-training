import React, { useState, useEffect } from "react";
import axios from "axios";

function ActiveMembers() {
  const [members, setMembers] = useState([]);
  const [message, setMessage] = useState("");

  useEffect(() => {
    fetchActiveMembers();
  }, []);

  const fetchActiveMembers = async () => {
    try {
      const res = await axios.get("http://localhost:8081/reports/active-members");
      setMembers(res.data);
      setMessage("Members with Active Issued Books");
    } catch (err) {
      console.error(err);
      setMembers([]);
      setMessage("Failed to fetch active members");
    }
  };

  return (
    <div style={{ padding: "20px", textAlign: "center" }}>
      <h2>📚 Active Members</h2>
      {message && <h3>{message}</h3>}

      <table
        style={{
          width: "80%",
          margin: "auto",
          borderCollapse: "collapse",
          boxShadow: "0px 4px 10px rgba(0,0,0,0.1)",
        }}
      >
        <thead>
          <tr style={{ background: "#007BFF", color: "white" }}>
            <th style={{ padding: "12px" }}>Member ID</th>
            <th style={{ padding: "12px" }}>Member Name</th>
            <th style={{ padding: "12px" }}>Email</th>
            <th style={{ padding: "12px" }}>Mobile</th>
          </tr>
        </thead>
        <tbody>
          {members.length > 0 ? (
            members.map((m, index) => (
              <tr
                key={m.memberId}
                style={{
                  background: index % 2 === 0 ? "#f9f9f9" : "white",
                  textAlign: "center",
                }}
              >
                <td style={{ padding: "10px" }}>{m.memberId}</td>
                <td style={{ padding: "10px" }}>{m.memberName}</td>
                <td style={{ padding: "10px" }}>{m.email}</td>
                <td style={{ padding: "10px" }}>{m.mobile}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="4" style={{ padding: "15px" }}>
                No active members found
              </td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}

export default ActiveMembers;