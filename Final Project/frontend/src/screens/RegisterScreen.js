import React, {useState, useEffect} from 'react'
import {Link, useLocation, useNavigate} from 'react-router-dom'
import {Form, Button, Row, Col} from 'react-bootstrap'
import Loader from '../components/Loader'
import Message from '../components/Message'
import FormContainer from '../components/FormContainer'
import { registerUser } from '../services/userServices'
import { useFormik } from 'formik'
import * as Yup from 'yup'

function RegisterScreen() {
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [first_name, setFirstName] = useState('')
    const [email, setEmail] = useState('')
    const [last_name, setLastName] = useState('')
    const [confirmedPassword, setConfirmedPassword] = useState('')

    const formik = useFormik({
        initialValues:{
            username: '',
            first_name: '',
            last_name: '',
            email: '',
            password: '',
            confirmedPassword: '',
        },
        
        validationSchema: Yup.object({
            first_name: Yup.string()
              .min(3, "Name must be 3 characters or more.")
              .required("Name is required"),
            last_name: Yup.string()
              .min(3, "Name must be 3 characters or more.")
              .required("Name is required"),
              username: Yup.string()
              .min(3, "Name must be 3 characters or more.")
              .required("Name is required"),
            email: Yup.string()
              .email("Invalid email address")
              .required("Email is required"),
            password: Yup.string()
                .min(8, "Password must be atleast 8 characters")
                .max(15, "Password must be less than 15 characters")
                .matches(/^(?=.*[0-9])/, 'Password must contain at least one number')
                .matches(/^(?=.*[A-Z])/, 'Password must contain at least one Uppercase character')
                .matches(/^(?=.*[a-z])/, 'Password must contain at least one Lowercase character')
                .matches(/^(?=.*[@#$%^&*!?><])/, 'Password must contain at least one symbol')
                .required('Enter password'),
            confirmedPassword: Yup.string()
                .min(8, "Password must be atleast 8 characters")
                .max(15, "Password must be less than 15 characters")
                .matches(/^(?=.*[0-9])/, 'Password must contain at least one number')
                .matches(/^(?=.*[A-Z])/, 'Password must contain at least one Uppercase character')
                .matches(/^(?=.*[a-z])/, 'Password must contain at least one Lowercase character')
                .matches(/^(?=.*[@#$%^&*!?><])/, 'Password must contain at least one symbol')
                .required('Confirm your password')
          }),

          onSubmit:values=>{
            if (values.password != values.confirmedPassword){
                setMessage('Passwords do not match')
            }else{
                registerUser(values.username,values.email, values.password, values.first_name, values.last_name, setLoading, setSuccess, setError)
            }
        },
 
    })

    const [message, setMessage] = useState('')
    const [loading, setLoading] = useState(false)
    const [error, setError] = useState('')
    const [success, setSuccess] = useState(false)
    const navigate = useNavigate()
    
    useEffect(() =>{
    
        if (success){
            setSuccess(false)
            navigate(-1)
        }
    }, [navigate, success])
  return (
    <FormContainer>
        <h1>Sign Up</h1>
        {message && <Message variant='danger'>{message}</Message>}
        {error && <Message variant='danger'>{error}</Message>}
        {loading && <Loader />}
        <Form onSubmit={formik.handleSubmit}>
            <Form.Group controlId='username' className="form-child">
                <Form.Label>Username
                <span className='d-block form-error'>
                        {(formik.touched.username && formik.errors.username)
                        && formik.errors.username}
                    </span>
                </Form.Label>
                <Form.Control
                    
                    type='text'
                    placeholder='enter username'
                    value = {formik.values.username}
                    onChange = {formik.handleChange}
                >
                </Form.Control>
            </Form.Group>

            <Form.Group controlId='email' className="form-child">
                <Form.Label>Email
                <span className='d-block form-error'>
                        {(formik.touched.email && formik.errors.email)
                        && formik.errors.email}
                    </span>
                </Form.Label>
                <Form.Control
                    
                    type='email'
                    placeholder='enter email'
                    value = {formik.values.email}
                    onChange = {formik.handleChange}
                >                    
                </Form.Control>
            </Form.Group>

            <Form.Group controlId='first_name' className="form-child">
                <Form.Label>First Name
                <span className='d-block form-error'>
                        {(formik.touched.first_name && formik.errors.first_name)
                        && formik.errors.first_name}
                    </span>
                </Form.Label>
                <Form.Control
                    
                    type='text'
                    placeholder='enter first name'
                    value = {formik.values.first_name}
                    onChange = {formik.handleChange}
                >                    
                </Form.Control>
            </Form.Group>

            <Form.Group controlId='last_name' className="form-child">
                <Form.Label>Last Name
                <span className='d-block form-error'>
                        {(formik.touched.last_name && formik.errors.last_name)
                        && formik.errors.last_name}
                    </span>
                </Form.Label>
                <Form.Control
                    
                    type='text'
                    placeholder='enter last name'
                    value = {formik.values.last_name}
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
                    
                    type='password'
                    placeholder='enter password'
                    value = {formik.values.password}
                    onChange = {formik.handleChange}
                >                    
                </Form.Control>
            </Form.Group>

            <Form.Group controlId='confirmedPassword' className="form-child">
                <Form.Label>Confirm Password
                <span className='d-block form-error'>
                        {(formik.touched.confirmedPassword && formik.errors.confirmedPassword)
                        && formik.errors.confirmedPassword}
                    </span>
                </Form.Label>
                <Form.Control
                    
                    type='password'
                    placeholder='enter password again'
                    value = {formik.values.confirmedPassword}
                    onChange = {formik.handleChange}
                >                    
                </Form.Control>
            </Form.Group>

            <Button type='submit' variant='danger' className="form-child">Sign Up</Button>
        </Form>
        <Row className='py-3'>
           <Col>Have an Account?</Col>  <Col md={2}><Link to={'/login'}>Login</Link></Col>
        </Row>
    </FormContainer>
    
  )
}

export default RegisterScreen