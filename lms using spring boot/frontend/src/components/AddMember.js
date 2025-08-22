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

  const [errors, setErrors] = useState({}); // store validation messages

  const handleChange = (e) => {
    setMember({ ...member, [e.target.name]: e.target.value });
    setErrors({ ...errors, [e.target.name]: "" }); // clear error while typing
  };

  const validate = () => {
    let newErrors = {};
    const nameRegex = /^[A-Za-z ]+$/;
    const mobileRegex = /^[6-9][0-9]{9}$/;

    if (!member.memberName.trim() || !nameRegex.test(member.memberName)) {
      newErrors.memberName = "Name must contain only alphabets and spaces.";
    }
    if (!member.memberMail.includes("@")) {
      newErrors.memberMail = "Enter a valid email address.";
    }
    if (!mobileRegex.test(member.mobileNo)) {
      newErrors.mobileNo = "Mobile must be 10 digits and start with 6,7,8,9.";
    }
    if (!member.gender) {
      newErrors.gender = "Please select gender.";
    }
    if (!member.memberAddress.trim()) {
      newErrors.memberAddress = "Address is required.";
    } else if (member.memberAddress.length > 60) {
      newErrors.memberAddress = "Address should not exceed 60 characters.";
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0; 
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validate()) return; 

    await axios.post("http://localhost:8081/members", member);
    alert("Member added successfully!");
    setMember({
      memberName: "",
      memberMail: "",
      mobileNo: "",
      gender: "",
      memberAddress: "",
    });
    setErrors({});
  };

  const inputStyle = {
    padding: "10px",
    borderRadius: "8px",
    border: "1px solid #ccc",
    outline: "none",
    transition: "0.3s",
  };

  const errorStyle = {
    color: "red",
    fontSize: "13px",
    marginTop: "-10px",
    marginBottom: "5px",
    textAlign: "left",
  };

  return (
    <div style={{ display: "flex", justifyContent: "center", marginTop: "50px" }}>
      <div
        style={{
          width: "400px",
          padding: "30px",
          borderRadius: "12px",
          boxShadow: "0 4px 10px rgba(0,0,0,0.2)",
          background: "linear-gradient(135deg, #89f7fe, #66a6ff)",
        }}
      >
        <h2 style={{ textAlign: "center", marginBottom: "20px", color: "#fff" }}>
          Add Member
        </h2>
        <form
          onSubmit={handleSubmit}
          style={{ display: "flex", flexDirection: "column", gap: "15px" }}
        >
          <label style={{ fontWeight: "bold", color: "#fff" }}>Name:</label>
          <input
            type="text"
            name="memberName"
            placeholder="Enter full name"
            value={member.memberName}
            onChange={handleChange}
            required
            style={inputStyle}
            onFocus={(e) => (e.target.style.border = "2px solid green")}
            onBlur={(e) => (e.target.style.border = "1px solid #ccc")}
          />
          {errors.memberName && <div style={errorStyle}>{errors.memberName}</div>}

          <label style={{ fontWeight: "bold", color: "#fff" }}>Email:</label>
          <input
            type="email"
            name="memberMail"
            placeholder="Enter email"
            value={member.memberMail}
            onChange={handleChange}
            required
            style={inputStyle}
            onFocus={(e) => (e.target.style.border = "2px solid green")}
            onBlur={(e) => (e.target.style.border = "1px solid #ccc")}
          />
          {errors.memberMail && <div style={errorStyle}>{errors.memberMail}</div>}

          <label style={{ fontWeight: "bold", color: "#fff" }}>Mobile:</label>
          <input
            type="text"
            name="mobileNo"
            placeholder="Enter mobile number"
            value={member.mobileNo}
            onChange={handleChange}
            required
            maxLength="10"
            style={inputStyle}
            onFocus={(e) => (e.target.style.border = "2px solid green")}
            onBlur={(e) => (e.target.style.border = "1px solid #ccc")}
          />
          {errors.mobileNo && <div style={errorStyle}>{errors.mobileNo}</div>}

          <label style={{ fontWeight: "bold", color: "#fff" }}>Gender:</label>
          <select
            name="gender"
            value={member.gender}
            onChange={handleChange}
            required
            style={inputStyle}
            onFocus={(e) => (e.target.style.border = "2px solid green")}
            onBlur={(e) => (e.target.style.border = "1px solid #ccc")}
          >
            <option value="">Select Gender</option>
            <option value="MALE">Male</option>
            <option value="FEMALE">Female</option>
             {/* <option value="OTHERS">OTHERS</option> */}
          </select>
          {errors.gender && <div style={errorStyle}>{errors.gender}</div>}

          <label style={{ fontWeight: "bold", color: "#fff" }}>Address:</label>
          <input
            type="text"
            name="memberAddress"
            placeholder="Enter address (max 60 chars)"
            value={member.memberAddress}
            onChange={handleChange}
            required
            maxLength="60"
            style={inputStyle}
            onFocus={(e) => (e.target.style.border = "2px solid green")}
            onBlur={(e) => (e.target.style.border = "1px solid #ccc")}
          />
          {errors.memberAddress && (
            <div style={errorStyle}>{errors.memberAddress}</div>
          )}

          <button
            type="submit"
            style={{
              padding: "12px",
              borderRadius: "8px",
              border: "none",
              backgroundColor: "#ff6a00",
              color: "#fff",
              fontWeight: "bold",
              cursor: "pointer",
              transition: "0.3s",
            }}
            onMouseOver={(e) => (e.target.style.backgroundColor = "#ff4500")}
            onMouseOut={(e) => (e.target.style.backgroundColor = "#ff6a00")}
          >
            Add Member
          </button>
        </form>
      </div>
    </div>
  );
}

export default AddMember;
