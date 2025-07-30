import React, { useState, useEffect } from 'react';
import {  useLocation,useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { addCustomer, updateCustomer, clearEditCustomer } from './redux_files/customers'



function RegisterPage(){

const location=useLocation();
const navigate=useNavigate();
const dispatch = useDispatch();
const user=location.state?.user || null;
// const index=location.state?.index ?? null;



  const [formData, setFormData] = useState({
    name: "",
     age:'',
    email: "",
    Gender: "",
    address: ''
  });

useEffect(()=> {
if (user) {
   setFormData(user);
};},[user]
);



 const handleChange  = (e) => {
    setFormData(prev => ({
      ...prev,
      [e.target.name]: e.target.value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // let mydata = JSON.parse(localStorage.getItem('myFormData')) || [];

    if (formData.id) {
      // Update existing customer
      dispatch(updateCustomer(formData));
      dispatch(clearEditCustomer());
    } else {
      // Add new customer, generate id
      dispatch(addCustomer({ ...formData, id: Date.now().toString() }));
    }

    // Reset form and navigate back
    setFormData({
      id: '',
      name: '',
      age: '',
      email: '',
      Gender: '',
      address: '',
    });

    navigate('/customerView');
  };




return( <div className='registration-border'>
<h1 style={{color:'red',textAlign:'center'}}>{ user ? 'Editing the details':'Registration'}</h1>
<form className='form' onSubmit={handleSubmit}>
      <label>Name:<input className="inputs-gap" type="text" name="name" value={formData.name} onChange={handleChange} placeholder="Name"  required /> </label><br />

    <label> Age: <input className="inputs-gap" type="number" name="age"  value={formData.age} onChange={handleChange}  placeholder="An integer"  required /> </label><br />

    <label required> Gender: <input className="inputs-gap" type="radio" onChange={handleChange} name="Gender" value="male" checked={formData.Gender === 'male'} /> male
                                <input className="inputs-gap" type="radio" onChange={handleChange}  name="Gender" value="female" checked={formData.Gender === 'female'}  /> Female</label><br />
       <label> Email: <input className="inputs-gap" type="email" name="email" value={formData.email} onChange={handleChange}  required /> </label><br />
       <label> Address: <input className="inputs-gap" type="text" name="address" value={formData.address} onChange={handleChange}  required /> </label><br />

      <button className='submit-button' type="submit" >Save</button>
      </form>

</div>);


}
export default RegisterPage;