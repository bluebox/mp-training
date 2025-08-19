import React, { useEffect, useState } from 'react'
import { listMembers } from '../services/MemberServices'
import { getmemberBooks ,returnbooks} from '../services/IssueBookService'
import { Dialog, DialogContent, DialogTitle, DialogContentText } from "@mui/material"
function ReturnBook() {
    const [books, setBooks] = useState([])
    const [members, setMembers] = useState([])
    const [memberId, setMemberId] = useState({
        member_Id: ""
    })
    const [returnbook,setReturnBook]=useState({
        issueId:" "
    })
    useEffect(() => {
        listMembers().then((response) => {
            setMembers(response.data);
        }).catch(error => {
            console.error(error)
        })
    }, [])


    const [flag, setFlag] = useState(false)
    const [returnResponse, setReturnResponse] = useState("")
    const [showError, setShowError] = useState(false)
    const [errorMessage, setErrorMessage] = useState("")
    const handleReturnBook = (e) => {
        e.preventDefault();
        console.log(returnbook)
        returnbooks(returnbook)
            .then(response => {
                setReturnResponse(response.data);
                setFlag(true);
            })
            .catch(error => {
                console.error("Error while returning book:", error);
                setErrorMessage("Failed to return book. Please try again.");
                setShowError(true);
            });
    };
    const getBooksClick = (memberId) => {
        getmemberBooks(memberId).then(response => {
            setBooks(response.data);
        })
    }
    return (
        <div class="issue-book-container">
            <h1 class="issue-book-title">Return Book</h1>

            <form onSubmit={handleReturnBook}>
                <div class="issue-book-form-group">
                    <label class="issue-book-label" for="members">Select Member</label>
                    <select class="issue-book-select" id="members" name="members" onChange={(e) => { setMemberId((preVal) => { return ({ ...preVal, member_Id: e.target.value }) }) , getBooksClick(memberId)}}>
                        <option value="" disabled selected>Choose a member</option>
                        {members.map((member) => (
                            <option key={member.memberId} value={member.member_Id}>{member.member_Id}. {member.member_Name}</option>
                        ))}
                    </select>
                </div>
                <div class="issue-book-form-group">
                    <label class="issue-book-label" for="books">Select Book</label>
                    <select class="issue-book-select" id="books" name="books" onChange={(e) => { setReturnBook((preVal) => { return ({ ...preVal, issueId: e.target.value.split("-")[0] }) }) }}>
                        <option value="" disabled selected>Choose a book</option>
                        {books.map((book) => (
                            <option key={book} value={book}>{book}</option>
                        ))}
                    </select>
                </div>

                <input class="issue-book-button" type="submit" name="action" value="Return" />

            </form>
            {flag &&
                <Dialog open={flag} onClose={() => {
                    setFlag(false)}}>
                    <DialogTitle>
                        {"Success"}
                    </DialogTitle>
                    <DialogContent>
                        <DialogContentText>{returnResponse}</DialogContentText>
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

export default ReturnBook