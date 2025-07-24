import React, { useContext, useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { AuthContext } from './AuthContext'

const Login = () => {
  const navigate=useNavigate()
  const {setIsLoggined}=useContext(AuthContext)
  useEffect(()=>{
    const token=localStorage.getItem('token')
    if(token){
      alert('user is already loggined')
      navigate('/')
      return 
    }
  },[])
  const[username,setUsername]=useState('')
  const [email,setEmail]=useState('')
  const [password,setPassword]=useState('')
  const HandleLogin=(e)=>{
    e.preventDefault()
    if (username==='admin' && password==='admin'){
      localStorage.setItem('token',true)
      setIsLoggined(true)
      navigate('/')
      return
    }
    setUsername('')
    setPassword('')
    setEmail('')
    alert('invalid credentials')
  }
  return (
    <div className='flex justify-center items-center min-h-screen'>
        <form action="" className='w-96 md:w-full md:max-w-2xl border border-gray-200 rounded-lg shadow-2xl ' onSubmit={HandleLogin}>
            <h1 className='text-center font-bold text-2xl m-2'>Login</h1>
            <div className='m-3'>
                <label htmlFor="username" className='block font-bold'>Username:</label>
                <input type="text"  id='username' className='w-full p-2 rounded-lg mt-2 border border-gray-400 ' value={username} onChange={(e)=>setUsername(e.target.value)} required placeholder='Enter Username'/>
            </div>
            <div className='m-3'>
                <label htmlFor="email" className='block font-bold'>Email:</label>
                <input type="text"  id='email' className='w-full p-2 rounded-lg mt-2 border border-gray-400 ' value={email} onChange={(e)=>setEmail(e.target.value)} required placeholder='Enter Email'/>
            </div>
            <div className='m-3'>
                <label htmlFor="password" className='block font-bold'>Password:</label>
                <input type="text"  id='password' className='w-full p-2 rounded-lg mt-2 border border-gray-400 ' value={password} onChange={(e)=>setPassword(e.target.value)} required placeholder='Enter Username'/>
            </div>
            <div className='m-3 flex justify-center items-center'>
              <button type='submit' className='bg-blue-500 px-3 py-2 rounded-lg font-bold'>Submit</button>
            </div>
        </form>
    </div>
  )
}

export default Login
