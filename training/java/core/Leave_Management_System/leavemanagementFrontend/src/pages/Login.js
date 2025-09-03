/*import React, { useState } from 'react';
import { useAuth } from '../context/AuthContext';
//import { useMessage } from '../context/MessageContext';
import './Login.css'; // Styling

const LoginPage = () => {
  const [userEmail, setUserEmail] = useState('');
  const [userPassword, setUserPassword] = useState('');
  const [showPassword, setShowPassword] = useState(false);
  const [errors, setErrors] = useState({});

  const { login } = useAuth();
  const { showMessage } = useMessage();

  // Email validation regex
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

  const validateForm = () => {
    const newErrors = {};
    if (!userEmail) {
      newErrors.userEmail = 'Email is required.';
    } else if (!emailRegex.test(userEmail)) {
      newErrors.userEmail = 'Please enter a valid email address.';
    }

    if (!userPassword) {
      newErrors.userPassword = 'Password is required.';
    } else if (userPassword.length < 6) {
      newErrors.userPassword = 'Password must be at least 6 characters.';
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (validateForm()) {
      const result = await login(userEmail, userPassword);
      if (!result.success) {
        showMessage(result.message || 'Invalid email or password.', 'error');
      }
    } else {
      showMessage('Please correct the form errors.', 'error');
    }
  };

  // Live validation for email
  const handleEmailChange = (e) => {
    const value = e.target.value;
    setUserEmail(value);

    if (!value) {
      setErrors((prev) => ({ ...prev, userEmail: 'Email is required.' }));
    } else if (!emailRegex.test(value)) {
      setErrors((prev) => ({ ...prev, userEmail: 'Please enter a valid email address.' }));
    } else {
      setErrors((prev) => {
        const { userEmail, ...rest } = prev;
        return rest;
      });
    }
  };

  // Live validation for password
  const handlePasswordChange = (e) => {
    const value = e.target.value;
    setUserPassword(value);

    if (!value) {
      setErrors((prev) => ({ ...prev, userPassword: 'Password is required.' }));
    } else if (value.length < 6) {
      setErrors((prev) => ({ ...prev, userPassword: 'Password must be at least 6 characters.' }));
    } else {
      setErrors((prev) => {
        const { userPassword, ...rest } = prev;
        return rest;
      });
    }
  };

  return (
    <div className="login-container">
      <form onSubmit={handleSubmit} className="login-form">
        <h2>Login</h2>

        {/* Email *///}
     /*   <div className="form-group">
          <label htmlFor="userEmail">Email</label>
          <input
            type="text"
            id="userEmail"
			placeholder='you@company.com'
            value={userEmail}
            onChange={handleEmailChange}
            className={errors.userEmail ? 'input-error' : ''}
          />
          {errors.userEmail && <div className="error-text">{errors.userEmail}</div>}
        </div>

        {/* Password *///}
  /*      <div className="form-group">
          <label htmlFor="userPassword">Password</label>
          <input
            type={showPassword ? 'text' : 'password'}
            id="userPassword"
			placeholder='Enter your password'
            value={userPassword}
            onChange={handlePasswordChange}
            className={errors.userPassword ? 'input-error' : ''}
          />
          {errors.userPassword && <div className="error-text">{errors.userPassword}</div>}
        </div>

        {/* Show password *///}
   /*     <div className="form-checkbox">
          <input
            type="checkbox"
            id="showPassword"
            checked={showPassword}
            onChange={() => setShowPassword(!showPassword)}
          />
          <label htmlFor="showPassword">Show Password</label>
        </div>

        <button type="submit" className="login-button">Login</button>
      </form>
    </div>
  );
};

export default LoginPage;*/


import React, { useState } from 'react';
import { useAuth } from '../context/AuthContext';
import './Login.css'; 

const LoginPage = () => {
  const [userEmail, setUserEmail] = useState('');
  const [userPassword, setUserPassword] = useState('');
  const [showPassword, setShowPassword] = useState(false);
  const [errors, setErrors] = useState({});
  const [formMessage, setFormMessage] = useState(''); 

  const { login } = useAuth();


  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

  const validateForm = () => {
    const newErrors = {};
    if (!userEmail) {
      newErrors.userEmail = 'Email is required.';
    } else if (!emailRegex.test(userEmail)) {
      newErrors.userEmail = 'Please enter a valid email address.';
    }

    if (!userPassword) {
      newErrors.userPassword = 'Password is required.';
    } else if (userPassword.length < 6) {
      newErrors.userPassword = 'Password must be at least 6 characters.';
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setFormMessage(''); 
    if (validateForm()) {
      const result = await login(userEmail, userPassword);
      if (!result.success) {
        setFormMessage(result.message || 'Invalid email or password.');
      }
    } else {
      setFormMessage('');
    }
  };

  return (
    <div className="login-container">
      <form onSubmit={handleSubmit} className="login-form">
        <h2>Login</h2>

     
        <div className="form-group">
          <label htmlFor="userEmail">Email</label>
          <input
            type="text"
            id="userEmail"
            placeholder='you@company.com'
            value={userEmail}
            onChange={(e) => setUserEmail(e.target.value)}
            className={errors.userEmail ? 'input-error' : ''}
          />
          {errors.userEmail && <div className="error-text">{errors.userEmail}</div>}
        </div>


        <div className="form-group">
          <label htmlFor="userPassword">Password</label>
          <input
            type={showPassword ? 'text' : 'password'}
            id="userPassword"
            placeholder='Enter your password'
            value={userPassword}
            onChange={(e) => setUserPassword(e.target.value)}
            className={errors.userPassword ? 'input-error' : ''}
          />
          {errors.userPassword && <div className="error-text">{errors.userPassword}</div>}
        </div>

    
        <div className="form-checkbox">
          <input
            type="checkbox"
            id="showPassword"
            checked={showPassword}
            onChange={() => setShowPassword(!showPassword)}
          />
          <label htmlFor="showPassword">Show Password</label>
        </div>

        <button type="submit" className="login-button">Login</button>

        
        {formMessage && <div className="error-text" style={{ marginTop: '10px' }}>{formMessage}</div>}
      </form>
    </div>
  );
};

export default LoginPage;

