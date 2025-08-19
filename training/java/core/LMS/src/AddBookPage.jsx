import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
export default function AddBookPage() {
  const nav = useNavigate();

  
  const [title, setTitle] = useState("");
  const [author, setAuthor] = useState("");
  const [category, setCategory] = useState("");

  
  const [errors, setErrors] = useState({
    title: "",
    author: "",
    category: "",
  });

  
  const handleSubmit = async (e) => {
    e.preventDefault();

    let newErrors = { title: "", author: "", category: "" };
    let valid = true;

    if (!title.trim()) {
      newErrors.title = "Title is required";
      valid = false;
    }
    if (!author.trim()) {
      newErrors.author = "Author is required";
      valid = false;
    }
    if (!category.trim()) {
      newErrors.category = "Please select a category";
      valid = false;
    }

    setErrors(newErrors);

    if (valid) {
        const newBook={title,author,category};
      const response =await axios.post("http://localhost:8082/books",newBook);
      const data=response.data;
      alert(`Book added successsfully id : ${data}`);
    }
  };

  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        height: "100vh",
      }}
    >
      <form
        onSubmit={handleSubmit}
        style={{
          width: "400px",
          padding: "20px",
          border: "1px solid #ccc",
          borderRadius: "10px",
          textAlign: "center",
        }}
      >
     
        <h2 style={{ fontFamily: "Arial", fontWeight: "bold" }}>
          Add A New Book
        </h2>

       
        <div style={{ textAlign: "left", marginTop: "20px" }}>
          <label style={{ display: "block", marginBottom: "5px" }}>Title:</label>
          <input
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            placeholder="Enter the Title of the Book"
            style={{ width: "100%", padding: "8px" }}
          />
          {errors.title && (
            <small style={{ color: "red", fontSize: "12px" }}>
              {errors.title}
            </small>
          )}
        </div>

      
        <div style={{ textAlign: "left", marginTop: "20px" }}>
          <label style={{ display: "block", marginBottom: "5px" }}>Author:</label>
          <input
            type="text"
            value={author}
            onChange={(e) => setAuthor(e.target.value)}
            placeholder="Enter the Author Name"
            style={{ width: "100%", padding: "8px" }}
          />
          {errors.author && (
            <small style={{ color: "red", fontSize: "12px" }}>
              {errors.author}
            </small>
          )}
        </div>

        <div style={{ textAlign: "left", marginTop: "20px" }}>
          <label style={{ display: "block", marginBottom: "5px" }}>Category:</label>
          <select
            value={category}
            onChange={(e) => setCategory(e.target.value)}
            style={{ width: "100%", padding: "8px" }}
          >
            <option value="">-- Select Category --</option>
            <option value="Fiction">Fiction</option>
            <option value="Science">Science</option>
            <option value="History">History</option>
            <option value="Technology">Technology</option>
          </select>
          {errors.category && (
            <small style={{ color: "red", fontSize: "12px" }}>
              {errors.category}
            </small>
          )}
        </div>

    
        <div style={{ marginTop: "30px" }}>
          <button
            type="submit"
            style={{
              marginRight: "15px",
              padding: "8px 20px",
              cursor: "pointer",
            }}
          >
            Submit
          </button>
          <button
            type="button"
            onClick={() => nav("/books")}
            style={{
              padding: "8px 20px",
              cursor: "pointer",
              color: "red",
            }}
          >
            Back
          </button>
        </div>
      </form>
    </div>
  );
}
