import React, { useState } from "react";
import { Link, useNavigate, useLocation } from "react-router-dom";
import axios from "axios";

export default function UpdateAvail() {
    const location = useLocation();
    const navigate = useNavigate();
    const { book } = location.state || {};

    const [availability, setAvailability] = useState(book?.availability || "A");
    const [errors, setErrors] = useState({});
    const [successMessage, setSuccessMessage] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();

        const updatedBook = {
            bookId: book.bookId,
            title: book.title,
            author: book.author,
            category: book.category,
            status: book.status,
            availability,
        };

        if (availability === book.availability) {
            setErrors({ form: "No changes made!" });
            return;
        }

        try {
            await axios.put(
                `http://localhost:8080/books/${book.bookId}/availability`,
                updatedBook
            );

            setSuccessMessage("Book availability updated successfully!");
            setErrors({});

            setTimeout(() => navigate("/books/view"), 1500);
        } catch (error) {
            console.error("Error updating availability:", error);
            setErrors({ form: "Failed to update availability. Please try again." });
        }
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
            <div className="bg-white p-4 rounded shadow" style={{ width: "450px" }}>
                <h3 className="fw-bold text-center mb-4">Update Availability</h3>

                {errors.form && <p className="text-danger text-center">{errors.form}</p>}
                    {successMessage && <p className="text-success text-center">{successMessage}</p>}

                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label className="form-label">Book ID:</label>
                        <input type="text" className="form-control" value={book.bookId} disabled />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Title:</label>
                        <input type="text" className="form-control" value={book.title} disabled />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Author:</label>
                        <input type="text" className="form-control" value={book.author} disabled />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Category:</label>
                        <input type="text" className="form-control" value={book.category} disabled />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Status:</label>
                        <select className="form-select" value={book.status} disabled>
                            <option value="A">Active</option>
                            <option value="I">Inactive</option>
                        </select>
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Availability:</label>
                        <select
                            className="form-select"
                            value={availability}
                            onChange={(e) => setAvailability(e.target.value)}
                        >
                            <option value="A">Available</option>
                            <option value="I">Issued</option>
                        </select>
                    </div>

                    <button type="submit" className="btn btn-primary w-100 mb-2">
                        Update Availability
                    </button>
                    <Link to="/books/view" className="btn btn-danger w-100">
                        Back to Book List
                    </Link>
                </form>
            </div>
        </div>
    );
}
