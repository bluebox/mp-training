import { createContext, useEffect, useState } from "react";
import { useSelector } from "react-redux";

export const AuthContext=createContext()

export const AuthProvider=({children})=>{
     const [isLoggined,setIsLoggined]=useState(false)
     const isAuthenticated=useSelector((state)=>state.auth.isAuthenticated)
     useEffect(()=>{
        const token=localStorage.getItem('access_token')
        if(token){
            setIsLoggined(true)
        }
     },[isAuthenticated,isLoggined])
     return (
        <AuthContext.Provider value={{isLoggined,setIsLoggined}}>
           {children}
        </AuthContext.Provider>
     )
}