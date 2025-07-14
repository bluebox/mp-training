import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export default function LoginPage() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();
  console.log(username,password);
  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      await axios.post("http://localhost:8080/login", new URLSearchParams({
        username,
        password
      }), {
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        withCredentials:true
      })
      .then(response => {
        const roleHeader = response.headers['role'];
        const role = roleHeader?.replace("[", "").replace("]", "");
        localStorage.setItem("role", role);
        localStorage.setItem("username",username);
        console.log(username,password);
        console.log(role);
        
        if (role.includes("ADMIN")) {
          navigate("/user/customerpage");
        } else if (role.includes("USER")) {
          navigate("/user");
        } else {
          alert("Unknown role: " + role);
        }
      });
    } catch (err) {
      alert("Login failed: " + err.response?.status);
    }
  };

  return (
    <div>
      <h2>Login</h2>
      <form onSubmit={handleLogin}>
        <label htmlFor="Username">User Name</label>
        <input type="text" placeholder="Username" id="Username" name='Username' value={username}
          onChange={(e) => setUsername(e.target.value)} required />
        <br/>
        <input type="password" placeholder="Password" value={password}
          onChange={(e) => setPassword(e.target.value)} required /><br/>
        <button type="submit">Login</button>
      </form>
    </div>
  );
}
