import React, { useState, useEffect, useRef } from "react";
import { Routes, Route, useNavigate } from "react-router-dom";
import AddMember from "./AddMember";
import ViewMembers from "./ViewMembers";
import UpdateMember from "./UpdateMember";
import { getAllMembers } from "../../api/memberService"; 
import "./Member.css";

function Members() {
  const navigate = useNavigate();
  const [members, setMembers] = useState([]);
  const alertShown = useRef(false); // prevents double alert in Strict Mode

  useEffect(() => {
  getAllMembers()
    .then((res) => setMembers(res.data))
    .catch((err) => console.error("Failed to load members:", err));
}, []);


  const goBack = () => navigate("/");

  return (
    <div className="Members">
      <header className="Members-header">
        <h1>Members Management</h1>

        <Routes>
          <Route
            path="/"
            element={
              <>
                <p>Choose an action below:</p>
                <div className="button-container">
                  <button className="main-button" onClick={() => navigate("/members/add")}>
                    Add Member
                  </button>
                  <button className="main-button" onClick={() => navigate("/members/view")}>
                    View Members
                  </button>
                </div>
                <button className="back-button" onClick={goBack}>
                  ← Back
                </button>
              </>
            }
          />
          <Route path="add" element={<AddMember members={members} setMembers={setMembers} />} />
          <Route path="view" element={<ViewMembers members={members} setMembers={setMembers} />} />
          <Route path="update/:id" element={<UpdateMember members={members} setMembers={setMembers} />} />
        </Routes>
      </header>
    </div>
  );
}

export default Members;
