import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
function IssueBookComponent({token}){
    const navigate=new useNavigate();
     const [bookid,setbookid]=useState('');
    const [memberid,setmemberid]=useState('');
     const handleform=async(e)=>{
         e.preventDefault();
        try{
            const response=await axios.post("http://localhost:8095/issuerecords/issuebook", {
  bookid:bookid,
  memberid:memberid,
  
},{ headers: {
    "X-XSRF-TOKEN": token 
  },withCredentials: true });console.log("hello");
console.log("this is response"+response.data);


        }
        catch(err){
            console.log("error is "+err);
        }
                alert("book Issued successfully");

    }

     const handlechange=(e)=>{
       
        if(e.target.name==="bookid")
        {
            
            setbookid(e.target.value);
        }
        else if(e.target.name==="memberid")
        {
            
            setmemberid(e.target.value);
        }
    }
    const handleback=()=>{
    navigate("/issuerecords");
  }


    return <div>
    <h2>Issue a Book</h2>
    <form  onSubmit={handleform}>
        <label htmlFor="bookid">Book ID:</label>
        <input type="number" id="bookid" name="bookid" required onChange={handlechange} value={bookid}/><br></br>

        <label htmlFor="memberid">Member ID:</label>
        <input type="number" id="memberid" name="memberid" required onChange={handlechange} value={memberid}/><br></br>

        <input type="submit" value="Issue Book"/>
    </form>
    <button onClick={handleback}>Back</button>

    
    </div>;
}

export default IssueBookComponent;