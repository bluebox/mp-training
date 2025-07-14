import { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export default function LoginPage() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      await axios.post("http://localhost:8000/login", new URLSearchParams({
        username,
        password
      }), {
        headers: { "Content-Type": "application/x-www-form-urlencoded" }
      })
      .then(response => {
        const roleHeader = response.headers['role'];
        const role = roleHeader?.replace("[", "").replace("]", "");
        localStorage.setItem("role", role);
        localStorage.setItem("username",username);
        console.log(username,password);
        console.log(role);

        if (role.includes("ADMIN")) {
          navigate("/admin");
        } else if (role.includes("USER")) {
          navigate("/user");
        } else {
          alert("Unknown role: " + role);
        }
      });
    } catch (err) {
      alert("Login failed: " + err.response?.status);
    }
  };

  return (
    <div>
      <form onSubmit={handleLogin} style={{width:"200px", height:"230px", marginLeft:"500px",textAlign:"center"}}>
        <h2>Login</h2>
        <input type="text" placeholder="Username" value={username}
          onChange={(e) => setUsername(e.target.value)} required /><br/>
        <input type="password" placeholder="Password" value={password}
          onChange={(e) => setPassword(e.target.value)} required />
        <button type="submit">Login</button>
      </form>
    </div>
  );
}
