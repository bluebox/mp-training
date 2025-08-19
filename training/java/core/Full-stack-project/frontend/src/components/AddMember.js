import React, { useState } from "react";
import axios from "axios";

function AddMember() {
  const [member, setMember] = useState({
    memberName: "",
    memberMail: "",
    mobileNo: "",
    gender: "",
    memberAddress: "",
  });

  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const handleChange = (e) => {
    if (e.target.name == "mobileNo") {
      e.target.value = e.target.value
        .replaceAll(/[^0-9]/g, "")
        .substring(0, 10);
    } else if (e.target.name == "memberName") {
      e.target.value = e.target.value.replaceAll(/[^a-zA-Z ]/g, "");
    }
    setMember({ ...member, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setSuccess("");

    try {
      const res = await axios.post("http://localhost:8080/members", member);
      setSuccess(res.data.message);
      setSuccess(" Member added successfully!");
      setMember({
        memberName: "",
        memberMail: "",
        mobileNo: "",
        gender: "",
        memberAddress: "",
      });
    } catch (err) {
      if (err.response && err.response.data) {
        if (err.response.data.errors) {
          setError(err.response.data.errors.join(","));
        } else {
          setError(err.response.data.message || "Failed to add member!");
        }
      } else {
        setError("Server error. Please try again.");
      }
    }
  };

  return (
    <div style={{ textAlign: "center" }}>
      <h2>Add Member</h2>

      {error && <p style={{ color: "red" }}>{error}</p>}

      {success && <p style={{ color: "green" }}>{success}</p>}

      <form onSubmit={handleSubmit}>
        <input
          style={formStyle}
          type="text"
          name="memberName"
          placeholder="Enter Your Name"
          value={member.memberName}
          onChange={handleChange}
          required
        />
        <br />
        <br />
        <input
          style={formStyle}
          type="email"
          name="memberMail"
          placeholder="Enter Your Email"
          value={member.memberMail}
          onChange={handleChange}
          required
        />
        <br />
        <br />
        <input
          style={formStyle}
          type="text"
          name="mobileNo"
          placeholder="Enter Your Mobile Number"
          value={member.mobileNo}
          onChange={handleChange}
          required
        />
        <br />
        <br />
        <select
          style={formStyle}
          name="gender"
          value={member.gender}
          onChange={handleChange}
          placeholder="select Your Gender"
          required
        >
          <option value="">Select Gender</option>
          <option value="MALE">Male</option>
          <option value="FEMALE">Female</option>
        </select>
        <br />
        <br />
        <input
          style={formStyle}
          type="text"
          name="memberAddress"
          placeholder="Enter Your Address"
          value={member.memberAddress}
          onChange={handleChange}
          required
        />
        <br />
        <br />
        <button type="submit" style={buttonStyle}>
          Add Member
        </button>
      </form>
    </div>
  );
}

const formStyle = {
  borderRadius: "5px",
  width: "200px",
  border: "2px sold black",
};

const buttonStyle = {
  borderRadius: "5px",
  width: "100px",
};

export default AddMember;
