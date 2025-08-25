

import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
function UpdateBookComponent({token}) {
    const navigate=new useNavigate();
    const [bookid,setbookid]=useState('');
    const [title,settitle]=useState('');
const [author,setauthor]=useState('');
const [category,setcategoroy]=useState('');
const [status,setstatus]=useState('');
const [availability,setavailability]=useState('');
    const handleform=async(e)=>{
         e.preventDefault();
         if(status===''|| availability===''){
            alert("select status and availability");
            return;
         }
        try{
            const response=await axios.post("http://localhost:8095/Book/updatebook", {
  bookid:bookid,
  title:title,
  author:author,
  category:category,
  status:status.toUpperCase(),
  availability:availability.toUpperCase()
},{ headers: {
    "X-XSRF-TOKEN": token 
  },withCredentials: true });
console.log("hello");
console.log("this is response"+response.data);
alert("book updated successfully");

        }
        catch(err){
            alert(err.response.data);
            console.log("error is "+err);
        }
                


    }
    const handlechange=(e)=>{
       if(e.target.name==="bookid"){
        setbookid(e.target.value);
       }
        if(e.target.name==="title")
        {
            
            settitle(e.target.value);
        }
        else if(e.target.name==="author")
        {
            
            setauthor(e.target.value);
        }
       else if(e.target.name==="category")
        {
            
            setcategoroy(e.target.value);
        }
      else  if(e.target.name==="status")
        {
            
            setstatus(e.target.value);
        }
        else if(e.target.name==="availability")
        {
            
            setavailability(e.target.value);
        }

    }
    const handleback=()=>{
    navigate("/viewbooks");
  }




    return <div>
            <form onSubmit={handleform} >
            <div><h2>Update Book</h2></div>
            <div>
            <label htmlFor="bookid">
                BookId:
            </label>
            <input type="number" id="bookid"  name="bookid" value={bookid} onChange={handlechange} required/>

            </div>
	<div><label htmlFor="title">Book title:</label>
	<input type="text" id="title" name="title" value={title} onChange={handlechange} required/></div>
    <div><label htmlFor="author">Author:</label>
	<input type="text" id="author" name="author" value={author} pattern="^[a-zA-Z\s]+$" title="name should not contain numbers" onChange={handlechange} required/></div>
    <div><label htmlFor="category">Category:</label>
	<input type="text" id="category" name="category" value={category} onChange={handlechange} required/></div>
    <div>
	<label htmlFor="status">Status:</label><br></br>
	<input type="radio" name="status" value="Active" onChange={handlechange}/>Active 
	<input type="radio" name="status" value="InActive" onChange={handlechange}/>Inactive
	</div>
    <div>
	<label htmlFor="availability">Availability:</label><br></br>
	<input type="radio" name="availability" value="Available" onChange={handlechange}/>Available
	<input type="radio" name="availability" value="Issued" onChange={handlechange}/>Issued
	</div>
    <button type="submit">submit</button>
	</form>
    <button onClick={handleback}>Back</button>


    </div>



}

export default UpdateBookComponent;