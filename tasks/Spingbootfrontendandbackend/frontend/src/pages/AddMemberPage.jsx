import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { addMember } from "../services/membersService";
import "./HomePage.css";

export default function AddMemberPage() {
  const navigate = useNavigate();

  const [member, setMember] = useState({
    name: "",
    email: "",
    mobile: "",
    gender: "MALE", 
    address: "",
  });

  const handleChange = (e) => {
    setMember({ ...member, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!member.name || !member.email || !member.mobile || !member.address) {
      alert("Please fill all fields");
      return;
    }

    try {
      await addMember(member); 
      alert("Member added successfully!");
      navigate("/members");
    } catch (error) {
      console.error(error);
      alert(
        "Failed to add member."
      );
    }
  };

  return (
    <div className="content">
      <h2>Add Member</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="name"
          placeholder="Name"
          value={member.name}
          onChange={handleChange}
        />
        <input
          type="email"
          name="email"
          placeholder="Email"
          value={member.email}
          onChange={handleChange}
        />
        <input
          type="text"
          name="mobile"
          placeholder="Mobile"
          value={member.mobile}
          onChange={handleChange}
        />

        <label>Gender:</label>
        <select name="gender" value={member.gender} onChange={handleChange}>
          <option value="MALE">Male</option>
          <option value="FEMALE">Female</option>
        </select>

        <input
          type="text"
          name="address"
          placeholder="Address"
          value={member.address}
          onChange={handleChange}
        />

        <button type="submit">Add Member</button>
      </form>

      <div style={{ marginTop: "20px" }}>
        <button onClick={() => navigate("/members")}>Back</button>
      </div>
    </div>
  );
}
