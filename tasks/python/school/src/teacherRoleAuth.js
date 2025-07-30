import React from "react";
// import Register from "./Register";
import { Outlet, Navigate } from "react-router-dom";

function TeacherRoleAuth({}){
    // const auth = Auth()
    return localStorage.getItem('access') === 'teacher'?<Outlet/>:<Navigate to ='/unauthorized'/>
}
export default TeacherRoleAuth
