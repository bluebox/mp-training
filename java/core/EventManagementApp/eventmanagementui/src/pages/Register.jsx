import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { api } from '../api/api';
import './Login.css';

export default function Register() {
  const [userId, setUserId] = useState('');
  const [userName, setUserName] = useState('');
  const [password, setPassword] = useState('');
  const [role, setRole] = useState('');

  const handleRegister = async (e) => {
    e.preventDefault();
    try {
       await api.post('/register',
        { userId: userId, userName, password,role }
      );
      alert('User Registered successfully');
      window.location.href = '/';
    } 
    catch (err) {
      alert('Registered failed');
    }
  };

  return (
    <div className="login-container">
      <h2>Register</h2>
      <form onSubmit={handleRegister} className="login-form">
        <input
          type="text"
          placeholder="User ID"
          value={userId}
          onChange={(e) => setUserId(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="Username"
          value={userName}
          onChange={(e) => setUserName(e.target.value)}
          required
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
           {/* <input
          type="text"
          placeholder="Role"
          value={role}
          onChange={(e) => setRole(e.target.value)}
          required
        /> */}
        <label>Role
            <select value={role} onChange={(e) => setRole(e.target.value)}>
                <option value="">Select</option>
                <option value="USER">USER</option>
                <option value="ADMIN">ADMIN</option>
                <option value="FACULTY">FACULTY</option>
            </select>
        </label>

        <button type="submit">Register</button>
      </form>
        <Link to="/">Login</Link>
    </div>
  );
}

