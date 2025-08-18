import React, { useEffect, useState } from 'react';
import './App.css';
  import { useCookies } from 'react-cookie';

function Login() {
  const [formData, setFormData] = useState({
    username:'',
    password:'',
  });
   const [cookies, setCookie] = useCookies(['name']);
  const [message,setMessage]=useState("");
  const [error,setError]=useState('');

   const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

 const handleSubmit = (e) => {
    e.preventDefault();
    fetch('http://localhost:8070/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formData),
    })
    .then(response=>response.text())
    .then(data=>{setMessage(data);
        setCookie("Admin");
    })
    .catch((error) => setError(error));
 };

  return (
   <div className="Container">
      <h2>LMS User Authentication Form</h2>
      <form onSubmit={handleSubmit} className="Container">
        <label>
          Username:
          <input
            type="text"
            name="username"
            value={formData.username}
            onChange={handleChange}
            required
          />
        </label>

        <label>
          Password:
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            required
          />
        </label>
        <button type="submit">Authenticate</button>
      </form>
    <p>{error}</p>
    </div>
  );
}

export default Login;
