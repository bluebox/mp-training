import axios from 'axios';
import React, { useContext, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext';

const Signup = () => {
  const {isLoggined } = useContext(AuthContext);
  const navigate=useNavigate()
  if(isLoggined){
    navigate('/')
    return 
  }
  const [formData, setFormData] = useState({
    username: '',
    email: '',
    password: '',
    confirmPassword:''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const validateForm = () => {
    if (!formData.email || !/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(formData.email)) {
      alert('Please enter a valid email');
      return false; 
    }
    if (!formData.username || !/^[a-zA-Z0-9._]{3,}$/.test(formData.username)) {
      alert('Please enter a valid username');
      return false; 
    }
    if (!formData.password || formData.password.length < 6) {
      alert('Password must be at least 6 characters');
      return false;
    }
    if(formData.password!=formData.confirmPassword){
      alert('Password not matched')
      return false
    }
    return true;
  };

  const handleSubmit = async(e) => {
    e.preventDefault();
    if (validateForm()) {
       try{
            const updatedData= {...formData}
            delete updatedData.confirmPassword
            await axios.post('http://127.0.0.1:8000/api/member/crud/',updatedData)
            alert('signup successful')
            navigate('/login')
       }
       catch(err){
          const error=err.response.data
          const msg=Object.values(error).flat().join('\n')
          alert(msg)
       }
    }
  };

  return (
    <div className="bg-gray-100 flex items-center justify-center min-h-screen">
      <div className="bg-white p-8 rounded-lg shadow-lg md:w-full md:max-w-2xl">
        <h2 className="text-2xl font-bold text-center text-gray-700 mb-6">Create an Account</h2>
        
        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <label htmlFor="username" className="block text-gray-600">Username</label>
            <input
              type="text"
              id="username"
              name="username"
              value={formData.username}
              onChange={handleChange}
              className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="Enter Username"
              required
            />
          </div>

          <div className="mb-4">
            <label htmlFor="email" className="block text-gray-600">Email Address</label>
            <input
              type="email"
              id="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="Enter email"
            />
          </div>

          <div className="mb-6">
            <label htmlFor="password" className="block text-gray-600">Password</label>
            <input
              type="password"
              id="password"
              name="password"
              value={formData.password}
              onChange={handleChange}
              className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="Enter password"
            />
          </div>
           <div className="mb-6">
            <label htmlFor="confirmPassword" className="block text-gray-600">confirmPassword</label>
            <input
              type="password"
              id="confirmPassword"
              name="confirmPassword"
              value={formData.confirmPassword}
              onChange={handleChange}
              className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="Enter password"
            />
          </div>

          <button type="submit" className="w-full bg-blue-500 text-white py-2 rounded-md hover:bg-blue-600 transition duration-300">
            Sign Up
          </button>
        </form>

        <p className="text-center text-gray-500 mt-4">
          Already have an account? <Link to='/login' className="text-blue-500 hover:underline">Login</Link>
        </p>
      </div>
    </div>
  );
};

export default Signup;
