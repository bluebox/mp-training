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
    axios.get("http://localhost:8080/books").then((res) => {
      const filtered = res.data.filter(
        (b) =>
          b.title.toLowerCase().includes(search.toLowerCase()) ||
          b.author.toLowerCase().includes(search.toLowerCase())
      );
      setSuggestions(filtered);
    });
  }, [search]);

  const handleChange = (e) => {
    e.target.value = e.target.value.replaceAll(/[^a-zA-Z0-9 ]/g, "");
    if (e.target.name == "author") {
      e.target.value = e.target.value.replaceAll(/[^a-zA-Z ]/g, "");
    }
    setBook({ ...book, [e.target.name]: e.target.value });
  };

  const handleSelectBook = async (id) => {
    const res = await axios.get(`http://localhost:8080/books/${id}`);
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
        `http://localhost:8080/books/${book.bookId}`,
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

  return (
    <div style={{ textAlign: "center" }}>
      <h2>Update Book</h2>

      <input
        type="text"
        placeholder="Search Book by Title/Author"
        value={search}
        onChange={(e) => setSearch(e.target.value)}
        style={{ width: "300px", padding: "8px" }}
      />
      {suggestions.length > 0 && (
        <ul
          style={{
            listStyle: "none",
            padding: 0,
            margin: "10px auto",
            width: "300px",
            border: "1px solid gray",
            textAlign: "left",
          }}
        >
          {suggestions.map((s) => (
            <li
              key={s.bookId}
              onClick={() => handleSelectBook(s.bookId)}
              style={{
                padding: "8px",
                cursor: "pointer",
                borderBottom: "1px solid #ddd",
              }}
            >
              {s.title} ({s.author})
            </li>
          ))}
        </ul>
      )}

      {error && <p style={{ color: "red" }}>{error}</p>}
      {success && <p style={{ color: "green" }}>{success}</p>}
      <form onSubmit={handleSubmit} style={{ marginTop: "20px" }}>
        <input
          style={formStyle}
          type="text"
          name="title"
          placeholder="Title"
          value={book.title}
          onChange={handleChange}
          required
        />
        <br />
        <br />
        <input
          style={formStyle}
          type="text"
          name="author"
          placeholder="Author"
          value={book.author}
          onChange={handleChange}
          required
        />
        <br />
        <br />
        <select
          style={formStyle}
          name="category"
          value={book.category}
          onChange={handleChange}
          required
        >
          <option value="">Select Category</option>
          {categories.map((cat) => (
            <option key={cat} value={cat}>
              {cat.replace("_", " ")}
            </option>
          ))}
        </select>
        <br />
        <br />
        <button style={buttonStyle} type="submit">
          Update Book
        </button>
      </form>
    </div>
  );
}

const formStyle = {
  borderRadius: "5px",
  width: "200px",
  border: "2px sold black",
};

const buttonStyle = {
  borderRadius: "5px",
  width: "100px",
};

export default UpdateBook;
