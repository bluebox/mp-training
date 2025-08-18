import React, { useEffect, useState } from 'react';
import './App.css';
import { Link } from 'react-router-dom';

function RepotsHome() {
  const [message, setMessage] = useState('');

  useEffect(() => {
    fetch('http://localhost:8070/Reports')
      .then(response => response.text()) 
      .then(data => setMessage(data))
      .catch(error => {console.error(error);
        setMessage(error);
      });
  }, []);

  return (
    <div className="Container">
      <h1>Library Management System - Reports Home</h1>
       <Link to="/Reportshome/OverdueBooks">View OverDue Books</Link>
      <br />
      <Link to="/Reportshome/BooksPerCategory">View Books Per Category</Link>
      <br />
      <Link to="/Reportshome/ActiveBookMembers">View Active Book Members</Link>
      <br />
      <p>{message}</p>
    </div>
  );
}

export default RepotsHome;