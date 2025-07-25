import React from "react";
// import Register from "./Register";
import { Outlet, Navigate } from "react-router-dom";

function PrivateRoute({login}){
    // const auth = Auth()
    return login?<Outlet/>:<Navigate to ='/login'/>
}
export default PrivateRoute
