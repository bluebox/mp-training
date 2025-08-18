import React, { useState, useEffect,useRef, use  } from 'react';

import {  useLocation,useNavigate } from 'react-router-dom';
import './forms.css'


function CustomerRegisterPage(){

const location=useLocation();
const navigate=useNavigate();
const [id,setId]=useState(0);
const { username="", from = "" } = location.state || {};
const [formData, setFormData] = useState({
    name: "",
    username: "",
     age:'',
    email: "",
    gender: "",
    address: '',
    password:"",
    confirmPassword:""
  });



const handleEditing =async (username) =>{
const response=await  fetch("http://127.0.0.1:8000/bookStore/customers/?username="+username,{method:'GET',headers: {'Content-Type': 'application/json','Authorization': 'Bearer ' + localStorage.getItem('access') }},);
const data =await response.json()
setId(data.id)
  setFormData(data)
  console.log("formated data",data)
}


useEffect(() => {
  if (username) {
     
    handleEditing(username);
  }
}, [username]);



 const handleChange  = (e) => {
    setFormData(prev => ({
      ...prev,
      [e.target.name]: e.target.value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
  if (formData.password !== formData.confirmPassword) {
      alert("Passwords do not match");
      return;
    } 




    const method = (username)? 'PATCH' :"POST"
    // const tempHeader=(username)? 'Bearer ' + localStorage.getItem('access') :""
    const url= (username)? 'http://127.0.0.1:8000/bookStore/customers/'+String(id) :'http://127.0.0.1:8000/bookStore/customers/' ;
    const headers = (username)? {'Content-Type': 'application/json','Authorization':'Bearer ' + localStorage.getItem('access') }:
    {'Content-Type': 'application/json'}; 
  const response=await fetch(url, {
    method: method,
    headers: headers,
    body: JSON.stringify(formData),
  })
    .then(response => {
      if (!response.ok) {
        alert("registration failed")
        throw new Error('registration failed');
      }
    })
    .catch(error => {
      console.error('Error:', error);
    });

  setFormData({
      name: "",
      username: "",
     age:'',
    email: "",
    gender: "",
    address: '',
    password:"",
    confirmPassword:""
    
    })
      if (from){
        navigate('/CustomerRelated')
      }
      else{
      navigate('/');
      }
  };




return( <><nav className="nav-link">
      <button className='nav-button' onClick={() => navigate('/CustomerPage' ,{state:username})}>HOME </button>
</nav>



<div className='registration-border'>
<h1 style={{color:'black',textAlign:'center'}}>{ username ? 'Editing the details':'Customer Registration'}</h1>
<form className='form' onSubmit={handleSubmit}>
  <div style={{display:"flex"}}>
      <div style={{display:"flow-root"}}>
      <label  htmlFor="name" >Name:<h className="red-star">*</h></label><input className="inputs-gap" type="text" name="name" value={formData.name} onChange={handleChange} placeholder="Name"  required /> <br />
      <label  htmlFor="username"> Username:<h className="red-star">*</h></label> <input className="inputs-gap" type="text" name="username" value={formData.username} onChange={handleChange}  required /> <br />
    </div>
    <div style={{display:'flow-root'}}>
      <label> Age:<h className="red-star">*</h> </label><input className="inputs-gap"  type="number" name="age"  value={formData.age} onChange={handleChange}  placeholder="An integer"  required /> <br />
      <label > Email:<h className="red-star">*</h></label> <input className="inputs-gap" type="email" name="email" value={formData.email} onChange={handleChange}  required /> <br />
    </div></div>

<div style={{display:'flex'}}>
      <div style={{display:'flow-root'}}>
        <label> Password:<h className="red-star">*</h><input className="inputs-gap" type="password" name="password" value={formData.password} onChange={handleChange}  required /> </label> 
      </div><div style={{display:'flow-root'}}>
        <label> Confrom Password: <h className="red-star">*</h></label><input className="inputs-gap" type="password" name="confirmPassword" value={formData.confirmPassword} onChange={handleChange}  required />
        </div>
         </div>

         <label style={{display:'flow-root'}} > Gender:<h className="red-star">*</h>
         <div style={{display: 'flex', flexDirection: 'row', gap: '10px'}}>
    <lable htmlFor='Male'><input  type="radio" onChange={handleChange} name="gender" value="Male" checked={formData.gender === 'Male'} /> male </lable>
    <lable htmlFor='Female'> <input  type="radio" onChange={handleChange}  name="gender" value="Female" checked={formData.gender === 'Female'}  /> Female
    </lable></div><br />
      </label>
       
      <label> Address: <h className="red-star">*</h></label><input className="inputs-gap" type="text" name="address" value={formData.address} onChange={handleChange}  required /> <br />
      <button className='submit-button' type="submit" >Save</button>
      </form>

</div></>);
      

}
export default CustomerRegisterPage;