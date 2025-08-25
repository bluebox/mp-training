import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function LoginPage() {
  const [userId, setUserId] = useState("");
  const [userPassword, setUserPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post("http://localhost:8080/login", {
        userId,
        userPassword,
      });

      alert("Login successful!");
      if (res.data.role === "ADMIN") {
        navigate("/admin");
      } else {
        navigate("/user");
      }
    } catch (err) {
      alert("Invalid UserId or password!");
    }
  };

  return (
    <div style={styles.container}>
      <div style={styles.card}>
        <h2 style={styles.title}>Login</h2>
        <form onSubmit={handleLogin} style={styles.form}>
          <input
            type="number"
            placeholder="UserId"
            value={userId}
            onChange={(e) => setUserId(e.target.value)}
            required
            style={styles.input}
          />
          <input
            type="password"
            placeholder="Password"
            value={userPassword}
            onChange={(e) => setUserPassword(e.target.value)}
            required
            style={styles.input}
          />

          <button type="submit" style={styles.loginButton}>
            Login
          </button>
        </form>

        <p style={styles.text}>
          Don’t have an account?{" "}
          <button
            onClick={() => navigate("/signup")}
            style={styles.signupButton}
          >
            Sign Up
          </button>
        </p>
      </div>
    </div>
  );
}

const styles = {
  container: {
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    minHeight: "100vh",
    background: "linear-gradient(135deg, #ff416c 0%, #ff4b2b 100%)",
    fontFamily: "Arial, sans-serif",
  },
  card: {
    backgroundColor: "#fff",
    padding: "40px",
    borderRadius: "15px",
    boxShadow: "0px 8px 25px rgba(0,0,0,0.2)",
    width: "100%",
    maxWidth: "400px",
    textAlign: "center",
  },
  title: {
    marginBottom: "25px",
    color: "#333",
  },
  form: {
    display: "flex",
    flexDirection: "column",
  },
  input: {
    padding: "12px",
    margin: "8px 0",
    borderRadius: "8px",
    border: "1px solid #ccc",
    outline: "none",
    fontSize: "14px",
    transition: "0.3s",
  },
  loginButton: {
    marginTop: "15px",
    padding: "12px",
    backgroundColor: "#ff416c",
    color: "#fff",
    border: "none",
    borderRadius: "8px",
    fontSize: "16px",
    fontWeight: "bold",
    cursor: "pointer",
    transition: "0.3s",
  },
  text: {
    marginTop: "20px",
    fontSize: "14px",
    color: "#555",
  },
  signupButton: {
    background: "none",
    border: "none",
    color: "#ff416c",
    fontWeight: "bold",
    cursor: "pointer",
    textDecoration: "underline",
  },
};

export default LoginPage;
