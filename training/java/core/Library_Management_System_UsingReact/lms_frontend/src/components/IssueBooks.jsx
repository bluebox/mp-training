import React, { useEffect, useState } from 'react'
import { getAvailableBooks } from '../services/BookServices'
import { listMembers } from '../services/MemberServices'
import { issuebooks } from '../services/IssueBookService'
import { Dialog, DialogContent, DialogTitle, DialogContentText } from "@mui/material"
function IssueBooks() {
    const [books, setBooks] = useState([])
    const [members, setMembers] = useState([])
    const [issuebook, setIssueBook] = useState({
        bookId: "",
        memberId: ""
    })

    useEffect(() => {
        getAvailableBooks().then((response) => {
            setBooks(response.data);
        }).catch(error => {
            console.error(error);
        })
        listMembers().then((response) => {
            setMembers(response.data);
        }).catch(error => {
            console.error(error)
        })
    }, [])
    const [flag, setFlag] = useState(false)
    const [issuedResponse, setIssuedResponse] = useState("")
    const [showError, setShowError] = useState(false)
    const [errorMessage, setErrorMessage] = useState("")
    const handleIssueBook = (e) => {
        e.preventDefault();
        console.log(issuebook)
        issuebooks(issuebook)
            .then(response => {
                setIssuedResponse(response.data);
                setFlag(true);
            })
            .catch(error => {
                console.error("Error while issuing book:", error);
                setErrorMessage("Failed to issue book. Please try again.");
                setShowError(true);
            });
    };
    return (
        <div class="issue-book-container">
            <h1 class="issue-book-title">Issue Book</h1>

            <form onSubmit={handleIssueBook}>
                <div class="issue-book-form-group">
                    <label class="issue-book-label" for="members">Select Member</label>
                    <select class="issue-book-select" id="members" name="members" onChange={(e) => { setIssueBook((preVal) => { return ({ ...preVal, memberId: e.target.value }) }) }}>
                        <option value="" disabled selected>Choose a member</option>
                        {members.map((member) => (
                            <option key={member.memberId} value={member.member_Id}>{member.member_Id}. {member.member_Name}</option>
                        ))}
                    </select>
                </div>

                {/* <div class="issue-book-form-group">
                    <div class="search-group">
                        <div class="input-container">
                            <label class="issue-book-label" for="email">Or Enter Email</label>
                            <input class="issue-book-input" id="email" name="email" type="email" pattern="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$" value="" />
                        </div>
                        <input class="issue-book-button" onclick="" type="submit" name="action" value="Search" />
                    </div>
                </div> */}

                <div class="issue-book-form-group">
                    <label class="issue-book-label" for="books">Select Book</label>
                    <select class="issue-book-select" id="books" name="books" onChange={(e) => { setIssueBook((preVal) => { return ({ ...preVal, bookId: e.target.value }) }) }}>
                        <option value="" disabled selected>Choose a book</option>
                        {books.map((book) => (
                            <option key={book.bookId} value={book.book_Id}>{book.book_Id}. {book.book_Title}[{book.book_Category}]</option>
                        ))}
                    </select>
                </div>

                <input class="issue-book-button" type="submit" name="action" value="Issue" />
                {/* <input class="issue-book-button secondary" onclick="cancelForm(event)" type="submit" name="action" value="Cancel"/> */}
            </form>
            {flag &&
                <Dialog open={flag} onClose={() => {
                    setFlag(false), setIssueBook({
                        bookId: "",
                        memberId: ""
                    })
                }}>
                    <DialogTitle>
                        {"Success"}
                    </DialogTitle>
                    <DialogContent>
                        <DialogContentText>{issuedResponse}</DialogContentText>
                    </DialogContent>
                </Dialog>
            }
            {
                showError &&
                <Dialog open={showError} onClose={() => setShowError(false)}>
                    <DialogTitle>
                        {"Error while issuing the book"}
                    </DialogTitle>
                    <DialogContent>
                        <DialogContentText>{errorMessage}</DialogContentText>
                    </DialogContent>
                </Dialog>
            }
        </div>
    )
}

export default IssueBooks