import React, { useState,useContext} from 'react';
import Axios from '../utils/Axios';
import { useNavigate } from 'react-router-dom';
import {AuthContext} from '../context/AuthContext'
import { Link } from 'react-router-dom';
import { Formik,Form,Field,ErrorMessage} from 'formik';
import * as Yup from 'yup'
const Login = () => {

  const {isLoggined,setIsLoggined } = useContext(AuthContext);
  const navigate = useNavigate();
  if(isLoggined){
    navigate('/')
    return 
  }
  const HandleSubmit = async (values,{setSubmitting,resetForm}) => {
    setSubmitting(true)
    try {
      const response = await Axios.post('member/login/', values)

      const { access, refresh } = response.data;

      localStorage.setItem('access_token', access);
      localStorage.setItem('refresh_token', refresh);
      setIsLoggined(true)
      resetForm()
      navigate('/');
    } catch (error) {
      alert('Login failed. Check credentials.');
      console.error('Login error:', error);
    }
    finally{  
      setSubmitting(false)
    }
  };

  return (
    <div className="flex justify-center items-center min-h-screen">
      <div className="w-96 md:w-full md:max-w-2xl bg-white p-8 border border-gray-200 rounded shadow-lg">
        <h1 className="text-2xl font-bold mb-6 text-center text-blue-700">Login</h1>
        <Formik
          initialValues={{
            email:'',
            password:''
          }}
          validationSchema={Yup.object({
              email:Yup.string().matches(/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,'Invalid email address').required('Email is required'),
              password:Yup.string().required('Password is required').min(6,'password must have atleast 6 characters')
          })}
          onSubmit={HandleSubmit}
        >
            {/* {(formik)=>(
                  <form onSubmit={formik.handleSubmit}>
                    <div className="mb-4">
                      <label className="block mb-1 font-medium text-gray-700">Email</label>
                      <input
                        type="email"
                        name='email'
                        className="w-full border border-gray-300 px-3 py-2 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
                        placeholder="Enter your email"
                        required
                        {...formik.getFieldProps('email')}
                      />
                      {formik.touched && formik.errors.email?(<p className='text-red-500'>{formik.errors.email}</p>):null}
                    </div>
                    <div className="mb-6">
                      <label className="block mb-1 font-medium text-gray-700">Password</label>
                      <input
                        type="password"
                        name='password'
                        className="w-full border border-gray-300 px-3 py-2 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
                        placeholder="Enter your password"
                        required
                        {...formik.getFieldProps('password')}
                      />
                      {formik.touched && formik.errors.password?(<p className='text-red-500'>{formik.errors.password}</p>):null}
                    </div>
                    <button
                      type="submit"
                      className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700 transition"
                    >
                      Login
                    </button>
                  </form>
            )} */}
            <Form>
                <div className='mt-2'>
                  <label htmlFor="email" className='block font-bold'>Email</label>
                  <Field 
                  name='email' 
                  type="email" 
                  className='w-full border border-gray-300 rounded-lg px-3 py-2' 
                  placeholder="Email"
                  />
                  <ErrorMessage name='email' component='div'className='text-red-600'/>
                </div>

                <div className='mt-2'>
                  <label htmlFor="password"  className="block font-bold">Password</label>
                  <Field
                    name="password"
                    type="password"
                    className="w-full border border-gray-300 rounded-lg px-3 py-2"
                    placeholder="Password"
                  />
                  <ErrorMessage name="password" component="div" className="text-red-600" />
                </div>
               <div className='mt-2'>
                  <button
                      type="submit"
                      className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700 "
                    >
                      Login
                    </button>
               </div>
            </Form>
         </Formik>
        <p className="text-center text-gray-500 mt-4">
          Don't have an account? <Link to='/signup' className="text-blue-500 hover:underline">Signup</Link>
        </p>
      </div>
    </div>
  );
};

export default Login;
