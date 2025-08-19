import React, { useState } from "react";
import { getIssueRecords, issueBook, returnBook } from "../services/issueRecordsService";

export default function IssueRecordsPage() {
  const [view, setView] = useState(""); // "view", "issue", "return"
  const [records, setRecords] = useState([]);
  const [form, setForm] = useState({ memberId: "", bookId: "" });

  // Fetch all issue records
  const fetchRecords = async () => {
    try {
      const res = await getIssueRecords();
      setRecords(res.data);
      setView("view");
    } catch (error) {
      console.error("Failed to fetch records:", error);
      alert("Failed to fetch records. Check console.");
    }
  };

  // Handle issuing a book
  const handleIssue = async (e) => {
    e.preventDefault();
    try {
      await issueBook(form.bookId, form.memberId);
      alert("Book issued successfully!");
      setForm({ memberId: "", bookId: "" });
    } catch (error) {
      console.error("Failed to issue book:", error);
      alert("Failed to issue book. Check console.");
    }
  };

  // Handle returning a book
  const handleReturn = async (e) => {
    e.preventDefault();
    try {
      await returnBook(form.bookId, form.memberId);
      alert("Book returned successfully!");
      setForm({ memberId: "", bookId: "" });
    } catch (error) {
      console.error("Failed to return book:", error);
      alert("Failed to return book. Check console.");
    }
  };

  // Render table of issue records
  const renderRecords = () => (
    <table border="1" style={{ marginTop: "20px", width: "100%" }}>
      <thead>
        <tr>
          <th>Issue ID</th>
          <th>Member ID</th>
          <th>Book ID</th>
          <th>Status</th>
          <th>Issue Date</th>
          <th>Return Date</th>
        </tr>
      </thead>
      <tbody>
        {records.map((r) => (
          <tr key={r.isssueid}>
            <td>{r.isssueid}</td>
            <td>{r.memberid}</td>
            <td>{r.bookid}</td>
            <td>{r.status_issue === "I" ? "Issued" : "Returned"}</td>
            <td>{r.issuedate}</td>
            <td>{r.returndate || "-"}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );

  // Render issue/return form
  const renderForm = (type) => (
    <form onSubmit={type === "issue" ? handleIssue : handleReturn}>
      <input
        placeholder="Member ID"
        value={form.memberId}
        onChange={(e) => setForm({ ...form, memberId: e.target.value })}
      />
      <input
        placeholder="Book ID"
        value={form.bookId}
        onChange={(e) => setForm({ ...form, bookId: e.target.value })}
      />
      <button type="submit">{type === "issue" ? "Issue Book" : "Return Book"}</button>
    </form>
  );

  return (
    <div style={{ padding: "20px" }}>
      <h2>Issue Records Management</h2>

      <div style={{ marginBottom: "20px" }}>
        <button onClick={fetchRecords}>View All Issue Records</button>
        <button onClick={() => setView("issue")} style={{ marginLeft: "10px" }}>
          Issue Book
        </button>
        <button onClick={() => setView("return")} style={{ marginLeft: "10px" }}>
          Return Book
        </button>
      </div>

      {view === "view" && renderRecords()}
      {view === "issue" && renderForm("issue")}
      {view === "return" && renderForm("return")}
    </div>
  );
}
