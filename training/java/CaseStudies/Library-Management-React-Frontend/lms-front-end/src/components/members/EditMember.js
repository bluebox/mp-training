import React, { useState } from 'react';
import './EditMember.css';

const EditMember = ({ member, onClose, onUpdate }) => {
  const [memberData, setMemberData] = useState({
    member_Id: member.member_Id,
    member_Name: member.member_Name,
    email: member.email,
    mobile_No: member.mobile_No,
    gender: member.gender,
    address: member.address
  });
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setMemberData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    setError(null);

    try {
      const response = await fetch(`http://localhost:8080/updatemember`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(memberData)
      });

      if (!response.ok) {
        throw new Error('Failed to update member');
      }

      const updatedMember = await response.json();
      onUpdate(updatedMember);
      onClose();
    } catch (err) {
      setError(err.message);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="edit-overlay">
      <div className="edit-modal">
        <h2>Edit Member</h2>
        {error && <div className="error-message">{error}</div>}
        
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="member_Name">Name:</label>
            <input
              type="text"
              id="member_Name"
              name="member_Name"
              value={memberData.member_Name}
              onChange={handleChange}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="email">Email:</label>
            <input
              type="email"
              id="email"
              name="email"
              value={memberData.email}
              onChange={handleChange}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="mobile_No">Mobile Number:</label>
            <input
              type="text"
              id="mobile_No"
              name="mobile_No"
              value={memberData.mobile_No}
              pattern="\d{10}"
              onChange={handleChange}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="gender">Gender:</label>
            <select
              id="gender"
              name="gender"
              value={memberData.gender}
              onChange={handleChange}
              required
            >
              <option value="M">Male</option>
              <option value="F">Female</option>
            </select>
          </div>

          <div className="form-group">
            <label htmlFor="address">Address:</label>
            <textarea
              id="address"
              name="address"
              value={memberData.address}
              onChange={handleChange}
              rows="3"
              required
            />
          </div>

          <div className="button-group">
            <button type="submit" disabled={isLoading}>
              {isLoading ? 'Updating...' : 'Update Member'}
            </button>
            <button type="button" onClick={onClose}>Cancel</button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default EditMember;