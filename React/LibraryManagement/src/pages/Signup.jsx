import axios from 'axios';
import React, { useContext, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext';
import { Formik,Form,Field,ErrorMessage } from 'formik';
import * as Yup from 'yup'
const Signup = () => {
  const {isLoggined } = useContext(AuthContext);
  const navigate=useNavigate()
  if(isLoggined){
    navigate('/')
    return 
  }
 
  const handleSubmit = async(values,{setSubmitting,resetForm,setErrors}) => {
      setSubmitting(true)
       try{
            await axios.post('http://127.0.0.1:8000/api/member/crud/',values)
            alert('signup successful')
            resetForm()
            navigate('/login')
       }
       catch(err){
          const error=err.response.data
          const duplicatedError={}
          Object.keys(error).forEach((key)=>{
             duplicatedError[key]=error[key].join(',')
          })
          setErrors(duplicatedError)
       }
       finally{
          setSubmitting(false)
       }
  };

  return (
    <div className="bg-gray-100 flex items-center justify-center min-h-screen">
      <div className="bg-white p-8 rounded-lg shadow-lg md:w-full md:max-w-2xl">
        <h2 className="text-2xl font-bold text-center text-gray-700 mb-6">Create an Account</h2>
        
        {/* <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <label htmlFor="username" className="block text-gray-600">Username</label>
            <input
              type="text"
              id="username"
              name="username"
              value={formData.username}
              onChange={handleChange}
              className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="Enter Username"
              required
            />
          </div>

          <div className="mb-4">
            <label htmlFor="email" className="block text-gray-600">Email Address</label>
            <input
              type="email"
              id="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="Enter email"
            />
          </div>

          <div className="mb-6">
            <label htmlFor="password" className="block text-gray-600">Password</label>
            <input
              type="password"
              id="password"
              name="password"
              value={formData.password}
              onChange={handleChange}
              className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="Enter password"
            />
          </div>
           <div className="mb-6">
            <label htmlFor="confirmPassword" className="block text-gray-600">confirmPassword</label>
            <input
              type="password"
              id="confirmPassword"
              name="confirmPassword"
              value={formData.confirmPassword}
              onChange={handleChange}
              className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="Enter password"
            />
          </div>

          <button type="submit" className="w-full bg-blue-500 text-white py-2 rounded-md hover:bg-blue-600 transition duration-300">
            Sign Up
          </button>
        </form> */}
         <Formik
           initialValues={{
              username:'',
              email:'',
              password:'',
              confirmpassword:''
           }}
           validationSchema={Yup.object({
               username:Yup.string().required('Username is Required').min(2,'Username must have atleast 2 char'),
               email:Yup.string().matches(/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,'Invalid email address').required('Email is required'),
               password:Yup.string().required('Password is required'),
               confirmpassword:Yup.string().oneOf([Yup.ref('password'), null], 'ConfirmPassword must match password').required('ConfirmPassword is required')
           })}
           onSubmit={handleSubmit}
         >
          <Form>
              <div className='mt-1'>
                   <label htmlFor="username" className='block font-bold'>Username</label>
                   <Field name='username' type='username' className='w-full border border-gray-300 rounded-lg px-2 py-1' placeholder='Username'/>
                   <ErrorMessage name='username' component='div' className='text-red-500'/>
              </div>
                <div className='mt-1'>
                   <label htmlFor="email" className='block font-bold'>Email</label>
                   <Field name='email' type='email' className='w-full border border-gray-300 rounded-lg px-2 py-1' placeholder='Email'/>
                   <ErrorMessage name='email' component='div' className='text-red-500'/>
              </div>
              <div className='mt-1'>
                   <label htmlFor="password" className='block font-bold'>Password</label>
                   <Field name='password' type='password' className='w-full border border-gray-300 rounded-lg px-2 py-1' placeholder='Password'/>
                   <ErrorMessage name='password' component='div' className='text-red-500'/>
              </div>
              <div className='mt-1'>
                   <label htmlFor="confirmpassword" className='block font-bold'>Confirmpassword</label>
                   <Field name='confirmpassword' type='password' className='w-full border border-gray-300 rounded-lg px-2 py-1' placeholder='Confirmpassword'/>
                   <ErrorMessage name='confirmpassword' component='div' className='text-red-500'/>
              </div>
              <div className='mt-2'>
                  <button type="submit" className="w-full bg-blue-500 text-white py-2 rounded-md hover:bg-blue-600 transition duration-300">
                  Sign Up
                </button>
              </div>
          </Form>
         </Formik>
        <p className="text-center text-gray-500 mt-4">
          Already have an account? <Link to='/login' className="text-blue-500 hover:underline">Login</Link>
        </p>
      </div>
    </div>
  );
};

export default Signup;
