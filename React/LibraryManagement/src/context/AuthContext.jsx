import { createContext, useEffect, useState } from "react";

export const AuthContext=createContext()

export const AuthProvider=({children})=>{
     const [isLoggined,setIsLoggined]=useState(false)
     useEffect(()=>{
        const token=localStorage.getItem('access_token')
        if(token){
            setIsLoggined(true)
        }
     },[isLoggined,setIsLoggined])
     return (
        <AuthContext.Provider value={{isLoggined,setIsLoggined}}>
           {children}
        </AuthContext.Provider>
     )
}