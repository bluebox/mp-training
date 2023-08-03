import React, {useState, useEffect} from 'react'
import { Link, useLocation , useNavigate} from 'react-router-dom'
import { Form, Button, Row, Col } from 'react-bootstrap'
import { useDispatch, useSelector } from 'react-redux'
import Loader from '../components/Loader'
import Message from '../components/Message'
import FormContainer from '../components/FormContainer'
import {useFormik} from 'formik'
import * as Yup from 'yup'

import { login } from '../actions/userActions'

function LoginScreen() {
    const { search } = useLocation();
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const navigate = useNavigate()

    const dispatch = useDispatch()

    const redirect = new URLSearchParams(search).get("redirect") || '/';
    const userLogin = useSelector(state => state.userLogin)
    const {error, loading, userInfo} = userLogin

    useEffect(() => {
      if(userInfo){
          navigate(redirect)
      }
    }, [userInfo, redirect])

    // const submitHandler = (e) => {
    //   e.preventDefault()
    //   dispatch(login(email, password))
    // }

    const formik = useFormik({
      initialValues: {
        email: "",
        password: "",
      },

      validationSchema: Yup.object({
        email: Yup.string()
        .email("Invalid Email Address")
        .required("Email is required"),
      password: Yup.string()
        .max(20, "Password must be less than 20 characters.")
        .required("Password is required"),
      }),

      onSubmit: (values) => {
        // console.log(values)
        const email = values.email
        const password = values.password
        // console.log(email)
        dispatch(login(email, password))
      },
    });

  return (
    <FormContainer>
      <h1>Sign In</h1>
      {error && <Message variant='danger'>{error}</Message>}
      <Form onSubmit={formik.handleSubmit}>
        <Form.Group controlId='email'>
            <Form.Label>Email Address</Form.Label>
            <Form.Control
              type='email'
              name='email'
              placeholder='Enter Email'
              onChange={formik.handleChange}
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
              onChange={formik.handleChange}
              value={formik.values.password}
              onBlur={formik.handleBlur} />
              <div style={{color:"red"}}>
                  {formik.touched.password && formik.errors.password
                  ? formik.errors.password : null}
              </div>
        </Form.Group>

        <Button type='submit' variant='primary'>
          Sign In
        </Button>

      </Form>

      <Row className='py-3'>
        <Col>
          New Customer? <Link
            to={redirect ? `/register?redirect=${redirect}` : '/register'}>
              Register
            </Link>
        </Col>
      </Row>
    </FormContainer>
  )
}

export default LoginScreen
