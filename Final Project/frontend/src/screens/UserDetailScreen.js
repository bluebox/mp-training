import React, {useState, useEffect} from 'react'
import { getUserDetails } from '../actions/userActions' 
import {Link, useLocation, useNavigate} from 'react-router-dom'
import {Form, Button, Row, Col} from 'react-bootstrap'
import {useDispatch, useSelector} from 'react-redux'
import Loader from '../components/Loader'
import Message from '../components/Message'
import FormContainer from '../components/FormContainer'
import { useParams } from 'react-router-dom'
import { updateUser } from '../services/userServices'
import { useFormik } from 'formik'
import * as Yup from 'yup'

function UserDetailScreen() {

    const {id} = useParams()
    const {search} = useLocation()
    const navigate = useNavigate()

    const dispatch = useDispatch()

    const redirect = new URLSearchParams(search).get('redirect') || '/'
    const userDetail= useSelector(state => state.userDetails)
    const {loading, error, user} = userDetail

    const [errorUpdate, setErrorUpdate] = useState('')
    const [successUpdate, setSuccessUpdate] = useState(false)  
    const [loadingUpdate, setLoadingUpdate] = useState(false)
    const [isAdmin, setIsAdmin] = useState('')


    const formik = useFormik({
        initialValues:{
            username: user && user.username,
            first_name: user && user.first_name,
            last_name: user && user.last_name,
            email: user && user.email,
        },
        
        validationSchema: Yup.object({
            username: Yup.string()
              .min(3, "username must be 3 characters or more.")
              .max(122, "username should be less than 255 characters.")
              .matches(/^[a-z0-9_]+$/, "username must contain only lowercase letters, underscore and number")
              .required("username is required"),
            first_name: Yup.string()
              .min(3, "first name must be 3 characters or more.")
              .matches(/^[a-zA-Z ]+$/, "username must contain only lowercase letters, underscore and number")
              .max(122, "first name should be less than 122 characters.")
              .required("first name is required"),
            last_name: Yup.string()
              .min(3, "last name must be 3 characters or more.")
              .matches(/^[a-zA-Z ]+$/, "username must contain only lowercase letters, underscore and number")
              .max(122, "last name should be less than 122 characters.")
              .required("last name is required"),
            email: Yup.string()
              .email("Invalid email address")
              .required("email is required"),            
          }),

          onSubmit:values=>{
            updateUser({
                id: user.id, 'username': values.username, 'first_name': values.first_name, 'last_name': values.last_name, 'email': values.email, isAdmin
            }, setLoadingUpdate, setSuccessUpdate, setErrorUpdate)
            
        }
 
    })

    

    useEffect(() =>{
        
        if(successUpdate){
            dispatch(getUserDetails(id))
            navigate(-1)
        }else{
            if(!user.username  || user.id != id || successUpdate){
                dispatch(getUserDetails(id))
            }else{
                formik.initialValues.username = user.username
                formik.initialValues.email = user.email
                formik.initialValues.first_name = user.first_name
                formik.initialValues.last_name = user.last_name
                setIsAdmin(user.isAdmin)
            }
        }
    }, [dispatch, user, id, navigate, successUpdate])

    
  return (
    <div>
        <Link to='/admin/userlist'>
            Go Back
        </Link>
        {loadingUpdate && <Loader/>}
        {errorUpdate && <Message variant='danger'>{errorUpdate}</Message>}
        {loading
        ? (
            <Loader/>
        ): error
        ? (
            <Message variant='danger'>{error}</Message>
        ):(
            <FormContainer>
                <h1>Edit User</h1>

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

                    <Form.Group controlId='is_admin' className="form-child">
                        <Form.Check
                            type='checkbox'
                            label='Admin'
                            checked = {isAdmin}
                            onChange = {(e) => setIsAdmin(e.target.checked)}
                        >                    
                        </Form.Check>
                    </Form.Group>

                    <Button type='submit' variant='danger' className="form-child">Update</Button>
                </Form>
                
            </FormContainer>
        )}
    </div>    
  )
}

export default UserDetailScreen