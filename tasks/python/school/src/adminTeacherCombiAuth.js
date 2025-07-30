import React from "react";
import { Outlet, Navigate } from "react-router-dom";

function AdminTeacherCombiAuth({}){
    return localStorage.getItem('access') === 'admin' || localStorage.getItem('access') ==='teacher'?<Outlet/>:<Navigate to ='/unauthorized'/>
}
export default AdminTeacherCombiAuth
