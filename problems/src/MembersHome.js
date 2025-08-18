import React, { useEffect, useState } from 'react';
import './App.css';
import { Link } from 'react-router-dom';

function MembersHome() {
  const [message, setMessage] = useState('');

  useEffect(() => {
    fetch('http://localhost:8070/Member')
      .then(response => response.text()) 
      .then(data => setMessage(data))
      .catch(error => {console.error(error);
        setMessage(error);
      });
  }, []);

  return (
    <div className="Container">
      <h1>Library Management System - Member Home</h1>
       <Link to="/Memberhome/ViewMembers">View All Members</Link>
      <br />
      <Link to="/Memberhome/AddMember">Add New Member</Link>
      <br />
      <Link to="/Memberhome/UpdateMember">Update the Member Details</Link>
      <br />
      <p>{message}</p>
    </div>
  );
}

export default MembersHome;