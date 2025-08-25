import React, { useState } from 'react';
import './App.css';
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import Carousel from 'react-bootstrap/Carousel';
import { useCookies } from 'react-cookie';


function AdminDashBoard(){
   const [cookies, setCookie, removeCookie] = useCookies(['userData']);
   const [contacts,setContacts]=useState(false);  
   const [details,setDetails]=useState(false);
     const navigate = useNavigate();
    function handleContacts(e){
        setContacts(!contacts);
   }

   function handleProfile(){
       setDetails(!details);
   }

   function logout(){
       removeCookie('userData');
       navigate("/");  
   }

   function handleinsurances(){
          navigate("/InsuranceOps");
   }

   function handleissues(){
          navigate("/IssueInsuranceOps");
   }

   function Requests(){
         navigate("/Requests");
   }

   return (
    <div className="Home">
      <div className="Navbar">
        <span className="logo">V.I.M.S</span>
        <ul className="right2">
          <span className="right1" onClick={handleContacts}>ContactUs</span>
          <span className="right1" onClick={handleProfile}>
            ID: {cookies.userData?.username?.userId || "N/A"}
          </span>
          <span className="right1" onClick={logout}>Logout</span>
        </ul>
      </div>
       <h1>Admin DashBoard</h1>
      <div className="adminDiv">
      <h2>The Admin Operations</h2>
      <br></br>
      <br></br>
        <button onClick={handleinsurances}>Insurance Operations</button>
        <br></br>
         <button onClick={handleissues}>Issue_Insurance Operations</button>
           <br></br>
         <button onClick={Requests}>Requests</button>
      </div>
      {details && cookies.userData?.username && (
        <div className="Contacts" onMouseLeave={handleProfile}>
          <h3>
            {cookies.userData.username.userFirstname}_{cookies.userData.username.userLastname}
          </h3>
          <p>
            Contacts: {cookies.userData.username.userEmail}, {cookies.userData.username.userPhone}
          </p>
          <p>Address: {cookies.userData.username.userAddress}</p>
          <p>Role: {cookies.userData.username.role}</p>
        </div>
      )}
      {contacts && (
        <div className="Contacts" onMouseLeave={handleContacts}>
          <h3>Contact US</h3>
          <p>VIMS.Support@Gmail.com</p>
          <p>012435678978</p>
        </div>
      )}
    </div>
  );



}


export default AdminDashBoard;