import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";


function LoginPage(){
    const [credentials ,setCredentials]=useState({ username: "", password: "" });
    const navigate=useNavigate();
    const [refresh_token,setRefresh]=useState("")
    const [access_token,setaccess]=useState("")


    const handleSubmit = async (e) =>{
        e.preventDefault();
        try{
            const response=await fetch("http://127.0.0.1:8000/blog/login/",{
                method:'POST',headers: {"Content-Type": "application/json",},
                body: JSON.stringify(credentials),
            });
            if (!response.ok){
                alert("oops login failed !!! may be invalid credentials")
                throw new Error("Login failed !!!!!!!!!!!!");
            }
            const data = await response.json();
            console.log(typeof(data));
            setRefresh(data['refresh'])
            console.log(data.access)
            setaccess(data['access'])
            console.log("Login success:",data)

            navigate('/Home')
        }catch (error) {
    console.error("Error during login:", error);
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
};export default LoginPage;