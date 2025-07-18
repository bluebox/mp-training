import React, { useState, useEffect } from "react";
import Member from "./Member";

const ViewAllMembers = () => {
  const [members, setMembers] = useState([]);

  useEffect(() => {
    const fetchMembers = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/members", {
          method: "GET",
          credentials: "include",
        });
        if (!response.ok) {
          throw new Error("Failed to fetch members");
        }
        const responseData = await response.json();
        setMembers(responseData.data);
      } catch (error) {
        console.error("Error fetching members:", error);
      }
    };
    fetchMembers();
  }, []);

  return (
    <div className="mt-5">
      <table>
        <thead>
          <tr>
            <th className="px-4 py-3">Member ID</th>
            <th className="px-4 py-3">Name</th>
            <th className="px-4 py-3">Email</th>
            <th className="px-4 py-3">Mobile</th>
            <th className="px-4 py-3">Gender</th>
            <th className="px-4 py-3">Address</th>
          </tr>
        </thead>
        <tbody>
          {members.map((member) => (
            <Member key={member.memberId} member={member} />
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ViewAllMembers;
