import React, { useState } from 'react'
import { addbook } from '../services/BookServices'
import { Dialog, DialogContent, DialogTitle, DialogContentText } from "@mui/material"
const AddBooks = () => {
    const [book, setBook] = useState({
        book_Title: "",
        book_Author: "",
        book_Category: ""
    })
    const [flag, setFlag] = useState(false)
    const [bookResponse, setBookResponse] = useState("")
    const [showError, setShowError] = useState(false)
    const [errorMessage, setErrorMessage] = useState("")
    const addBook = async () => {
        try {
            const response = await addbook(book);
            if (response.status === 200) {
                setBookResponse(response.data)
                setFlag(true)
            }
        } catch (error) {
            setErrorMessage(error.message)
            setShowError(true)
        }
    }

    const handleClose = () => {
    setFlag(false);
    setBook((preVal) => ({
      ...preVal,
      book_Title: '',
      book_Author: '',
      book_Category: ''
    }));
  };
    return (
        <div>

            <div className="add-book-container">
                <h1 className="add-book-title">Add Book  </h1>
                <div className="add-book-form">
                    <div className="add-book-form-group">
                        <label className="add-book-label" for="bookTitle">Enter book title </label>
                        <input type='text' name='bookTitle' id="bookTitle" className="add-book-input" onChange={(e) => { setBook((preVal) => { return ({ ...preVal, book_Title: e.target.value }) }) }} value={book.book_Title} required />
                    </div>

                    <div className="add-book-form-group">
                        <label className="add-book-label" for="author">Enter book's Author</label>
                        <input type='text' name='author' id="author" className="add-book-input" onChange={(e) => { setBook((preVal) => { return ({ ...preVal, book_Author: e.target.value }) }) }} value={book.book_Author} required />
                    </div>

                    <div className="add-book-form-group">
                        <label className="add-book-label" for="category">Select book category</label>
                        <select name="category" id="category" className="add-book-select" onChange={(e) => { setBook((preVal) => { return ({ ...preVal, book_Category: e.target.value }) }) }} value={book.book_Category} required>
                            <option value="" disabled selected>Choose a category</option>
                            <option value="Fiction">Fiction</option>
                            <option value="Non-Fiction">Non-Fiction</option>
                            <option value="Science">Science</option>
                            <option value="Technology">Technology</option>
                            <option value="History">History</option>
                            <option value="Biography">Biography</option>
                        </select>
                    </div>
                    <input type='button' value='AddBook' onClick={addBook} className="add-book-submit" />
                </div>
            </div>
            {flag &&
                <Dialog open={flag} onClose={() => {
                    setFlag(false), handleClose()
                }}>
                    <DialogTitle>
                        {"Success"}
                    </DialogTitle>
                    <DialogContent>
                        <DialogContentText>{bookResponse}</DialogContentText>
                    </DialogContent>
                </Dialog>
            }
            {
                showError &&
                <Dialog open={showError} onClose={() => setShowError(false)}>
                    <DialogTitle>
                        {"Error while uploading book details"}
                    </DialogTitle>
                    <DialogContent>
                        <DialogContentText>{errorMessage}</DialogContentText>
                    </DialogContent>
                </Dialog>
            }
        </div>
    )
}

export default AddBooks

