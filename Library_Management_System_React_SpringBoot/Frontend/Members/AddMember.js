import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { addMember } from "../../api/memberService"; // your axios API
import "./Member.css";

function AddMember({ members, setMembers }) {
  const navigate = useNavigate();
  const [memberData, setMemberData] = useState({
    name: "",
    email: "",
    mobile: "",
    gender: "M", // default
    address: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setMemberData({ ...memberData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      // Call backend to save member
      const response = await addMember(memberData);
      const savedMember = response.data; // contains memberId
      // Immediately add to frontend state
      setMembers([...members, savedMember]);
      alert("Member added successfully!");
      navigate("/members/view");
    } catch (err) {
      console.error(err);
      alert("Failed to add member, check backend server");
    }
  };

  return (
    <div className="Members Members-update">
      <header className="Members-header">
        <h1>Add Member</h1>
        <form onSubmit={handleSubmit}>
          <input
            type="text"
            name="name"
            placeholder="Name"
            value={memberData.name}
            onChange={handleChange}
            required
          />
          <input
            type="email"
            name="email"
            placeholder="Email"
            value={memberData.email}
            onChange={handleChange}
            required
          />
          <input
            type="tel"
            name="mobile"
            placeholder="Mobile"
            value={memberData.mobile}
            onChange={handleChange}
            required
          />
          <select
            name="gender"
            value={memberData.gender}
            onChange={handleChange}
            required
          >
            <option value="M">Male</option>
            <option value="F">Female</option>
          </select>
          <input
            type="text"
            name="address"
            placeholder="Address"
            value={memberData.address}
            onChange={handleChange}
            required
          />
          <div className="form-buttons">
            <button type="submit" className="main-button">
              Add Member
            </button>
            <button
              type="button"
              className="back-button"
              onClick={() => navigate("/members")}
            >
              Cancel
            </button>
          </div>
        </form>
      </header>
    </div>
  );
}

export default AddMember;
