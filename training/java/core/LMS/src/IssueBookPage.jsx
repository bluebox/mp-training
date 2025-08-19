import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function IssueBookPage() {
  const nav = useNavigate();

  const [bookId, setBookId] = useState("");
  const [memberId, setMemberId] = useState("");
  const [issueDate, setIssueDate] = useState("");

  const [bookIdError, setBookIdError] = useState("");
  const [memberIdError, setMemberIdError] = useState("");
  const [issueDateError, setIssueDateError] = useState("");

  const [bookOptions, setBookOptions] = useState([]);
  const [memberOptions, setMemberOptions] = useState([]);

  
  useEffect(() => {
    axios
      .get("http://localhost:8082/issueReturn/books") 
      .then((res) => {
        setBookOptions(res.data);
      })
      .catch((err) => {
        console.error("Failed to fetch books:", err);
      });

    axios
      .get("http://localhost:8082/issueReturn/members") 
      .then((res) => {
        setMemberOptions(res.data);
      })
      .catch((err) => {
        console.error("Failed to fetch members:", err);
      });
  }, []);

  const handleIssueBook = () => {
    let isValid = true;

    if (!bookId) {
      setBookIdError("Book ID is required");
      isValid = false;
    } else setBookIdError("");

    if (!memberId) {
      setMemberIdError("Member ID is required");
      isValid = false;
    } else setMemberIdError("");

    if (!issueDate) {
      setIssueDateError("Issue Date is required");
      isValid = false;
    } else setIssueDateError("");

    if (isValid) {
      axios
        .post("http://localhost:8082/issueReturn/issue", {
          bookId,
          memberId,
          issueDate,
        })
        .then(() => {
          alert("Book Issued Successfully!");
          setBookId("");
          setMemberId("");
          setIssueDate("");
        })
        .catch((err) => {
          console.error("Error issuing book:", err);
          alert("Failed to issue book.");
        });
    }
  };

  return (
    <div style={container}>
      <h2 style={title}>Issue Book</h2>

      <div style={fieldWrapper}>
        <label style={label}>Book ID:</label>
        <select
          value={bookId}
          onChange={(e) => setBookId(e.target.value)}
          style={input}
        >
          <option value="">Select Book ID</option>
          {bookOptions.map((book) => (
            <option key={book.bookId} value={book.bookd}>
              {book.bookId}-{book.title}
            </option>
          ))}
        </select>
      </div>
      {bookIdError && <p style={error}>{bookIdError}</p>}

      <div style={fieldWrapper}>
        <label style={label}>Member ID:</label>
        <select
          value={memberId}
          onChange={(e) => setMemberId(e.target.value)}
          style={input}
        >
          <option value="">Select Member ID</option>
          {memberOptions.map((member) => (
            <option key={member.memberId} value={member.memberId}>
              {member.memberId}-{member.name}
            </option>
          ))}
        </select>
      </div>
      {memberIdError && <p style={error}>{memberIdError}</p>}

      <div style={fieldWrapper}>
        <label style={label}>Issue Date:</label>
        <input
          type="date"
          value={issueDate}
          onChange={(e) => setIssueDate(e.target.value)}
          style={input}
        />
      </div>
      {issueDateError && <p style={error}>{issueDateError}</p>}

      <div style={buttonRow}>
        <button style={btnBlue} onClick={handleIssueBook}>
          Issue Book
        </button>
        <button style={btnRed} onClick={() => nav("/issue-return")}>
          Back
        </button>
      </div>
    </div>
  );
}

const container = {
  display: "flex",
  flexDirection: "column",
  gap: "15px",
  alignItems: "center",
  margin: "50px auto",
  padding: "20px",
  width: "400px",
  background: "#fff",
  borderRadius: "10px",
  boxShadow: "0 4px 10px rgba(0,0,0,0.1)",
};
const title = { fontSize: "22px", fontWeight: "bold", marginBottom: "10px" };
const fieldWrapper = {
  display: "flex",
  justifyContent: "space-between",
  alignItems: "center",
  width: "100%",
};
const label = { minWidth: "100px", fontSize: "16px", color: "#333" };
const input = {
  flex: 1,
  padding: "6px",
  borderRadius: "6px",
  border: "1px solid #ccc",
};
const error = { fontSize: "12px", color: "red", alignSelf: "flex-start" };
const buttonRow = {
  display: "flex",
  gap: "20px",
  marginTop: "20px",
};
const btnBlue = {
  background: "#3498db",
  color: "white",
  border: "none",
  padding: "10px 20px",
  borderRadius: "8px",
  cursor: "pointer",
};
const btnRed = { ...btnBlue, background: "#e74c3c" };

export default IssueBookPage;
