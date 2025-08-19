import axios from "axios";
import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";

function UpdateAvailabilityPage() {
  const nav = useNavigate();
  const { id } = useParams();

  const [book, setBook] = useState({});
  const [availability, setAvailability] = useState("");

  useEffect(() => {
    fetchBooks();
  }, []);

  const fetchBooks = async () => {
    try {
      const response = await axios.get(`http://localhost:8082/books/${id}`);
      const data = response.data;
      setBook(data);
      setAvailability(data.availability);
    } catch {
      alert("Failed to fetch books");
    }
  };

  const handleUpdate = async () => {
    try {
      const updatedBook = { ...book, availability }; 
      const response = await axios.put(`http://localhost:8082/books/${id}`, updatedBook);
      const data = response.data;
      setBook(data);
      alert("Book updated successfully");
    } catch (err) {
      alert("Failed to update book");
    }
  };

  return (
    <div style={{ maxWidth: "500px", margin: "auto", padding: "20px" }}>
      <h2 style={{ marginBottom: "20px" }}>Update Book Availability</h2>

      <div style={{ marginBottom: "10px" }}>
        <label>Book ID:</label>
        <input type="text" value={book.bookId || ""} readOnly />
      </div>

      <div style={{ marginBottom: "10px" }}>
        <label>Title:</label>
        <input type="text" value={book.title || ""} readOnly />
      </div>

      <div style={{ marginBottom: "10px" }}>
        <label>Author:</label>
        <input type="text" value={book.author || ""} readOnly />
      </div>

      <div style={{ marginBottom: "10px" }}>
        <label>Category:</label>
        <input type="text" value={book.category || ""} readOnly />
      </div>

      <div style={{ marginBottom: "10px" }}>
        <label>Availability:</label>
        <select
          value={availability}
          onChange={(e) => setAvailability(e.target.value)}
        >
          <option value="I">I</option>
          <option value="A">A</option>
        </select>
      </div>

      <button onClick={handleUpdate} style={{ marginRight: "10px" }}>
        Update Availability
      </button>
      <button onClick={() => nav("/view-books")} style={{ color: "red" }}>
        Back
      </button>
    </div>
  );
}

export default UpdateAvailabilityPage;
