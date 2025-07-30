import React ,{useEffect, useState} from "react";
import { useNavigate ,useLocation} from "react-router-dom";
import './forms.css'

export default function Adminlogin(){
    const navigate=useNavigate()
    const [Credentials ,setCredentials]=useState({'username':"",'password':""});
    const location=useLocation()
    const [id,setId]=useState(0)
    // const [data,setData]=useState({})
   
useEffect(()=> {
  localStorage.setItem('role',"")
  localStorage.setItem('access',"")
  localStorage.setItem('refresh',"")
})

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
        localStorage.setItem('role',"admin")
        navigate('/AdminHomePage')
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
      <button className='nav-button' onClick={() => navigate('/CustomerLogInpage')}>log in as Customer</button>
      <button className='nav-button' onClick={() => navigate('/AuthorLogInpage')}>log in as Author</button><h1>new customer?</h1>
      
      <button className='nav-button' onClick={() => navigate('/CustomerRegisterPage')}>sign up</button>
      <h1>new Author?</h1>
      <button className='nav-button' onClick={() => navigate('/AuthorRegisterPage')}>sign up</button>
</nav>

<div className="login-box">
        <form className='form' onSubmit={handleSubmit} >
      <label>Username:<input className="inputs-gap" type="text" name="username" onChange={handleChange}   placeholder="username"  required /> </label><br />

    <label> Password: <input className="inputs-gap" type="password" name="password" onChange={handleChange}  placeholder="password"  required /> </label><br />
    <button className='submit-button' type="submit" >Login</button>


</form>
    </div>
        
        
        </>)
}