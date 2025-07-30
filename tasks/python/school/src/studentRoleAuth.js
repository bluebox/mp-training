import React from "react";
import { Outlet, Navigate } from "react-router-dom";

function StudentRoleAuth({}){
    // const auth = Auth()
    console.log("in student role auth")
    return localStorage.getItem('access') === 'student'?<Outlet/>:<Navigate to ='/unauthorized'/>
}
export default StudentRoleAuth
