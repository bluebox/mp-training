import React, { useState } from "react";
import { updateMember } from "../../api/memberService"; // import your axios API
import "./Member.css";

function ViewMembers({ members, setMembers }) {
  const [modal, setModal] = useState({ isOpen: false, member: null });

  const openModal = (member) => {
    setModal({ isOpen: true, member: { ...member } });
  };

  const closeModal = () => setModal({ isOpen: false, member: null });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setModal((prev) => ({
      ...prev,
      member: { ...prev.member, [name]: value },
    }));
  };

  const handleUpdate = async () => {
    try {
      const response = await updateMember(modal.member.memberId, modal.member);
      const updatedMember = response.data;
      setMembers((prev) =>
        prev.map((m) => (m.memberId === updatedMember.memberId ? updatedMember : m))
      );
      closeModal();
      alert("Member updated successfully!");
    } catch (err) {
      console.error(err);
      alert("Failed to update member, check backend server");
    }
  };

  return (
    <div className="view-members">
      <table className="member-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Mobile</th>
            <th>Gender</th>
            <th>Address</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {members.map((member) => (
            <tr key={member.memberId}>
              <td>{member.memberId}</td>
              <td>{member.name}</td>
              <td>{member.email}</td>
              <td>{member.mobile}</td>
              <td>{member.gender}</td>
              <td>{member.address}</td>
              <td>
                <button className="main-button" onClick={() => openModal(member)}>
                  Update
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {modal.isOpen && (
        <div className="modal-overlay">
          <div className="modal">
            <h2>Update Member</h2>
            <form
              onSubmit={(e) => {
                e.preventDefault();
                handleUpdate();
              }}
            >
              <input
                type="text"
                name="name"
                value={modal.member.name}
                onChange={handleChange}
                required
              />
              <input
                type="email"
                name="email"
                value={modal.member.email}
                onChange={handleChange}
                required
              />
              <input
                type="text"
                name="mobile"
                value={modal.member.mobile}
                onChange={handleChange}
                required
              />
              <select
                name="gender"
                value={modal.member.gender}
                onChange={handleChange}
                required
              >
                <option value="M">Male</option>
                <option value="F">Female</option>
              </select>
              <input
                type="text"
                name="address"
                value={modal.member.address}
                onChange={handleChange}
                required
              />
              <div className="form-buttons">
                <button type="submit" className="main-button">
                  Save
                </button>
                <button type="button" className="back-button" onClick={closeModal}>
                  Cancel
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}

export default ViewMembers;
