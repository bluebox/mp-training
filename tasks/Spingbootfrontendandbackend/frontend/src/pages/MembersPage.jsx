import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { getMembers } from "../services/membersService";

export default function MembersPage() {
  const navigate = useNavigate();
  const [members, setMembers] = useState([]);
  const [showTable, setShowTable] = useState(false);

  const handleViewMembers = async () => {
    try {
      const res = await getMembers();
      setMembers(res.data);
      setShowTable(true);
    } catch (err) {
      console.error(err);
      alert("Failed to fetch members");
    }
  };

  return (
    <div className="content">
      <h2>Members</h2>

      <div className="button-group">
        <button onClick={() => navigate("/members/add")}>Add Member</button>
        <button onClick={handleViewMembers}>View All Members</button>
      </div>

      {showTable && members.length > 0 && (
        <table border="1" style={{ marginTop: "20px", width: "100%" }}>
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Email</th>
              <th>Mobile</th>
              <th>Gender</th>
              <th>Address</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {members.map((m) => (
              <tr key={m.memberid}>
                <td>{m.memberid}</td>
                <td>{m.name}</td>
                <td>{m.email}</td>
                <td>{m.mobile}</td>
                <td>{m.gender}</td>
                <td>{m.address}</td>
                <td>
                  <button onClick={() => navigate(`/members/update/${m.memberid}`)}>
                    Update
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

      {showTable && members.length === 0 && <p>No members available.</p>}

      <div style={{ marginTop: "20px" }}>
        <button onClick={() => navigate(-1)}>Back</button>
      </div>
    </div>
  );
}
