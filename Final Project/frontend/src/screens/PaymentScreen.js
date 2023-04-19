import React, {useState, useEffect} from 'react'
import FormContainer from '../components/FormContainer'
import { Form, Button, Col, Row } from 'react-bootstrap'
import { useDispatch, useSelector } from 'react-redux'
import { useNavigate } from 'react-router-dom'
import CheckoutSteps from '../constants/CheckoutSteps'
import { savePaymentMethod } from '../actions/cartAction'


function PaymentScreen() {

    const navigate = useNavigate()

    const [paymentMethod, setPaymentMethod] = useState('UPI')


    const submitHandler = (e) => {
        e.preventDefault()
        localStorage.setItem('paymentMethod', JSON.stringify(paymentMethod))
        navigate('/placeorder')
    }
    useEffect(()=>{

    }, [])
  return (
    <><CheckoutSteps step1 step2 step3/>
    <FormContainer>
        <Form onSubmit={submitHandler}>

            <Form.Group className="form-child" >
                <Form.Label as='legend'>Select method</Form.Label>
                <Row>
                    <Form.Check type="radio" id="upi" name="payment_method" label="UPI" defaultChecked/>
                </Row>

                <Row>
                    <Form.Check type="radio" id="credit_card" name="payment_method" label="Credit Card"/>
                </Row>
                
                <Row>
                    <Form.Check type="radio" id="debit_card" name="payment_method" label="Debit Card"/>
                </Row>

            </Form.Group>
            <Button type="submit" variant="success" className="form-child">
                Continue
            </Button>
        </Form>
    </FormContainer>
    </>
  )
}

export default PaymentScreen