import React, { useState,useContext} from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import {AuthContext} from '../context/AuthContext'
import { Link } from 'react-router-dom';

const Login = () => {

  const {isLoggined,setIsLoggined } = useContext(AuthContext);
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();
  if(isLoggined){
    navigate('/')
    return 
  }
  const HandleSubmit = async (e) => {
    e.preventDefault();
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if(!email && !emailRegex.test(email)){
        alert('enter valid email')
        return 
    }
    try {
      const response = await axios.post('http://127.0.0.1:8000/api/member/login/', {
        email,
        password
      });

      const { access, refresh } = response.data;

      localStorage.setItem('access_token', access);
      localStorage.setItem('refresh_token', refresh);
      setIsLoggined(true)
      navigate('/');
    } catch (error) {
      alert('Login failed. Check credentials.');
      console.error('Login error:', error);
    }
  };

  return (
    <div className="flex justify-center items-center min-h-screen">
      <div className="w-96 md:w-full md:max-w-2xl bg-white p-8 border border-gray-200 rounded shadow-lg">
        <h1 className="text-2xl font-bold mb-6 text-center text-blue-700">Login</h1>
        <form onSubmit={HandleSubmit}>
          <div className="mb-4">
            <label className="block mb-1 font-medium text-gray-700">Email</label>
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="w-full border border-gray-300 px-3 py-2 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="Enter your email"
              required
            />
          </div>
          <div className="mb-6">
            <label className="block mb-1 font-medium text-gray-700">Password</label>
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="w-full border border-gray-300 px-3 py-2 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="Enter your password"
              required
            />
          </div>
          <button
            type="submit"
            className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700 transition"
          >
            Login
          </button>
        </form>
        <p className="text-center text-gray-500 mt-4">
          Don't have an account? <Link to='/signup' className="text-blue-500 hover:underline">Signup</Link>
        </p>
      </div>
    </div>
  );
};

export default Login;
