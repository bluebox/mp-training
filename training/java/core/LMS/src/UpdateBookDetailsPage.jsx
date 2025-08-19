import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

function UpdateBookDetailsPage() {
  const nav = useNavigate();
  const { id } = useParams();

  
   const [book,setBook]=useState([]);
    useEffect(() => {
      fetchBooks();
    }, []);
  
    const fetchBooks = async () => {
      try {
         
              const response =await axios.get(`http://localhost:8082/books/${id}`);
              const data=response.data;
              setBook(data);
            //  console.log(book);
      } catch {
        alert("Failed to fetch books");
      }
    };
  

  const handleSave =async () => {
    
            const response =await axios.put(`http://localhost:8082/books/${id}`,book);
            const data=response.data;
            console.log(data);
            alert("book updated successfully");
  };

  return (
    <div style={{ maxWidth: "600px", margin: "auto", padding: "20px" }}>
      <h2 style={{ marginBottom: "20px" }}>Update Book Details</h2>

      <div style={{ marginBottom: "10px" }}>
        <label>Title:</label>
        <input
          type="text"
          value={book.title}
          onChange={(e) => setBook({ ...book, title: e.target.value })}
        />
      </div>

      <div style={{ marginBottom: "10px" }}>
        <label>Author:</label>
        <input
          type="text"
          value={book.author}
          onChange={(e) => setBook({ ...book, author: e.target.value })}
        />
      </div>

      <div style={{ marginBottom: "10px" }}>
        <label>Category:</label>
        <input
          type="text"
          value={book.category}
          onChange={(e) => setBook({ ...book, category: e.target.value })}
        />
      </div>

      <div style={{ marginBottom: "10px" }}>
        <label>Status:</label>
        <select
          value={book.status}
          onChange={(e) => setBook({ ...book, status: e.target.value })}
        >
          <option>A</option>
          <option>I</option>
        </select>
      </div>

      <button onClick={handleSave} style={{ marginRight: "10px" }}>
        Save Changes
      </button>
      <button onClick={() => nav("/view-books")} style={{ color: "red" }}>
        Back
      </button>
    </div>
  );
}

export default UpdateBookDetailsPage;
