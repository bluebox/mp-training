import React from "react";
// import Register from "./Register";
import { Outlet, Navigate } from "react-router-dom";

function PrivateRoute({login}){
    // const auth = Auth()
    return localStorage.getItem('isLogin') === 'true'?<Outlet/>:<Navigate to ='/login'/>
}
export default PrivateRoute
