import React, { useEffect, useState } from "react";
import axios from "axios";

function ViewMembers() {
  const [members, setMembers] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/members").then((res) => {
      console.log(res.data);
      setMembers(res.data);
    });
  }, []);

  return (
    <div style={{ textAlign: "center" }}>
      <h2>All Members</h2>
      <table border="1" style={{ margin: "auto", width: "80%" }}>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Mobile</th>
            <th>Gender</th>
            <th>Address</th>
          </tr>
        </thead>
        <tbody>
          {members.map((m) => (
            <tr key={m.memberId}>
              <td>{m.memberId}</td>
              <td>{m.memberName}</td>
              <td>{m.memberMail}</td>
              <td>{m.mobileNo}</td>
              <td>{m.gender}</td>
              <td>{m.memberAddress}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default ViewMembers;
