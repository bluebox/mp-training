import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { loginUser } from "../service/loginService";

export default function Login({ setRole }) {
  const [formData, setFormData] = useState({ username: '', password: '' });
  const navigate = useNavigate();

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const data = await loginUser(formData.username, formData.password);
      console.log(data);
      const role = data.role;

      localStorage.setItem("role", role);

      console.log("User Logged in with role : " + role);
      setRole(role);
      navigate('/dashboard');
    } catch (error) {
      console.error('Login error:', error);
      alert('Login failed. Please check your credentials.');
    }
  };

  return (
    <form onSubmit={handleLogin} className="container mt-5" style={{ maxWidth: '400px' }}>
      <h2>Login</h2>
      <div className="mb-3">
        <input
          name="username"
          value={formData.username}
          onChange={handleInputChange}
          placeholder="Username"
          required
          className="form-control"
        />
      </div>
      <div className="mb-3">
        <input
          name="password"
          type="password"
          value={formData.password}
          onChange={handleInputChange}
          placeholder="Password"
          required
          className="form-control"
        />
      </div>
      <button type="submit" className="btn btn-primary w-100">Login</button>
    </form>
  );
}
