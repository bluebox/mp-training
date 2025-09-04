import React, { useState, useEffect } from 'react'
import { listBooks } from '../services/BookServices'
import { useNavigate } from 'react-router-dom'
const ListBooksComponent = () => {
    const [books, setBooks] = useState([])
    const navigate = useNavigate();
    const handleUpdateClick = (bookId) => {
        navigate(`/updatebook/${bookId}`); 
    };

    useEffect(() => {
        listBooks().then((response) => {
            setBooks(response.data);
        }).catch(error => {
            console.error(error);
        })
    }, [])

    return (
        <div className='container'>
            <h2 className='text-center'>List Of Books</h2>
            <div style={{ maxHeight: '500px', overflowY: 'auto' }}>
                <table className='books-table'>
                    <thead>
                        <tr>
                            <th>Book Id</th>
                            <th>Book Title</th>
                            <th>Book Author</th>
                            <th>Category</th>
                            <th>Status</th>
                            <th>Availability</th>
                            <th>update</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            books.map(book =>
                                <tr key={book.book_Id}>
                                    <td>{book.book_Id}</td>
                                    <td>{book.book_Title}</td>
                                    <td>{book.book_Author}</td>
                                    <td>{book.book_Category}</td>
                                    <td>{book.book_Status}</td>
                                    <td>{book.book_Availability}</td>
                                    <td><button  style={{backgroundColor: '#4B0082', color: 'white' }} onClick={() => handleUpdateClick(book.book_Id)}>update</button></td>
                                </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>

        </div>


    )
}

export default ListBooksComponent