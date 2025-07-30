import React from "react";
// import Register from "./Register";
import { Outlet, Navigate } from "react-router-dom";

function AdminRoleAuth({}){
    // const auth = Auth()
    return localStorage.getItem('access') === 'admin'?<Outlet/>:<Navigate to ='/unauthorized'/>
}
export default AdminRoleAuth
