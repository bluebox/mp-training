import React, { useEffect, useState } from 'react';
import './App.css';
import { Link } from 'react-router-dom';

function IssueRecordHome() {
  const [message, setMessage] = useState('');

  useEffect(() => {
    fetch('http://localhost:8070/Issues')
      .then(response => response.text()) 
      .then(data => setMessage(data))
      .catch(error => console.error('Error fetching message:', error));
  }, []);

  return (
    <div className="Container">
      <h1>Library Management System - Issue Records Home</h1>
      <Link to="/IssueRecordshome/ViewIssues">View All Issues</Link>
            <br />
            <Link to="/IssueRecordshome/CreateIssue">Issue Book</Link>
            <br />
            <Link to="/IssueRecordshome/Return">Return Book</Link>
            <br />
      <p>{message}</p>
    </div>
  );
}

export default IssueRecordHome;