import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';

function AddMemberForm() {

  const location = useLocation();
  const editData = location.state?.member;
  const isEdit = location.state?.isEdit;
  const [member, setMember] = useState({
    name: '',
    email: '',
    mobile: '',
    age: '',
    gender: '',
    address: ''
  });

  useEffect(() => {
  if (isEdit && editData) {
    setMember({
      name: editData.name,
      email: editData.email,
      mobile: editData.mobile,
      age: editData.age,
      gender: editData.gender,
      address: editData.address
    });
  }
}, [editData, isEdit]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setMember((prev) => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const payload = {
      ...member,
      mobile: parseInt(member.mobile),
      age: parseInt(member.age),
      gender: member.gender.toUpperCase()
    };
    const url = isEdit
      ? `http://localhost:8080/members/${editData.memberId}`
      : 'http://localhost:8080/members/add';

    const method = isEdit ? 'PUT' : 'POST';

    try {
      const response = await fetch(url, {
        method: method,
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
      });

      if (response.ok) {
        alert(isEdit ? 'Member updated successfully!' : 'Member added successfully!');
        setMember({
          name: '',
          email: '',
          mobile: '',
          age: '',
          gender: '',
          address: ''
        });
      } else {
        const errorData = await response.json();
        alert(`Failed to add member: ${errorData.message || 'Unknown error'}`);
      }
    } catch (error) {
      console.error('Error:', error);
      alert('Error adding member.');
    }
  };

  return (
    <div className="form-container">
      <h2>{isEdit ? 'Edit Member' : 'Add Member'}</h2>
      <form onSubmit={handleSubmit}>
        <label htmlFor="name">Name:</label><br />
        <input
          type="text"
          id="name"
          name="name"
          value={member.name}
          onChange={handleChange}
          required
        /><br />

        <label htmlFor="email">Email:</label><br />
        <input
          type="email"
          id="email"
          name="email"
          value={member.email}
          onChange={handleChange}
          required
        /><br />

        <label htmlFor="mobile">Mobile:</label><br />
        <input
          type="number"
          id="mobile"
          name="mobile"
          value={member.mobile}
          onChange={handleChange}
          required
        /><br />

        <label htmlFor="age">Age:</label><br />
        <input
          type="number"
          id="age"
          name="age"
          value={member.age}
          onChange={handleChange}
          required
        /><br />

        <label htmlFor="gender">Gender (M/F):</label><br />
        <input
          type="text"
          id="gender"
          name="gender"
          maxLength="1"
          value={member.gender}
          onChange={handleChange}
          required
        /><br />

        <label htmlFor="address">Address:</label><br />
        <textarea
          id="address"
          name="address"
          value={member.address}
          onChange={handleChange}
          required
        /><br />

        <button type="submit">{isEdit ? 'Update' : 'Submit'}</button>
      </form>
    </div>
  );
}

export default AddMemberForm;
