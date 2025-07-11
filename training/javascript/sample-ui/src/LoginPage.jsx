import React, { useState } from 'react';
import axios from 'axios';
const LoginPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');
    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const params = new URLSearchParams();
            params.append('username', username);
            params.append('password', password);
            const response = await axios.post(
                'http://localhost:8080/login',
                params,
                {
                    withCredentials: true,
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
                }
            );
            if (response.status === 200) {
                setMessage('Login successful!'+response.headers.role);
                console.log(response.headers.role);

            }
        } catch (err) {
            setMessage('Login failed!');
        }
    };
    return (
        <div style={{ display: 'flex', height: '100vh', justifyContent: 'center', alignItems: 'center', background: '#f4f4f4' }}>
            <form onSubmit={handleLogin} style={{ background: '#fff', padding: '2rem', borderRadius: '8px', boxShadow: '0 2px 10px rgba(0,0,0,0.1)' }}>
                <h2>Login</h2>
                <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} placeholder="Username" required style={{ display: 'block', marginBottom: '1rem', padding: '0.5rem', width: '100%' }} />
                <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="Password" required style={{ display: 'block', marginBottom: '1rem', padding: '0.5rem', width: '100%' }} />
                <button type="submit" style={{ padding: '0.5rem 1rem' }}>Login</button>
                <p style={{ marginTop: '1rem' }}>{message}</p>
            </form>
        </div>
    );
};
export default LoginPage;