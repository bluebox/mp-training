import React, {useState, useEffect, useContext} from 'react'
import { getUserDetails, updateUserProfile } from '../actions/userActions' 
import { useNavigate} from 'react-router-dom'
import {LinkContainer} from 'react-router-bootstrap'
import {Table, Form, Button, Row, Col} from 'react-bootstrap'
import {useDispatch, useSelector} from 'react-redux'
import Loader from '../components/Loader'
import Message from '../components/Message'
import {listMyOrders} from '../actions/orderActions'
import axios from 'axios'
import { UsernameContext } from '../App'
import { updateProfile } from '../services/userServices'
import { useFormik } from 'formik'
import * as Yup from 'yup'

function ProfileScreen() {
    const userDetail= useSelector(state => state.userDetails)
    const {loading, error, user} = userDetail

    const formik = useFormik({
        initialValues:{
            first_name: user && user.first_name,
            last_name: user && user.last_name,
            email: user && user.email,
            password: '',
            confirmedPassword: '',
        },
        onSubmit:values=>{
            if (values.password != values.confirmedPassword){
                setMessage('Passwords do not match')
            }else{
                updateProfile({
                    'email': values.email, 
                    'password': values.password, 
                    'first_name': values.first_name, 
                    'last_name': values.last_name
                }, setUsername, setSuccess, setErrorUpdate)
            }
        },
        validationSchema: Yup.object({
            first_name: Yup.string()
              .min(3, "Name must be 3 characters or more.")
              .required("Name is required"),
            last_name: Yup.string()
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
          }),
 
    })
    const [message, setMessage] = useState('')
    const [errorUpdate, setErrorUpdate] = useState('')
    const [success, setSuccess] = useState(false)
    const navigate = useNavigate()

    const dispatch = useDispatch()

    const userLogin = useSelector(state => state.userLogin)
    const {userInfo} = userLogin


    const {loading: loadingOrders, error: errorOrders, orders} = useSelector(state=>state.orderListMy)
    const [username, setUsername] = useContext(UsernameContext)

    useEffect(() =>{
        
        if (!userInfo){
            navigate('/login')
        }else{
            if(!user || !user.username || success || user.id !== userInfo.id){
                setSuccess(false)
                dispatch(getUserDetails('profile'))
                dispatch(listMyOrders())
            }else{
                formik.initialValues.first_name = user.first_name
                formik.initialValues.last_name = user.last_name
                formik.initialValues.email = user.email
            }
        }
    }, [dispatch, navigate, user, success])


    
  return (
    <Row>
        <Col md={3}>
            <h2>User Profile</h2>
            {message && <Message variant='danger'>{message}</Message>}
            {error && <Message variant='danger'>{error}</Message>}
            {errorUpdate && <Message variant='danger'>{errorUpdate}</Message>}
            {loading && <Loader />}
            <Form onSubmit={formik.handleSubmit}>
            
                <Form.Group controlId='email' className="form-child">
                    <Form.Label>Email 
                    <span className='d-block form-error'>
                        {(formik.touched.email && formik.errors.email)
                        && formik.errors.email}
                    </span>
                    </Form.Label>
                    <Form.Control
                        
                        type='d-block email'
                        placeholder='enter email'
                        value = {formik.values.email}
                        onChange = {formik.handleChange}
                    >                    
                    </Form.Control>
                </Form.Group>

                <Form.Group controlId='first_name' className="form-child">
                    <Form.Label>First Name
                    <span className='form-error d-block'>
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
                        onChange = {formik.handleChange}
                    >                    
                    </Form.Control>
                    
                </Form.Group>

                <Form.Group controlId='confirmedPassword' className="form-child">
                    <Form.Label>Confirm Password</Form.Label>
                    <Form.Control
                        type='password'
                        placeholder='enter password again'
                        onChange = {formik.handleChange}
                    >                    
                    </Form.Control>
                </Form.Group>

                <Button type='submit' variant='danger' className="form-child">Update</Button>
            </Form>
        </Col>
        <Col md={9}>
            <h2>My Orders</h2>
            {loadingOrders ? (
                    <Loader />
                ) : errorOrders ? (
                    <Message variant='danger'>{errorOrders}</Message>
                ) : (
                    <Table striped responsive className='table-sm'>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Date</th>
                                <th>Total</th>
                                <th>Paid</th>
                                <th>Delivered</th>
                                <th></th>
                            </tr>
                        </thead>

                        <tbody>
                            {orders.map(order => (
                                <tr key={order.id}>
                                    <td>{order.id}</td>
                                    <td>{order.createdAt.substring(0, 10)}</td>
                                    <td>${order.totalPrice}</td>
                                    <td>{order.isPaid ? order.paidAt.substring(0, 10) : (
                                        <i className='fas fa-times' style={{ color: 'red' }}></i>
                                    )}</td>
                                    <td>
                                        <LinkContainer to={`/order/${order.id}`}>
                                            <Button className='btn-sm'>Details</Button>
                                        </LinkContainer>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </Table>
                )
            }
        </Col>
    </Row>
  )
}

export default ProfileScreen