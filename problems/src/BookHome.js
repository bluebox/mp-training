import React, { useEffect, useState } from 'react';
import './App.css';
import { Link } from 'react-router-dom';

function BookHome() {
  const [message, setMessage] = useState('');

  useEffect(() => {
    fetch('http://localhost:8070/Book')
      .then(response => response.text()) 
      .then(data => setMessage(data))
      .catch(error =>{ console.error('Error fetching message:', error);
        setMessage(error);
  });
  }, []);

  return (
    <div className="Container">
      <h1>Library Management System - Books Home</h1>
       <Link to="/Bookhome/viewbooks">View All Books</Link>
      <br />
      <Link to="/Bookhome/AddBook">Add New Book</Link>
      <br />
      <Link to="/Bookhome/UpdateBook">Update the Book Details</Link>
      <br />
      <p>{message}</p>
    </div>
  );
}

export default BookHome;