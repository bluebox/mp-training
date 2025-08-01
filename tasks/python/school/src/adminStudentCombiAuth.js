import React from "react";
import { Navigate, Outlet, } from "react-router-dom";

function AdminStudentCombiAuth(){
    return localStorage.getItem('access').toLowerCase() === 'admin' || localStorage.getItem('access').toLowerCase() === 'student'?<Outlet/>:<Navigate to='/unauthorized'/> 
}
export default AdminStudentCombiAuth