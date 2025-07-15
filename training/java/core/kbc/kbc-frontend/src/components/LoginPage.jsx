import '../App.css';
import { useState, useEffect } from "react";
import { useNavigate, Link } from 'react-router-dom';
import axios from 'axios';

axios.defaults.withCredentials = true;

function LoginPage() {
  const navigate = useNavigate();
  const [userCredentials, setUserCredentials] = useState({
    username: "",
    password: ""
  });
  const [error, setError] = useState("");

  useEffect(() => {
    checkSession();
  },[]);

  const checkSession = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/user/check-auth');
      if (response.status === 200) {
        const userInfoResponse = await axios.get('http://localhost:8080/api/user/user-info');
        const userData = userInfoResponse.data;
        if (userData.role === 'USER') {
          navigate('/user/home');
        } else if (userData.role === 'ADMIN') {
          navigate('/admin/home');
        }
      }
    } catch (error) {
      navigate('/');
    }
  };

  const handleLoginForm = async (e) => {
    e.preventDefault();
    setError("");
    
    try {
      const response = await axios.post('http://localhost:8080/api/user/login', userCredentials);
     
      if (response.data.role === 'USER') {
        navigate('/user/home');
      } else if (response.data.role === 'ADMIN') {
        navigate('/admin/home');
      }
    } catch (err) {
      console.error('Login error:', err);
      if (err.response && err.response.data) {
        setError(err.response.data.message || 'Login failed');
      } else {
        setError('Login failed. Please check your credentials.');
      }
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name === 'username' && !/^[a-zA-Z0-9]{0,50}$/.test(value)) return;
    setUserCredentials((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  return (
    <div className="logindiv">
      <h3 className="">Login Here</h3>
      <p>New User? <Link to="/register" style={{ color:"white" }} >Register</Link></p>
      {error && <p style={{color: 'red', marginBottom: '10px'}}>{error}</p>}
      <form className="loginform" onSubmit={handleLoginForm}>
        <label htmlFor="username">Username</label>
        <input
          id="username"
          name="username"
          type="text"
          placeholder="Enter Username"
          value={userCredentials.username}
          onChange={handleChange}
          required
        /><br /><br />
        <label htmlFor="password">Password</label>
        <input
          id="password"
          name="password"
          type="password"
          maxLength={15}
          minLength={6}
          placeholder="Enter Password"
          value={userCredentials.password}
          onChange={handleChange}
          required
        /><br /><br />
        <button type="submit">Login</button>
      </form>
    </div>
  );
}

export default LoginPage;
