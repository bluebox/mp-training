import axios from 'axios';
import React, { useState } from 'react'
import { Link } from 'react-router-dom'

const ReturnBook = () => {
  const [formData, setFormData] = useState({
        issueId:""
    });
    const [responseMessage, setResponseMessage] = useState("");

    const handleChange = event => {
        setFormData(prev => ({ ...prev, [event.target.name]: event.target.value }));
    };

    const handleClick = async (event) => {
    event.preventDefault();
    console.log("Return Book data : ", formData);  
    try {
        const result = await axios.post("http://localhost:8080/api/issue/returnBook", formData,
            {
                headers:{
                    "invocationFrom": "invocationFrom"  
                }
            }
        );
        setResponseMessage("Book return successfully!");
    } 
    catch (error) {
        console.log("Error : ", error.response);  
        setResponseMessage("Book failed to Return!");
    }
    };

    return (
        <div>
            <h1>Return Book</h1>

            {responseMessage && <h2>{responseMessage}</h2>}

            <form onSubmit={handleClick}>
                <label>Issue ID</label><br />
                <input type="text" name="issueId" id="" value={formData.issueId} onChange={handleChange} required /><br /><br />
                <button type="submit" className="btn btn-success">Submit</button>
                <button type="reset" className="btn btn-info ms-3">Reset</button>
                <Link className="btn btn-dark ms-3" to="/">Home</Link>
            </form>             
        </div>
    );
}

export default ReturnBook
