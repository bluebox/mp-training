import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'

const ProtectedRoute = ({children}) => {
  const navigate=useNavigate()
  useEffect(()=>{
    
     const token=localStorage.getItem('token')
     if(!token){
        alert(`login to access ${children.type?.name}`)
        navigate('/login')
        return 
     }
  },[navigate])
  return (
    <>
     {children}
    </>
  )
}

export default ProtectedRoute
