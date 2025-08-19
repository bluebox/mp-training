import React, { useEffect, useState } from "react";
import axios from "axios";

function IssueBook() {
  const [members, setMembers] = useState([]);
  const [books, setBooks] = useState([]);
  const [selectedMember, setSelectedMember] = useState("");
  const [selectedBook, setSelectedBook] = useState("");

  const loadData = () => {
    axios
      .get("http://localhost:8081/members")
      .then((res) => setMembers(res.data))
      .catch((err) => console.error("Failed to load members", err));

    axios
      .get("http://localhost:8081/books")
      .then((res) => {
        const availableBooks = Array.from(res.data).filter((b) => b.availability=== "AVAILABLE");
        setBooks(availableBooks);
        console.log("Books loaded:", availableBooks);
      })
      .catch((err) => console.error("Failed to load books", err));
  };

  useEffect(() => {
    loadData();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!selectedBook || !selectedMember) {
      alert("Please select both member and book");
      return;
    }

    try {
      await axios.post("http://localhost:8081/issues/issue", {
        memberId: parseInt(selectedMember),
        bookId: parseInt(selectedBook),
      });
      alert("Book issued successfully!");
      setSelectedMember("");
      setSelectedBook("");
      loadData(); 
    } catch (err) {
      console.error("Issue failed", err);
      alert("Failed to issue book");
    }
  };

  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        minHeight: "100vh",
        background: "linear-gradient(135deg, #667eea, #764ba2)",
        fontFamily: "Arial, sans-serif",
      }}
    >
      <div
        style={{
          background: "#fff",
          padding: "30px",
          borderRadius: "12px",
          boxShadow: "0 8px 16px rgba(0,0,0,0.2)",
          width: "400px",
          textAlign: "center",
        }}
      >
        <h2 style={{ marginBottom: "20px", color: "#333" }}>📚 Issue Book</h2>

        <form onSubmit={handleSubmit}>
          <div style={{ marginBottom: "20px" }}>
            <label
              style={{
                display: "block",
                marginBottom: "8px",
                fontWeight: "bold",
              }}
            >
              Select Member
            </label>
            <select
              value={selectedMember}
              onChange={(e) => setSelectedMember(e.target.value)}
              required
              style={{
                width: "100%",
                padding: "10px",
                borderRadius: "6px",
                border: "1px solid #ccc",
                outline: "none",
              }}
            >
              <option value="">-- Choose Member --</option>
              {members.map((m) => (
                <option key={m.memberId} value={m.memberId}>
                  {m.memberId}. {m.memberName}
                </option>
              ))}
            </select>
          </div>

          <div style={{ marginBottom: "20px" }}>
            <label
              style={{
                display: "block",
                marginBottom: "8px",
                fontWeight: "bold",
              }}
            >
              Select Book
            </label>
            <select
              value={selectedBook}
              onChange={(e) => setSelectedBook(e.target.value)}
              required
              style={{
                width: "100%",
                padding: "10px",
                borderRadius: "6px",
                border: "1px solid #ccc",
                outline: "none",
              }}
            >
              <option value="">-- Choose Book --</option>
              {books.map((b) => (
               
               
                <option key={b.bookId} value={b.bookId}>
                  {b.bookId}, {b.title}
                </option>
              ))}
            </select>
          </div>

          <button
            type="submit"
            style={{
              width: "100%",
              padding: "12px",
              background: "linear-gradient(135deg, #ff6a00, #ee0979)",
              color: "#fff",
              fontWeight: "bold",
              border: "none",
              borderRadius: "6px",
              cursor: "pointer",
              transition: "0.3s",
            }}
            onMouseOver={(e) =>
              (e.target.style.background = "linear-gradient(135deg, #ee0979, #ff6a00)")
            }
            onMouseOut={(e) =>
              (e.target.style.background = "linear-gradient(135deg, #ff6a00, #ee0979)")
            }
          >
            ✅ Issue Book
          </button>
        </form>
      </div>
    </div>
  );
}

export default IssueBook;