import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";


function AuthorLogInpage(){
    const [credentials ,setCredentials]=useState({'username':"",'password':""});
    const navigate=useNavigate();
    


       const handleSubmit =async (e) =>{
        e.preventDefault();
        const { username, password } = credentials;
        const response = await fetch("http://127.0.0.1:8000/bookStore/login/", {method: 'POST',headers: {'Content-Type': 'application/json',},
        body: JSON.stringify({ username, password }),
});

        if (!response.ok){
          alert("invalid credentials!!!!!!")
        }
        else{
        const data=await response.json()
        localStorage.setItem('access',data.access)
        localStorage.setItem('refresh',data.refresh)
        localStorage.setItem('role',"author")
        navigate('/AuthorsPage',{state:username})
        }
      

    }

    const handleChange  = (e) => {
    setCredentials(prev => ({
      ...prev,
      [e.target.name]: e.target.value
    }));
  };


    return (<>
       
       <nav className="nav-link">
      <button className='nav-button' onClick={() => navigate('/')}>HOME </button>
</nav>
   
    <div className="login-box">

<h2 style={{color:'red',textAlign:'center'}}>Author Login </h2>
        <form className='form' onSubmit={handleSubmit} >
      <label>Username:<input className="inputs-gap" type="text" name="username" onChange={handleChange}   placeholder="username"  required /> </label><br />

    <label> Password: <input className="inputs-gap" type="password" name="password" onChange={handleChange}  placeholder="password"  required /> </label><br />
    <button className='submit-button' type="submit" >Login</button>


</form>
    </div>
    
    
    </>)
};export default AuthorLogInpage;