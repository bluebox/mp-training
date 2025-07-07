import React, { useState } from 'react'

const IssueForm = () => {
  const [data , setData] = useState({
        bookId : "",
        memberId : ""
      });  

    const [bookIssued , setBookIssued] = useState(false);
    
      const handleChange = (event) => {
        event.preventDefault();
        const { name , value } = event.target;
        setData({...data , [name] : value})
      }
    
      const handleSubmit = async (event) =>{
         event.preventDefault();
         console.log('Form data submitted:', data);
         try {
          const response = await fetch("http://localhost:8080/issues/issue" , {
            method : "POST", 
            headers : {
              'Content-Type' : "application/json"
            },
            body : JSON.stringify(data)
    
    
          })
    
          if (!response.ok){
            throw new Error(`http error , Status ${response.status}`)
          }
    
          const res = await response.json();
          console.log(res);
          setBookIssued(true);
         } catch (error) {
            console.error("error : " , error);
         }
      }
      
      return (
    
        <>
    
        <h1>Issue Form</h1>
        <br />
    
        <form action="" onSubmit={handleSubmit}>
    
            <label htmlFor="bookId">Book Id :</label>
            <input type="number" name="bookId" id="bookId" placeholder='enter book id :' value={data.bookId} onChange={handleChange} required/>
    
            <br></br>
            <br></br>
    
            <label htmlFor="memberId">Member Id :</label>
            <input type="number" name="memberId" id="memberId" placeholder="enter member id :" value={data.memberId} onChange={handleChange}/>
    
            <br></br>
            <br></br>         
    
            <input type="submit" />
    
        </form>

        {bookIssued ? <div>Book Issued Successfully</div> : ""}
       
        </>

        
       
       
    
      )
}

export default IssueForm
