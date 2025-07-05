import axios from 'axios';
import React, { useState } from 'react'
import { Link } from 'react-router-dom'

const IssueBook = () => {
  const [formData, setFormData] = useState({
        bookId: "",
        memberId: ""
    });
    const [responseMessage, setResponseMessage] = useState("");

    const handleChange = event => {
        setFormData(prev => ({ ...prev, [event.target.name]: event.target.value }));
    };

    const handleClick = async (event) => {
    event.preventDefault();
    console.log("Issue Book data : ", formData);  
    try {
        const result = await axios.post("http://localhost:8080/api/issue/issueBook", formData,
            {
                headers:{
                    "invocationFrom": "invocationFrom"  
                }
            }
        );
        setResponseMessage("Book Issued successfully!");
    } 
    catch (error) {
        console.log("Error : ", error.response);  
        setResponseMessage("Book failed to Issue!");
    }
    };

    return (
        <div>
            <h1>Issue Book</h1>

            {responseMessage && <h2>{responseMessage}</h2>}

            <form onSubmit={handleClick}>
                <label>Book ID</label><br />
                <input type="text" name="bookId" id="" value={formData.bookId} onChange={handleChange} required /><br /><br />
                <label>Member ID</label><br />
                <input type="text" name="memberId" id="" value={formData.memberId} onChange={handleChange} required /><br /><br />
                <button type="submit" className="btn btn-success">Submit</button>
                <button type="reset" className="btn btn-info ms-3">Reset</button>
                <Link className="btn btn-dark ms-3" to="/">Home</Link>
            </form>             
        </div>
    );
}

export default IssueBook
