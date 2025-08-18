import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { updateMember } from "../../api/memberService";
import "./Member.css";

function UpdateMember({ members, setMembers }) {
  const { id } = useParams();
  const navigate = useNavigate();

  const [memberData, setMemberData] = useState({
    name: "",
    email: "",
    mobile: "",
    gender: "M",
    address: "",
  });

  useEffect(() => {
    const member = members.find((m) => m.memberId === parseInt(id));
    if (member) setMemberData(member);
  }, [id, members]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setMemberData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await updateMember(id, memberData);
      const updatedMember = response.data;
      setMembers((prev) =>
        prev.map((m) =>
          m.memberId === updatedMember.memberId ? updatedMember : m
        )
      );
      alert("Member updated successfully!");
      navigate("/members/view");
    } catch (err) {
      console.error(err);
      alert("Failed to update member. Check backend.");
    }
  };

  return (
    <div className="Members Members-update">
      

      <header className="Members-header">
        <h1>Update Member</h1>
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
              Update Member
            </button>
            <button
              type="button"
              className="back-button"
              onClick={() => navigate("/members/view")}
            >
              Cancel
            </button>
          </div>
        </form>
      </header>
    </div>
  );
}

export default UpdateMember;
