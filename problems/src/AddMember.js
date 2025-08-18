import React, { useState } from 'react';
import './App.css';

function AddMember() {
  const [formData, setFormData] = useState({
    MemberId:0,
    Name: '',
    Email: '',
    Mobile: '',
    gender: 'Select Below',
    Address: '',
  });

  const [message,setMessage]=useState("");

   const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

 const handleSubmit = (e) => {
    e.preventDefault();
    fetch('http://localhost:8070/Member/addMember', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formData),
    })
    .then(response=>response.text())
    .then(data=>setMessage(data))
     .catch((error) => setMessage(error));;
 };

  return (
   <div className="Container">
      <h2>Add New Member</h2>
      <form onSubmit={handleSubmit} className="Container">
        <label>
          Name:
          <input
            type="text"
            name="Name"
            value={formData.Name}
            onChange={handleChange}
            required
          />
        </label>

        <label>
          Email:
          <input
            type="text"
            name="Email"
            value={formData.Email}
            onChange={handleChange}
            required
          />
        </label>

        <label>
          Mobile:
          <input
            type="text"
            name="Mobile"
            value={formData.Mobile}
            onChange={handleChange}
            required
          />
        </label>

        <label>
          Gender:
          <select
            name="gender"
            value={formData.gender}
            onChange={handleChange}
            required
          >
          <option >--Select Below--</option>
            <option value="MALE">Male</option>
            <option value="FEMALE">Female</option>
          </select>
        </label>

        <label>
          Address:
            <input
            type="text"
            name="Address"
            value={formData.Address}
            onChange={handleChange}
            required
          /> 
        </label>

        <button type="submit">Add Member</button>
      </form>

      {message && <p>{message}</p>}
    </div>
  );
}

export default AddMember;
