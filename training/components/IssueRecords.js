import React, { useState } from "react";
import AddIssueRecord from "./AddIssueRecord";
import ReturnBook from "./ReturnBook";
import ViewIssueRecords from "./ViewIssueRecords";

const IssueRecords = () => {
  const [selected, setSelected] = useState("");

  return (
    <div>
      <h2> Issue Records</h2>

      <div style={{ marginBottom: "20px" }}>
        <button onClick={() => setSelected("add")}> Issue Book</button>
        <button onClick={() => setSelected("return")}>Return Book</button>
        <button onClick={() => setSelected("view")}> View Records</button>
      </div>

      {selected === "add" && <AddIssueRecord />}
      {selected === "return" && <ReturnBook />}
      {selected === "view" && <ViewIssueRecords />}
    </div>
  );
};

export default IssueRecords;
