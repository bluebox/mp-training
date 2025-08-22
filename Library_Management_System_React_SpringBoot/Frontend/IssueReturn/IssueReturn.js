import React, { useState, useEffect } from "react";
import IssueBook from "./IssueBook";
import ReturnBook from "./ReturnBook";
import "./IssueReturn.css";

import { getAllBooks } from "../../api/bookService";
import { getAllMembers } from "../../api/memberService";
import { getRecords } from "../../api/issueService";

function IssueReturn() {
  const [view, setView] = useState(""); // issue / return
  const [books, setBooks] = useState([]);
  const [members, setMembers] = useState([]);
  const [records, setRecords] = useState([]);

  useEffect(() => {
    getAllBooks().then(res => setBooks(res.data)).catch(console.error);
    getAllMembers().then(res => setMembers(res.data)).catch(console.error);
    getRecords().then(res => setRecords(res.data)).catch(console.error);
  }, []);

  return (
    <div className="IssueReturn">
      <h1>Issue & Return Books</h1>
      <div className="button-container">
        <button className="main-button" onClick={() => setView("issue")}>
          Issue Book
        </button>
        <button className="main-button" onClick={() => setView("return")}>
          Return Book
        </button>
      </div>

      <div className="content-area">
        {view === "issue" && (
          <IssueBook
            books={books}
            members={members}
            records={records}
            setRecords={setRecords}
          />
        )}
        {view === "return" && (
          <ReturnBook
            books={books}
            members={members}
            records={records}
            setRecords={setRecords}
          />
        )}
      </div>
    </div>
  );
}

export default IssueReturn;
