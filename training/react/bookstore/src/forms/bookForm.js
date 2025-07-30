import React, { useState, useEffect } from 'react';
import {  useLocation,useNavigate } from 'react-router-dom';
import AuthorsPage from '../Pages/Authors/AuthorsPage';
import { getCSRFToken } from './csrf'; 




function BookAddingForm(){

  const location=useLocation();
  const { book="", from = "" } = location.state || {};
  const navigate=useNavigate();
  const [formData, setFormData] = useState({
    title: "",
     price:'',
    content: "",
  });


useEffect(() => {
  if (book) {
    setFormData(book);
  }
}, [book]);

useEffect(() => {
  console.log("formData updated:", formData);
}, [formData]);


 const handleChange = (e) => {
  setFormData(prev => ({
    ...prev,
    [e.target.name]: e.target.value
  }));
};

const handleSubmit = async (e) => {
  e.preventDefault();
  console.log("book.id",book.id)
  const url = book
    ? 'http://127.0.0.1:8000/bookStore/books/'+book.id
    : 'http://127.0.0.1:8000/bookStore/books/';
  const method = book ? 'PATCH' : 'POST';

  try {
    const response = await fetch(url, {
      method: method,
      headers: {
        'Content-Type': 'application/json','X-CSRFToken': getCSRFToken(),'Authorization':'Bearer ' + localStorage.getItem('access') 
      },
      body: JSON.stringify(formData),
    });
       if (response.status === 401 ||
      response.status === 403 ){
          alert("your session was expired")
      localStorage.removeItem('access')
      localStorage.removeItem('refresh')
      navigate('/')
      }
    if (!response.ok) {
      alert("registration failed");
      throw new Error('registration failed');
    }

    setFormData({
      title: "",
      price: '',
      content: "",
    });
    if(from === 'admin'){
    navigate('/BooksPage');
    }
    else {
      navigate('/AuthorsPage')
    }
  } catch (error) {
    console.error('Error:', error);
  }
};



return( <div className='registration-border'>
<h1 style={{color:'red',textAlign:'center'}}>{ book ? 'Editing the details':'Registration'}</h1>
<form className='form' onSubmit={handleSubmit}>
      <label>Title:<input className="inputs-gap" type="text" name="title" value={formData.title} onChange={handleChange} placeholder="title"  required /> </label><br />
     <label> Price: <input className="inputs-gap" type="number" name="price"  value={formData.price} onChange={handleChange}  placeholder="price"  required /> </label><br />
     <label> Content: <input className="inputs-gap" type="text" name="content" value={formData.content} onChange={handleChange}  required /> </label><br />
      
      {(book)?
       <button className='submit-button' type="submit" >SAVE </button>:
      <button className='submit-button' type="submit" >Submit</button>}
      </form>

</div>);


}
export default BookAddingForm;