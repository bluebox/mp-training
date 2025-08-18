import React, { useState } from "react";
import AddBook from "./AddBook";
import UpdateBook from "./UpdateBook";
import ViewBooks from "./ViewBooks";

const Books = () => {
  const [selected, setSelected] = useState("");

  return (
    <div>
      <h2> Book Management</h2>

      <div style={{ marginBottom: "20px" }}>
        <button onClick={() => setSelected("add")}> Add Book</button>
        <button onClick={() => setSelected("update")}> Update Book</button>
        <button onClick={() => setSelected("view")}> View Books</button>
      </div>

      {selected === "add" && <AddBook />}
      {selected === "update" && <UpdateBook />}
      {selected === "view" && <ViewBooks />}
    </div>
  );
};

export default Books;
