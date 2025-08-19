import React, { useState } from 'react';
import './AddMember.css';

const AddMember = () => {
  const [memberData, setMemberData] = useState({
    member_Name: '',
    email: '',
    mobile_No: '',
    gender: '',
    address: ''
  });
  const [isLoading, setIsLoading] = useState(false);
  const [message, setMessage] = useState({ type: '', text: '' });

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
    setMessage({ type: '', text: '' });

    try {
      const response = await fetch('http://localhost:8080/addmember', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(memberData)
      });

     
      if (!response.ok) {
         const data = await response.json();
         console.log(data);
        throw new Error(data.message);
      }

      const data = await response.json();
      setMessage({ type: 'success', text: 'Member added successfully!' });
      setMemberData({
        member_Name: '',
        email: '',
        mobile_No: '',
        gender: '',
        address: ''
      });
    } catch (error) {
      setMessage({ type: 'error', text: error.message });
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="member-form-container">
      <h2>Add New Member</h2>
      {message.text && (
        <div className={`message ${message.type}`}>
          {message.text}
        </div>
      )}
      <form onSubmit={handleSubmit} className="member-form">
        <div className="form-group">
          <label htmlFor="member_Name">Name:</label>
          <input
            type="text"
            id="member_Name"
            name="member_Name"
            value={memberData.member_Name}
            onChange={handleChange}
            placeholder="Enter Member Name"
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
            placeholder="Enter Email Address"
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
            onChange={handleChange}
            pattern="\d{10}"
            placeholder="Enter 10-digit mobile number"
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
            <option value="">Select Gender</option>
            <option value="M">Male</option>
            <option value="F">Female</option>
          </select>
        </div>

        <div className="form-group">
          <label htmlFor="address">Address:</label>
          <textarea
            id="address"
            name="address"
            rows="3"
            value={memberData.address}
            onChange={handleChange}
            placeholder="Enter full address"
            required
          />
        </div>

        <button type="submit" disabled={isLoading}>
          {isLoading ? 'Adding Member...' : 'Add Member'}
        </button>
      </form>
    </div>
  );
};

export default AddMember;
