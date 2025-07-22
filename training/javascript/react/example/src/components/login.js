import React from "react";

function Login(){
    return(
        <div>
            <h1>Login form</h1>
            <form className="login-form">
                <label htmlFor="userid">User Name</label>
                <input id="userid" type="email"></input>
                <label htmlFor="password"> Password</label>
                <input id="password" type="password"></input>
                <button type="Submit">Submit</button>
            </form>
        </div>
    )
}

export default login;