import React, {useEffect, useState} from 'react'
import FormContainer from '../components/FormContainer'
import { Form, Button } from 'react-bootstrap'
import { saveShippingAddress } from '../services/orderServices'
import { useNavigate } from 'react-router-dom'
import CheckoutSteps from '../constants/CheckoutSteps'
import { useFormik } from 'formik'
import * as Yup from 'yup'

function ShippingScreen() {
    const [fetch, setFetch] = useState(false)
    const formik = useFormik({
        initialValues:{
            locality: '',
            city: '',
            postalCode: '',
            state: ''
        },
        
        validationSchema: Yup.object({
            locality: Yup.string()
              .min(3, "Locality must be 3 characters or more.")
              .max(255, "Locality can have maximum 255 characters")
              .required("Locality is required"),
            city: Yup.string()
              .min(3, "City must be 3 characters or more.")
              .max(255, "City can have maximum 255 characters")
              .required("City is required"),
            postalCode: Yup.string()
              .min(6, "Postal code must be of 6 digit")
              .max(6, "Postal code must be of 6 digit")
              .required("Postal code is required"),
            state: Yup.string()
              .min(3, "State must be 3 characters or more.")
              .max(255, "State can have maximum 255 characters")
              .required("State is required"),
          }),

        onSubmit:values=>{
            saveShippingAddress({'locality': values.locality, 'city': values.city, 'postalCode': values.postalCode,'state': values.state})
            navigate('/payment')
        },
 
    })

    const navigate = useNavigate()


    useEffect(() => {
        if (localStorage.getItem('shippingAddress') != null) {
            const shippingAddress = JSON.parse(localStorage.getItem('shippingAddress'));
            formik.initialValues.locality = shippingAddress.locality;
            formik.initialValues.city = shippingAddress.city;
            formik.initialValues.postalCode = shippingAddress.postalCode;
            formik.initialValues.state = shippingAddress.state;
            setFetch(true);
        }
        }, [fetch]);
  return (
    <>
    <CheckoutSteps step1 step2/>
    <FormContainer>
        <h1>Shipping</h1>
        <Form onSubmit={formik.handleSubmit}>
            <Form.Group controlId='locality' className="form-child">
                <Form.Label>Locality</Form.Label>
                <Form.Control
                    required
                    type='text'
                    value={formik.values.locality}
                    placeholder='Enter Locality'
                    onChange={formik.handleChange}
                >
                </Form.Control>
            </Form.Group>
            <Form.Group controlId='city' className="form-child">
                <Form.Label>City</Form.Label>
                <Form.Control
                    required
                    type='text'
                    placeholder='Enter city'
                    value={formik.values.city}
                    onChange={formik.handleChange}
                >
                </Form.Control>
            </Form.Group>

            <Form.Group controlId='state' className="form-child">
                <Form.Label>State</Form.Label>
                <Form.Control
                    required
                    type='text'
                    placeholder='Enter State'
                    value={formik.values.state}
                    onChange={formik.handleChange}
                ></Form.Control>
            </Form.Group>

            <Form.Group controlId='postalCode' className="form-child">
                <Form.Label>Postal Code</Form.Label>
                <Form.Control
                    required
                    type='text'
                    placeholder="Enter postal code"
                    value={formik.values.postalCode}
                    onChange={formik.handleChange}
                >

                </Form.Control>
            </Form.Group>
            

            <Button className="form-child" type='submit' variant='primary'>Continue</Button>
        </Form>
    </FormContainer>
    </>
  )
}

export default ShippingScreen