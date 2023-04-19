import React, {useState, useEffect} from 'react'
import { userLogin } from '../actions/userActions' 
import {Link, useLocation, useNavigate} from 'react-router-dom'
import {Form, Button, Row, Col} from 'react-bootstrap'
import {useDispatch, useSelector} from 'react-redux'
import Loader from '../components/Loader'
import Message from '../components/Message'
import FormContainer from '../components/FormContainer'
import { useFormik } from 'formik'
import * as Yup from 'yup'

function LoginScreen() {
    const dispatch = useDispatch()
    const user= useSelector(state => state.userLogin)
    const {loading, error, userInfo} = user

    const formik = useFormik({
        initialValues:{
            username: '',
            password: '',
        },
        
        validationSchema: Yup.object({
            username: Yup.string()
              .min(3, "Name must be 3 characters or more.")
              .required("username is required"),
            password: Yup.string()
                // .min(8, "Password must be atleast 8 characters")
                // .max(15, "Password must be less than 15 characters")
                // .matches(/^(?=.*[0-9])/, 'Password must contain at least one number')
                // .matches(/^(?=.*[A-Z])/, 'Password must contain at least one Uppercase character')
                // .matches(/^(?=.*[a-z])/, 'Password must contain at least one Lowercase character')
                .required('Password required'),
          }),

        onSubmit:values=>{
            console.log('hello')
            dispatch(userLogin(values.username, values.password))
        },
 
    })

    const {search} = useLocation()
    const navigate = useNavigate()


    const redirect = new URLSearchParams(search).get('redirect') || '/'
    
    

    useEffect(() =>{
        
        if (userInfo && userInfo.username){
            navigate(redirect)
        }
    }, [navigate, userInfo, redirect])

  return (
    <FormContainer>
        <h1>Sign in</h1>
        {error && <Message variant='danger'>{error}</Message>}
        {loading && <Loader></Loader>}
        <Form onSubmit={formik.onSubmit}>
            <Form.Group controlId='username' className="form-child">
                <Form.Label>Username
                    <span className='d-block form-error'>
                        {(formik.touched.username && formik.errors.username)
                        && formik.errors.username}
                    </span>
                </Form.Label>
                <Form.Control
                    required
                    type='text'
                    placeholder='enter username'
                    value={formik.values.username}
                    onChange = {formik.handleChange}
                >
                </Form.Control>
            </Form.Group>

            <Form.Group controlId='password' className="form-child">
                <Form.Label>Password
                    <span className='d-block form-error'>
                        {(formik.touched.password && formik.errors.password)
                        && formik.errors.password}
                    </span>
                </Form.Label>
                <Form.Control
                    required
                    type='password'
                    placeholder='enter password'
                    value={formik.values.password}
                    onChange = {formik.handleChange}
                >                    
                </Form.Control>
            </Form.Group>

            <Button onClick={formik.handleSubmit} variant='danger' className="form-child">Sign In</Button>
        </Form>
        <Row className='py-3'>
           <Col> New Customer? </Col><Col md={2}><Link to={redirect? `/register?redirect=${redirect}`: '/register'}>Register</Link></Col>
        </Row>
    </FormContainer>
  )
}

export default LoginScreen