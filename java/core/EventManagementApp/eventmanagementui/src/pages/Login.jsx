import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { api } from '../api/api';
import './Login.css';

export default function Login() {
  const [userId, setUserId] = useState('');
  const [userName, setUserName] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const res = await api.post(
        '/login',
        { userId: userId, userName, password },
        { withCredentials: true }
      );
      const msg = res.data.statusMsg;

      const roleMatch = msg.match(/ROLE_(\w+)/);
      const idMatch = msg.match(/user_id\s*:(\d+)/);

      const role = roleMatch ? roleMatch[1] : null;
      const userIdFromServer = idMatch ? idMatch[1] : null;

      if (!role || !userIdFromServer) {
        alert('Login failed: Unexpected server response.');
        return;
      }

      localStorage.setItem('role', role);
      localStorage.setItem('userId', userIdFromServer);

      if (role === 'ADMIN') navigate('/admin');
      else if (role === 'FACULTY') navigate('/faculty');
      else if (role === 'USER') navigate('/student');
      else alert('Unknown role');
    } catch (err) {
      alert('Login failed: Invalid username or password');
    }
  };

  return (
    <div className="login-container">
      <h2>Login</h2>
      <form onSubmit={handleLogin} className="login-form">
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
        <button type="submit">Login</button>
      </form>
      <Link to="/register">New User</Link>
    </div>
  );
}
