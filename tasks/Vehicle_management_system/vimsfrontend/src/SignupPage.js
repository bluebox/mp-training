import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { useCookies } from 'react-cookie';

export default function SignupPage() {
   const [cookies, setCookie, removeCookie] = useCookies(['userData']);
  const [userFirstname, setUserFirstname] = useState("");
  const [message,setmessage]=useState("");
  const [userLastname, setUserLastname] = useState("");
  const [userEmail, setUserEmail] = useState("");
  const [userPassword, setUserPassword] = useState("");
  const [userPhone, setUserPhone] = useState("");
  const [userAddress, setUserAddress] = useState("");
  const [userAge, setUserAge] = useState(0);
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
        userAge,
        userRegistationDate:today,
       userModifiedDate:null,
        role
      });
       let data="";
        data=res.data;
       setCookie('userData', { username:res.data.user , token: res.data.jwt }, { path: '/' });
       console.log(cookies.userData);
       if(res.status===200 && res.data.user.role==="ADMIN"){
      alert("Signup successful!");
      setmessage(res.data.message);
      navigate("/AdminDashBoard"); }
      else{
       navigate("/"); 
      }
    } catch (err) {
      alert(err.message);
      console.error(err);
      alert("Signup failed. Please try again.");
    }
  };

  return (
    <div  style={styles.container}>
    <div style={styles.card}>
      <h2 style={styles.title}>Signup Form</h2>
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
          onChange={(e) => setUserAge(Number(e.target.value))}
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

        <button type="submit" style={styles.loginButton}>Signup</button>
      </form>
    </div>
    </div>
  );
}

const styles = {
  container: {
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    minHeight: "100vh",
    background: "white",
    fontFamily: "Arial, sans-serif",
  },
  card: {
    backgroundColor: "#fff",
    padding: "40px",
    borderRadius: "15px",
    boxShadow: "0px 8px 25px rgba(0,0,0,0.2)",
    width: "100%",
    maxWidth: "400px",
    textAlign: "center",
  },
  title: {
    marginBottom: "25px",
    color: "#333",
  },
  form: {
    display: "flex",
    flexDirection: "column",
  },
  input: {
    padding: "12px",
    margin: "8px 0",
    borderRadius: "8px",
    border: "1px solid #ccc",
    outline: "none",
    fontSize: "14px",
    transition: "0.3s",
  },
  loginButton: {
    marginTop: "15px",
    padding: "12px",
    backgroundColor: "cornflowerblue",
    color: "#fff",
    border: "none",
    borderRadius: "8px",
    fontSize: "16px",
    fontWeight: "bold",
    cursor: "pointer",
    transition: "0.3s",
  },
  text: {
    marginTop: "20px",
    fontSize: "14px",
    color: "#555",
  },
  signupButton: {
    background: "none",
    border: "none",
    color: "#ff416c",
    fontWeight: "bold",
    cursor: "pointer",
    textDecoration: "underline",
  },
};
