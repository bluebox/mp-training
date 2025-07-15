/* eslint-disable react-hooks/exhaustive-deps */
import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './User.css';

const UserDetails = () => {
  const [editableUser, setEditableUser] = useState({
    userId: '',
    username: '',
    password: '',
    role: '',
    status: 'ACTIVE',
    createdAt: '',
    modifiedAt: ''
  })
  const [originalUser, setOriginalUser] = useState({
    userId: '',
    username: '',
    password: '',
    role: '',
    status: 'ACTIVE',
    createdAt: '',
    modifiedAt: ''
  }); 
  
  ;
  const [isEditing, setIsEditing] = useState(false);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [changed, setChanged] = useState(1);
  const navigate = useNavigate();

  useEffect(() => {
    fetchUserDetails();
  }, []);

  const fetchUserDetails = async () => {
    try {
      setLoading(true);
      const response = await axios.get('http://localhost:8080/api/user/user-info');
      const userData = response.data;
      setEditableUser({
        userId: userData.userId,
        username: userData.username,
        password: userData.password,
        role: userData.role,
        status: userData.status,
      })
      setOriginalUser({
        userId: userData.userId,
        username: userData.username,
        password: userData.password,
        role: userData.role,
        status: userData.status,
      })
      
    } catch (err) {
      setError('Failed to load user details');
      console.error('Error fetching user details:', err);
    } finally {
      setLoading(false);
    }
  };
  const isUserChanged = () => {
    if (!originalUser) return false;
    return (
      editableUser.username !== originalUser.username ||
      editableUser.password !== originalUser.password ||
      editableUser.status !== originalUser.status
    );
  };


    const handleSave = async () => {
    if (!isUserChanged()) {
      setChanged(0);
      return false;
    }
    const { role, ...dataToUpdate } = editableUser;
    if(editableUser.password.length > 5 ) {
      await axios.patch(`http://localhost:8080/api/user/${editableUser.userId}/update`, dataToUpdate)
        .then((response) => {
          alert('User details updated successfully:', response.data);
          setIsEditing(false);
        }).catch((error) => {
          alert(error.response.data.message );
        });
      await fetchUserDetails();
    }
    else {
      alert('Password must be at least 6 characters long.');
    }
    return true;
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setChanged(isUserChanged() ? 1 : -1);
    if(name.length<5 )
    {
      alert('Username must be at least 5 characters long.');
      return;
    }
    setEditableUser((prevUser) => ({
      ...prevUser,
      [name]: value,
    }));
  };

  const handleBack = () => {
    navigate(-1);
  };

  if (loading) return <div>Loading user details...</div>;
  if (error) return <div>Error loading user details: {error}</div>;
  if (!editableUser.userId) return <div>No user data available.</div>;

  return (
    <>
    {changed===0 && <h1>No changes</h1>}
        <div className="user-details-container">
  <h2 className="user-details-title">User Details</h2>
  <div className="user-details-icon">👤</div>
  
  <p><strong>UserId:</strong> {editableUser.userId}</p>
  
  <p>
    <strong>UserName:</strong>
    {isEditing ? (
      <input
        className="input-edit"
        type="text"
        name="username"
        value={editableUser.username || ''}
        onChange={handleInputChange}
      />
    ) : (
      editableUser.username
    )}
  </p>

  <p>
    <strong>Password:</strong>
    {isEditing ? (
      <input
        className="input-edit"
        type="text"
        name="password"
        maxLength={15}
        minLength={6}
        value={editableUser.password}
        onChange={handleInputChange}
        required
      />
    ) : (
      editableUser.password
    )}
  </p>

  <p><strong>Role:</strong> {editableUser.role}</p>

  <p>
    <strong>Status:</strong>
    {isEditing ? (
      <select
        className="select-edit"
        name="status"
        value={editableUser.status || ''}
        onChange={handleInputChange}
      >
        <option value="ACTIVE">ACTIVE</option>
        <option value="INACTIVE">INACTIVE</option>
      </select>
    ) : (
      editableUser.status
    )}
  </p>

  {isEditing ? (
    <>
      <button className="btn-save" onClick={handleSave}>Save</button>
      <button className="btn-cancel" onClick={() => {setIsEditing(false)
        setChanged(-1);}
      }>Cancel</button>
    </>
  ) : (
    <div>
      <button className="btn-back" onClick={handleBack}>Back</button>
      <button className="btn-edit" onClick={() => setIsEditing(true)}>Edit</button>
    </div>
  )}
</div></>


  );
};

export default UserDetails;