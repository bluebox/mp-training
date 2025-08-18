import React, { useEffect, useState } from "react";
import axios from "axios";

const ViewMembers = () => {
  const [members, setMembers] = useState([]);

  useEffect(() => {
    fetchMembers();
  }, []);

  const fetchMembers = async () => {
    try {
      const response = await axios.get("http://localhost:8080/members");
      setMembers(response.data);
    } catch (error) {
      console.error("Error fetching members:", error);
    }
  };

  return (
    <div>
      <h2>All Members</h2>
      <table border="1" cellPadding="10">
        <thead>
          <tr>
            <th>ID</th>
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
};

export default ViewMembers;
