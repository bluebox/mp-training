import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import {addNewAddress, resetAddedAddress} from '../actions/addressActions'
import { useNavigate } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux'
import React, { useEffect} from 'react';
import FormContainer from '../components/FormContainer';
import Loader from '../components/Loader';
import Message from '../components/Message';
import { useFormik } from "formik";
import * as Yup from "yup";



function NewAddressForm() {

    const dispatch = useDispatch();

    const navigate = useNavigate();


    const newAddress = useSelector(state => state.userAddress)
    const {error, loading, addedAddress} = newAddress

    useEffect(() => {
        if(addedAddress){
            dispatch(resetAddedAddress())
            navigate('/address')
        }
    }, [dispatch, navigate, addedAddress])


    const formik = useFormik({
        initialValues: {
            houseNo: "",
            streetNo: "",
            city: "",
            state: "",
            country: "",
        },
    
        validationSchema: Yup.object({
            houseNo: Yup.string()
              .max(20, "House No must be 20 characters or less.")
              .required("House No is required")
              .min(5, 'House No must not be less than 5 characters'),
            streetNo: Yup.string()
              .max(20, "Street No must be 20 characters or less.")
              .required("Street No is required")
              .min(5, 'Street No must not be less than 5 characters'),
            city: Yup.string()
              .max(20, "City must be 20 characters or less.")
              .required("City is required")
              .min(5, 'City must not be less than 5 characters'),
            state: Yup.string()
              .max(20, "State must be 20 characters or less.")
              .required("State is required")
              .min(5, 'State must not be less than 5 characters'),
            country: Yup.string()
              .max(20, "Country must be 20 characters or less.")
              .required("Country is required")
              .min(5, 'Country must not be less than 5 characters'),
          }),
    
        onSubmit: (values) => {
            dispatch(addNewAddress(values))
      
        },
      });

  return (
    <FormContainer>
        {error && <Message>{error}</Message>}
        {loading && <Loader />}
        <Form onSubmit={formik.handleSubmit}>
        <Form.Group className="mb-3" controlId="houseNo">
            <Form.Label >House No.</Form.Label>
            <Form.Control 
                type="text" 
                name='houseNo'
                placeholder="Enter House No" 
                onChange={formik.handleChange}
                value={formik.values.houseNo}
                onBlur={formik.handleBlur} />
            <div style={{color: 'red'}}> 
                {formik.touched.houseNo && formik.errors.houseNo
                ? formik.errors.houseNo : null}  
            </div>
            
        </Form.Group>

        <Form.Group className="mb-3" controlId="streetNo">
            <Form.Label >Street</Form.Label>
            <Form.Control 
                type="text" 
                name='streetNo'
                placeholder="Enter Street" 
                onChange={formik.handleChange}
                value={formik.values.streetNo}
                onBlur={formik.handleBlur} />
            <div style={{color: 'red'}}> 
                {formik.touched.streetNo && formik.errors.streetNo
                ? formik.errors.streetNo : null}  
            </div>
        </Form.Group>

        <Form.Group className="mb-3" controlId="city">
            <Form.Label>City</Form.Label>
            <Form.Control 
                type="text" 
                name='city'
                placeholder="Enter City" 
                onChange={formik.handleChange}
                value={formik.values.city}
                onBlur={formik.handleBlur} />
            <div style={{color: 'red'}}> 
                {formik.touched.city && formik.errors.city
                ? formik.errors.city : null}  
            </div>
        </Form.Group>

        <Form.Group className="mb-3" controlId="state">
            <Form.Label>State</Form.Label>
            <Form.Control 
                type="text"
                name='state' 
                placeholder="Enter State" 
                onChange={formik.handleChange}
                value={formik.values.state}
                onBlur={formik.handleBlur} />
            <div style={{color: 'red'}}> 
                {formik.touched.state && formik.errors.state
                ? formik.errors.state : null}  
            </div>
        </Form.Group>

        <Form.Group className="mb-3" controlId="country">
            <Form.Label>Country</Form.Label>
            <Form.Control 
                type="text"
                name='country' 
                placeholder="Enter Country" 
                onChange={formik.handleChange}
                value={formik.values.country}
                onBlur={formik.handleBlur} />
            <div style={{color: 'red'}}> 
                {formik.touched.country && formik.errors.country
                ? formik.errors.country : null}  
            </div>
        </Form.Group>
        <Button variant="primary" type="submit">
            Submit
        </Button>
        </Form>
    </FormContainer>
  )
}

export default NewAddressForm