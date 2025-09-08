import { useState, useEffect } from "react";

function ReturnBook() {
  const [bookId, setbookId] = useState(null);
  const [statusMessage, setStatusMessage] = useState("");
  const [issuedBooks, setIssuedBooks] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/issues/viewIssuedRecords")
      .then(res => res.json())
      .then(data => { setIssuedBooks(data) })
      .catch(() => setIssuedBooks([]));
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const bookIdVal = bookId;

    if (bookIdVal === null || Number.isNaN(bookIdVal)) {
      setStatusMessage("Please enter a valid numeric Issue ID.");
      return;
    }

    try {
      const url = `http://localhost:8080/api/issues/returnBook?bookId=${bookIdVal}`;
      const res = await fetch(url, { method: "POST" });
      const text = await res.text();

      let responseBody;
      try {
        responseBody = JSON.parse(text);
      } catch {
        responseBody = text;
      }

      if (res.ok) {
        alert("Book returned successfully!");
        setStatusMessage("Book returned successfully!");
        setbookId(null);
      } else {
        alert(typeof responseBody === "object" ? JSON.stringify(responseBody) : responseBody);
        setStatusMessage(typeof responseBody === "object" ? JSON.stringify(responseBody) : responseBody);
      }
    } catch (err) {
      alert("Error: " + err.message);
      setStatusMessage("Error: " + err.message);
    }
  };
  const formatDateTime = (dateTime) => {
    if (!dateTime) return ''
    dateTime = String(dateTime)
    dateTime = dateTime.replace('T', ' ')
    return dateTime;
  }
  return (
    <div className="return-full">
      <h2>Return Book</h2>
      <div className="return-container">
        <form onSubmit={handleSubmit} className="form">
          <label>Enter book ID</label>
          <input
            type="number"
            placeholder="Enter Book ID"
            value={bookId ?? ""}
            onChange={(e) => {
              const v = e.target.value;
              setbookId(v === "" ? null : parseInt(v));
            }}
            required
          />
          <button type="submit">Return Book</button>
          <button type="button" onClick={() => (window.location.href = "/")}>
            Back to Main Menu
          </button>
        </form>

        <div className="return-display">
          <h4>Issued Books</h4>
          {issuedBooks.length > 0 ? (
            <textarea
              readOnly
              value={issuedBooks.filter(issue => !issue.returnDate)
                .map(
                  i =>
                    `BookID: ${i.bookId}, MemberID: ${i.memberId}, Issue Date: ${formatDateTime(i.issueDate)}`
                ).join("\n")}
            />
          ) : (
            <p>No issued books.</p>
          )}
        </div>
      </div>
    </div>
  );
}

export default ReturnBook;
