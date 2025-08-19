import React, { useState } from "react";
import axios from "axios";

function AddBook() {
  const [book, setBook] = useState({
    title: "",
    author: "",
    category: "",
  });

  const categories = [
    "NON_FICTION",
    "FICTION",
    "FANTASY",
    "SCIENCE_FICTION",
    "HORROR",
    "ROMANCE",
    "MYSTERY",
  ];

  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const handleChange = (e) => {
    e.target.value = e.target.value.replaceAll(/[^a-zA-Z0-9 ]/g, "");
    if (e.target.name == "author") {
      e.target.value = e.target.value.replaceAll(/[^a-zA-Z ]/g, "");
    }
    setBook({ ...book, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setSuccess("");
    try {
      const res = await axios.post("http://localhost:8080/books", book);
      setSuccess("Book added successfully!");
      setBook({ title: "", author: "", category: "" });
      setTimeout(() => setSuccess(""), 3000);
    } catch (err) {
      if (err.response && err.response.data) {
        if (err.response.data.errors) {
          setError(err.response.data.errors.join(","));
        } else {
          setError(err.response.data.message || "Failed to add Book!");
        }
      } else {
        setError("Server error. Please try again.");
      }
    }
  };

  return (
    <div style={{ textAlign: "center" }}>
      <h2>Add Book</h2>
      {error && <p style={{ color: "red" }}>{error}</p>}
      {success && <p style={{ color: "green" }}>{success}</p>}
      <form onSubmit={handleSubmit}>
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
          {categories.map((c, idx) => (
            <option key={idx} value={c}>
              {c.replace("_", " ")}
            </option>
          ))}
        </select>
        <br />
        <br />
        <button style={buttonStyle} type="submit">
          Add Book
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
export default AddBook;
