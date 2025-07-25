import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { login } from "../Saveit/authSlice";
import { useNavigate } from "react-router-dom";
import "./Login.css";

const Login = () => {
  const [form, setForm] = useState({ username: "", password: "" });
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (form.username === "Kanishka" && form.password === "Kanishka") {
      dispatch(login());
      navigate("/");
    } else {
      alert("Invalid credentials.");
    }
  };

  return (
    <div className="login-container">
      <h2>Login</h2>
      <form onSubmit={handleSubmit}>
        <input name="username" value={form.username} onChange={handleChange} placeholder="Username" required />
        <input type="password" name="password" value={form.password} onChange={handleChange} placeholder="Password" required />
        <button type="submit">Login</button>
      </form>
    </div>
  );
};

export default Login;

