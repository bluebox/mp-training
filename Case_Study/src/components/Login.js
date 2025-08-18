import { useState } from "react";
import axios from "axios";

function Login({token}) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleChange = (e) => {
    if (e.target.name === "username") {
      setUsername(e.target.value);
    } else {
      setPassword(e.target.value);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();  

    try {
     
    
      await axios.post(
        "http://localhost:8095/Login",
        {username:username,
            password:password
        },
        {
          headers: { 'X-XSRF-TOKEN': token },
          withCredentials: true,
        }
      );

      alert("Login successful!");
    } catch (err) {
      console.error("Error during login", err);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label htmlFor="username">Username:</label>
        <input type="text" name="username" id="username" required onChange={handleChange} />
      </div>
      <div>
        <label htmlFor="password">Password:</label>
        <input type="password" name="password" id="password" required onChange={handleChange} />
      </div>
      <div>
        <button type="submit">Submit</button>
      </div>
    </form>
  );
}

export default Login;
