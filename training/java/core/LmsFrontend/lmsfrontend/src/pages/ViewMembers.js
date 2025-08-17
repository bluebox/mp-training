import React, { useEffect, useState } from "react";
import MembersTable from "../components/MembersTable";

const ViewMembers = () => {
  const [members, setMembers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    fetch("http://localhost:8080/api/members")
      .then((res) => {
        if (!res.ok) throw new Error("Failed to fetch members");
        return res.json();
      })
      .then((data) => {
        console.log("Fetched members:", data); 
        setMembers(data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Error fetching members:", err);
        setError(err.message);
        setLoading(false);
      });
  }, []);

  if (loading) {
    return (
      <div className="container mt-4">
        <div className="alert alert-info">Loading members...</div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="container mt-4">
        <div className="alert alert-danger">{error}</div>
      </div>
    );
  }

  return (
    <div className="container mt-4">
      <div className="card shadow p-4">
        <h2 className="card-title mb-3">All Members</h2>
        <MembersTable members={members} />
      </div>
    </div>
  );
};

export default ViewMembers;
