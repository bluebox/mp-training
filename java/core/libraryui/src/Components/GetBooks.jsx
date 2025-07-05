import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'

const GetBooks = () => {
    const [books,setBooks] = useState([])
    const [error,setError] = useState("")
     useEffect(() => {
        axios.get("http://localhost:8080/api/book/books")
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
      <h1>All Books</h1>

      <table className='table'>
        <thead>
            <tr>
                <th>Book ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>Category</th>
                <th>Status</th>
                <th>Availability</th>
            </tr>
        </thead>
        <tbody>
                {                  
                    books.map((book)=>(
                        <tr>
                        <td key={book.bookId}>{book.bookId}</td>
                        <td>{book.title}</td>
                        <td>{book.author}</td>
                        <td>{book.category}</td>
                        <td>{book.status}</td>
                        <td>{book.availability}</td>
                        </tr>
                    ))
                }
         </tbody>
      </table>  
      <Link className="btn btn-dark" to="/">Go to Home</Link>
    </div>
  )
}

export default GetBooks
