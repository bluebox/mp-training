import React, { useContext } from 'react'
import { Link, NavLink, useNavigate } from 'react-router-dom'
import {AuthContext} from './AuthContext'

const Navbar = () => {
  const navigate=useNavigate()
  const {setIsLoggined}=useContext(AuthContext)
  const HandleLogout=()=>{
    const token=localStorage.getItem('token')
    if(!token){
      alert('user is not logined')
      return 
    }
    localStorage.removeItem('token')
    setIsLoggined(false)
    navigate('/login')
    alert('logout successful')
  }
  return (
    <div className='bg-blue-500 text-white px-6 py-4 shadow-md sticky top-0'>
       <div className='flex justify-between items-center space-x-6'>
          <div className='space-x-10'>
              <NavLink to="/" className={({isActive }) =>isActive ? 'hover:text-white font-bold text-black bg-blue-700 p-2 rounded-md' : 'text-black font-bold hover:text-white'}>Home</NavLink>
              <NavLink to='/create' className={({isActive})=>isActive?'hover:text-white font-bold text-black bg-blue-700 p-2 rounded-md':'text-black font-bold hover:text-white'}>Create</NavLink>
          </div>
          <div className='space-x-10'>
             <button className='text-black font-bold hover:text-white' onClick={HandleLogout}> Logout</button>
          </div>
       </div>
    </div>
  )
}

export default Navbar
