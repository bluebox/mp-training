import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";


function DjangoLogin(){
    const [credentials ,setCredentials]=useState({});
    const admin={'Prasad':'P'};
    const navigate=useNavigate();


 const isLogin=localStorage.getItem('IsLogin')==='true';
 if(isLogin){
    navigate('/Home');
    return 
 }

    const handleSubmit = (e) =>{
        e.preventDefault();
        const { username, password } = credentials;
        if (username in admin && admin[username]===password){
            localStorage.setItem('IsLogin',true);
            navigate('/Home');
        }
        else{
            alert("Invalid credentials");
        }

    }

    const handleChange  = (e) => {
    setCredentials(prev => ({
      ...prev,
      [e.target.name]: e.target.value
    }));
  };


    return (<>
   <h1 style={{color:'red',textAlign:'center'}}>Log in </h1>
    <div className="login-box">


        <form className='form' onSubmit={handleSubmit} >
      <label>Username:<input className="inputs-gap" type="text" name="username" onChange={handleChange}   placeholder="username"  required /> </label><br />

    <label> Password: <input className="inputs-gap" type="password" name="password" onChange={handleChange}  placeholder="password"  required /> </label><br />
    <button className='submit-button' type="submit" >Login</button>


</form>
    </div>
    
    
    </>)
};export default DjangoLogin;