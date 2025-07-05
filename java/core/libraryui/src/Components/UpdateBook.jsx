import axios from 'axios';
import React, { useState } from 'react'
import { Link } from 'react-router-dom'

const UpdateBook = () => {
  const [formData, setFormData] = useState({
        bookId:"",
        title: "",
        author: "",
        category: "",
        status: "",
        availability: ""
    });

    const [responseMessage, setResponseMessage] = useState("");

    const handleChange = event => {
        setFormData(prev => ({ ...prev, [event.target.name]: event.target.value }));
    };

    const handleClick = async (event) => {
    event.preventDefault();
    console.log("Book data : ", formData);  
    try {
        const result = await axios.put("http://localhost:8080/api/book/updateBook", formData,
            {
                headers:{
                    "invocationFrom": "invocationFrom"  
                }
            }
        );
        setResponseMessage("Book Updated successfully!");
    } 
    catch (error) {
        console.log("Error : ", error.response);  
        setResponseMessage("Book failed to update!");
    }
    };

    return (
        <div>
            <h1>Update Book</h1>

            {responseMessage && <h2>{responseMessage}</h2>}

            <form onSubmit={handleClick}>
                <label>Book ID</label><br />
                <input type="text" name="bookId" id="" value={formData.bookId} onChange={handleChange} required /><br /><br />
                <label>Title</label><br />
                <input type="text" name="title" id="" value={formData.title} onChange={handleChange} required /><br /><br />
                <label>Author</label><br />
                <input type="text" name="author" id="" value={formData.author} onChange={handleChange} required /><br /><br />
                <label>Category</label><br />
                <input type="text" name="category" id="" value={formData.category} onChange={handleChange} required /><br /><br />

                <label>Book Status</label><br />
                <label>Active</label>
                <input type="radio" name="status" value="ACTIVE" onChange={handleChange} required /> <br />
                <label>Inactive</label>
                <input type="radio" name="status" value="INACTIVE" onChange={handleChange} /><br /><br />

                <label>Book Availability</label><br />
                <label>Available</label>
                <input type="radio" name="availability" value="AVAILABLE" onChange={handleChange} required /> <br />
                <label>Issued</label>
                <input type="radio" name="availability" value="ISSUED" onChange={handleChange} /><br /><br />

                <button type="submit" className="btn btn-success">Submit</button>
                <button type="reset" className="btn btn-info ms-3">Reset</button>
                <Link className="btn btn-dark ms-3" to="/">Home</Link>
            </form>             
        </div>
    );
}

export default UpdateBook
