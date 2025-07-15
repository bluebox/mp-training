import './user/User.css';
import { useState } from "react";
import { useNavigate, Link } from 'react-router-dom';
import axios from 'axios';

function RegisterPage() {
  const navigate = useNavigate();
  const [user, setUser] = useState({
    username: "",
    password: "",
    confirmPassword: ""
  });
  const [error, setError] = useState("");

  const handleRegisterForm = async (e) => {
    e.preventDefault();
    setError("");

    if (user.password !== user.confirmPassword) {
      setError("Passwords do not match");
      return;
    }

    if (user.password.length < 6) {
      setError("Password must be at least 6 characters long");
      return;
    }

    try {
      const res = await axios.post('http://localhost:8080/api/user/register', {
        username: user.username,
        password: user.password
      });
      
      if (res.status === 200) {
        alert('Registration successful! Please login.');
        navigate('/');
      }
    } catch (err) {
      if (err.response && err.response.data) {
        setError(err.response.data.message || err.response.data || 'Registration failed');
      } else {
        setError('Registration failed. Please try again.');
      }
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name === 'username' && !/^[a-zA-Z0-9]{0,50}$/.test(value)) return;
    setUser((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  return (
    <div className="registerdiv">
  <h3 className="register-title">Register Here</h3>
  <p className="register-login-link">
    Already have an account? <Link to="/">Login</Link>
  </p>
  {error && <p className="error-message">{error}</p>}
  <form className="registerform" onSubmit={handleRegisterForm}>
    <label className="input-label" htmlFor="username">Username</label>
    <input
      className="input-field"
      id="username"
      name="username"
      type="text"
      placeholder="Enter Username"
      value={user.username}
      onChange={handleChange}
      required
    /><br /><br />

    <label className="input-label" htmlFor="password">Password</label>
    <input
      className="input-field"
      id="password"
      name="password"
      type="password"
      maxLength={15}
      minLength={6}
      placeholder="Enter Password"
      value={user.password}
      onChange={handleChange}
      required
    /><br /><br />

    <label className="input-label" htmlFor="confirmPassword">Confirm Password</label>
    <input
      className="input-field"
      id="confirmPassword"
      name="confirmPassword"
      type="password"
      maxLength={15}
      minLength={6}
      placeholder="Confirm Password"
      value={user.confirmPassword}
      onChange={handleChange}
      required
    /><br /><br />

    <button className="btn-submit" type="submit">Register</button>
  </form>
</div>
  );
}

export default RegisterPage;