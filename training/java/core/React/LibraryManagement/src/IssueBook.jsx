import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";

export default function IssueBook() {
    const [books, setBooks] = useState([]);
    const [members, setMembers] = useState([]);
    const [bookId, setBookId] = useState("");
    const [memberId, setMemberId] = useState("");
    const [issueDate, setIssueDate] = useState("");
    const navigate = useNavigate();

    useEffect(() => {
        const fetchBooks = async () => {
            try {
                const res = await axios.get("http://localhost:8080/issueReturn/books");
                setBooks(res.data);
            } catch (error) {
                console.error("Error fetching books:", error);
            }
        };

        const fetchMembers = async () => {
            try {
                const res = await axios.get("http://localhost:8080/issueReturn/members");
                setMembers(res.data);
            } catch (error) {
                console.error("Error fetching members:", error);
            }
        };

        fetchBooks();
        fetchMembers();
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!bookId || !memberId || !issueDate) {
            alert("Please fill all fields!");
            return;
        }

        const newIssue = { bookId, memberId, issueDate };

        try {
            await axios.post("http://localhost:8080/issueReturn/issue", newIssue);
            alert("Book issued successfully!");
            navigate("/issue-return");
        } catch (error) {
            alert("Failed to issue book.");
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
                <h3 className="fw-bold text-center mb-4">Issue Book</h3>

                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label className="form-label">Book:</label>
                        <select
                            className="form-select"
                            value={bookId}
                            onChange={(e) => setBookId(e.target.value)}
                            required
                        >
                            <option value="">Select Book</option>
                            {books.map((b) => (
                                <option key={b.bookId} value={b.bookId}>
                                    {b.bookId} - {b.title}
                                </option>
                            ))}
                        </select>
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Member:</label>
                        <select
                            className="form-select"
                            value={memberId}
                            onChange={(e) => setMemberId(e.target.value)}
                            required
                        >
                            <option value="">Select Member</option>
                            {members.map((m) => (
                                <option key={m.memberId} value={m.memberId}>
                                    {m.memberId} - {m.name}
                                </option>
                            ))}
                        </select>
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Issue Date:</label>
                        <input
                            type="date"
                            className="form-control"
                            value={issueDate}
                            max={new Date().toISOString().split("T")[0]} // today’s date
                            onChange={(e) => setIssueDate(e.target.value)}
                            required
                        />
                    </div>

                    <div className="d-flex gap-2">
                        <button type="submit" className="btn btn-primary w-100">
                            Issue Book
                        </button>
                        <Link to="/issue-return" className="btn btn-danger w-100">
                            Back
                        </Link>
                    </div>
                </form>
            </div>
        </div>
    );
}
