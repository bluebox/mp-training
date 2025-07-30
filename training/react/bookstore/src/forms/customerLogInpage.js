import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";


function CustomerLogInpage(){
    const [Credentials ,setCredentials]=useState({'username':"",'password':""});
    const navigate=useNavigate();
    const location=useLocation()
    const [id,setId]=useState(0)
    const [data,setData]=useState({})
   


    const handleSubmit =async (e) =>{
        e.preventDefault();
        const { username, password } = Credentials;
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
        localStorage.setItem('role',"customer")
        navigate('/CustomerPage',{state:username})
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
   <h1 style={{color:'red',textAlign:'center'}}>Customer Login </h1>
    <div className="login-box">


        <form className='form' onSubmit={handleSubmit} >
      <label>Username:<input className="inputs-gap" type="text" name="username" onChange={handleChange}   placeholder="username"  required /> </label><br />

    <label> Password: <input className="inputs-gap" type="password" name="password" onChange={handleChange}  placeholder="password"  required /> </label><br />
    <button className='submit-button' type="submit" >Login</button>


</form>
    </div>
    
    
    </>)
};export default CustomerLogInpage;