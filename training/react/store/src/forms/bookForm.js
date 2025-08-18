import React, { useState, useEffect, useRef } from 'react';
import {  useLocation,useNavigate } from 'react-router-dom';
import AuthorsPage from '../Pages/Authors/AuthorsPage';
import { getCSRFToken } from './csrf'; 




function BookAddingForm(){

  const location=useLocation();
  const { book="", from = "" } = location.state || {};
  const navigate=useNavigate();
  const [headers,setHeaders]=useState({'Content-Type': 'application/json'})
  const [access,setAccess]=useState("");
   const [authorsList, setAuthorsList] = useState([]);

  const [formData, setFormData] = useState({
    title: "",
     price:'',
    content: "",
    author:""
  });



const handleAccessToken =( async ()=>{
  const  response_access= await fetch("http://127.0.0.1:8000/store/GetAccessToken/"+localStorage.getItem('id'))
  const response_access_json=await response_access.json()
  if (response_access_json.access ==="Session expired !!! please login again"){
     alert("Session expired !!! please login again")
      navigate('/')
      return;}
  const  get_authors= await fetch("http://127.0.0.1:8000/store/getAuthors/")
const authorslist=await get_authors.json()
setAuthorsList(authorslist)
  setAccess(response_access_json.access)
  console.log(response_access_json.access)
  setHeaders({...headers,'Authorization': 'Bearer ' + response_access_json.access });
})


  useEffect( ()=> {
 handleAccessToken();
  },[])


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
  const url = book
    ? 'http://127.0.0.1:8000/store/books/'+book.id
    : 'http://127.0.0.1:8000/store/books/';
  const method = book ? 'PATCH' : 'POST';

  try {
    const response = await fetch(url, {
      method: method,
      headers:headers,
      body: JSON.stringify(formData),
    });
      
    if (!response.ok) {
      alert("registration failed");
      throw new Error('registration failed');
    }

    setFormData({
      title: "",
      price: '',
      content: "",
      author:""
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



return( <>
<nav className="nav-link">

  <button className='nav-button' onClick={() => navigate('/AdminHomePage')}>Home</button>
<button className="nav-button" onClick={() => navigate('/BooksPage')}>Back</button>

</nav>
<div className='registration-border'>


<h1 style={{color:'red',textAlign:'center'}}>{ book ? 'Editing the details':'Registration'}</h1>
<form className='form' onSubmit={handleSubmit}>
      <label>Title:<input className="inputs-gap" type="text" name="title" value={formData.title} onChange={handleChange} placeholder="title"  required /> </label><br />
     <label> Price: <input className="inputs-gap" type="number" name="price"  value={formData.price} onChange={handleChange}  placeholder="price"  required /> </label><br />
     <label> Content: <input className="inputs-gap" type="text" name="content" value={formData.content} onChange={handleChange}  required /> </label><br />
      <label>Author:</label>
        <select title='Select' name='author' onChange={handleChange} value={formData.author}>
        {authorsList.map((option) => (
          <option key={option.id} value={option.id}>
            {option.username}
          </option>
        ))}
      </select>
      {(book)?
       <button className='submit-button' type="submit" >SAVE </button>:
      <button className='submit-button' type="submit" >Submit</button>}
      </form>

</div></>);


}
export default BookAddingForm;