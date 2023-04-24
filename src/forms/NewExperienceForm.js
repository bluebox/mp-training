import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { useNavigate, useLocation } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux'
import React, {useState, useEffect} from 'react';
import FormContainer from '../components/FormContainer';
import Loader from '../components/Loader';
import Message from '../components/Message';
import { addNewExperience, resetAddedExperience } from '../actions/experienceActions';
import { useFormik } from "formik";
import * as Yup from "yup";



function NewExperienceForm() {
    const dispatch = useDispatch();

    const navigate = useNavigate();


    const newExperience = useSelector(state => state.userExperience)
    const {error, loading, addedExperience} = newExperience

    useEffect(() => {
        if(addedExperience){
            dispatch(resetAddedExperience())
            navigate('/experience')
        }
    }, [dispatch, navigate, addedExperience])


    const formik = useFormik({
        initialValues: {
            companyName: "",
            jobLocation: "",
            jobDescription: "",
            experience: "",
        },
    
        validationSchema: Yup.object({
            companyName: Yup.string()
            .max(20, "Company Name must be 20 characters or less.")
            .required("Company Name is required")
            .min(5, 'Company Name must not be less than 5 characters'),
            jobLocation: Yup.string()
            .max(20, "Job Location must be 20 characters or less.")
            .required("Job Location is required")
            .min(5, 'Job Location must not be less than 5 characters'),
            jobDescription: Yup.string()
            .max(500, "Job Description must be 500 characters or less.")
            .required("Job Description is required")
            .min(50, 'Job Description must not be less than 50 characters'),
            experience: Yup.number()
            .max(10, "Experience not greater than 10")
            .min(0, 'Experience must not be less than zero')
            .required("Experience is required"),
        }),
    
        onSubmit: (values) => {
            dispatch(addNewExperience(values))
      
        },
      });


  return (
    <FormContainer>
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

        <Form.Group className="mb-3" controlId="jobLocation">
            <Form.Label >Job Location</Form.Label>
            <Form.Control 
                type="text" 
                name='jobLocation'
                placeholder="Enter Job Location" 
                onChange={formik.handleChange}
                value={formik.values.jobLocation}
                onBlur={formik.handleBlur} />
            <div style={{color: 'red'}}> 
                {formik.touched.jobLocation && formik.errors.jobLocation
                ? formik.errors.jobLocation : null}  
            </div>
        </Form.Group>

        <Form.Group className="mb-3" controlId="jobDescription">
            <Form.Label>Job Description</Form.Label>
            <Form.Control 
                as='textarea' rows={3}
                name='jobDescription'
                placeholder="Enter Job Description" 
                onChange={formik.handleChange}
                value={formik.values.jobDescription}
                onBlur={formik.handleBlur} />
            <div style={{color: 'red'}}> 
                {formik.touched.jobDescription && formik.errors.jobDescription
                ? formik.errors.jobDescription : null}  
            </div>
        </Form.Group>

        <Form.Group className="mb-3" controlId="experience">
            <Form.Label>Experience</Form.Label>
            <Form.Control 
                type="number"
                name='experience' 
                placeholder="Enter Experience" 
                onChange={formik.handleChange}
                value={formik.values.experience}
                onBlur={formik.handleBlur} />
            <div style={{color: 'red'}}> 
                {formik.touched.experience && formik.errors.experience
                ? formik.errors.experience : null}  
            </div>
        </Form.Group>

        <Button variant="primary" type="submit">
            Submit
        </Button>
        </Form>
    </FormContainer>
  )
}

export default NewExperienceForm