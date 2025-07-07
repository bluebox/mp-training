import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';

const ViewAllBooks = () => {

  const [books , setBooks] = useState([]);
  const [loading , setLoading] = useState(true);
  const [error , setError] = useState(null);

  useEffect(()=>{
    const fetchData = async () => {
        try{
            const response = await fetch("http://localhost:8080/books/list");
            if(!response.ok){
                throw new Error(`http error status : ${response.status}`)

            }
            const data = await response.json();
            setBooks(data);
            console.log(data);
        }catch(err){
            setError(err);
        }
        finally{
            setLoading(false);
        }
    };

    fetchData();
  }, [])

  if(loading) return <div>Loading</div>;
  if(error) return <div>Error : {error.message}</div>;

  return (
    <div>
      <h1>Books List</h1>

      <table border="1">

        <thead>
            <tr>

            <th>Id</th>
            <th>Title</th>
            <th>Author</th>
            <th>Category</th>
            <th>Status</th>
            <th>Availability</th>
            <th>Action</th>

            </tr>
            

        </thead>

        <tbody>
            {
                books.map((book) => (
                    <tr key={book.bookId}>

                        <td>{book.bookId}</td>
                        <td>{book.title}</td>
                        <td>{book.author}</td>
                        <td>{book.category}</td>
                        <td>{book.status}</td>
                        <td>{book.available}</td>
                        <td><Link to ={`/edit/${book.bookId}`} >edit</Link></td>
                    </tr>
                ))
            }
        </tbody>
      </table>


    </div>
  );
};

export default ViewAllBooks
