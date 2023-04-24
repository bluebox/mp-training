import React, {useState, useEffect} from 'react'
import { Form, Button, Row, Col, Container} from 'react-bootstrap'
import Message from '../components/Message'
import Loader from '../components/Loader'
import { useDispatch, useSelector } from 'react-redux'
import { getCompanyDetails, updateCompanyProfile } from '../actions/companyActions'
import { useNavigate } from 'react-router-dom'
import { COMPANY_UPDATE_PROFILE_RESET } from '../constants/companyConstants'


function CompanyProfileScreen() {

    const [email, setEmail] = useState('')
    const [name, setName] = useState('')
    const [password, setPassword] = useState('')
    const [confirmPassword, setConfirmPassword] = useState('')
    const [message, setMessage] = useState('')


    const dispatch = useDispatch();

    const navigate = useNavigate();

    const userDetails = useSelector(state => state.companyDetails)
    const {error, loading, company} = userDetails

    const userLogin = useSelector(state => state.companyLogin)
    const {companyInfo} = userLogin

    const userUpdateProfile = useSelector(state => state.companyUpdateProfile)
    const {success} = userUpdateProfile

    useEffect(() => {
        if(!companyInfo){
            navigate('/login')
        }else{
            if(!company || !company.name || success ){
                dispatch({type: COMPANY_UPDATE_PROFILE_RESET })
                dispatch(getCompanyDetails('profile'))
            }else{
                setName(company.name)
                setEmail(company.email)
            }
        }
    }, [dispatch, navigate, companyInfo, company, success])

    const submitHandler = (e) => {
        e.preventDefault()
        if(password != confirmPassword){
            setMessage('passwords do not match')
        }else{
            dispatch(updateCompanyProfile({
                'id': company._id,
                'name': name,
                'email': email,
                'password': password
            }))
            setMessage('')
        }
    }

    
  return (
    <Container>
        <Col md={3}>
            <h2>Company Profile</h2>

            {message && <Message variant='danger'>{message}</Message>}
            {error && <Message>{error}</Message>}
            {loading && <Loader />}
            <Form onSubmit={submitHandler}>

                <Form.Group controlId='name'>
                    <Form.Label>User Name</Form.Label>
                    <Form.Control
                        required
                        type='name'
                        placeholder='Enter Name'
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                    >
                    </Form.Control>
                </Form.Group>

                <Form.Group controlId='email'>
                    <Form.Label>Email Address</Form.Label>
                    <Form.Control
                        required
                        type='email'
                        placeholder='Enter Email'
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    >
                    </Form.Control>
                </Form.Group>

                <Form.Group controlId='password'>
                    <Form.Label>Password</Form.Label>
                    <Form.Control
                        
                        type='password'
                        placeholder='Enter Password'
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    >
                    </Form.Control>
                </Form.Group>


                <Form.Group controlId='passwordConfirm'>
                    <Form.Label>Confirm Password</Form.Label>
                    <Form.Control
                        
                        type='password'
                        placeholder='Confirm Password'
                        value={confirmPassword}
                        onChange={(e) => setConfirmPassword(e.target.value)}
                    >
                    </Form.Control>
                </Form.Group>


                <Button type='submit' variant='primary'>Update</Button>
            </Form>
        </Col>
    </Container>
  )
}

export default CompanyProfileScreen