import React, { useState, useEffect } from "react";
import axios from "axios";

function UpdateBook() {
  const [search, setSearch] = useState("");
  const [suggestions, setSuggestions] = useState([]);

  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const [book, setBook] = useState({
    bookId: "",
    title: "",
    author: "",
    category: "",
  });

  const categories = [
    "FICTION",
    "NON_FICTION",
    "FANTASY",
    "SCIENCE_FICTION",
    "HORROR",
    "ROMANCE",
    "MYSTERY",
  ];

  useEffect(() => {
    if (search.trim() === "") {
      setSuggestions([]);
      return;
    }
    axios.get("http://localhost:8081/books").then((res) => {
      const filtered = res.data.filter(
        (b) =>
          b.title.toLowerCase().includes(search.toLowerCase()) ||
          b.author.toLowerCase().includes(search.toLowerCase())
      );
      setSuggestions(filtered);
    });
  }, [search]);

  const handleChange = (e) => {
    setBook({ ...book, [e.target.name]: e.target.value });
  };

  const handleSelectBook = async (id) => {
    const res = await axios.get(`http://localhost:8081/books/${id}`);
    setBook(res.data);
    setSearch(res.data.title + " - " + res.data.author);
    setSuggestions([]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setSuccess("");

    try {
      if (!book.bookId) {
        alert("Please select a book to update.");
        return;
      }
      const res = await axios.put(
        `http://localhost:8081/books/${book.bookId}`,
        book
      );
      setSuccess(res.data.message);
      setSuccess("Book updated successfully!");
    } catch (err) {
      if (err.response && err.response.data) {
        if (err.response.data.errors) setError(err.response.data.errors);
        else setError(err.response.data.message || "Failed to update Book");
      } else setError("server errror,please try again");
    }
  };

  // --------- alignment-first styles ----------
  const FIELD_WIDTH = "360px";
  const inputStyle = {
    width: FIELD_WIDTH,
    padding: "10px",
    margin: "8px auto",
    border: "1px solid #ccc",
    borderRadius: "8px",
    boxSizing: "border-box",
    display: "block",
  };
  const msgStyle = {
    width: FIELD_WIDTH,
    margin: "8px auto 0",
    textAlign: "left",
    fontWeight: "bold",
  };

  return (
    <div style={{ display: "flex", justifyContent: "center", marginTop: "40px" }}>
      <div
        style={{
          width: "500px",
          padding: "24px",
          borderRadius: "12px",
          boxShadow: "0 6px 16px rgba(0,0,0,0.12)",
          background: "linear-gradient(135deg, #ffecd2, #fcb69f)",
          textAlign: "center",
        }}
      >
        <h2 style={{ color: "#333", marginBottom: "14px" }}>Update Book</h2>

        {/* Search */}
        <input
          type="text"
          placeholder="Search Book by Title/Author"
          value={search}
          onChange={(e) => setSearch(e.target.value)}
          style={inputStyle}
        />

        {suggestions.length > 0 && (
          <ul
            style={{
              width: FIELD_WIDTH,
              margin: "6px auto 0",
              padding: 0,
              listStyle: "none",
              border: "1px solid #d0d0d0",
              borderRadius: "8px",
              background: "#fff",
              boxShadow: "0 4px 10px rgba(0,0,0,0.08)",
              maxHeight: "180px",
              overflowY: "auto",
              textAlign: "left",
            }}
          >
            {suggestions.map((s) => (
              <li
                key={s.bookId}
                onClick={() => handleSelectBook(s.bookId)}
                style={{
                  padding: "10px",
                  cursor: "pointer",
                  borderBottom: "1px solid #f1f1f1",
                  transition: "background 0.2s",
                }}
                onMouseEnter={(e) => (e.currentTarget.style.background = "#ffe7cc")}
                onMouseLeave={(e) => (e.currentTarget.style.background = "#fff")}
              >
                {s.title} ({s.author})
              </li>
            ))}
          </ul>
        )}

        {/* Messages */}
        {error && <p style={{ ...msgStyle, color: "red" }}>{error}</p>}
        {success && <p style={{ ...msgStyle, color: "green" }}>{success}</p>}

        {/* Form */}
        <form onSubmit={handleSubmit} style={{ marginTop: "14px" }}>
          <input
            type="text"
            name="title"
            placeholder="Title"
            value={book.title}
            onChange={handleChange}
            required
            style={inputStyle}
          />

          <input
            type="text"
            name="author"
            placeholder="Author"
            value={book.author}
            onChange={handleChange}
            required
            style={inputStyle}
          />

          <select
            name="category"
            value={book.category}
            onChange={handleChange}
            required
            style={{ ...inputStyle, background: "#fafafa" }}
          >
            <option value="">Select Category</option>
            {categories.map((cat) => (
              <option key={cat} value={cat}>
                {cat.replace("_", " ")}
              </option>
            ))}
          </select>

          <button
            type="submit"
            style={{
              width: FIELD_WIDTH,
              padding: "12px",
              margin: "12px auto 0",
              border: "none",
              borderRadius: "8px",
              background: "linear-gradient(135deg, #ff6a00, #ee0979)",
              color: "#fff",
              fontWeight: "bold",
              cursor: "pointer",
              transition: "0.25s",
              boxSizing: "border-box",
              display: "block",
            }}
            onMouseOver={(e) => (e.currentTarget.style.opacity = "0.9")}
            onMouseOut={(e) => (e.currentTarget.style.opacity = "1")}
          >
            Update Book
          </button>
        </form>
      </div>
    </div>
  );
}

export default UpdateBook;