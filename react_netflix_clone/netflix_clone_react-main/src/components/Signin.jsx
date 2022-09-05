import React, { useState } from 'react'
import Footer from './Footer';
import "../styles/signin.css"
import { useNavigate } from "react-router-dom";


import backgroundphoto from "../resources/netflix_logo.png";
import { useCookies } from 'react-cookie';


const Signin = () => {
  const [cookies,setcookie,removecookie] = useCookies(['user_login']);
  const navigate = useNavigate();
  
  const [id,setid]=useState("");
  const [error,seterror]=useState("");
  const [pass,setpass]=useState("");
  function onchangeclass1(e){
   setid(e.target.value) ;
  }
  function onchangeclass2(e){
    setpass(e.target.value) ;
  }
  function checklogin(){
    for (var prop in userid_pass) {
      if(id==prop){
        if (pass == userid_pass[prop]){
          setcookie("login","false");
          // removecookie("login")

          navigate("/videos")
        
        }
        else{
          seterror("Invalid id or password!")
        }
      }
      else{
        seterror("Invalid id or password!")
      }
  }
  }
  var userid_pass = {"yatinvohra": "yatin", "ishansharma": "ishan", "root": "root"};
  return (
    <>
    <div className="homefold2">
    <div className="heading2">
          <img src={backgroundphoto} alt="" />
          </div>
          <div className="mainform">
            <div className="form">
            
                <h2>Sign In</h2>
                <input type="text" placeholder='Email or phone number' name='id' onChange={(e)=>onchangeclass1(e)} />
                <input type="password" placeholder='Password' name='password' onChange={(e)=>onchangeclass2(e)} />
            
<br />
<button className='signinin' onClick={checklogin}>Sign In</button>
<div className="help">
<div className='rememberme'>
<div className="leftalign">
  <input type="checkbox" name='check' className='Checkinp'/>
<label htmlFor="name">Remeber me</label>
<p id="red">{error}</p>
</div>

<p >Need Help?</p>
</div>
</div>
<div className="newtonetflix">
    <div className="aligntogeth"><h4>New to netflix?</h4><h4>Sign up now</h4></div>
    <p>This page is protected by Google reCAPTCHA to <br/>ensure you're not a bot. </p>
</div>
</div>
            {/* </form> */}
          </div>
        </div>
        <Footer/> 
        </>
  )
}

export default Signin;