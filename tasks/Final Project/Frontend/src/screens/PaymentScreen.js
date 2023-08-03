import React, {useState, useEffect} from 'react'
import { Navigate, useLocation , useNavigate} from 'react-router-dom'
import { Form, Button, Col} from 'react-bootstrap' 
import { useDispatch, useSelector } from 'react-redux'
import FormContainer from '../components/FormContainer'
import { savePaymentMethod } from '../actions/wishlistActions'
import CheckoutSteps from '../components/CheckoutSteps'

function PaymentScreen() {

    const wishlist = useSelector(state => state.wishlist)
    const { bookingAddress} = wishlist
    const navigate = useNavigate()
    const dispatch = useDispatch()

    const [paymentMethod, setPaymentMethod] = useState('PayTm')

    if(!bookingAddress.address){
        navigate('/booking')
    }

    const submitHandler = (e) => {
        e.preventDefault()
        dispatch(savePaymentMethod(paymentMethod))
        navigate('/ticketbooked')
        
    }
  return (
    <FormContainer>
        <CheckoutSteps step1 step2 step3 />
        <Form onSubmit={submitHandler}>
            <Form.Group>
                <Form.Label as='legend'>Select Method</Form.Label>
                <Col>
                    <Form.Check
                        type='radio'
                        label='PayTm or Credit Card'
                        id= 'paytm'
                        name='paymentMethod'
                        checked
                        onChange={(e) => setPaymentMethod(e.target.value)}
                    >

                    </Form.Check>
                </Col>
            </Form.Group>
            <Button type='submit' variant='primary'>
                Continue
            </Button>
        </Form>
    </FormContainer>
  )
}

export default PaymentScreen
