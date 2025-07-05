import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'

const GetIssuedRecords = () => {
    const [books,setBooks] = useState([])
    const [error,setError] = useState("")
     useEffect(() => {
        axios.get("http://localhost:8080/api/issue/issueRecords")
            .then((response) => {
                setBooks(response.data);
            })
            .catch((err) => {
                setError(err.message);
            });
    }, []);

    if (error) return <h3 style={{color : 'red'}}>Error : {error}</h3>;
    
    return (
    <div>
      <h1>All Issued Books/Records</h1>

      <table className='table'>
        <thead>
            <tr>
                <th>Issued ID</th>
                <th>Book ID</th>
                <th>Member ID</th>
                <th>Status</th>
                <th>Issued Date</th>
            </tr>
        </thead>
        <tbody>
                {                  
                
                    books.map((book)=>(
                        <tr>
                        <td key={book.issueId}>{book.issueId}</td>
                        <td>{book.bookId}</td>
                        <td>{book.memberId}</td>
                        <td>{book.status}</td>
                        <td>{book.issueDate}</td>
                        </tr>
                    ))
                }
         </tbody>
      </table>  
      <Link className="btn btn-dark" to="/">Go to Home</Link>
    </div>
  )
}

export default GetIssuedRecords
