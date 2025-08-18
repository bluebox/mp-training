import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";

export default function BookList() {
    const [books, setBooks] = useState([]);
    const navigate = useNavigate();
    useEffect(() => {
        fetchBooks();
    }, []);

    const fetchBooks = async () => {
        try {
            const response = await axios.get("http://localhost:8080/books");
            setBooks(response.data);
        } catch (error) {
            console.error("Error fetching books:", error);
            alert("Failed to fetch books.");
        }
    };

    const updateBook = (book) => {
        navigate("/books/update", { state: {book} })
    };

    const updateAvailability = (book) => {
        navigate("/books/updateAvailability", { state: {book} })
    };

    const getStatusLabel = (status) => {
        if (status === "A") return "Active";
        if (status === "I") return "Inactive";
        return status;
    };

    const getAvailabilityLabel = (availability) => {
        if (availability === "A") return "Available";
        if (availability === "I") return "Issued";
        return availability;
    };

    return (
        <div
            className="d-flex align-items-center justify-content-center"
            style={{
                minHeight: "100vh",
                backgroundImage: "url('/library.jpg')",
                backgroundSize: "cover",
                backgroundPosition: "center",
                backgroundAttachment: "fixed",
            }}
        >
            <div className="bg-white p-4 rounded shadow" style={{ width: "70%" }}>
                <Link to="/books" className="btn btn-danger mb-3">
                    Back to Dashboard
                </Link>

                <h3 className="fw-bold text-center mb-4">All Books</h3>

                <table className="table table-bordered table-striped">
                    <thead className="table-light">
                        <tr>
                            <th>Book ID</th>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Category</th>
                            <th>Status</th>
                            <th>Availability</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {books.length > 0 ? (
                            books.map((book) => (
                                <tr key={book.bookId}>
                                    <td>{book.bookId}</td>
                                    <td>{book.title}</td>
                                    <td>{book.author}</td>
                                    <td>{book.category}</td>
                                    <td>{getStatusLabel(book.status)}</td>
                                    <td>{getAvailabilityLabel(book.availability)}</td>
                                    <td>
                                        <button
                                            className="btn btn-primary btn-sm me-2"
                                            onClick={() => updateBook(book)}
                                        >
                                            Update Book
                                        </button>
                                        <button
                                            className="btn btn-primary btn-sm"
                                            onClick={() => updateAvailability(book)}
                                        >
                                            Update Availability
                                        </button>
                                    </td>
                                </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan="7" className="text-center">
                                    No books available.
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        </div>
    );
}
