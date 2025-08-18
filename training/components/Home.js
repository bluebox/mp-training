import React, { useState } from "react";
import Books from "./Books";
import Members from "./Members";
import IssueRecords from "./IssueRecords";
import Reports from "./Reports";

const Home = () => {
  const [selected, setSelected] = useState("");

  return (
    <div>
      <h1>Library Management System</h1>

      {/* Main buttons */}
      <div style={{ marginBottom: "20px" }}>
        <button onClick={() => setSelected("books")}> Books</button>
        <button onClick={() => setSelected("members")}> Members</button>
        <button onClick={() => setSelected("issues")}> Issue Records</button>
        <button onClick={() => setSelected("reports")}> Reports</button>
      </div>

      {/* Conditional rendering based on selection */}
      {selected === "books" && <Books />}
      {selected === "members" && <Members />}
      {selected === "issues" && <IssueRecords />}
      {selected === "reports" && <Reports />}
    </div>
  );
};
export default Home;