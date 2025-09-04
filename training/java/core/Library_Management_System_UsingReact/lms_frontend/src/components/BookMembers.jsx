import React, { useEffect, useState } from 'react'
import { listBooks } from '../services/BookServices'
import { bookMembers } from '../services/IssueBookService'

function BookMembers() {
    const [members, setMembers] = useState([])
    const [books, setBooks] = useState([])
    const [bookData, setBookData] = useState({
        book_Id : ""
    })

    useEffect(() => {
        listBooks().then((response) => {
            setBooks(response.data);
        }).catch(error => {
            console.error(error)
        })
    }, [])

    const getMembersClick = (bookData) => {
        bookMembers(bookData).then(response => {
            setMembers(response.data);
        })
    }

    return (
        <div>
            <div className="members-books-container">
                <h1 className="members-books-title">Book Members</h1>
                <form >
                    <div className="form-group">
                        <label className="form-label" htmlFor="books">Select Book:</label>
                        <select className="form-select" id="books" name="books"
                            onChange={(e) => { setBookData((preVal) => { return ({ ...preVal, book_Id: e.target.value }) }), getMembersClick(bookData) }}>
                            <option value="" disabled selected>Select a Book</option>
                            {books.map((book) => (
                                <option key={book.book_Id} value={book.book_Id}>{book.book_Id}. {book.book_Title}</option>
                            ))}
                        </select>
                    </div>
                </form>
                <table class="books-table">
                    <thead>
                        <tr>
                            <th>Member Id</th>
                            <th>Name</th>
                            <th>Email</th>
                        </tr>
                    </thead>
                    <tbody>
                        {members.length > 0 ? (
                            members.map(member => (
                                <tr key={member.member_Id}>
                                    <td>{member.member_Id}</td>
                                    <td>{member.member_Name}</td>
                                    <td>{member.email}</td>
                                </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan="3" style={{ textAlign: 'center' }}>
                                    No Members to display
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default BookMembers