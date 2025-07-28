import React, { useState } from "react";
import axios from "axios";


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
      await axios.post("http://localhost:8000/api/register/", formData);
      alert("registration sucessful");
    } catch (err) {
      setError("Registration failed.");
    }
  };

  return (
    <div>
      <h2>Register</h2>
      {error && <p>{error}</p>}
      <form onSubmit={handleSubmit}>
        <div>
          <label>Username</label>
          <input
            name="username"
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <label>First Name</label>
          <input
            name="first_name"
            type="text"
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <label>Last Name</label>
          <input
            name="last_name"
            type="text"
            onChange={handleChange}
            required
          />
        </div>

        <div>
          <label>Email</label>
          <input
            name="email"
            type="email"
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Password</label>
          <input
            name="password"
            type="password"
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Role</label>
          <select name="role" onChange={handleChange}>
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
