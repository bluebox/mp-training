import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
    import Cookies from 'js-cookie';
import { jwtDecode } from 'jwt-decode';

function LoginPage() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            if (!username || !password) {
                setError('Please enter both username and password.');
                return;
            }

            console.log(username,password)
            const response = await axios.post('http://localhost:8090/auth/signin', { username, password });
            console.log('Login successful:', response.data);
                // localStorage.setItem('accessToken', response.data.jwt);
                         Cookies.set('jwt_token', response.data.jwt, { expires: 7, secure: true, sameSite: 'Strict' });

                console.log("token is ",response.data.jwt);
                  const decodedToken = jwtDecode(response.data.jwt);
                    console.log("decoded",decodedToken);
                localStorage.setItem('role',decodedToken.authorities.replace("ROLE_",""));
               localStorage.setItem('email',decodedToken.email);
                localStorage.setItem('userid',decodedToken.userid);
                console.log("role is",localStorage.getItem('role'));
            navigate('/dashboard');
        } catch (error) {
            console.error('Login failed:', error.response ? error.response.data : error.message);
            setError('Invalid username or password.');
        }
    };

    return ( <div className="auth-container">
      <div className="auth-card">
        <h2 className="auth-title">Login</h2>
        <form onSubmit={handleLogin} className="auth-form">
          <input
            type="email"
            placeholder="Email"
            value={username}
            onChange={e => setUsername(e.target.value)}
            required
          />
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={e => setPassword(e.target.value)}
            required
          />
          <button type="submit"  className="btn-primary">Log In</button>
        </form>
        <p className="switch-text">
          Don’t have an account?{" "}
          <span onClick={() => navigate("/register")} className="switch-link">
            Register
          </span>
        </p>
      </div>
    </div>


        // <div className="d-flex justify-content-center align-items-center vh-100">
        //     <div className="border rounded-lg p-4" style={{ width: '500px', height: 'auto' }}>
        //         <div className="p-3">
        //             <h2 className="mb-4 text-center">Login Page</h2>
        //             <div> <input placeholder='Email address' id='email' value={username} type='email' onChange={(e) => setUsername(e.target.value)}/> </div>
        //             <div> <input placeholder='Password' id='password' type='password' value={password} onChange={(e) => setPassword(e.target.value)}/></div>
        //             {error && <p className="text-danger">{error}</p>} 
        //             <button className="mb-4 d-block btn-primary" style={{ height:'50px',width: '100%' }} onClick={handleLogin}>Sign in</button>
        //             <div className="text-center">
        //                 <p>Not a member? <a href="/register" >Register</a></p>
        //             </div>
        //         </div>
        //     </div>
        // </div>
    );
}

export default LoginPage;