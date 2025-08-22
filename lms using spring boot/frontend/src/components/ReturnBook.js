import React, { useEffect, useState } from "react";
import axios from "axios";

function ReturnBook() {
  const [members, setMembers] = useState([]);
  const [books, setBooks] = useState([]);
  const [selectedMember, setSelectedMember] = useState("");
  const [selectedBook, setSelectedBook] = useState("");

  useEffect(() => {
    axios
      .get("http://localhost:8081/members")
      .then((res) => setMembers(res.data))
      .catch((err) => console.error("Failed to load members", err));
  }, []);

  useEffect(() => {
    if (selectedMember) {
      axios
        .get(`http://localhost:8081/issues/issue/${selectedMember}`)
        .then((res) => setBooks(res.data))
        .catch((err) => console.error("Failed to load issued books", err));
    } else {
      setBooks([]);
    }
  }, [selectedMember]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!selectedMember || !selectedBook) {
      alert("Please select both member and book");
      return;
    }

    try {
      const res = await axios.post("http://localhost:8081/issues/return", {
        memberId: parseInt(selectedMember),
        bookId: parseInt(selectedBook),
      });
      alert(res.data);
      setSelectedMember("");
      setSelectedBook("");
      setBooks([]);
    } catch {
      alert("Failed to return book");
    }
  };

  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        marginTop: "40px",
      }}
    >
      <div
        style={{
             background: "linear-gradient(135deg, #667eea, #764ba2)",
          padding: "30px",
          borderRadius: "10px",
          boxShadow: "0px 4px 10px rgba(0,0,0,0.2)",
          width: "400px",
          textAlign: "center",
        }}
      >
        <h2 style={{ color: "#2c3e50", marginBottom: "20px" }}>Return Book</h2>

        <form onSubmit={handleSubmit}>
          <div style={{ marginBottom: "20px", textAlign: "left" }}>
            <label style={{ display: "block", marginBottom: "8px", fontWeight: "bold" }}>
              Select Member:
            </label>
            <select
              value={selectedMember}
              onChange={(e) => setSelectedMember(e.target.value)}
              required
              style={{
                width: "100%",
                padding: "10px",
                borderRadius: "5px",
                border: "1px solid #ccc",
              }}
            >
              <option value="">-- Select Member --</option>
              {members.map((m) => (
                <option key={m.memberId} value={m.memberId}>
                  {m.memberId}. {m.memberName}
                </option>
              ))}
            </select>
          </div>

          <div style={{ marginBottom: "20px", textAlign: "left" }}>
            <label style={{ display: "block", marginBottom: "8px", fontWeight: "bold" }}>
              Select Book:
            </label>
            <select
              value={selectedBook}
              onChange={(e) => setSelectedBook(e.target.value)}
              required
              disabled={!selectedMember}
              style={{
                width: "100%",
                padding: "10px",
                borderRadius: "5px",
                border: "1px solid #ccc",
                background: !selectedMember ? "#eee" : "white",
              }}
            >
              <option value="">-- Select Book --</option>
              {books.map((b) => (
                <option key={b.bookId} value={b.bookId}>
                  {b.bookId}. {b.title}
                </option>
              ))}
            </select>
          </div>

          <button
            type="submit"
            style={{
              padding: "10px 20px",
              background: "#27ae60",
              color: "white",
              border: "none",
              borderRadius: "5px",
              cursor: "pointer",
              fontWeight: "bold",
              transition: "0.3s",
            }}
            onMouseOver={(e) => (e.target.style.background = "#219150")}
            onMouseOut={(e) => (e.target.style.background = "#27ae60")}
          >
            Return Book
          </button>
        </form>
      </div>
    </div>
  );
}

export default ReturnBook;