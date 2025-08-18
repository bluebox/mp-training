import React, { useState } from "react";
import { useLocation, useNavigate, Link } from "react-router-dom";
import axios from "axios";

export default function UpdateBook() {
    const location = useLocation();
    const navigate = useNavigate();
    const { book } = location.state || {};

    const [title, setTitle] = useState(book?.title || "");
    const [author, setAuthor] = useState(book?.author || "");
    const [category, setCategory] = useState(book?.category || "");
    const [status, setStatus] = useState(book?.status || "A");

    const [errors, setErrors] = useState({});
    const [successMessage, setSuccessMessage] = useState("");

    const validate = () => {
        let newErrors = {};
        const stringRegex = /^[A-Za-z\s]+$/;

        if (!title.trim()) newErrors.title = "Title is required.";
        else if (!stringRegex.test(title)) newErrors.title = "Title must contain only letters and spaces.";

        if (!author.trim()) newErrors.author = "Author is required.";
        else if (!stringRegex.test(author)) newErrors.author = "Author must contain only letters and spaces.";

        if (!category.trim()) newErrors.category = "Category is required.";
        else if (!stringRegex.test(category)) newErrors.category = "Category must contain only letters and spaces.";

        if (!["A", "I"].includes(status)) newErrors.status = "Invalid status selected.";

        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setSuccessMessage("");

        if (!validate()) return;

        const updatedBook = {
            bookId: book.bookId,
            title,
            author,
            category,
            status,
        };

        const isUnchanged =
            title === book.title &&
            author === book.author &&
            category === book.category &&
            status === book.status;

        if (isUnchanged) {
            setErrors({ form: "No changes made!" });
            return;
        }

        try {
            await axios.put(`http://localhost:8080/books/${book.bookId}`, updatedBook);
            setSuccessMessage("Book updated successfully!");
            setErrors({});
            setTimeout(() => navigate("/books/view"), 1500);
        } catch (error) {
            console.error("Error updating book:", error);
            setErrors({ form: "Failed to update book. Please try again." });
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
                <h3 className="fw-bold text-center mb-4">Update Book</h3>

                <form onSubmit={handleSubmit}>
                    {errors.form && <p className="text-danger text-center">{errors.form}</p>}
                    {successMessage && <p className="text-success text-center">{successMessage}</p>}

                    <div className="mb-3">
                        <label className="form-label">Book ID:</label>
                        <input
                            type="text"
                            className="form-control"
                            value={book.bookId}
                            disabled
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Title:</label>
                        <input
                            type="text"
                            className={`form-control ${errors.title ? "is-invalid" : ""}`}
                            value={title}
                            onChange={(e) => setTitle(e.target.value)}
                        />
                        {errors.title && <div className="text-danger">{errors.title}</div>}
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Author:</label>
                        <input
                            type="text"
                            className={`form-control ${errors.author ? "is-invalid" : ""}`}
                            value={author}
                            onChange={(e) => setAuthor(e.target.value)}
                        />
                        {errors.author && <div className="text-danger">{errors.author}</div>}
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Category:</label>
                        <input
                            type="text"
                            className={`form-control ${errors.category ? "is-invalid" : ""}`}
                            value={category}
                            onChange={(e) => setCategory(e.target.value)}
                        />
                        {errors.category && <div className="text-danger">{errors.category}</div>}
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Status:</label>
                        <select
                            className={`form-select ${errors.status ? "is-invalid" : ""}`}
                            value={status}
                            onChange={(e) => setStatus(e.target.value)}
                        >
                            <option value="A">Active</option>
                            <option value="I">Inactive</option>
                        </select>
                        {errors.status && <div className="text-danger">{errors.status}</div>}
                    </div>

                    <button type="submit" className="btn btn-primary w-100 mb-2">
                        Update Book
                    </button>
                    <Link to="/books/view" className="btn btn-danger w-100">
                        Back to Book List
                    </Link>
                </form>
            </div>
        </div>
    );
}
