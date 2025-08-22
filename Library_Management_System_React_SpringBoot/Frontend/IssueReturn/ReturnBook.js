import React, { useState, useEffect } from "react";
import { getRecords, returnBook } from "../../api/issueService";
import { getAllBooks } from "../../api/bookService";
import { getAllMembers } from "../../api/memberService";

function ReturnBook() {
  const [records, setRecords] = useState([]);
  const [books, setBooks] = useState([]);
  const [members, setMembers] = useState([]);
  const [selectedRecord, setSelectedRecord] = useState("");

  // Fetch all required data from backend
  useEffect(() => {
    getRecords().then(res => setRecords(res.data)).catch(console.error);
    getAllBooks().then(res => setBooks(res.data)).catch(console.error);
    getAllMembers().then(res => setMembers(res.data)).catch(console.error);
  }, []);

  const handleReturn = async (e) => {
    e.preventDefault();
    if (!selectedRecord) return alert("Select a record to return");

    try {
      await returnBook(selectedRecord);
      alert("Book returned successfully!");
      setSelectedRecord("");

      // Refresh issued records from backend
      getRecords().then(res => setRecords(res.data)).catch(console.error);
    } catch (err) {
      console.error(err);
      alert("Failed to return book");
    }
  };

  // Only issued books
  const issuedRecords = records.filter(r => r.status === "I");

  return (
    <div className="ReturnBook">
      <h2>Return Book</h2>
      <form onSubmit={handleReturn}>
        <select value={selectedRecord} onChange={e => setSelectedRecord(e.target.value)} required>
          <option value="">Select Issued Book</option>
          {issuedRecords.map(r => {
            const book = books.find(b => b.id === r.bookId);
            const member = members.find(m => m.memberId === r.memberId);
            return (
              <option key={r.issueId} value={r.issueId}>
                {book?.title} issued to Member ID: {member?.memberId} on {r.issueDate}
              </option>
            );
          })}
        </select>

        <button type="submit" className="main-button">Return Book</button>
      </form>
    </div>
  );
}

export default ReturnBook;
