import React, { useState, useEffect } from "react";
import axios from "axios";

function UpdateMember() {
  const [search, setSearch] = useState("");
  const [suggestions, setSuggestions] = useState([]);

  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const [member, setMember] = useState({
    memberId: "",
    memberName: "",
    memberMail: "",
    mobileNo: "",
    gender: "",
    memberAddress: "",
  });
  useEffect(() => {
    if (search.trim() === "") {
      setSuggestions([]);
      return;
    }
    axios.get("http://localhost:8080/members").then((res) => {
      const filtered = res.data.filter((m) =>
        m.memberName.toLowerCase().includes(search.toLowerCase())
      );
      setSuggestions(filtered);
    });
  }, [search]);
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

  const handleSelectMember = async (id) => {
    const res = await axios.get(`http://localhost:8080/members/${id}`);
    setMember(res.data);
    setSearch(res.data.memberName);
    setSuggestions([]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setSuccess("");

    try {
      if (!member.memberId) {
        alert("Please select a member to update.");
        return;
      }
      const res = await axios.put(
        `http://localhost:8080/members/${member.memberId}`,
        member
      );
      setSuccess(res.data.message);
      setSuccess("Member updated successfully!");
    } catch (err) {
      if (err.response && err.response.data) {
        if (err.response.data.errors) setError(err.response.data.errors);
        else setError(err.response.data.message || "Failed to update Book");
      } else setError("server errror,please try again");
    }
  };

  return (
    <div style={{ textAlign: "center" }}>
      <h2>Update Member</h2>

      <input
        type="text"
        placeholder="Search Member by Name"
        value={search}
        onChange={(e) => setSearch(e.target.value)}
        style={{ width: "300px", padding: "8px" }}
      />
      {suggestions.length > 0 && (
        <ul
          style={{
            listStyle: "none",
            padding: 0,
            margin: "10px auto",
            width: "300px",
            border: "1px solid gray",
            textAlign: "left",
          }}
        >
          {suggestions.map((s) => (
            <li
              key={s.memberId}
              onClick={() => handleSelectMember(s.memberId)}
              style={{
                padding: "8px",
                cursor: "pointer",
                borderBottom: "1px solid #ddd",
              }}
            >
              {s.memberName} ({s.memberMail})
            </li>
          ))}
        </ul>
      )}

      {error && <p style={{ color: "red" }}>{error}</p>}

      {success && <p style={{ color: "green" }}>{success}</p>}
      <form onSubmit={handleSubmit} style={{ marginTop: "20px" }}>
        <input
          style={formStyle}
          type="text"
          name="memberName"
          placeholder="Name"
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
          placeholder="Email"
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
          placeholder="Mobile"
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
          placeholder="Address"
          value={member.memberAddress}
          onChange={handleChange}
          required
        />
        <br />
        <br />
        <button type="submit" style={formStyle}>
          Update Member
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
export default UpdateMember;
