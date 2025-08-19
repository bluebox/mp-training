import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import "./HomePage.css";

export default function UpdateBookPage() {
  const navigate = useNavigate();
  const { bookid } = useParams(); // get book ID from URL
  const [book, setBook] = useState({
    title: "",
    author: "",
    category: "",
    status: "ACTIVE",       // default to match Enum
    availability: "AVAILABLE" // default to match Enum
  });

  const [loading, setLoading] = useState(true);

  // Fetch book details by ID when page loads
  useEffect(() => {
    const fetchBook = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/books/bookbyid/${bookid}`);
        // Assuming your backend returns the full book object in `response.data`
        const data = response.data;
        setBook({
          title: data.title || "",
          author: data.author || "",
          category: data.category || "",
          status: data.status || "ACTIVE",
          availability: data.availability || "AVAILABLE"
        });
        setLoading(false);
      } catch (error) {
        console.error(error);
        alert("Failed to fetch book details.");
        setLoading(false);
      }
    };
    fetchBook();
  }, [bookid]);

  // Handle form input changes
  const handleChange = (e) => {
    const { name, value } = e.target;
    setBook((prev) => ({ ...prev, [name]: value }));
  };

  // Handle form submission
  const handleUpdate = async (e) => {
    e.preventDefault();
    try {
      await axios.post(`http://localhost:8080/books/updatebook/${bookid}`, book);
      alert("Book updated successfully!");
      navigate("/books");
    } catch (error) {
      console.error(error);
      alert("Failed to update book. Make sure all fields are valid.");
    }
  };

  if (loading) {
    return <p>Loading book details...</p>;
  }

  return (
    <div className="content">
      <h2>Update Book</h2>
      <form onSubmit={handleUpdate}>
        <div>
          <label>Title: </label>
          <input
            type="text"
            name="title"
            value={book.title}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Author: </label>
          <input
            type="text"
            name="author"
            value={book.author}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Category: </label>
          <input
            type="text"
            name="category"
            value={book.category}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Status: </label>
          <select name="status" value={book.status} onChange={handleChange} required>
            <option value="ACTIVE">Active</option>
            <option value="INACTIVE">Inactive</option>
          </select>
        </div>
        <div>
          <label>Availability: </label>
          <select name="availability" value={book.availability} onChange={handleChange} required>
            <option value="AVAILABLE">Available</option>
            <option value="ISSUED">Issued</option>
          </select>
        </div>
        <div style={{ marginTop: "20px" }}>
          <button type="submit">Update Book</button>
          <button type="button" onClick={() => navigate("/books")} style={{ marginLeft: "10px" }}>
            Back
          </button>
        </div>
      </form>
    </div>
  );
}
