import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Login({ setUserRole }) {
  const [formData, setFormData] = useState({ username: '', password: '' });
  const navigate = useNavigate();

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(formData),
      });
      if (!response.ok) throw new Error('Login failed');
      const data = await response.json();
      const role = data.role;
      setUserRole(role);
      navigate('/dashboard');
    } catch (error) {
      console.error('Login error:', error);
      alert('Login failed. Please check your credentials.');
    }
  };

  return (
    <form onSubmit={handleLogin} style={{ display: 'flex', flexDirection: 'column', width: '200px' }}>
      <input
        name="username"
        value={formData.username}
        onChange={handleInputChange}
        placeholder="Username"
        required
        style={{ marginBottom: '10px', padding: '6px' }}
      />
      <input
        name="password"
        type="password"
        value={formData.password}
        onChange={handleInputChange}
        placeholder="Password"
        required
        style={{ marginBottom: '10px', padding: '6px' }}
      />
      <button type="submit" style={{ padding: '6px' }}>Login</button>
    </form>
  );
}