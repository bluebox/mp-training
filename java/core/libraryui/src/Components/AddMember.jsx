import axios from 'axios';
import React, { useState } from 'react'
import { Link } from 'react-router-dom';

const AddMember = () => {

const [formData, setFormData] = useState({
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
        const result = await axios.post("http://localhost:8080/api/member/addMember", formData,
            {
                headers:{
                    "invocationFrom": "invocationFrom"  
                }
            }
        );
        setResponseMessage("Member Added successfully!");
    } 
    catch (error) {
        console.log("Error : ", error.response);  
        setResponseMessage("Member failed to add!");
    }
    };

    return (
        <div>
            <h1>Add Member</h1>

            {responseMessage && <h2>{responseMessage}</h2>}

            <form onSubmit={handleClick}>
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

export default AddMember;
