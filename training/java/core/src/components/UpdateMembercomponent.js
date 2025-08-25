import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
function UpdateMemberComponent({token}) {
    const navigate=new useNavigate();
    const [memberid,setmemberid]=useState('')
    const [name,setname]=useState('');
const [email,setemail]=useState('');
const [mobile,setmobile]=useState('');
const [gender,setgender]=useState('');
const [address,setaddress]=useState('');
    const handleform=async(e)=>{
         e.preventDefault();
        try{
            const response=await axios.post("http://localhost:8095/Member/updatemember", {
  memberid:memberid,
  name:name,
  email:email,
  mobile:mobile,
  gender:gender.toUpperCase(),
  address:address
},{ headers: {
    "X-XSRF-TOKEN": token 
  },withCredentials: true });
console.log("hello");
console.log("this is response",response.data);

        alert("Member updated successfully");

        }
        catch(err){
            alert(err.response.data);
            console.log("error is "+err);
        }

    }
    const handlechange=(e)=>{
       if(e.target.name==="memberid"){
        setmemberid(e.target.value);
       }
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
            <div><h2>Update Member</h2></div>
            <div>

                <label htmlFor="memberid">Member id</label>
                <input type="number" name="memberid" value={memberid} required onChange={handlechange}/>
            </div>
	<div><label htmlFor="name">Member Name:</label>
	<input type="text" id="name" name="name" value={name} pattern="^[a-zA-Z\s]+$" title="name should not contain numbers" required onChange={handlechange}/></div>
    <div><label htmlFor="email" aria-placeholder="example@gmail.com" >Email:</label>
	<input type="email" id="email" name="email" value={email} required onChange={handlechange}/></div>
    <div><label htmlFor="mobile">Phoneno:</label>
<input type="tel"  id="mobile"  name="mobile"  value={mobile}  onChange={handlechange}  onInput={(e) => e.target.value = e.target.value.replace(/[^0-9]/g, '')}  required  pattern="^\d{10}$"  title="Mobile number must be exactly 10 digits"
/>
</div>    <div>
	<label htmlFor="gender">Gender:</label><br></br>
	<input type="radio" name="gender" value="Male" onChange={handlechange}/>Male 
	<input type="radio" name="gender" value="Female" onChange={handlechange}/>Female
	</div>
    <div>
	<label htmlFor="address">address:</label>
	<textarea name="address" id="address" value={address} required onChange={handlechange}></textarea>
	</div>
    <button type="submit">submit</button>
	</form>
    <button onClick={handleback}>Back</button>


    </div>



}

export default UpdateMemberComponent;