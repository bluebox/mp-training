import React, { useState } from 'react';
import axios from 'axios';
import './Login.css';


function Login({ onAuthenticate, onNavigate }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(null);

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
     const response = await axios.post('http://localhost:8080/auth/login', { username, password });
	  
	  const userCode = username;
      const role = response.data.role || 'ROLE_USER';
	  const userName =  response.data.username;
      const auth = { userCode , userName, password, role };
	  

	  setError(null);
	  if (onAuthenticate) onAuthenticate(auth);

    } catch (err) {
      console.error('Login failed:', err.response?.data || err.message);
      setError(err.response?.data || 'Invalid username or password');
    }
  };

  return (
    <div className="login-container">
      <form className="login-form" onSubmit={handleLogin}>
        <h2 className="login-title">Login</h2>

        <input
          type="text"
          placeholder="Username"
          className="login-input"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />

        <input
          type="password"
          placeholder="Password"
          className="login-input"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />

        <button type="submit" className="login-button">Login</button>

        <div className="login-links" style={{ marginTop: '8px' }}>
          <button
            type="button"
            className="login-button"
            onClick={() => {
              setError(null);
              if (onNavigate) onNavigate('changePassword');
            }}
          >
            Change password
          </button>
        </div>
		

        {error && <div className="login-error" >{error}</div>}
      </form>
    </div>
  );
}

export default Login;
