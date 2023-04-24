import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import {editJobPost, resetAddedJobPost} from '../actions/postsActions'
import { useNavigate, useLocation } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux'
import React, {useState, useEffect} from 'react';
import FormContainer from '../components/FormContainer';
import Loader from '../components/Loader';
import Message from '../components/Message';
import { useFormik } from "formik";
import * as Yup from "yup";



function NewEditCompanyJobPost() {

    const jobPostDetails = useSelector(state => state.companyJobPostDetails)
    const {loading, error, companyJob} = jobPostDetails
    const dispatch = useDispatch();

    const navigate = useNavigate();
    const { search } = useLocation()
    const job_post_id = new URLSearchParams(search).get("job_id") || '0';


    const formik = useFormik({
        initialValues: {
            jobType: companyJob.job_type,
            role: companyJob.role,
            jobDescription: companyJob.job_description,
            location: companyJob.location,
            education: companyJob.education,
            experience: companyJob.experience,
            department: companyJob.department,
            roleCategory: companyJob.role_category,
            skillsRequired: companyJob.skills_required,
        },
    
        validationSchema: Yup.object({
            jobType: Yup.string()
            .max(20, "Job Type must be 20 characters or less.")
            .required("Job Type is required")
            .min(5, "Job Type must be at least 5 characters"),
            role: Yup.string()
            .max(20, "Role must be 20 characters or less.")
            .required("Role is required")
            .min(5, "Role must be at least 5 characters"),
            jobDescription: Yup.string()
            .max(200, "Job description must be 200 characters or less.")
            .min(50, 'Job description must not be less than 50 characters')
            .required("Job description is required"),
            location: Yup.string()
            .max(20, "Job Location must be 20 characters or less.")
            .required("Job Location is required")
            .min(5, "Location must be at least 5 characters"),
            education: Yup.string()
            .max(20, "Education must be 20 characters or less.")
            .required("Education is required")
            .min(5, "Education must be at least 5 characters"),
            experience: Yup.number()
            .min(0, "Experience should not be less than zero")
            .integer('Experience must be integer value')
            .required("Experience is required"),
            department: Yup.string()
            .max(20, "Department must be 20 characters or less.")
            .required("Department is required")
            .min(5, "Department must be at least 5 characters"),
            roleCategory: Yup.string()
            .max(20, "Role Category must be 20 characters or less.")
            .required("Role Category is required")
            .min(5, "Role Category must be at least 5 characters"),
            skillsRequired: Yup.string()
            .max(40, "Skills Required must be 40 characters or less.")
            .required("Skills Required is required")
            .min(5, "Skills Required must be at least 5 characters"),
        }),
    
        onSubmit: (values) => {
            dispatch(editJobPost(job_post_id, values))
            navigate(`/company-post/${job_post_id}`)
      
        },
      });

  return (
    <FormContainer>
        {error && <Message>{error}</Message>}
        {loading && <Loader />}
        <Form onSubmit={formik.handleSubmit}>
        <Form.Group className="mb-3" controlId="jobType">
            <Form.Label >Job Type</Form.Label>
            <Form.Control 
                type="text" 
                name='jobType'
                placeholder="Enter Job Type" 
                onChange={formik.handleChange}
                value={formik.values.jobType}
                onBlur={formik.handleBlur} />
            <div style={{color: 'red'}}> 
                {formik.touched.jobType && formik.errors.jobType
                ? formik.errors.jobType : null}  
            </div>
            
        </Form.Group>

        <Form.Group className="mb-3" controlId="role">
            <Form.Label >Job Role</Form.Label>
            <Form.Control 
                type="text" 
                name='role'
                placeholder="Enter Job Role" 
                onChange={formik.handleChange}
                value={formik.values.role}
                onBlur={formik.handleBlur} />
            <div style={{color: 'red'}}> 
                {formik.touched.role && formik.errors.role
                ? formik.errors.role : null}  
            </div>
        </Form.Group>

        <Form.Group className="mb-3" controlId="jobDescription">
            <Form.Label>Job Description</Form.Label>
            <Form.Control
                as='textarea'
                rows={3} 
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

        <Form.Group className="mb-3" controlId="location">
            <Form.Label>Job Location</Form.Label>
            <Form.Control 
                type="text"
                name='location' 
                placeholder="Enter Job Location" 
                onChange={formik.handleChange}
                value={formik.values.location}
                onBlur={formik.handleBlur} />
            <div style={{color: 'red'}}> 
                {formik.touched.location && formik.errors.location
                ? formik.errors.location : null}  
            </div>
        </Form.Group>

        <Form.Group className="mb-3" controlId="education">
            <Form.Label>Education</Form.Label>
            <Form.Control 
                type="text"
                name='education' 
                placeholder="Enter Education" 
                onChange={formik.handleChange}
                value={formik.values.education}
                onBlur={formik.handleBlur} />
            <div style={{color: 'red'}}> 
                {formik.touched.education && formik.errors.education
                ? formik.errors.education : null}  
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

        <Form.Group className="mb-3" controlId="department">
            <Form.Label>Department</Form.Label>
            <Form.Control 
                type="text"
                name='department' 
                placeholder="Enter Department" 
                onChange={formik.handleChange}
                value={formik.values.department}
                onBlur={formik.handleBlur} />
            <div style={{color: 'red'}}> 
                {formik.touched.department && formik.errors.department
                ? formik.errors.department : null}  
            </div>
        </Form.Group>

        <Form.Group className="mb-3" controlId="roleCategory">
            <Form.Label>Role Category</Form.Label>
            <Form.Control 
                type="text"
                name='roleCategory' 
                placeholder="Enter Role Category" 
                onChange={formik.handleChange}
                value={formik.values.roleCategory}
                onBlur={formik.handleBlur} />
            <div style={{color: 'red'}}> 
                {formik.touched.roleCategory && formik.errors.roleCategory
                ? formik.errors.roleCategory : null}  
            </div>
        </Form.Group>

        <Form.Group className="mb-3" controlId="skillsRequired">
            <Form.Label>Skills Required</Form.Label>
            <Form.Control 
                type="text"
                name='skillsRequired' 
                placeholder="Enter Skills Required" 
                onChange={formik.handleChange}
                value={formik.values.skillsRequired}
                onBlur={formik.handleBlur} />
            <div style={{color: 'red'}}> 
                {formik.touched.skillsRequired && formik.errors.skillsRequired
                ? formik.errors.skillsRequired : null}  
            </div>
        </Form.Group>

        <Button variant="primary" type="submit">
            Submit
        </Button>
        </Form>
    </FormContainer>
  )
}

export default NewEditCompanyJobPost