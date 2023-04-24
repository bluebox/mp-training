import React, {useState, useEffect} from 'react'
import {Link} from 'react-router-dom'
import { Form, Button, Row, Col} from 'react-bootstrap'
import Message from '../components/Message'
import Loader from '../components/Loader'
import { useDispatch, useSelector } from 'react-redux'
import { register } from '../actions/userActions'
import FormContainer from '../components/FormContainer'
import { useNavigate, useLocation } from 'react-router-dom'
import { useFormik } from 'formik'
import * as Yup from 'yup'

function RegisterScreen() {

    const [message, setMessage] = useState('')

    const { search } = useLocation();

    const dispatch = useDispatch();
    const redirect = new URLSearchParams(search).get("redirect") || '/';

    const navigate = useNavigate();

    const userRegister = useSelector(state => state.userRegister)
    const {error, loading, userInfo} = userRegister

    useEffect(() => {
        if(userInfo){
            navigate(redirect)
        }
    }, [navigate, userInfo, redirect])


    const formik = useFormik({
        initialValues: {
            name: "",
            email: "",
            password: "",
            confirmPassword: "",
        },
    
        validationSchema: Yup.object({
          name: Yup.string()
            .max(20, "Name must be 20 characters or less.")
            .required("Name is required")
            .min(5, 'Name must not be less than 5 characters'),
          email: Yup.string()
            .email("Invalid Email Address")
            .required("Email is required"),
          password: Yup.string()
            .min(7, "Password must be alteast 7 characters")
            .max(20, "Password must be 20 characters or less.")
            .required("Password is required"),
          confirmPassword: Yup.string()
          .min(7, "Password must be alteast 7 characters")
            .max(20, "Password must be 20 characters or less.")
            .required("Password is required"),
        }),
    
        onSubmit: (values) => {
            if(values.password != values.confirmPassword){
                setMessage('passwords do not match')
            }else{
                dispatch(register(values))
            }
      
        },
      });

    

  return (
    <FormContainer>
        <h1>Sign In</h1>
        {message && <Message variant='danger'>{message}</Message>}
        {error && <Message>{error}</Message>}
        {loading && <Loader />}
        <Form onSubmit={formik.handleSubmit}>
        <Form.Group className="mb-3" controlId="name">
            <Form.Label >User Name</Form.Label>
            <Form.Control 
                type="text" 
                name='name'
                placeholder="Enter User Name" 
                onChange={formik.handleChange}
                value={formik.values.name}
                onBlur={formik.handleBlur} />
            <div style={{color: 'red'}}> 
                {formik.touched.name && formik.errors.name
                ? formik.errors.name : null}  
            </div>
            
        </Form.Group>

        <Form.Group className="mb-3" controlId="email">
            <Form.Label >Email</Form.Label>
            <Form.Control 
                type="email" 
                name='email'
                placeholder="Enter Email" 
                onChange={formik.handleChange}
                value={formik.values.email}
                onBlur={formik.handleBlur} />
            <div style={{color: 'red'}}> 
                {formik.touched.email && formik.errors.email
                ? formik.errors.email : null}  
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

        <Form.Group className="mb-3" controlId="confirmPassword">
            <Form.Label>Confirm Password</Form.Label>
            <Form.Control 
                type="password"
                name='confirmPassword' 
                placeholder="Enter Confirm Password" 
                onChange={formik.handleChange}
                value={formik.values.confirmPassword}
                onBlur={formik.handleBlur} />
            <div style={{color: 'red'}}> 
                {formik.touched.confirmPassword && formik.errors.confirmPassword
                ? formik.errors.confirmPassword : null}  
            </div>
        </Form.Group>

        <Button variant="primary" type="submit">
            Submit
        </Button>
        </Form>

        <Row className='py-3'>
            <Col>
                Have an Account ? 
                <Link
                    to={redirect ? `/login?redirect=${redirect}` : '/login'}
                >
                    Sign In
                </Link>
            </Col>
        </Row>
    </FormContainer>
  )
}

export default RegisterScreen