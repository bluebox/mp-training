import React, { useState } from "react";
import axios from "axios";

const AddIssueRecord = () => {
  const [record, setRecord] = useState({
    bookId: "",
    memberId: "",
  });

  const handleChange = (e) => {
    setRecord({ ...record, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/issues", record);
      alert(response.data);
      setRecord({ bookId: "", memberId: "" });
    } catch (error) {
      console.error("Error issuing book:", error);
      alert("Failed to issue book");
    }
  };

  return (
    <div>
      <h2>Issue Book</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="number"
          name="bookId"
          placeholder="Book ID"
          value={record.bookId}
          onChange={handleChange}
          required
        />
        <br />
        <input
          type="number"
          name="memberId"
          placeholder="Member ID"
          value={record.memberId}
          onChange={handleChange}
          required
        />
        <br />
        <button type="submit">Issue Book</button>
      </form>
    </div>
  );
};

export default AddIssueRecord;
