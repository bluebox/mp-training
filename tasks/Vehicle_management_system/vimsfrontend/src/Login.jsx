import React, { useState } from 'react';
import './App.css';
import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { useCookies } from 'react-cookie';


export default function Login(){
     const [data,setdata] =useState({
        UserId:0,
        password:""
     });
       const navigate = useNavigate();
      const [cookies, setCookie, removeCookie] = useCookies(['userData']);
      const [message,setmessage]=useState("");
     const handleChange = (e) => {
    const { name, value } = e.target;
    setdata((prevdata) => ({
      ...prevdata,
      [name]: value,
    }));
  };

    async function handleSubmit(e){
           e.preventDefault();
           try{
             let response=await axios.post('http://localhost:8080/UserAuth/signin', data) ;
             console.log(response);
             setmessage(response.data.message);
             if(response.status===200){
                let data="";
                data=response.data;
                 setCookie('userData', { username:response.data.user , token: response.data.jwt }, { path: '/' });
                console.log("move to home")  ;
                 if(response.status===200 && response.data.user.role==="ADMIN"){
      alert("Signup successful!");
      navigate("/AdminDashBoard");
     }else{
         alert("login successful!");
navigate("/IssueInsuranceform");
      }
    } }catch(error){
             setmessage("Error occured"+error.message);
             console.error(error);
           }
     }

  
    return(
<div style={styles.container}>
      <div style={styles.card}>
        <h2 style={styles.title}>Login Page</h2>
        <form onSubmit={handleSubmit} style={styles.form}>
          <input
            type="number"
            placeholder="UserId"
            value={data.UserId}
            name="UserId"
           onChange={handleChange}  required
            style={styles.input}
          />
          <input
            type="password"
            placeholder="Password"
            value={data.password}
            name="password"
            onChange={handleChange}  required
            style={styles.input}
          />

          <button type="submit" style={styles.loginButton}>
            Login
          </button>
        </form>

        <p style={styles.text}>
          Don’t have an account?{" "}
          <button
            onClick={() => navigate("/Register")}
            style={styles.signupButton}
          >
            Sign Up
          </button>
        </p>
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

