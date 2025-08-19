import React, { useEffect, useState } from "react";
import axios from "axios";

function ReturnBook() {
  const [members, setMembers] = useState([]);
  const [books, setBooks] = useState([]);
  const [selectedMember, setSelectedMember] = useState("");
  const [selectedBook, setSelectedBook] = useState("");

  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  useEffect(() => {
    axios
      .get("http://localhost:8080/members")
      .then((res) => setMembers(res.data))
      .catch((err) => console.error("Failed to load members", err));
  }, []);
  useEffect(() => {
    axios
      .get(`http://localhost:8080/issues/issued-books/${selectedMember}`)
      .then((res) => setBooks(res.data))
      .catch((err) => console.error("Failed to load issued books", err));
  }, [selectedMember]);

  const handleSubmit = async (e) => {
    setError("");
    setSuccess("");
    e.preventDefault();
    if (!selectedMember || !selectedBook) {
      alert("Please select both member and book");
      return;
    }

    try {
      const res = await axios.post("http://localhost:8080/issues/return", {
        memberId: parseInt(selectedMember),
        bookId: parseInt(selectedBook),
      });
      setSuccess("Book Returned successfully!");
      setError("");
      setSelectedMember("");
      setSelectedBook("");
      setBooks([]);
    } catch {
      alert("Failed to return book");
    }
  };

  return (
    <div style={{ textAlign: "center" }}>
      <h2>Return Book</h2>
      {error && <p style={{ color: "red" }}>{error}</p>}
      {success && <p style={{ color: "green" }}>{success}</p>}
      <form onSubmit={handleSubmit}>
        <select
          style={formStyle}
          value={selectedMember}
          onChange={(e) => setSelectedMember(e.target.value)}
          required
        >
          <option value="">Select Member</option>
          {members.map((m) => (
            <option key={m.memberId} value={m.memberId}>
              {m.memberId}. {m.memberName}
            </option>
          ))}
        </select>
        <br />
        <br />

        <select
          style={formStyle}
          value={selectedBook}
          onChange={(e) => setSelectedBook(e.target.value)}
          required
        >
          <option value="">Select Book</option>
          {books.map((b) => (
            <option key={b.bookId} value={b.bookId}>
              {b.bookId}. {b.title}
            </option>
          ))}
        </select>
        <br />
        <br />

        <button type="submit">Return Book</button>
      </form>
    </div>
  );
}

const formStyle = {
  borderRadius: "5px",
  width: "200px",
  border: "2px sold black",
};
export default ReturnBook;
