import React, { useEffect, useState } from 'react';
import './App.css';
import { Link } from 'react-router-dom';
  import { useCookies } from 'react-cookie';


function Home() {
  const [message, setMessage] = useState('');
   const [cookies, setCookie] = useCookies(['name']);

  useEffect(() => {
    fetch('http://localhost:8070/')
      .then(response => response.text()) 
      .then(data => {
        setMessage(data);
        setCookie(message);
      })
      .catch(error => console.error(error));
  }, []);

  return (
    <div className="Container">
      <h1>Library Management System</h1>
      <Link to="/Bookhome">Books Home</Link>
      <br />
      <Link to="/Memberhome">Members Home</Link>
      <br />
      <Link to="/IssueRecordshome">Issues Home</Link>
      <br />
      <Link to="/Reportshome">Reports Home</Link>
      <p>@ {message}</p>
    </div>
  );
}

export default Home;
