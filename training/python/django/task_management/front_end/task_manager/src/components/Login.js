import React, {  useState } from "react";

import api from "../api/axios";
import { useNavigate } from "react-router-dom";


function Login() {
  const [credentials, setCredentials] = useState({
    username: "",
    password: "",
  });

  const [user, setUser] = useState({
    id: "",
    username: "",
    email: "",
    first_name: "",
    last_name: "",
    role: "",
  });

  const navigate = useNavigate();

  const handleLogin = async (e) => {
  e.preventDefault();
  try {
    const response = await api.post("login/", credentials);
    const access = response.data.access;
    localStorage.setItem("access", access);
    localStorage.setItem("refresh", response.data.refresh);
    const profileResponse = await api.get("profile/");

    setUser(profileResponse.data);
    localStorage.setItem('user', JSON.stringify(profileResponse.data));
    setUser(user);
    navigate('/home');
    window.location.reload();
  } catch (err) {
    console.error("Login error:", err);
    alert("Invalid credentials");
    navigate("/login");
  }
};


  return (
    <div>
      <form onSubmit={handleLogin}>
        <label>Username: </label>
        <input
          placeholder="Username"
          onChange={(e) =>
            setCredentials({ ...credentials, username: e.target.value })
          }
        />
        <br/>
        <label>password: </label>
        <input
          type="password"
          placeholder="Password"
          onChange={(e) =>
            setCredentials({ ...credentials, password: e.target.value })
          }
        />
        <br/>
        <button type="submit">Login</button>
      </form>
    </div>
  );
}

export default Login;
