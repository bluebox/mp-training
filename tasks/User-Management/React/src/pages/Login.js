import { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import { FaEye, FaEyeSlash } from 'react-icons/fa'

function Login() {
  const navigate = useNavigate();
  const [userName, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);

  const togglePasswordVisibility = () => {
    setShowPassword((prevShowPassword) => !prevShowPassword);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (userName === 'admin') {
      if (password === 'admin') {
        window.location.href = '/home'
      } else {
        alert('incorrect password')
      }
    }
    else {
      try {
        const res = await fetch(`http://localhost:8080/api/users/userLogin?userName=${userName}&password=${password}`);
        if (res.ok) {
          const data = await res.json();
          navigate('/profile', { state: { data } });
        } else {
          const errorText = await res.text();
          alert('Login failed: ' + errorText);
        }
      } catch (error) {
        alert('error occured: ' + error.message);
      }
    }
  };

  return (
    <div className="login-container">
      <form onSubmit={handleSubmit} className="login-form">
        <h2>Login</h2>

        <div className="form-group">
          <label>Username</label>
          <input
            type="text"
            value={userName}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>

        <div className="form-group">
          <label>Password</label>
          <div style={{ position: 'relative' }}>
            <input
              type={showPassword ? 'text' : 'password'}
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
            <span
              onClick={togglePasswordVisibility}
              style={{ position: 'absolute', right: '10px', top: '50%', transform: 'translateY(-50%)', cursor: 'pointer' }}
            >
              {showPassword ? <FaEyeSlash /> : <FaEye />}
            </span>
          </div>
        </div>

        <button type="submit">Login</button>

        <button onClick={() => window.location.href = '/'}>Back</button>
      </form>
    </div>
  )
}

export default Login
