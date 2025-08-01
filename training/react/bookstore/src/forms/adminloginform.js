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
},[])

    const handleSubmit =async (e) =>{
        e.preventDefault();
        const { username, password } = Credentials;
        const response = await fetch("http://127.0.0.1:8000/bookStore/login/", {method: 'POST',headers: {'Content-Type': 'application/json',},
        body: JSON.stringify({ username, password }),
});
        // const data1 =  response1.json()

        if (!response.ok){
          alert("invalid credentials!!!!!!")
        }

        else{
           const response1 = await fetch("http://127.0.0.1:8000/bookStore/getUserRole/"+username)
            const data1 = await response1.json()
          console.log(data1)
        const data=await response.json()
        localStorage.setItem('access',data.access)
        localStorage.setItem('refresh',data.refresh)
        localStorage.setItem('role',data1)


        if (localStorage.getItem('role') === "admin") {
        navigate('/AdminHomePage')
        }
        else if (localStorage.getItem('role')=== "author") {
          navigate('/AuthorsPage',{state:username})}
        else if (localStorage.getItem('role') === "customer") {
          navigate('/CustomerPage',{state:username})
        }
        else {
          alert("Invalid role")
        }
      

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
      
      <button className='nav-button' onClick={() => navigate('/CustomerRegisterPage')}>sign up as customer</button>
      
      <button className='nav-button' onClick={() => navigate('/AuthorRegisterPage')}>sign up Author</button>
</nav>

<div className="login-box">
        <form className='form' onSubmit={handleSubmit} >
      <label htmlFor="username" style={{display:'flex', margin:'15px' }}>Username:<h className="red-star">*</h>   <input className="inputs-gap" type="text" name="username" onChange={handleChange}   placeholder="username"  required /> </label><br />

    <label htmlFor="password" style={{display:'flex'}}> Password: <h className="red-star">*</h><input className="inputs-gap" type="password" name="password" onChange={handleChange}  placeholder="password"  required /></label> <br />
    <button  type="submit" >Login</button>


</form>
    </div>
        
        
        </>)
}