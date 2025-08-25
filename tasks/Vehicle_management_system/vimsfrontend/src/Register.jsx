import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function SignupPage() {
  const [userFirstname, setUserFirstname] = useState("");
  const [userLastname, setUserLastname] = useState("");
  const [userEmail, setUserEmail] = useState("");
  const [userPassword, setUserPassword] = useState("");
  const [userPhone, setUserPhone] = useState("");
  const [userAddress, setUserAddress] = useState("");
  const [userAge, setUserAge] = useState("");
  const [role, setRole] = useState("USER"); 
  const navigate = useNavigate();
  const now = new Date();
    const isoDate = now.toISOString().slice(0, 10);
   let today=isoDate;

   
  const handleSignup = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post("http://localhost:8080/UserAuth/signup", {
        userId:0,
        userFirstname,
        userLastname,
        userEmail,
        userPassword,
        userPhone,
        userAddress,
        userAge: parseInt(userAge),
        userRegistationDate:today,
       userModifiedDate:null,
        role
      });

      alert("Signup successful!");
      navigate("/login"); // back to login
    } catch (err) {
      alert("Signup failed. Please try again.");
    }
  };

  return (
    <div style={{ padding: "50px" }}>
      <h2>Signup Form</h2>
      <form onSubmit={handleSignup}>
        <input
          type="text"
          placeholder="First Name"
          value={userFirstname}
          onChange={(e) => setUserFirstname(e.target.value)}
          required
        /><br /><br />

        <input
          type="text"
          placeholder="Last Name"
          value={userLastname}
          onChange={(e) => setUserLastname(e.target.value)}
          required
        /><br /><br />

        <input
          type="email"
          placeholder="Email"
          value={userEmail}
          onChange={(e) => setUserEmail(e.target.value)}
          required
        /><br /><br />

        <input
          type="password"
          placeholder="Password"
          value={userPassword}
          onChange={(e) => setUserPassword(e.target.value)}
          required
        /><br /><br />

        <input
          type="text"
          placeholder="Phone"
          value={userPhone}
          onChange={(e) => setUserPhone(e.target.value)}
          required
        /><br /><br />

        <input
          type="text"
          placeholder="Address"
          value={userAddress}
          onChange={(e) => setUserAddress(e.target.value)}
          required
        /><br /><br />

        <input
          type="number"
          placeholder="Age"
          value={userAge}
          onChange={(e) => setUserAge(e.target.value)}
          required
        /><br /><br />

        <div>
          <button
            type="button"
            style={{
              backgroundColor: role === "USER" ? "green" : "gray",
              color: "white",
              marginRight: "10px"
            }}
            onClick={() => setRole("USER")}
          >
            User
          </button>

          <button
            type="button"
            style={{
              backgroundColor: role === "ADMIN" ? "green" : "gray",
              color: "white"
            }}
            onClick={() => setRole("ADMIN")}
          >
            Admin
          </button>
        </div>
        <br />

        <button type="submit">Signup</button>
      </form>
    </div>
  );
}

export default SignupPage;
