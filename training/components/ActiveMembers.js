import React, { useEffect, useState } from "react";
import axios from "axios";

function ActiveMembers() {
  const [members, setMembers] = useState([]);

  const fetchActiveMembers = async () => {
    try {
      const response = await axios.get("http://localhost:8080/reports/active-members");
      setMembers(response.data);
    } catch (error) {
      console.error("Error fetching active members:", error);
    }
  };

  useEffect(() => {
    fetchActiveMembers();
  }, []);

  return (
    <div>
      <h3> Members with Active Issued Books</h3>
      <table border="1" cellPadding="10">
        <thead>
          <tr>
            <th>Member ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Mobile</th>
            <th>Address</th>
            <th>Gender</th>
          </tr>
        </thead>
        <tbody>
          {members.map((m) => (
            <tr key={m.memberId}>
              <td>{m.memberId}</td>
              <td>{m.name}</td>
              <td>{m.email}</td>
              <td>{m.mobile}</td>
              <td>{m.address}</td>
              <td>{m.gender}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default ActiveMembers;
