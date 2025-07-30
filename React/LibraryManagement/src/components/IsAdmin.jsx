import React, { useContext, useEffect, useState } from 'react'
import { UserContext } from '../context/UserContext'
import { useNavigate } from 'react-router-dom'

const IsAdmin = ({children}) => {
    const { user } = useContext(UserContext)
    const navigate = useNavigate()
    useEffect(()=>{
       if(!user?.is_admin){
        navigate('/')
       }
    },[])
    return (
        <div>
          {children}
        </div>
    )
}

export default IsAdmin
