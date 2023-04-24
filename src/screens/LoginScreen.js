import React, {useState, useEffect} from 'react'
import {Link} from 'react-router-dom'
import { Form, Button, Row, Col} from 'react-bootstrap'
import Message from '../components/Message'
import Loader from '../components/Loader'
import { useDispatch, useSelector } from 'react-redux'
import { login } from '../actions/userActions'
import FormContainer from '../components/FormContainer'
import { useNavigate, useLocation } from 'react-router-dom'
import { useFormik } from 'formik'
import * as Yup from 'yup'

function LoginScreen() {

    const { search } = useLocation();
    const dispatch = useDispatch();
    const redirect = new URLSearchParams(search).get("redirect") || '/';
    const navigate = useNavigate();
    const userLogin = useSelector(state => state.userLogin)
    const {error, loading, userInfo} = userLogin
    const companyLogin = useSelector(state => state.companyLogin)
    const { companyInfo } = companyLogin


    useEffect(() => {
        if(userInfo){
            navigate(redirect)
        }else if(companyInfo){
            navigate('/home-screen')
        }
    }, [navigate, userInfo, redirect, companyInfo])


    const formik = useFormik({
        initialValues: {
            username: "",
            password: "",
        },
    
        validationSchema: Yup.object({
            username: Yup.string()
            .email("Invalid Email Address")
            .required("Email is required"),
          password: Yup.string()
            .max(20, "Password must be 20 characters or less.")
            .required("Password is required"),
        }),
    
        onSubmit: (values) => {
            dispatch(login(values))
      
        },
      });

  return (
    <FormContainer>
        <h1>Sign In</h1>
        {error && <Message>{error}</Message>}
        <Form onSubmit={formik.handleSubmit}>
        

        <Form.Group className="mb-3" controlId="username">
            <Form.Label >Email</Form.Label>
            <Form.Control 
                type="email" 
                name='username'
                placeholder="Enter Email" 
                onChange={formik.handleChange}
                value={formik.values.username}
                onBlur={formik.handleBlur} />
            <div style={{color: 'red'}}> 
                {formik.touched.username && formik.errors.username
                ? formik.errors.username : null}  
            </div>
        </Form.Group>

        <Form.Group className="mb-3" controlId="password">
            <Form.Label>Password</Form.Label>
            <Form.Control 
                type="password" 
                name='password'
                placeholder="Enter Password" 
                onChange={formik.handleChange}
                value={formik.values.password}
                onBlur={formik.handleBlur} />
            <div style={{color: 'red'}}> 
                {formik.touched.password && formik.errors.password
                ? formik.errors.password : null}  
            </div>
        </Form.Group>

        <Button variant="primary" type="submit">
            Sign In
        </Button>
        </Form>

        <Row className='py-3'>
            <Col>
                New User ? {" "}
                <Link
                    to={redirect ? `/register?redirect=${redirect}` : '/register'}
                >
                    Register as Job Seeker
                </Link> {" "}
                or      {" "}
                <Link
                    to={'/register-employer'}
                >
                    Register as Employer
                </Link>
            </Col>
        </Row>
    </FormContainer>
  )
}

export default LoginScreen