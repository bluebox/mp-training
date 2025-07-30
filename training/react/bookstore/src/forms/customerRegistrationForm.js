import React, { useState, useEffect,useRef, use  } from 'react';

import {  useLocation,useNavigate } from 'react-router-dom';



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
    const method = (username)? 'PATCH' :"POST"
    // const tempHeader=(username)? 'Bearer ' + localStorage.getItem('access') :""
    const url= (username)? 'http://127.0.0.1:8000/bookStore/customers/'+String(id) :'http://127.0.0.1:8000/bookStore/customers/' ;
    
  const response=await fetch(url, {
    method: method,
    headers: {
      'Content-Type': 'application/json','Authorization':'Bearer ' + localStorage.getItem('access') 
    },
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
      navigate('/customerLogInpage');
      }
  };




return( <div className='registration-border'>
<h1 style={{color:'red',textAlign:'center'}}>{ username ? 'Editing the details':'Customer Registration'}</h1>
<form className='form' onSubmit={handleSubmit}>
      <label>Name:<input className="inputs-gap" type="text" name="name" value={formData.name} onChange={handleChange} placeholder="Name"  required /> </label><br />
      <label> Username: <input className="inputs-gap" type="text" name="username" value={formData.username} onChange={handleChange}  required /> </label><br />
    <label> Age: <input className="inputs-gap" type="number" name="age"  value={formData.age} onChange={handleChange}  placeholder="An integer"  required /> </label><br />

    <label required> Gender: <input className="inputs-gap" type="radio" onChange={handleChange} name="gender" value="Male" checked={formData.gender === 'Male'} /> male
                                <input className="inputs-gap" type="radio" onChange={handleChange}  name="gender" value="Female" checked={formData.gender === 'Female'}  /> Female</label><br />
       <label> Email: <input className="inputs-gap" type="email" name="email" value={formData.email} onChange={handleChange}  required /> </label><br />
       <label> Address: <input className="inputs-gap" type="text" name="address" value={formData.address} onChange={handleChange}  required /> </label><br />
        <label> Password: <input className="inputs-gap" type="password" name="password" value={formData.password} onChange={handleChange}  required /> </label><br />
        <label> Confrom Password: <input className="inputs-gap" type="password" name="confirmPassword" value={formData.confirmPassword} onChange={handleChange}  required /> </label><br />
      <button className='submit-button' type="submit" >Save</button>
      </form>

</div>);


}
export default CustomerRegisterPage;