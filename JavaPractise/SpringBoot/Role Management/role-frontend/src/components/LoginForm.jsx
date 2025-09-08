import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

export default function LoginForm() {
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const [errorMsg, setErrorMsg] = useState("");
  const navigate = useNavigate();
  const { login } = useAuth();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setErrorMsg("");
    const errorMsg = validateForm();
    if (errorMsg) {
      setErrorMsg(errorMsg);
      return;
    }

    try {
      const res = await fetch("http://localhost:8080/users/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ id, password }),
      });

      const isJson = (res.headers.get("content-type") || "").includes("application/json");
      const data = isJson ? await res.json() : null;

      if (!res.ok || !data?.success) {
        setErrorMsg(data?.message || `Login failed (${res.status})`);
        return;
      }

      const user = data.data || {};
      login({
        id: user.id || id,
        firstName: user.firstName,
        lastName: user.lastName,
        roles: user.roles || [],
        username: user.username,
        password: user.password || password,
      });

      alert("Login successful");
      navigate("/");
    } catch (err) {
      console.error(err);
      setErrorMsg("Something went wrong. Please try again later.");
    }
  };
  const validateForm = () => {
      if (!id?.trim() || id.trim().length > 15)
        return "User Id is required and should not exceed 15 characters.";
      if (!password?.trim() || password.trim().length > 15)
        return "Password is required and should not exceed 15 characters.";
      
      return null;
  };
  const containerStyle = {
    maxWidth: "400px",
    margin: "50px auto",
    padding: "30px",
    border: "1px solid #ccc",
    borderRadius: "10px",
    boxShadow: "0 2px 8px rgba(0,0,0,0.1)",
    backgroundColor: "#f9f9f9",
    fontFamily: "Arial, sans-serif",
  };

  const inputStyle = {
    width: "100%",
    padding: "10px",
    margin: "8px 0 20px 0",
    border: "1px solid #ccc",
    borderRadius: "5px",
    boxSizing: "border-box",
  };

  const labelStyle = {
    fontWeight: "bold",
  };

  const buttonStyle = {
    width: "100%",
    padding: "10px",
    backgroundColor: "#007bff",
    color: "#fff",
    border: "none",
    borderRadius: "5px",
    cursor: "pointer",
    fontSize: "16px",
  };

  const errorStyle = {
    color: "red",
    marginBottom: "15px",
  };

  return (
    <div style={containerStyle}>
      <h2 style={{ textAlign: "center", marginBottom: "20px" }}>Login</h2>
      {errorMsg && <p style={errorStyle}>{errorMsg}</p>}
      <form onSubmit={handleSubmit}>
        <div>
          <label style={labelStyle}>User ID:</label>
          <input
            type="text"
            value={id}
            onChange={(e) => setId(e.target.value)}
            style={inputStyle}
            placeholder="Enter your User ID"
          />
        </div>
        <div>
          <label style={labelStyle}>Password:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            style={inputStyle}
            placeholder="Enter your Password"
          />
        </div>
        <button type="submit" style={buttonStyle}>Login</button>
      </form>
    </div>
  );
}
