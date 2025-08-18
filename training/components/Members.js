import React, { useState } from "react";
import AddMember from "./AddMember";
import UpdateMember from "./UpdateMember";
import ViewMembers from "./ViewMembers";

const Members = () => {
  const [selected, setSelected] = useState("");

  return (
    <div>
      <h2> Member Management</h2>

      <div style={{ marginBottom: "20px" }}>
        <button onClick={() => setSelected("add")}> Add Member</button>
        <button onClick={() => setSelected("update")}> Update Member</button>
        <button onClick={() => setSelected("view")}> View Members</button>
      </div>

      {selected === "add" && <AddMember />}
      {selected === "update" && <UpdateMember />}
      {selected === "view" && <ViewMembers />}
    </div>
  );
};

export default Members;
