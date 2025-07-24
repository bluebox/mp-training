import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import customAXIOS from "./apis";
import { LOGIN } from "./urls";

function Login({login,setLogin})
{
    const [userName,setUserName] = useState("")
    const [password,setPassword] = useState("") 
    const navigate = useNavigate()

    
    const handleLogin = async (e)=>{
        console.log("In login")
        e.preventDefault()
        const creds = {username:userName,password:password}
        try {
            const response = await customAXIOS(LOGIN, null, "login", creds, navigate);
            if (response?.access && response?.refresh) {
                setLogin(true);
                navigate("/");
            } else {
                alert("Login failed. Please check your credentials.");
            }
        } catch (error) {
            console.error("Login error:", error);
            alert("Invalid credentials or server error.");
        }
    }
    const handleEmail = (e)=>{
        const value = e.target.value
        setUserName(value)
    }
    const handlePassword = (e)=>{
        const value = e.target.value
        setPassword(value);
    }
    return(
        <div>
            <form onSubmit={(e) =>{
                console.log("submit")
                handleLogin(e)}}>
                <h3>Login</h3>
                <label htmlFor="uname">Username</label>
                <input id = "email" type="text" onChange={handleEmail} required></input>
                <label htmlFor="pwd">Password</label>
                <input id = "pwd" type="password"  onChange={handlePassword} required/>
                <button type="submit" className="submit-buttons" >Login</button>
            </form>
        </div>
    )
}
export default Login