import React, { useState } from "react";
import axios from "axios";

const AddMember = () => {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    mobile: "",
    gender: "",
    address: "",
  });
  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post("http://localhost:8080/api/members", formData);
      setMessage("✅ Member added successfully!");
      setFormData({ name: "", email: "", mobile: "", gender: "", address: "" });
    } catch (error) {
      setMessage(error.response?.data?.message || "❌ Error adding member!");
    }
  };

  return (
    <div className="container mt-4">
      <div className="card shadow p-4">
        <h2 className="card-title mb-3">Add Member</h2>

        {message && <div className="alert alert-info">{message}</div>}

        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label className="form-label">Name:</label>
            <input
              type="text"
              name="name"
              value={formData.name}
              onChange={handleChange}
              className="form-control"
              placeholder="Enter Your Name"
              required
            />
          </div>

          <div className="mb-3">
            <label className="form-label">Email:</label>
            <input
              type="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              className="form-control"
              placeholder="example@example.com"
              required
            />
          </div>

          <div className="mb-3">
            <label className="form-label">Mobile Number:</label>
            <input
              type="text"
              name="mobile"
              value={formData.mobile}
              onChange={handleChange}
              className="form-control"
              placeholder="Enter Mobile Number"
              required
            />
          </div>

          <div className="mb-3">
            <label className="form-label">Gender:</label>
            <select
              name="gender"
              value={formData.gender}
              onChange={handleChange}
              className="form-select"
              required
            >
              <option value="" disabled>Select Your Gender</option>
              <option value="Male">Male</option>
              <option value="Female">Female</option>
              <option value="Other">Other</option>
            </select>
          </div>

          <div className="mb-3">
            <label className="form-label">Address:</label>
            <textarea
              name="address"
              value={formData.address}
              onChange={handleChange}
              className="form-control"
              rows="3"
              placeholder="Enter Your Full Address"
              required
            ></textarea>
          </div>

          <button type="submit" className="btn btn-success">Add Member</button>
        </form>
      </div>
    </div>
  );
};

export default AddMember;
