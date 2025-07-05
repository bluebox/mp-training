import axios from 'axios';
import React, { useState } from 'react'
import { Link } from 'react-router-dom'

const UpdateMember = () => {
  const [formData, setFormData] = useState({
        memberId:"",
        name: "",
        email: "",
        mobile: "",
        gender: "",
        address: ""
    });
    const [responseMessage, setResponseMessage] = useState("");

    const handleChange = event => {
        setFormData(prev => ({ ...prev, [event.target.name]: event.target.value }));
    };

    const handleClick = async (event) => {
    event.preventDefault();
    console.log("Member data : ", formData);  
    try {
        const result = await axios.put("http://localhost:8080/api/member/updateMember", formData,
            {
                headers:{
                    "invocationFrom": "invocationFrom"  
                }
            }
        );
        setResponseMessage("Member Updated successfully!");
    } 
    catch (error) {
        console.log("Error : ", error.response);  
        setResponseMessage("Member failed to update!");
    }
    };

    return (
        <div>
            <h1>Update Member</h1>

            {responseMessage && <h2>{responseMessage}</h2>}

            <form onSubmit={handleClick}>
                <label>Member ID</label><br />
                <input type="text" name="memberId" id="" value={formData.memberId} onChange={handleChange} required /><br /><br />
                <label>Name</label><br />
                <input type="text" name="name" id="" value={formData.name} onChange={handleChange} required /><br /><br />
                <label>Email</label><br />
                <input type="text" name="email" id="" value={formData.email} onChange={handleChange} required /><br /><br />
                <label>Mobile</label><br />
                <input type="text" name="mobile" id="" value={formData.mobile} onChange={handleChange} required /><br /><br />

                <label>Gender</label><br />
                <label>Male</label>
                <input type="radio" name="gender" value="MALE" onChange={handleChange} required /> <br />
                <label>Female</label>
                <input type="radio" name="gender" value="FEMALE" onChange={handleChange} /><br /><br />

                <label>Address</label><br />
                <input type="text" name="address" id="" value={formData.address} onChange={handleChange} required /><br /><br />

                <button type="submit" className="btn btn-success">Submit</button>
                <button type="reset" className="btn btn-info ms-3">Reset</button>
                <Link className="btn btn-dark ms-3" to="/">Home</Link>
            </form>             
        </div>
    );
}

export default UpdateMember
