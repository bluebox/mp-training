import React, { useState } from 'react';
import axios from 'axios';

function ChangePassword({ onNavigate }) {
  const [username, setUsername] = useState('');
  const [currentPassword, setCurrentPassword] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [confirmNewPassword, setConfirmNewPassword] = useState('');
  const [error, setError] = useState(null);
  const [message, setMessage] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);
    setMessage(null);

    if (!username.trim()) {
      setError('Enter your User ID.');
      return;
    }
    if (!currentPassword || !newPassword) {
      setError('Please fill all password fields.');
      return;
    }
    if (newPassword !== confirmNewPassword) {
      setError('New password and confirm password do not match.');
      return;
    }

    try {
      // call backend change-password endpoint
      await axios.post('http://localhost:8080/auth/change-password', {
        username,
        currentPassword,
        newPassword
      });

      setMessage('Password changed successfully. You can go back to login.');
      setCurrentPassword('');
      setNewPassword('');
      setConfirmNewPassword('');
    } catch (err) {
      console.error('Change password failed:', err.response?.data || err.message);
      setError(err.response?.data || 'Failed to change password');
    }
  };

  return (
    <div className="login-container">
      <form className="login-form" onSubmit={handleSubmit}>
        <h2 className="login-title">Change Password</h2>

        <input
          type="text"
          placeholder="User ID"
          className="login-input"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />

        <input
          type="password"
          placeholder="Current password"
          className="login-input"
          value={currentPassword}
          onChange={(e) => setCurrentPassword(e.target.value)}
        />

        <input
          type="password"
          placeholder="New password"
          className="login-input"
          value={newPassword}
          onChange={(e) => setNewPassword(e.target.value)}
        />

        <input
          type="password"
          placeholder="Confirm new password"
          className="login-input"
          value={confirmNewPassword}
          onChange={(e) => setConfirmNewPassword(e.target.value)}
        />

        <button type="submit" className="login-button">Update Password</button>

        {error && <div className="login-error">{error}</div>}
        {message && <div className="login-success">{message}</div>}

        <div style={{ marginTop: '12px' }}>
          <button
            type="button"
            className="login-button"
            onClick={() => {
              setError(null);
              setMessage(null);
              if (onNavigate) onNavigate(''); // go back to login (App renders Login when currentPage === '')
            }}
          >
            Back to Login
          </button>
        </div>
      </form>
    </div>
  );
}

export default ChangePassword;
