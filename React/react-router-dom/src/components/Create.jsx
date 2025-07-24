import React, { useContext, useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { EditContext } from './EditContext'

const Create = () => {
    const {EditData,setEditData}=useContext(EditContext)
    const[username,setUsername]=useState('')
    const[email,setEmail]=useState('')
    const[phoneNo,setPhoneNo]=useState('')
    const [usernameError,setUsernameError]=useState(false)
    const [emailError,setEmailError]=useState(false)
    const [phoneNoError,setPhoneNoError]=useState(false)
    useEffect(()=>{
        setUsername(EditData.username || '')
        setEmail(EditData.email || '')
        setPhoneNo(EditData.phoneNo || '')
    },[])
    const handleUsername=()=>{
        const regex = /^[a-zA-Z0-9_ ]{3,}$/;
        return regex.test(username);
    }
    const handleEmail=()=>{
        const regex=/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/
        return regex.test(email)
    }
    const handlePhoneNo=()=>{
        const regex=/^[6-9]\d{9}$/
        return regex.test(phoneNo)
    }
    const handleSubmit=(e)=>{
        e.preventDefault()
        if(!handleUsername()){
            //  alert('enter valid username')
            setUsernameError(true)
            return 
        }
        if (!handleEmail()){
            // alert('enter valid email')
            setEmailError(true)
            return 
        }
        if(!handlePhoneNo()){
            // alert('enter valid phoneNo')
            setPhoneNoError(true)
            return
        }
        const existingUsers = JSON.parse(localStorage.getItem('users') || '[]');
        existingUsers.push({username,email,phoneNo})
        localStorage.setItem('users', JSON.stringify(existingUsers));
        alert('user added successfully') 
        setEmail('')
        setUsername('')
        setPhoneNo('')
        setEditData([])
    }
  return (
    <div className='flex justify-center items-center min-h-screen'>
        <form className='bg-white  rounded-md p-5 w-96 md:w-full md:max-w-2xl border border-gray-200 shadow-2xl' onSubmit={handleSubmit}>
            <h1 className='text-2xl text-center pb-4 font-bold'>Create User</h1>
            <div className='mb-3'>
                <label htmlFor="username" className='block font-bold'>Username:</label>
                <input  className="w-full mt-2 p-2 rounded-lg border border-gray-300 " border border-gray-200 id="username" type="text" value={username} onChange={(e)=>{
                    setUsername(e.target.value)
                    setUsernameError(false)
                    }} required placeholder='enter your name'/>
                {usernameError && (<h1 className='text-red-600 '>enter valid username</h1>)}
            </div>
            <div className='mb-3'>
                <label htmlFor="email" className='block font-bold '>Email:</label>
                <input  className="w-full mt-2 p-2 rounded-lg border border-gray-300  "id="email" type="text" value={email} onChange={(e)=>{
                    setEmail(e.target.value)
                    setEmailError(false)
                    }} required placeholder='enter your email address'/>
                {emailError && (<h1 className='text-red-600 '>enter valid email</h1>)}
            </div>
            <div className='mb-3'>
                <label htmlFor="phone" className='block font-bold'>PhoneNo:</label>
                <input  className="w-full mt-2 p-2 rounded-lg border border-gray-300 "id="phone" type="text" value={phoneNo} onChange={(e)=>{
                    setPhoneNo(e.target.value)
                    setPhoneNoError(false)
                    }} required placeholder='enter your phone Number'/>
                {phoneNoError && (<h1 className='text-red-600 '>enter valid phoneNo</h1>)}
            </div>
            <div className='flex justify-center items-center'>
                <button className='bg-blue-500 text-black px-3 py-2 rounded-md font-bold' type='submit'>Submit</button>
            </div>
             <div className='mt-2'>
                <button className='bg-blue-500 text-black px-3 py-2 rounded-md font-bold'><Link to='/'>View Users</Link></button>
            </div>
        </form>
    </div>
  )
}

export default Create
