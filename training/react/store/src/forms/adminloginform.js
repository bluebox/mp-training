import React ,{useEffect, useState} from "react";
import { useNavigate ,useLocation} from "react-router-dom";
import './forms.css'

export default function Adminlogin(){
    const navigate=useNavigate()
    const [Credentials ,setCredentials]=useState({'username':"",'password':""});
    const location=useLocation()
    const [id,setId]=useState(0)
    const [AAlert_username,setusername_alert]=useState(false)
    const [AAlert_password,setpassword_alert]=useState(false)
    // const [data,setData]=useState({})
   

    useEffect(() => {
      localStorage.clear();},[])

    const handleSubmit =async (e) =>{
        e.preventDefault();
        const { username, password } = Credentials;
        const response = await fetch("http://127.0.0.1:8000/store/login/", {method: 'POST',headers: {'Content-Type': 'application/json',},
        body: JSON.stringify({ username, password }),
});
        if (!response.ok) {
          alert("invalid credentials!!!!!!")
        }

        else{
          const data = await response.json()
          console.log("data",data[1])
          console.log("ID",data[0])
          setId(data[0])
          localStorage.setItem('id',data[0])
          localStorage.setItem('role',data[1])

        if (data === "failed") {
          alert("invalid credentials!")}
        else if (data[1]=== "admin") {
        navigate('/AdminHomePage',{state:{'username':username}})
        }
        else if (data[1]=== "author") {
          navigate('/AuthorsPage',{state:username})}
        else if (data[1] === "customer") {
          navigate('/CustomerPage',{state:username})
        }
        else {
          alert("Invalid role")
        }
      

      }
    }
    const handleChange_username = (e) => {
      console.log(e.target.value)
      if (e.target.value.length < 3) {
        setusername_alert(true)}
      else{
        setusername_alert(false)
      
    setCredentials(prev => ({
      ...prev,
      [e.target.name]: e.target.value
    }));}
  };


  const handleChange_password  = (e) => {
      console.log(e.target.value)
      if (e.target.value.length < 3) {
        setpassword_alert(true)}
      else{
        setpassword_alert(false)
      }
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
      <label htmlFor="username" style={{display:'flex'}}>Username:<h5 className="red-star">*</h5>   <input className="inputs-gap" type="text" name="username" onChange={handleChange_username}   placeholder="username"  required /> </label>
      { AAlert_username&& <p className="error-message">Username must be at least 3 characters long</p>} <br />
    <label htmlFor="password" style={{display:'flex'}}> Password: <h5 className="red-star">*</h5><input className="inputs-gap" type="password" name="password" onChange={handleChange_password}  placeholder="password"  required /></label>
    { AAlert_password && <p className="error-message">Password must be at least 3 characters long</p>} <br />
    <button  type="submit" >Login</button>


</form>
    </div>
        
        
        </>)
}