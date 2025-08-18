import React, { useState, useEffect } from 'react';
import {  useLocation,useNavigate } from 'react-router-dom';




function AuthorForm(){
  
 const location=useLocation();
const navigate=useNavigate();
const [id,setId]=useState(0)
const { username, from = "" } = location.state || {};
const [formData, setFormData] = useState({
    name: "",
    username:"",
     age:'',
    email: "",
    gender: "",
    address: '',
    password:"",
    confirmPassword:""
  });

  useEffect(()=>{
  
    const fetchauthor =async () =>{
      const response=await fetch("http://127.0.0.1:8000/store/CustomUserView/"+username)
      const data=await response.json()
      setId(data.id)
      setFormData(data)
    }
if (username ){
  fetchauthor()
}
  },[username])
  


 const handleChange  = (e) => {
    setFormData(prev => ({
      ...prev,
      [e.target.name]: e.target.value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const response_access= await fetch("http://127.0.0.1:8000/store/GetAccessToken/"+localStorage.getItem('id'))

    console.log("response access",response_access)
    const response_access_json=await response_access.json()
    console.log("response access json",response_access_json.access)
    if (response_access.access ==="Session expired !!! please login again"){
      navigate('/')
      return;
    }
    if (formData.password !== formData.confirmPassword) {
      alert("Passwords do not match");
      return;
    }
  const method=(username)? 'PATCH':'POST';
  const headers= (username)? {
      'Content-Type': 'application/json','Authorization':'Bearer ' +response_access_json.access
    }:{'Content-Type': 'application/json'}
  const url=(username)? 'http://127.0.0.1:8000/store/CustomUserView/'+String(id) :'http://127.0.0.1:8000/store/CustomUserView/'
  const response=await fetch(url, {
    method: method,
    headers,
    body: JSON.stringify({...formData,role:'author'}),
  })
      if (response.status === 401 ||
      response.status === 403 ){
          alert("your session was expired")
      localStorage.removeItem('access')
      localStorage.removeItem('refresh')
      navigate('/')
      }
      if (!response.ok) {
      alert("registration failed")
        throw new Error('registration failed');
      }
    
    

  setFormData({
      name: "",
      username:"",
     age:'',
    email: "",
    gender: "",
    address: '',
    password:"",
    confirmPassword:""
    
    })
      if(from){
        navigate('/Authorrelatedpage')
      }
      else if (username){
        navigate('/Authorspage',{state:username})
      }
      else{
      navigate('/');
      }
      
  };

const handleHome = () => {
  if (from === 'admin') {
    navigate('/AdminHomePage');
  } else if (from === 'author') {
    navigate('/AuthorsPage', { state: username });
  } else {
    navigate('/');
  }
};


return( <><nav className="nav-link">
      <button className='nav-button' onClick={handleHome}>HOME </button>
</nav>



<div className='registration-border'>
<h1 style={{color:'black',textAlign:'center'}}>{ username ? 'Editing the details':'Author Registration'}</h1>
<form className='form' onSubmit={handleSubmit}>
      <label  htmlFor="name" >Name:<h className="red-star">*</h></label><input className="inputs-gap" type="text" name="name" value={formData.name} onChange={handleChange} placeholder="Name"  required /> <br />
      <label  htmlFor="username"> Username:<h className="red-star">*</h></label> <input className="inputs-gap" type="text" name="username" value={formData.username} onChange={handleChange}  required /> <br />
    <label> Age:<h className="red-star">*</h> </label><input className="inputs-gap" type="number" name="age"  value={formData.age} onChange={handleChange}  placeholder="An integer"  required /> <br />

    <label > Gender:<h className="red-star">*</h>
    <lable htmlFor='Male'><input  type="radio" onChange={handleChange} name="gender" value="Male" checked={formData.gender === 'Male'} /> male </lable>
    <lable htmlFor='Female'> <input  type="radio" onChange={handleChange}  name="gender" value="Female" checked={formData.gender === 'Female'}  /> Female</lable><br />
      </label>
       <label > Email:<h className="red-star">*</h></label> <input className="inputs-gap" type="email" name="email" value={formData.email} onChange={handleChange}  required /> <br />
       <label> Address: <h className="red-star">*</h></label><input className="inputs-gap" type="text" name="address" value={formData.address} onChange={handleChange}  required /> <br />
       <label> Password:<h className="red-star">*</h></label> <input className="inputs-gap" type="password" name="password" value={formData.password} onChange={handleChange}  required /> <br />
        <label> Confrom Password: <h className="red-star">*</h></label><input className="inputs-gap" type="password" name="confirmPassword" value={formData.confirmPassword} onChange={handleChange}  required /><br />
      <button className='submit-button' type="submit" >Save</button>
      </form>
      {/* hofi */}

</div></>);


}
export default AuthorForm;