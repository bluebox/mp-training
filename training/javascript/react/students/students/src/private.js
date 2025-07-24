import React from "react";
import Auth from "./auth";
import Register from "./Register";
import { Outlet, Navigate } from "react-router-dom";

function PrivateRoute(){
    const auth = Auth()
    return auth?<Outlet/>:<Navigate to ='/login'/>
}
export default PrivateRoute
