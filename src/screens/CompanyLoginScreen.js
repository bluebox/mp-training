import React, {useState, useEffect} from 'react'
import {Link} from 'react-router-dom'
import { Form, Button, Row, Col} from 'react-bootstrap'
import Message from '../components/Message'
import Loader from '../components/Loader'
import { useDispatch, useSelector } from 'react-redux'
import { loginCompany } from '../actions/companyActions'
import FormContainer from '../components/FormContainer'
import { useNavigate, useLocation } from 'react-router-dom'

function LoginScreen() {

    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const companyLogin = useSelector(state => state.companyLogin)
    const {error, loading, companyInfo} = companyLogin

    const submitHandler = (e) => {
        e.preventDefault()
        dispatch(loginCompany(email, password))
    }

    useEffect(() => {
        if(companyInfo){
            navigate('/home-screen')
        }
    }, [navigate, companyInfo])


  return (
    <FormContainer>
        <h1>Sign In</h1>
        {error && <Message>{error}</Message>}
        {loading && <Loader />}
        <Form onSubmit={submitHandler}>
            <Form.Group controlId='email'>
                <Form.Label>Email Address</Form.Label>
                <Form.Control
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

            <Button type='submit' variant='primary'>Sign In</Button>
        </Form>

        <Row className='py-3'>
            <Col>
                New Employer ? 
                <Link
                    to={'/register-employer'}
                >
                    Register
                </Link>
            </Col>
        </Row>
    </FormContainer>
  )
}

export default LoginScreen