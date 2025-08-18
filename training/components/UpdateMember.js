import React, { useState } from "react";
import axios from "axios";

const UpdateMember = () => {
  const [memberId, setMemberId] = useState("");
  const [member, setMember] = useState({
    name: "",
    email: "",
    mobile: "",
    address: "",
    gender: "MALE",
  });

  const handleChange = (e) => {
    setMember({ ...member, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.put(`http://localhost:8080/members/${memberId}`, member);
      alert("Member updated successfully!");
    } catch (error) {
      console.error("Error updating member:", error);
      alert("Failed to update member");
    }
  };

  return (
    <div>
      <h2>Update Member</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="number"
          placeholder="Member ID"
          value={memberId}
          onChange={(e) => setMemberId(e.target.value)}
          required
        />
        <br />
        <input
          type="text"
          name="name"
          placeholder="Name"
          value={member.name}
          onChange={handleChange}
        />
        <br />
        <input
          type="email"
          name="email"
          placeholder="Email"
          value={member.email}
          onChange={handleChange}
        />
        <br />
        <input
          type="number"
          name="mobile"
          placeholder="Mobile"
          value={member.mobile}
          onChange={handleChange}
        />
        <br />
        <input
          type="text"
          name="address"
          placeholder="Address"
          value={member.address}
          onChange={handleChange}
        />
        <br />
        <select name="gender" value={member.gender} onChange={handleChange}>
          <option value="MALE">MALE</option>
          <option value="FEMALE">FEMALE</option>
          <option value="OTHER">OTHER</option>
        </select>
        <br />
        <button type="submit">Update Member</button>
      </form>
    </div>
  );
};

export default UpdateMember;
