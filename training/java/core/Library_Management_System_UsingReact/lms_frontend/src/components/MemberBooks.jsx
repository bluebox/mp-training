import React, { useEffect, useState } from 'react'
import { listMembers } from '../services/MemberServices'
import { memberBooks } from '../services/IssueBookService'

function MemberBooks() {
    const [members, setMembers] = useState([])
    const [books, setBooks] = useState([])
    const [memberId, setMemberId] = useState({
        member_Id: ""
    })

    useEffect(() => {
        listMembers().then((response) => {
            setMembers(response.data);
        }).catch(error => {
            console.error(error)
        })
    }, [])

    const getBooksClick = (memberId) => {
        console.log(memberId.member_Id + "Hi")
        memberBooks(memberId).then(response => {
            setBooks(response.data);
        })
    }


    return (
        <div>
            <div className="members-books-container">
                <h1 className="members-books-title">Members Books</h1>
                <form >
                    <div className="form-group">
                        <label className="form-label" htmlFor="members">Select Member:</label>
                        <select className="form-select" id="members" name="members"
                            onChange={(e) => { setMemberId((preVal) => { return ({ ...preVal, member_Id: e.target.value }) }), getBooksClick(memberId) }}>
                            <option value="" disabled selected>Select a Member</option>
                            {members.map((member) => (
                                <option key={member.memberId} value={member.member_Id}>{member.member_Id}. {member.member_Name}</option>
                            ))}
                        </select>
                    </div>
                </form>
                <table class="books-table">
                    <thead>
                        <tr>
                            <th>Book Id</th>
                            <th>Title</th>
                            <th>Author</th>
                        </tr>
                    </thead>
                    <tbody>
                        {books.length > 0 ? (
                            books.map(book => (
                                <tr key={book.book_Id}>
                                    <td>{book.book_Id}</td>
                                    <td>{book.book_Title}</td>
                                    <td>{book.book_Author}</td>
                                </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan="3" style={{ textAlign: 'center' }}>
                                    No Books to display
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default MemberBooks