import React, { useState } from "react";
import api from '../api/axios';


const Register = () => {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
    email: "",
    first_name: "",
    last_name: "",
    role: "member",
  });
  const [error, setError] = useState("");

  const handleChange = (e) =>
    setFormData({ ...formData, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.post("register/", formData);
      alert("registration sucessful");
    } catch (err) {
      setError("Registration failed.");
    }
    setFormData({
      username: "",
      password: "",
      email: "",
      first_name: "",
      last_name: "",
      role: "member",
    });
  };

  return (
    <div>
      {error && <p>{error}</p>}
      <form onSubmit={handleSubmit}>
        <div>
          <label>Username</label>
          <input
            name="username"
            value={formData.username}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <label>First Name</label>
          <input
            name="first_name"
            type="text"
            value={formData.first_name}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <label>Last Name</label>
          <input
            name="last_name"
            type="text"
            value={formData.last_name}
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <label>Email</label>
          <input
            name="email"
            type="email"
            value={formData.email}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Password</label>
          <input
            name="password"
            type="password"
            value={formData.password}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Role</label>
          <select name="role" onChange={handleChange} value={formData.role}>
            <option value="admin">Admin</option>
            <option value="lead">Lead</option>
            <option value="member">Member</option>
          </select>
        </div>
        <button>Register</button>
      </form>
    </div>
  );
};

export default Register;
