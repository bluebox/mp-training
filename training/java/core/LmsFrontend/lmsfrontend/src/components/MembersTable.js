import React from "react";
import axios from "axios";

const MembersTable = ({ members }) => {

  const fetchMembers = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/members");
      return response.data;
    } catch (err) {
      console.error("Failed to fetch members", err);
    }
  };

  return (
    <div className="card shadow p-3">
      <h2 className="card-title text-center mb-3"> Members List</h2>

      <button
        onClick={fetchMembers}
        className="btn btn-primary mb-3"
        style={{ width: "100px" }}
      >
         Refresh
      </button>

      {members.length === 0 ? (
        <p className="text-muted">No members found.</p>
      ) : (
        <div className="table-responsive">
          <table className="table table-bordered table-hover">
            <thead className="table-light">
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
              {members.map((member) => (
                <tr key={member.memberId}>
                  <td>{member.memberId}</td>
                  <td>{member.name}</td>
                  <td>{member.email}</td>
                  <td>{member.mobile}</td>
                  <td>{member.gender}</td>
                  <td>{member.address}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
};

export default MembersTable;
