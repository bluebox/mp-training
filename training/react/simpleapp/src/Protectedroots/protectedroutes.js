import React, { Children } from "react";
import { Navigate } from "react-router-dom";


export default function Protect({children}){
const isLogin=localStorage.getItem('IsLogin')==='true';
 if(isLogin){
    return children;

 }
 else{
    return <Navigate to="/login" replace />;
 }
 

};