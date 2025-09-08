import { useEffect, useState } from "react";

function ViewIssueRecords() {
  const [records, setRecords] = useState([]);
  const [books, setBooks] = useState([]);
  const [members, setMembers] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchRecords = async () => {
      try {
        const responses = await Promise.all([
          fetch("http://localhost:8080/api/issues/viewIssuedRecords"),
          fetch("http://localhost:8080/api/books/viewBooks"),
          fetch("http://localhost:8080/api/members/viewMembers"),
        ]);

        const bad = responses.find(r => !r.ok);
        if (bad) {
          const text = await bad.text();
          throw new Error(`Request failed (${bad.status}): ${text.slice(0, 1000)}`);
        }

        const parsed = await Promise.all(responses.map(async (r) => {
          const ct = (r.headers.get("content-type") || "").toLowerCase();
          if (ct.includes("application/json")) return r.json();
          const text = await r.text();
          throw new Error(`Expected JSON but received: ${text.slice(0, 1000)}`);
        }));

        const [recordsData, booksData, membersData] = parsed;
        setRecords(recordsData);
        setBooks(booksData);
        setMembers(membersData);
      } catch (err) {
        console.error(err);
        setError(err.message || String(err));
        alert(err.message);
      }
    };
    fetchRecords();
  }, []);

  const getBookNameById = (bookId) => {
    const book = books.find((b) => b.bookId === bookId)
    return book ? book.title : "---";
  }

  const getMemberNameById = (memberId) => {
    const member = members.find((m) => m.memberID === memberId);
    return member ? member.name : "---";
  };

  const formatDateTime = (dateTime) => {
    if (!dateTime) return ''
    dateTime = String(dateTime)
    dateTime = dateTime.replace('T', ' ')
    return dateTime;
  }

  return (
    <div>
      <h2>Issued Records</h2>
      {error && <p className="error">{error}</p>}

      {records.length > 0 ? (
        <table>
          <thead>
            <tr>
              <th>Issue ID</th>
              <th>Book ID</th>
              <th>Book Name</th>
              <th>Member ID</th>
              <th>Member Name</th>
              <th>Issue Date</th>
              <th>Return Date</th>
            </tr>
          </thead>
          <tbody>
            {records.map((rec) => (
              <tr key={rec.issueId}>
                <td>{rec.issueId}</td>
                <td>{rec.bookId}</td>
                <td>{getBookNameById(rec.bookId)}</td>
                <td>{rec.memberId}</td>
                <td>{getMemberNameById(rec.memberId)}</td>
                <td>{formatDateTime(rec.issueDate)}</td>
                <td>{(rec.returnDate && formatDateTime(rec.returnDate)) || "Not Returned"}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        !error && <p>No issued records found.</p>
      )}
      <button type="button" onClick={() => (window.location.href = "/")} className="back-button">
        Back to Main Menu
      </button>
    </div>
  );
}

export default ViewIssueRecords;
