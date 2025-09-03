import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

const LogoutButton = () => {
  const navigate = useNavigate();
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const username = localStorage.getItem("username"); 

  useEffect(() => {
   
    if (localStorage.getItem("token") && username) {
      setIsLoggedIn(true);
    } else {
      setIsLoggedIn(false);
    }
  }, [username]);

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("username");


    navigate("/login"); 
    setIsLoggedIn(false);
  };

  return (
    <>
      {isLoggedIn && (
        <div
          style={{
            position: 'fixed',
            top: '20px',
            right: '20px',
            display: 'flex',
            alignItems: 'center',
            backgroundColor: '#238306ff',
            padding: '10px 20px',
            borderRadius: '5px',
            cursor: 'pointer',
            color: 'white',
          }}
        >
          {username && (
            <span style={{ marginRight: '10px' }}>
              Hello, <strong>{username}</strong>
            </span>
          )}
          <button
            onClick={handleLogout}
            style={{
              backgroundColor: '#0463e8ff',
              border: 'none',
              color: 'white',
              fontWeight: 'bold',
              borderRadius: '5px',
              padding: '8px 15px',
              cursor: 'pointer',
            }}
          >
            Logout
          </button>
        </div>
      )}
      {!isLoggedIn && (
        <div>
          
          <h2>Welcome To Store, Please Login</h2>
        </div>
      )}
    </>
  );
};

export default LogoutButton;
