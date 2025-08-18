import React, { useState } from "react";
import axios from "axios";

const AddMember = () => {
  const [member, setMember] = useState({
    name: "",
    email: "",
    mobile: "",
    address: "",
    gender: "MALE",
  });

  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const handleChange = (e) => {
    setMember({ ...member, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setSuccess("");

    try {
      const res = await axios.post("http://localhost:8080/members", member);
      setSuccess(res.data.message || "Member added successfully!");
      setMember({
        name: "",
        email: "",
        mobile: "",
        address: "",
        gender: "MALE",
      });
    } catch (err) {
      if (err.response && err.response.data) {
        // Handle validation errors (Spring Boot @Valid errors)
        if (typeof err.response.data === "object") {
          const messages = Object.values(err.response.data).join(", ");
          setError(messages);
        } else {
          setError(err.response.data.message || "Failed to add member!");
        }
      } else {
        setError("Server error. Please try again.");
      }
    }
  };

  return (
    <div>
      <h2>Add Member</h2>

      {/* Display success and error messages */}
      {success && <p style={{ color: "green" }}>{success}</p>}
      {error && <p style={{ color: "red" }}>{error}</p>}

      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="name"
          placeholder="Name"
          value={member.name}
          onChange={handleChange}
          required
        />
        <br />
        <input
          type="email"
          name="email"
          placeholder="Email"
          value={member.email}
          onChange={handleChange}
          required
        />
        <br />
        <input
          type="number"
          name="mobile"
          placeholder="Mobile"
          value={member.mobile}
          onChange={handleChange}
          required
        />
        <br />
        <input
          type="text"
          name="address"
          placeholder="Address"
          value={member.address}
          onChange={handleChange}
          required
        />
        <br />
        <select name="gender" value={member.gender} onChange={handleChange}>
          <option value="MALE">MALE</option>
          <option value="FEMALE">FEMALE</option>
          <option value="OTHER">OTHER</option>
        </select>
        <br />
        <button type="submit">Add Member</button>
      </form>
    </div>
  );
};

export default AddMember;
