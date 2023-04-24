import React, {useState, useEffect} from 'react'
import {Link} from 'react-router-dom'
import { Form, Button, Row, Col} from 'react-bootstrap'
import Message from '../components/Message'
import Loader from '../components/Loader'
import { useDispatch, useSelector } from 'react-redux'
import { registerCompany } from '../actions/companyActions'
import FormContainer from '../components/FormContainer'
import { useNavigate, useLocation } from 'react-router-dom'
import { useFormik } from 'formik'
import * as Yup from 'yup'

function EmployerRegisterScreen() {

    const [message, setMessage] = useState('')


    const dispatch = useDispatch();

    const navigate = useNavigate();

    const companyRegister = useSelector(state => state.companyRegister)
    const {error, loading, companyInfo} = companyRegister

    useEffect(() => {
        if(companyInfo){
            navigate('/home-screen')
        }
    }, [navigate, companyInfo])


    const formik = useFormik({
        initialValues: {
            companyName: "",
            email: "",
            password: "",
            confirmPassword: "",
            industryType: "",
            description: "",
            established: "",
        },
    
        validationSchema: Yup.object({
          companyName: Yup.string()
            .max(20, "Company Name must be 20 characters or less.")
            .required("Company Name is required")
            .min(5, 'Company Name must not be less than 5 characters'),
          email: Yup.string()
            .email("Invalid Email Address")
            .required("Street No is required"),
          password: Yup.string()
          .min(7, "Password must be alteast 7 characters")
            .max(20, "Password must be 20 characters or less.")
            .required("Password is required"),
          confirmPassword: Yup.string()
          .min(7, "Password must be alteast 7 characters")
            .max(20, "Password must be 20 characters or less.")
            .required("Password is required"),
          industryType: Yup.string()
            .max(20, "Industry Type must be 20 characters or less.")
            .required("Industry Type is required")
            .min(5, 'Industry Type must not be less than 5 characters'),
          description: Yup.string()
            .max(200, "description must be 200 characters or less.")
            .required("description is required")
            .min(50, 'Description must not be less than 50 characters'),
          established: Yup.date()
            .required("Establishment Date is required"),
        }),
    
        onSubmit: (values) => {
            if(values.password != values.confirmPassword){
                setMessage('passwords do not match')
            }else{
                dispatch(registerCompany(values))
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
        <Form.Group className="mb-3" controlId="companyName">
            <Form.Label >Company Name</Form.Label>
            <Form.Control 
                type="text" 
                name='companyName'
                placeholder="Enter Company Name" 
                onChange={formik.handleChange}
                value={formik.values.companyName}
                onBlur={formik.handleBlur} />
            <div style={{color: 'red'}}> 
                {formik.touched.companyName && formik.errors.companyName
                ? formik.errors.companyName : null}  
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

        <Form.Group className="mb-3" controlId="industryType">
            <Form.Label >Industry Type</Form.Label>
            <Form.Control 
                type="text" 
                name='industryType'
                placeholder="Enter Industry Type" 
                onChange={formik.handleChange}
                value={formik.values.industryType}
                onBlur={formik.handleBlur} />
            <div style={{color: 'red'}}> 
                {formik.touched.industryType && formik.errors.industryType
                ? formik.errors.industryType : null}  
            </div>
            
        </Form.Group>

        <Form.Group className="mb-3" controlId="description">
            <Form.Label >Description</Form.Label>
            <Form.Control 
                name='description'
                as='textarea'
                rows={3}
                placeholder="Enter Description" 
                onChange={formik.handleChange}
                value={formik.values.description}
                onBlur={formik.handleBlur} />
            <div style={{color: 'red'}}> 
                {formik.touched.description && formik.errors.description
                ? formik.errors.description : null}  
            </div>
            
        </Form.Group>

        <Form.Group className="mb-3" controlId="established">
            <Form.Label >Established</Form.Label>
            <Form.Control 
                type="date" 
                name='established'
                placeholder="Established On" 
                onChange={formik.handleChange}
                value={formik.values.established}
                onBlur={formik.handleBlur} />
            <div style={{color: 'red'}}> 
                {formik.touched.established && formik.errors.established
                ? formik.errors.established : null}  
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
                    to={'/login'}
                >
                    Sign In
                </Link>
            </Col>
        </Row>
    </FormContainer>
  )
}

export default EmployerRegisterScreen