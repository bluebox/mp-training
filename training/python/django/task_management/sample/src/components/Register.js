import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Register = () => {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
    email: "",
    role: "member",
  });
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleChange = (e) =>
    setFormData({ ...formData, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post("http://localhost:8000/api/register/", formData);
      navigate("/login");
    } catch (err) {
      setError("Registration failed.");
    }
  };

  return (
    <div className="container mt-4">
      <h2>Register</h2>
      {error && <p className="text-danger">{error}</p>}
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label>Username</label>
          <input
            className="form-control"
            name="username"
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label>Email</label>
          <input
            className="form-control"
            name="email"
            type="email"
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label>Password</label>
          <input
            className="form-control"
            name="password"
            type="password"
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label>Role</label>
          <select className="form-control" name="role" onChange={handleChange}>
            <option value="admin">Admin</option>
            <option value="lead">Lead</option>
            <option value="member">Member</option>
          </select>
        </div>
        <button className="btn btn-primary">Register</button>
      </form>
    </div>
  );
};

export default Register;
