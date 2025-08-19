import axios from "axios";
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

function ViewMembersPage() {
  const nav = useNavigate();

const [members,setMembers]=useState([]);
  useEffect(() => {
    fetchMembers();
  }, []);

  const fetchMembers = async () => {
    try {
       
            const response =await axios.get("http://localhost:8082/members");
            const data=response.data;
            // console.log(data);
      setMembers(data);
    } catch {
      alert("Failed to fetch Members");
    }
  };



  return (
    <div style={{ padding: "30px" }}>
      <h2 style={{ textAlign: "center", marginBottom: "20px" }}>
        All Members
      </h2>

      <table
        style={{
          width: "100%",
          borderCollapse: "collapse",
          marginBottom: "20px",
        }}
      >
        <thead>
          <tr style={{ backgroundColor: "#f2f2f2" }}>
            <th style={thStyle}>Member ID</th>
            <th style={thStyle}>Name</th>
            <th style={thStyle}>Email</th>
            <th style={thStyle}>Mobile</th>
            <th style={thStyle}>Gender</th>
            <th style={thStyle}>Address</th>
            <th style={thStyle}>Action</th>
          </tr>
        </thead>
        <tbody>
          {members.map((m) => (
            <tr key={m.memberId}>
              <td style={tdStyle}>{m.memberId}</td>
              <td style={tdStyle}>{m.name}</td>
              <td style={tdStyle}>{m.email}</td>
              <td style={tdStyle}>{m.mobile}</td>
              <td style={tdStyle}>{m.gender}</td>
              <td style={tdStyle}>{m.address}</td>
              <td style={tdStyle}>
               <button
  style={btnStyle}
  onClick={() => {nav(`/update-member/${m.memberId}`,{state:m})
            // console.log(m);
  }}>
  Update Details
</button>

              </td>
            </tr>
          ))}
        </tbody>
      </table>

      <div style={{ textAlign: "center" }}>
        <button
          onClick={() => nav("/members")}
          style={{
            backgroundColor: "#e74c3c",
            color: "white",
            border: "none",
            padding: "10px 20px",
            borderRadius: "8px",
            cursor: "pointer",
          }}
        >
          Back
        </button>
      </div>
    </div>
  );
}


const thStyle = {
  border: "1px solid #ddd",
  padding: "10px",
  textAlign: "center",
  fontWeight: "bold",
};
const tdStyle = {
  border: "1px solid #ddd",
  padding: "10px",
  textAlign: "center",
};
const btnStyle = {
  backgroundColor: "#3498db",
  color: "white",
  border: "none",
  padding: "6px 12px",
  borderRadius: "6px",
  cursor: "pointer",
};

export default ViewMembersPage;
