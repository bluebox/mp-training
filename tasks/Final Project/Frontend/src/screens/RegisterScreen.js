import React, {useState, useEffect} from 'react'
import { Link, useLocation , useNavigate} from 'react-router-dom'
import { Form, Button, Row, Col } from 'react-bootstrap'
import { useDispatch, useSelector } from 'react-redux'
import Loader from '../components/Loader'
import Message from '../components/Message'
import FormContainer from '../components/FormContainer'
import {useFormik} from 'formik'
import * as Yup from 'yup'

import { register } from '../actions/userActions'

function RegisterScreen() {
    const { search } = useLocation();
    const[name, setName] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [confirmPassword, setConfirmPassword] = useState('')
    const [message, setMessage] = useState('') 
    const location = useLocation()
    const navigate = useNavigate()

    const dispatch = useDispatch()

    const redirect = new URLSearchParams(search).get("redirect") || '/';
    const userRegister = useSelector(state => state.userRegister)
    const {error, loading, userInfo} = userRegister

    useEffect(() => {
      if(userInfo){
          navigate(redirect)
      }
    }, [userInfo, redirect])

    // const submitHandler = (e) => {
    //   e.preventDefault()

    //   if(password != confirmPassword){
    //     setMessage('Passwords do not match')
    //   }
    //   else{
    //     dispatch(register(name, email, password))
    //   }
      
    //}

    const formik = useFormik({
      initialValues: {
        name: "",
        email: "",
        password: "",
        confirmPassword: "",
      },

      validationSchema: Yup.object({
          name: Yup.string()
            .max(25, "Name must not be less than 25 characters")
            .min(2, "Name should have more than two characters")
            .required("Name is required"),
          email: Yup.string()
            .email("Invalid Email Address")
            .required("Email is required"),
          password: Yup.string()
            .max(20, "Password must be less than 20 characters.")
            .required("Password is required"),
          confirmPassword: Yup.string()
            .max(20, "Password must be less than 20 characters.")
            .oneOf([Yup.ref('password'), null], "Does not match with password!")
            .required("Password is required"),
      }),

      onSubmit: (values) => {
        const name = values.name
        const email = values.email
        const password = values.password
        const confirmPassword = values.confirmPassword
        dispatch(register(name, email, password, confirmPassword))

      }
    })
  return (
    <FormContainer>
      <h1>Sign In</h1>
      {/* {message && <Message variant='danger'>{error}</Message>} */}
      {error && <Message variant='danger'>{error}</Message>}
      {/* {loading && <Loader />}  */}
      <Form onSubmit={formik.handleSubmit}>

        <Form.Group controlId='name'>
            <Form.Label>Name</Form.Label>
            <Form.Control
              type='name'
              name='name'
              placeholder='Enter name'
              value={formik.values.name}
              onBlur={formik.handleBlur}
              onChange={formik.handleChange}
              />
              <div style={{color: 'red'}}>
                {formik.touched.name && formik.errors.name
                ? formik.errors.name : null}
              </div>
        </Form.Group>

        <Form.Group className='mb-3' controlId='email'>
            <Form.Label>Email Address</Form.Label>
            <Form.Control
              type='email'
              name='email'
              placeholder='Enter Email'
              onChange = {formik.handleChange}
              value={formik.values.email}
              onBlur={formik.handleBlur} />
            <div style={{color: 'red'}}>
              {formik.touched.email && formik.errors.email
              ? formik.errors.email : null}
            </div>
        </Form.Group>

        <Form.Group className='mb-3' controlId='password'>
            <Form.Label>Password</Form.Label>
            <Form.Control
              type='password'
              name='password'
              placeholder='Enter Password'
              value={formik.values.password}
              onChange={formik.handleChange}
              onBlur={formik.handleBlur} />
              <div style = {{color:"red"}}>
                {formik.touched.password && formik.errors.password
                ? formik.errors.password : null}
              </div>
        </Form.Group>

        <Form.Group className='mb-3' controlId='confirmPassword'>
            <Form.Label>Confirm Password</Form.Label>
            <Form.Control
              type='password'
              name='confirmPassword'
              placeholder='Confirm Password'
              value={formik.values.confirmPassword}
              onChange={formik.handleChange}
              onBlur={formik.handleBlur}
              />
              <div style={{color:"red"}}>
                {formik.touched.confirmPassword && formik.errors.confirmPassword
                ? formik.errors.confirmPassword : null}
              </div>
        </Form.Group>

        <Button type='submit' variant='primary'>
          Register
        </Button>
      </Form>

      <Row className='py-3'>
        <Col>
          Have an Account? <Link
            to={redirect ? `/login?redirect=${redirect}` : '/login'}>
              Sign In
            </Link>
        </Col> 
      </Row>
    </FormContainer>
  )
}

export default RegisterScreen
