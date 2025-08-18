import React, { useState } from 'react';
import './App.css';

function CreateIssue() {
  const [formData, setFormData] = useState({
      IssueRecordId:0,
      BookId:0,
      MemberId:0,
      status:"",
      issueDate:'',
      ReturnDate:'',
  });

  const [message,setMessage]=useState("");

   const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

 const handleSubmit = (e) => {
    e.preventDefault();
    fetch('http://localhost:8070/Issues/issue', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formData),
    })
    .then(response=>response.text())
    .then(data=>setMessage(data))
     .catch((error) => setMessage(error));;
 };

  return (
   <div className="Container">
      <h2>Create New Issue</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Book Id:
          <input
            type="number"
            name="BookId"
            value={formData.BookId}
            onChange={handleChange}
            required
          />
        </label>

        <label>
          MemberId:
          <input
            type="number"
            name="MemberId"
            value={formData.MemberId}
            onChange={handleChange}
            required
          />
        </label>

        <label>
          Status:
          <input
            type="text"
            name="status"
            value={formData.status}
            onChange={handleChange}
            required
          />
        </label>

        <label>
          Issue Date:
           <input
            type="date"
            name="issueDate"
            value={formData.issueDate}
            onChange={handleChange}
            required
          /> 
        </label>

        <label>
          Address:
            <input
            type="date"
            name="ReturnDate"
            value={formData.ReturnDate}
            onChange={handleChange}
            required
          /> 
        </label>

        <button type="submit">Issue Book</button>
      </form>

      {message != null && <p>{message}</p>}
    </div>
  );
}

export default CreateIssue;
