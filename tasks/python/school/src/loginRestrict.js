import React from "react";
import { Navigate, Outlet } from "react-router-dom";

function LoginRestrict(){

    return localStorage.getItem('isLogin')==='true'?<Navigate to ='/'/>:<Outlet/>
}
export default LoginRestrict