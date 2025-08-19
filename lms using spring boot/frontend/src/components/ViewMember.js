import React, { useEffect, useState } from "react";
import axios from "axios";

function ViewMembers() {
  const [members, setMembers] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8081/members").then((res) => {
      setMembers(res.data);
    });
  }, []);

  return (
    <div style={{ textAlign: "center", marginTop: "40px" }}>
      <h2
        style={{
          color: "#fff",
          background: "linear-gradient(135deg, #667eea, #764ba2)",
          padding: "15px",
          borderRadius: "8px",
          width: "50%",
          margin: "auto",
          marginBottom: "25px",
          boxShadow: "0 4px 10px rgba(0,0,0,0.2)",
        }}
      >
        All Members
      </h2>
      <table
        style={{
          margin: "auto",
          width: "85%",
          borderCollapse: "collapse",
          boxShadow: "0 4px 12px rgba(0,0,0,0.2)",
          borderRadius: "12px",
          overflow: "hidden",
        }}
      >
        <thead>
          <tr
            style={{
              background: "linear-gradient(135deg, #ff6a00, #ee0979)",
              color: "white",
            }}
          >
            <th style={{ padding: "12px" }}>ID</th>
            <th style={{ padding: "12px" }}>Name</th>
            <th style={{ padding: "12px" }}>Email</th>
            <th style={{ padding: "12px" }}>Mobile</th>
            <th style={{ padding: "12px" }}>Gender</th>
            <th style={{ padding: "12px" }}>Address</th>
          </tr>
        </thead>
        <tbody>
          {members.map((m, index) => (
            <tr
              key={m.memberId}
              style={{
                backgroundColor: index % 2 === 0 ? "#f3f4f6" : "#e0f7fa",
                transition: "0.3s",
              }}
              onMouseOver={(e) => (e.currentTarget.style.backgroundColor = "#ffe0b2")}
              onMouseOut={(e) =>
                (e.currentTarget.style.backgroundColor =
                  index % 2 === 0 ? "#f3f4f6" : "#e0f7fa")
              }
            >
              <td style={{ padding: "10px", fontWeight: "bold" }}>{m.memberId}</td>
              <td style={{ padding: "10px" }}>{m.memberName}</td>
              <td style={{ padding: "10px" }}>{m.memberMail}</td>
              <td style={{ padding: "10px" }}>{m.mobileNo}</td>
              <td style={{ padding: "10px" }}>{m.gender}</td>
              <td style={{ padding: "10px" }}>{m.memberAddress}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default ViewMembers;
