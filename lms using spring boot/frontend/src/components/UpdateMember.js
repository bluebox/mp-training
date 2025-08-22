import React, { useState, useEffect } from "react";
import axios from "axios";

function UpdateMember() {
  const [search, setSearch] = useState("");
  const [suggestions, setSuggestions] = useState([]);
  const [member, setMember] = useState({
    memberId: "",
    memberName: "",
    memberMail: "",
    mobileNo: "",
    gender: "",
    memberAddress: "",
  });

  const [errors, setErrors] = useState({}); // validation errors

  useEffect(() => {
    if (search.trim() === "") {
      setSuggestions([]);
      return;
    }
    axios.get("http://localhost:8081/members").then((res) => {
      const filtered = res.data.filter((m) =>
        m.memberName.toLowerCase().includes(search.toLowerCase())
      );
      setSuggestions(filtered);
    });
  }, [search]);

  const validateField = (name, value) => {
    let msg = "";
    switch (name) {
      case "memberName":
        if (!/^[A-Za-z ]+$/.test(value)) {
          msg = "Name should contain only alphabets and spaces.";
        }
        break;
      case "memberMail":
        if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value)) {
          msg = "Invalid email format.";
        }
        break;
      case "mobileNo":
        if (!/^[6-9]\d{9}$/.test(value)) {
          msg = "Mobile must start with 6/7/8/9 and be 10 digits.";
        }
        break;
      case "gender":
        if (value === "") {
          msg = "Please select gender.";
        }
        break;
      case "memberAddress":
        if (value.length > 60) {
          msg = "Address cannot exceed 60 characters.";
        }
        break;
      default:
        break;
    }
    setErrors((prev) => ({ ...prev, [name]: msg }));
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setMember({ ...member, [name]: value });
    validateField(name, value);
  };

  const handleSelectMember = async (id) => {
    const res = await axios.get(`http://localhost:8081/members/${id}`);
    setMember(res.data);
    setSearch(res.data.memberName);
    setSuggestions([]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Final validation check
    Object.keys(member).forEach((field) => validateField(field, member[field]));
    if (Object.values(errors).some((err) => err !== "")) {
      alert("Please fix validation errors before submitting.");
      return;
    }

    if (!member.memberId) {
      alert("Please select a member to update.");
      return;
    }

    await axios.put(`http://localhost:8081/members/${member.memberId}`, member);
    alert("Member updated successfully!");
  };

  return (
    <div style={{ textAlign: "center", padding: "30px", background: "#f8f9fa" }}>
      <h2 style={{ color: "#007bff", marginBottom: "20px" }}>Update Member</h2>

      {/* Search Input */}
      <input
        type="text"
        placeholder="Search Member by Name"
        value={search}
        onChange={(e) => setSearch(e.target.value)}
        style={{
          width: "320px",
          padding: "10px",
          borderRadius: "6px",
          border: "1px solid #007bff",
          marginBottom: "10px",
        }}
      />
      {suggestions.length > 0 && (
        <ul
          style={{
            listStyle: "none",
            padding: 0,
            margin: "10px auto",
            width: "320px",
            border: "1px solid #007bff",
            borderRadius: "6px",
            background: "#ffffff",
            boxShadow: "0 4px 6px rgba(0,0,0,0.1)",
            textAlign: "left",
          }}
        >
          {suggestions.map((s) => (
            <li
              key={s.memberId}
              onClick={() => handleSelectMember(s.memberId)}
              style={{
                padding: "10px",
                cursor: "pointer",
                borderBottom: "1px solid #eee",
              }}
            >
              {s.memberName} ({s.memberMail})
            </li>
          ))}
        </ul>
      )}

      {/* Update Form */}
      <form
        onSubmit={handleSubmit}
        style={{
          marginTop: "20px",
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          gap: "15px",
        }}
      >
        <div>
          <input
            type="text"
            name="memberName"
            placeholder="Name"
            value={member.memberName}
            onChange={handleChange}
            required
            style={{
              width: "320px",
              padding: "10px",
              border: errors.memberName ? "1px solid red" : "1px solid #28a745",
              borderRadius: "6px",
            }}
          />
          <div style={{ color: "red", fontSize: "12px" }}>{errors.memberName}</div>
        </div>

        <div>
          <input
            type="email"
            name="memberMail"
            placeholder="Email"
            value={member.memberMail}
            onChange={handleChange}
            required
            style={{
              width: "320px",
              padding: "10px",
              border: errors.memberMail ? "1px solid red" : "1px solid #28a745",
              borderRadius: "6px",
            }}
          />
          <div style={{ color: "red", fontSize: "12px" }}>{errors.memberMail}</div>
        </div>

        <div>
          <input
            type="text"
            name="mobileNo"
            placeholder="Mobile"
            value={member.mobileNo}
            onChange={handleChange}
            required
            style={{
              width: "320px",
              padding: "10px",
              border: errors.mobileNo ? "1px solid red" : "1px solid #28a745",
              borderRadius: "6px",
            }}
          />
          <div style={{ color: "red", fontSize: "12px" }}>{errors.mobileNo}</div>
        </div>

        <div>
          <select
            name="gender"
            value={member.gender}
            onChange={handleChange}
            required
            style={{
              width: "320px",
              padding: "10px",
              border: errors.gender ? "1px solid red" : "1px solid #28a745",
              borderRadius: "6px",
            }}
          >
            <option value="">Select Gender</option>
            <option value="MALE">Male</option>
            <option value="FEMALE">Female</option>
          </select>
          <div style={{ color: "red", fontSize: "12px" }}>{errors.gender}</div>
        </div>

        <div>
          <input
            type="text"
            name="memberAddress"
            placeholder="Address"
            value={member.memberAddress}
            onChange={handleChange}
            required
            style={{
              width: "320px",
              padding: "10px",
              border: errors.memberAddress ? "1px solid red" : "1px solid #28a745",
              borderRadius: "6px",
            }}
          />
          <div style={{ color: "red", fontSize: "12px" }}>{errors.memberAddress}</div>
        </div>

        <button
          type="submit"
          style={{
            padding: "12px 24px",
            background: "linear-gradient(90deg,#007bff,#00c6ff)",
            border: "none",
            borderRadius: "6px",
            color: "white",
            fontWeight: "bold",
            cursor: "pointer",
            transition: "0.3s",
          }}
        >
          Update Member
        </button>
      </form>
    </div>
  );
}

export default UpdateMember;