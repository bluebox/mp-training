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
    setBook({ ...book, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setSuccess("");
    try {
      await axios.post("http://localhost:8081/books", book);
      setSuccess("Book added successfully!");
      setBook({ title: "", author: "", category: "" });
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
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        minHeight: "100vh",
        // background: "linear-gradient(135deg, #857c8fff, #a9adb4ff)",
      }}
    >
      <div
        style={{
          background: "white",
          padding: "30px",
          borderRadius: "12px",
          width: "400px",
          boxShadow: "0px 8px 20px rgba(0,0,0,0.2)",
          textAlign: "center",
        }}
      >
        <h2 style={{ color: "#2575fc", marginBottom: "20px" }}>Add Book</h2>

        {error && (
          <p style={{ color: "red", fontWeight: "bold", marginBottom: "10px" }}>
            {error}
          </p>
        )}
        {success && (
          <p
            style={{
              color: "green",
              fontWeight: "bold",
              marginBottom: "10px",
            }}
          >
            {success}
          </p>
        )}

        <form onSubmit={handleSubmit}>
          <input
            type="text"
            name="title"
            placeholder="Title"
            value={book.title}
            onChange={handleChange}
            required
            style={{
              width: "100%",
              padding: "10px",
              marginBottom: "15px",
              border: "1px solid #ccc",
              borderRadius: "8px",
              fontSize: "14px",
            }}
          />

          <input
            type="text"
            name="author"
            placeholder="Author"
            value={book.author}
            onChange={handleChange}
            required
            style={{
              width: "100%",
              padding: "10px",
              marginBottom: "15px",
              border: "1px solid #ccc",
              borderRadius: "8px",
              fontSize: "14px",
            }}
          />

          <select
            name="category"
            value={book.category}
            onChange={handleChange}
            required
            style={{
              width: "100%",
              padding: "10px",
              marginBottom: "15px",
              border: "1px solid #ccc",
              borderRadius: "8px",
              fontSize: "14px",
              backgroundColor: "#f9f9f9",
            }}
          >
            <option value="">Select Category</option>
            {categories.map((c, idx) => (
              <option key={idx} value={c}>
                {c.replace("_", " ")}
              </option>
            ))}
          </select>

          <button
            type="submit"
            style={{
              width: "100%",
              padding: "12px",
              background: "linear-gradient(135deg, #ff512f, #dd2476)",
              color: "white",
              border: "none",
              borderRadius: "8px",
              fontSize: "16px",
              cursor: "pointer",
              fontWeight: "bold",
              transition: "0.3s",
            }}
            onMouseOver={(e) =>
              (e.target.style.background =
                "linear-gradient(135deg, #dd2476, #ff512f)")
            }
            onMouseOut={(e) =>
              (e.target.style.background =
                "linear-gradient(135deg, #ff512f, #dd2476)")
            }
          >
            Add Book
          </button>
        </form>
      </div>
    </div>
  );
}

export default AddBook;
