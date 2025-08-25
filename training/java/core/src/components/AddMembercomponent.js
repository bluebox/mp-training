import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
function AddMemberComponent({token}) {
    const navigate=new useNavigate();
    const [name,setname]=useState('');
const [email,setemail]=useState('');
const [mobile,setmobile]=useState('');
const [gender,setgender]=useState('');
const [address,setaddress]=useState('');
    const handleform=async(e)=>{
         e.preventDefault();
         if(gender===''){
            alert("please select gender ");
            return;
         }
        try{
            const response=await axios.post("http://localhost:8095/Member/addmember", {
  memberid:1,
  name:name,
  email:email,
  mobile:mobile,
  gender:gender.toUpperCase(),
  address:address}
,{ headers: {
    "X-XSRF-TOKEN": token 
  },withCredentials: true });
console.log("hello");
console.log("this is response",response.data);

alert("member added successfully");
        }
        catch(err){
            alert(err.response.data);
            console.log("error is "+err);
        }

    }
    const handlechange=(e)=>{
       
        if(e.target.name==="name")
        {
            
            setname(e.target.value);
        }
        else if(e.target.name==="email")
        {
            
            setemail(e.target.value);
        }
       else if(e.target.name==="mobile")
        {
            
            setmobile(e.target.value);
        }
      else  if(e.target.name==="gender")
        {
            
            setgender(e.target.value);
        }
        else if(e.target.name==="address")
        {
            
            setaddress(e.target.value);
        }

    }

    const handleback=()=>{
    navigate("/viewallmembers");
  }

    return <div>
            <form onSubmit={handleform} >
            <div><h2>Add Member</h2></div>
	<div><label htmlFor="name">Member Name:</label>
	<input type="text" id="name" name="name" value={name} pattern="^[a-zA-Z\s]+$" title="name should not contain numbers" onChange={handlechange} required/></div>
    <div><label htmlFor="email" aria-placeholder="example@gmail.com">Email:</label>
	<input type="email" id="email" name="email" value={email} onChange={handlechange} required/></div>
    <div><label htmlFor="mobile">Phoneno:</label>
<input type="tel"  id="mobile"  name="mobile" value={mobile}  onChange={handlechange}  required  pattern="^\d{10}$"  title="Mobile number must be exactly 10 digits"/></div>    <div>
	<label htmlFor="gender">Gender:</label><br></br>
	<input type="radio" name="gender" value="Male" onChange={handlechange}/>Male 
	<input type="radio" name="gender" value="Female" onChange={handlechange}/>Female
	</div>
    <div>
	<label htmlFor="address">address:</label>
	<textarea name="address" id="address" value={address} onChange={handlechange} required></textarea>
	</div>
    <button type="submit">submit</button>
	</form>
    <button onClick={handleback}>Back</button>


    </div>



}

export default AddMemberComponent;