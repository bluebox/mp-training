import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { useNavigate, useLocation } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux'
import React, {useState, useEffect} from 'react';
import FormContainer from '../components/FormContainer';
import Loader from '../components/Loader';
import Message from '../components/Message';
import { editEducation } from '../actions/educationActions';
import { useFormik } from "formik";
import * as Yup from "yup";


function NewEditEducationForm() {

    const { search } = useLocation();
    const education_id = new URLSearchParams(search).get("education") || '0';
    const userEducations = useSelector(state => state.getUserEducation) 
    const { loading, error, getEducations} = userEducations
    const founcedEducation = getEducations.find(element => element._id == education_id);

    const dispatch = useDispatch();

    const navigate = useNavigate();

    const formik = useFormik({
        initialValues: {
            major: founcedEducation.major,
            degree: founcedEducation.degree,
            university: founcedEducation.university,
            percentage: founcedEducation.percentage,
        },
    
        validationSchema: Yup.object({
          major: Yup.string()
            .max(20, "Major must be 20 characters or less.")
            .required("Major is required")
            .min(5, 'House No must not be less than 5 characters'),
          degree: Yup.string()
            .max(20, "Degree must be 20 characters or less.")
            .required("Degree is required")
            .min(5, 'Degree must not be less than 5 characters'),
          university: Yup.string()
            .max(20, "University must be 20 characters or less.")
            .required("University is required")
            .min(5, 'University must not be less than 5 characters'),
          percentage: Yup.number()
            .max(10, "Percentage not greater than 10")
            .min(0, 'Percentage must not be less than zero')
            .required("Percentage is required"),
        }),
    
        onSubmit: (values) => {
            dispatch(editEducation(education_id, values))
            navigate('/education')
        },
      });

  return (
    <FormContainer>
        {error && <Message>{error}</Message>}
        {loading && <Loader />}
        <Form onSubmit={formik.handleSubmit}>
        <Form.Group className="mb-3" controlId="major">
            <Form.Label >Major </Form.Label>
            <Form.Control 
                type="text" 
                name='major'
                placeholder="Enter Major" 
                onChange={formik.handleChange}
                value={formik.values.major}
                onBlur={formik.handleBlur} />
            <div style={{color: 'red'}}> 
                {formik.touched.major && formik.errors.major
                ? formik.errors.major : null}  
            </div>
            
        </Form.Group>

        <Form.Group className="mb-3" controlId="degree">
            <Form.Label >Degree</Form.Label>
            <Form.Control 
                type="text" 
                name='degree'
                placeholder="Enter Degree" 
                onChange={formik.handleChange}
                value={formik.values.degree}
                onBlur={formik.handleBlur} />
            <div style={{color: 'red'}}> 
                {formik.touched.degree && formik.errors.degree
                ? formik.errors.degree : null}  
            </div>
        </Form.Group>

        <Form.Group className="mb-3" controlId="university">
            <Form.Label>Univeristy</Form.Label>
            <Form.Control 
                type="text" 
                name='university'
                placeholder="Enter Univeristy" 
                onChange={formik.handleChange}
                value={formik.values.university}
                onBlur={formik.handleBlur} />
            <div style={{color: 'red'}}> 
                {formik.touched.university && formik.errors.university
                ? formik.errors.university : null}  
            </div>
        </Form.Group>

        <Form.Group className="mb-3" controlId="percentage">
            <Form.Label>CGPA</Form.Label>
            <Form.Control 
                type="number"
                name='percentage' 
                placeholder="Enter CGPA" 
                onChange={formik.handleChange}
                value={formik.values.percentage}
                onBlur={formik.handleBlur} />
            <div style={{color: 'red'}}> 
                {formik.touched.percentage && formik.errors.percentage
                ? formik.errors.percentage : null}  
            </div>
        </Form.Group>
        <Button variant="primary" type="submit">
            Submit
        </Button>
        </Form>
    </FormContainer>
  )
}

export default NewEditEducationForm