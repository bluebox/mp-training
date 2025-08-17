import React, { useEffect, useState } from "react";
import MembersTable from "./MembersTable";

const MembersPage = () => {
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
        setMembers(data);
        setLoading(false);
      })
      .catch((err) => {
        setError(err.message);
        setLoading(false);
      });
  }, []);

  if (loading) return <p className="text-center mt-3">Loading members...</p>;
  if (error) return <p className="text-center text-danger mt-3">{error}</p>;

  return <MembersTable members={members} />;
};

export default MembersPage;
