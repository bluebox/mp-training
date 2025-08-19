import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function ReturnBookPage() {
  const nav = useNavigate();

  const [selectedRecord, setSelectedRecord] = useState(null);
  const [issuedBooks, setIssuedBooks] = useState([]);
  const [statusMessage, setStatusMessage] = useState("");
  const [statusColor, setStatusColor] = useState("red");

  useEffect(() => {
    axios
      .get("http://localhost:8082/issueReturn/issued")
      .then((res) => {
        setIssuedBooks(res.data);
      })
      .catch((err) => {
        console.error(err);
        setStatusMessage("Error fetching issued books");
        setStatusColor("red");
      });
  }, []);

  const handleReturnBook = () => {
    if (!selectedRecord) {
      setStatusMessage("Please select a book to return");
      setStatusColor("red");
      return;
    }

    axios
      .post("http://localhost:8082/issueReturn/return", selectedRecord)
      .then(() => {
        setStatusMessage("Book returned successfully!");
        setStatusColor("green");
        setSelectedRecord(null);

        return axios.get("http://localhost:8082/issueReturn/issued");
      })
      .then((res) => setIssuedBooks(res.data))
      .catch((err) => {
        console.error(err);
        setStatusMessage("Error: " + err.message);
        setStatusColor("red");
      });
  };

  return (
    <div style={container}>
      <h2 style={title}>Return Book</h2>

      <div style={fieldRow}>
        <label style={label}>Select Book ID:</label>
        <select
          value={selectedRecord?.bookId || ""}
          onChange={(e) => {
            const selected = issuedBooks.find(
              (book) => book.bookId === parseInt(e.target.value)
            );
            setSelectedRecord(selected);
          }}
          style={input}
        >
          <option value="">-- Select --</option>
          {issuedBooks.map((book) => (
            <option key={book.bookId} value={book.bookId}>
              {book.bookId} 
            </option>
          ))}
        </select>
      </div>

      {statusMessage && (
        <p style={{ ...status, color: statusColor }}>{statusMessage}</p>
      )}

      <div style={buttonRow}>
        <button style={btnBlue} onClick={handleReturnBook}>
          Submit
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
  gap: "20px",
  alignItems: "center",
  margin: "50px auto",
  padding: "30px",
  width: "400px",
  background: "#fff",
  borderRadius: "10px",
  boxShadow: "0 4px 10px rgba(0,0,0,0.1)",
};
const title = { fontSize: "26px", fontWeight: "bold" };
const fieldRow = {
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
  gap: "10px",
};
const label = { fontSize: "18px", fontWeight: "500" };
const input = {
  padding: "8px",
  borderRadius: "6px",
  border: "1px solid #ccc",
  width: "200px",
};
const status = { fontSize: "14px", fontWeight: "bold" };
const buttonRow = { display: "flex", gap: "20px", marginTop: "10px" };
const btnBlue = {
  background: "#3498db",
  color: "white",
  border: "none",
  padding: "10px 20px",
  borderRadius: "8px",
  cursor: "pointer",
};
const btnRed = { ...btnBlue, background: "#e74c3c" };

export default ReturnBookPage;