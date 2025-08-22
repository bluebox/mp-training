import React, { useState, useEffect } from "react";
import { getAllBooks } from "../../api/bookService";
import { getAllMembers } from "../../api/memberService";
import { issueBook } from "../../api/issueService";

function IssueBook() {
  const [books, setBooks] = useState([]);
  const [members, setMembers] = useState([]);
  const [selectedBook, setSelectedBook] = useState("");
  const [selectedMember, setSelectedMember] = useState("");

  useEffect(() => {
    getAllBooks().then(res => setBooks(res.data)).catch(console.error);
    getAllMembers().then(res => setMembers(res.data)).catch(console.error);
  }, []);

  const handleIssue = async (e) => {
    e.preventDefault();
    if (!selectedBook || !selectedMember) return alert("Select both book and member");

    const record = {
      bookId: parseInt(selectedBook),
      memberId: parseInt(selectedMember),
    };

    try {
      await issueBook(record);
      alert("Book issued successfully!");
      setSelectedBook("");
      setSelectedMember("");
    } catch (err) {
      console.error(err);
      alert("Failed to issue book");
    }
  };

  // Only show books available to issue
  const availableBooks = books.filter(b => b.availability === "A" && b.status === "A");

  return (
    <div className="IssueBook">
      <h2>Issue Book</h2>
      <form onSubmit={handleIssue}>
        <select value={selectedBook} onChange={e => setSelectedBook(e.target.value)} required>
          <option value="">Select Book</option>
          {availableBooks.map(b => (
            <option key={b.id} value={b.id}>{b.title} ({b.category})</option>
          ))}
        </select>

        <select value={selectedMember} onChange={e => setSelectedMember(e.target.value)} required>
          <option value="">Select Member</option>
          {members.map(m => (
            <option key={m.memberId} value={m.memberId}>{m.name}</option>
          ))}
        </select>

        <button type="submit" className="main-button">Issue Book</button>
      </form>
    </div>
  );
}

export default IssueBook;
