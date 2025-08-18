import React, { useState } from "react";
import axios from "axios";

const ReturnBook = () => {
  const [issueId, setIssueId] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.put(`http://localhost:8080/issues/return/${issueId}`);
      alert(response.data);
      setIssueId("");
    } catch (error) {
      console.error("Error returning book:", error);
      alert("Failed to return book");
    }
  };

  return (
    <div>
      <h2>Return Book</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="number"
          placeholder="Issue ID"
          value={issueId}
          onChange={(e) => setIssueId(e.target.value)}
          required
        />
        <br />
        <button type="submit">Return Book</button>
      </form>
    </div>
  );
};

export default ReturnBook;
