import React, {useState, useEffect} from 'react'
import { Navigate, useLocation , useNavigate} from 'react-router-dom'
import { Form, Button} from 'react-bootstrap'
import { useDispatch, useSelector } from 'react-redux'
import FormContainer from '../components/FormContainer'
import { saveBookingAddress } from '../actions/wishlistActions'
import CheckoutSteps from '../components/CheckoutSteps'

function BookingScreen() {

    const wishlist = useSelector(state => state.wishlist)
    const { bookingAddress } = wishlist

    const navigate = useNavigate()

    const dispatch = useDispatch()

    const [address, setAddress] = useState(bookingAddress.address)
    const [city, setCity] = useState(bookingAddress.city)
    const [postalCode, setPostalCode] = useState(bookingAddress.postalCode )
    const [country, setCountry] = useState(bookingAddress.country)

    const submitHandler = (e) => {
        e.preventDefault()
        dispatch(saveBookingAddress({ address, city, postalCode, country}))
        navigate('/payment')
    }
  return (
    <FormContainer>
        <CheckoutSteps step1 step2/>
        <h1>booking</h1>
        <Form onSubmit={submitHandler}>

            <Form.Group controlId='address'>
                <Form.Label>Address</Form.Label>
                <Form.Control
                required
                type='text'
                placeholder='Enter address'
                value={address ? address : ''}
                onChange={(e) => setAddress(e.target.value)}
                >
                </Form.Control>
            </Form.Group>

            <Form.Group controlId='city'>
                <Form.Label>City</Form.Label>
                <Form.Control
                required
                type='text'
                placeholder='Enter city'
                value={city ? city : ''}
                onChange={(e) => setCity(e.target.value)}
                >
                </Form.Control>
            </Form.Group>

            <Form.Group controlId='postalCode'>
                <Form.Label>Postal Code</Form.Label>
                <Form.Control
                required
                type='text'
                placeholder='Enter postal code'
                value={postalCode ? postalCode : ''}
                onChange={(e) => setPostalCode(e.target.value)}
                >
                </Form.Control>
            </Form.Group>

            <Form.Group controlId='country'>
                <Form.Label>Country</Form.Label>
                <Form.Control
                required
                type='text'
                placeholder='Enter country'
                value={country ? country : ''}
                onChange={(e) => setCountry(e.target.value)}
                >
                </Form.Control>
            </Form.Group>

            <Button type='submit' variant='primary'>
                Continue
            </Button>

        </Form>
    </FormContainer>
  )
}

export default BookingScreen
