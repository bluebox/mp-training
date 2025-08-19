import React, { useState, useEffect } from 'react';
import { getBookById, updatebook } from '../services/BookServices';
import { useParams,useNavigate } from 'react-router-dom';
import { Dialog, DialogContent, DialogTitle, DialogContentText } from "@mui/material";

const UpdateBook = () => {
    const { bookId } = useParams();
    const navigate = useNavigate();
    const [book, setBook] = useState({
        book_Title: "",
        book_Author: "",
        book_Category: "",
        book_Status: "",
        book_Availability: ""
    });

    useEffect(() => {
        if (bookId) {
            getBookById(bookId)
                .then((response) => {
                    setBook({
                        book_Title: response.data.book_Title,
                        book_Author: response.data.book_Author,
                        book_Category: response.data.book_Category,
                        book_Status: response.data.book_Status,
                        book_Availability: response.data.book_Availability,

                    });
                })
                .catch(error => {
                    console.error("Error fetching book details:", error);
                    setErrorMessage("Failed to load book details. Please try again.");
                    setShowError(true);
                });
        }
    }, [bookId]);

    const [flag, setFlag] = useState(false);
    const [bookResponse, setBookResponse] = useState("");
    const [showError, setShowError] = useState(false);
    const [errorMessage, setErrorMessage] = useState("");

    const handleUpdate = (e) => {
        e.preventDefault();
      
        updatebook(bookId,book)
            .then(response => {
                console.log(response.data);
                setBookResponse(response.data);
                setFlag(true);
            })
            .catch(error => {
                console.error("Error updating book:", error);
                setErrorMessage("Failed to update book. Please try again.");
                setShowError(true);
            });
    };

    const handleUpdateClick = (bookId) => {
        navigate(`/updatebook/${bookId}`); 
    };

    const handleCloseSuccess = () => {
        setFlag(false);
        navigate(`/books`);
    }
    const handleCloseError = () => setShowError(false);

    return (
        <div>
            <div className="add-book-container">
                <h1 className="add-book-title">Update Book Details</h1>
                <form className="add-book-form" onSubmit={handleUpdate}>
                    <div className="add-book-form-group">
                        <label className="add-book-label" htmlFor="bookTitle">Enter book title</label>
                        <input type='text' name='bookTitle' id="bookTitle" className="add-book-input" value={book.book_Title}
                            onChange={(e) => { setBook((preVal) => ({ ...preVal, book_Title: e.target.value })) }}
                            required
                        />
                    </div>

                    <div className="add-book-form-group">
                        <label className="add-book-label" htmlFor="author">Enter book's Author</label>
                        <input type='text' name='author' id="author" className="add-book-input" value={book.book_Author}
                            onChange={(e) => { setBook((preVal) => ({ ...preVal, book_Author: e.target.value })) }}
                            required
                        />
                    </div>

                    <div className="add-book-form-group">
                        <label className="add-book-label" htmlFor="category">Select book category</label>
                        <select name="category" id="category" className="add-book-select" value={book.book_Category}
                            onChange={(e) => { setBook((preVal) => ({ ...preVal, book_Category: e.target.value })) }}
                            required
                        >
                            <option value="" disabled>Choose a category</option>
                            <option value="Fiction">Fiction</option>
                            <option value="Non-Fiction">Non-Fiction</option>
                            <option value="Science">Science</option>
                            <option value="Technology">Technology</option>
                            <option value="History">History</option>
                            <option value="Biography">Biography</option>
                        </select>
                    </div>
                    <div className="add-book-form-group">
                        <label className="add-book-label" htmlFor="status">Status</label>
                        <select id="status" name="status" className="add-book-select" value={book.book_Status}
                            onChange={(e) => { setBook((preVal) => ({ ...preVal, book_Status: e.target.value })) }} required>
                            <option value="A" >Active (A)</option>
                            <option value="I" >Inactive (I)</option>
                        </select>
                    </div>
                    <div className="add-book-form-group">
                        <label className="add-book-label" htmlFor="availability">Book Availability</label>
                        <input type='text' name='availability' id="availability" className="add-book-input"
                            value={book.book_Availability} readOnly disabled />
                    </div>

                    <button type='submit' className="add-book-submit">Update Book</button>
                </form>
            </div>

            {flag &&
                <Dialog open={flag} onClose={handleCloseSuccess}>
                    <DialogTitle>{"Success"}</DialogTitle>
                    <DialogContent>
                        <DialogContentText>{bookResponse}</DialogContentText>
                    </DialogContent>
                </Dialog>
            }

            {showError &&
                <Dialog open={showError} onClose={handleCloseError}>
                    <DialogTitle>{"Error while updating book details"}</DialogTitle>
                    <DialogContent>
                        <DialogContentText>{errorMessage}</DialogContentText>
                    </DialogContent>
                </Dialog>
            }
        </div>
    );
};

export default UpdateBook;