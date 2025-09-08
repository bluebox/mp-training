import { useState, useEffect } from "react";

function IssueBook() {
  const [bookId, setBookId] = useState(null);
  const [memberId, setMemberId] = useState(null);
  const [statusMessage, setStatusMessage] = useState("");
  const [books, setBooks] = useState([]);
  const [members, setMembers] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/books/viewBooks")
      .then(res => res.json())
      .then(data => setBooks(data))
      .catch(() => setBooks([]));

    fetch("http://localhost:8080/api/members/viewMembers")
      .then(res => res.json())
      .then(data => setMembers(data))
      .catch(() => setMembers([]));
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const bookIdVal = bookId;
    const memberIdVal = memberId;

    if (bookIdVal === null || memberIdVal === null || Number.isNaN(bookIdVal) || Number.isNaN(memberIdVal)) {
      setStatusMessage("Please enter valid Book ID and Member ID.");
      return;
    }

    try {
      const params = new URLSearchParams({
        bookId: String(bookIdVal),
        memberId: String(memberIdVal)
      });
      const url = `http://localhost:8080/api/issues/issueBook?${params.toString()}`;
      const res = await fetch(url, { method: "POST" });
      const text = await res.text();

      let responseBody;
      try {
        responseBody = JSON.parse(text);
      } catch {
        responseBody = text;
      }

      if (res.ok) {
        alert("Book issued successfully!");
        setStatusMessage("Book issued successfully!");
        setBookId(null);
        setMemberId(null);
        fetch("http://localhost:8080/api/books/viewBooks")
          .then(res => res.json())
          .then(data => setBooks(data))
          .catch(() => setBooks([]));

        fetch("http://localhost:8080/api/members/viewMembers")
          .then(res => res.json())
          .then(data => setMembers(data))
          .catch(() => setMembers([]));
      } else {
        alert(typeof responseBody === "object" ? JSON.stringify(responseBody) : responseBody);
        setStatusMessage(typeof responseBody === "object" ? JSON.stringify(responseBody) : responseBody);
      }
    } catch (err) {
      alert("Error: " + err.message);
      setStatusMessage("Error: " + err.message);
    }
  };

  return (

    <div className="issue-full">
      <h2>Issue Book</h2>
      <div className="issue-container">
        <form onSubmit={handleSubmit} className="form">
          <label>Enter Book ID:</label>
          <input
            type="number"
            placeholder="Enter Book ID"
            value={bookId ?? ""}
            onChange={(e) => {
              const v = e.target.value;
              setBookId(v === "" ? null : parseInt(v));
            }}
            required
          />
          <label>Enter member ID:</label>
          <input
            type="number"
            placeholder="Enter Member ID"
            value={memberId ?? ""}
            onChange={(e) => {
              const v = e.target.value;
              setMemberId(v === "" ? null : parseInt(v, 10));
            }}
            required
          />
          <button type="submit">Issue Book</button>
          <button type="button" onClick={() => (window.location.href = "/")}>
            Back to Main Menu
          </button>
        </form>

        <div className="issue-display">
          <h4>Available Books</h4>
          {books.length > 0 ? (
            <textarea readOnly style={{ height: '200px' }}
              value={books.filter(book => book.availability === "AVAILABLE")
                .filter(book => book.status === 'ACTIVE')
                .map(
                  b => `ID: ${b.bookId}, Title: ${b.title}, Author: ${b.author}`
                ).join("\n")} />
          ) : (
            <p>No books in the library</p>
          )}

          <h4>All Members</h4>
          {members.length > 0 ? (
            <textarea readOnly style={{ height: '200px' }}
              value={members.map(
                m => `ID: ${m.memberID}, Name: ${m.name}`
              ).join("\n")} />
          ) : (
            <p>No members</p>
          )}
        </div>
      </div>
    </div>

  );
}

export default IssueBook;
