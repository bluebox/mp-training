import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import {editJobPost, resetAddedJobPost} from '../actions/postsActions'
import { useNavigate, useLocation } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux'
import React, {useState, useEffect} from 'react';
import FormContainer from '../components/FormContainer';
import Loader from '../components/Loader';
import Message from '../components/Message';

function EditCompanyJobPost() {

  const jobPostDetails = useSelector(state => state.companyJobPostDetails)
  const {loading, error, companyJob} = jobPostDetails

  const [location, setlocation] = useState(companyJob.location)
  const [jobType, setjobType] = useState(companyJob.job_type)
  const [skillsRequired, setskillsRequired] = useState(companyJob.skills_required)
  const [role, setrole] = useState(companyJob.role)
  const [jobDescription, setjobDescription] = useState(companyJob.job_description)
  const [experience, setexperience] = useState(companyJob.experience)
  const [education, seteducation] = useState(companyJob.education)
  const [department, setdepartment] = useState(companyJob.department)
  const [roleCategory, setroleCategory] = useState(companyJob.role_category)


  const dispatch = useDispatch();

  const navigate = useNavigate();
  const { search } = useLocation()
  const job_post_id = new URLSearchParams(search).get("job_id") || '0';
  console.log(job_post_id)

  const submitHandler = (e) => {
      e.preventDefault()
      dispatch(editJobPost(job_post_id, location, jobType, skillsRequired, role, jobDescription, experience, education, department, roleCategory))
      navigate(`/company-post/${job_post_id}`)


  }


  return (
  <FormContainer>
        {error && <Message>{error}</Message>}
        {loading && <Loader />}
    <Form onSubmit={submitHandler}>
      <Form.Group className="mb-3" controlId="formBasicRole">
        <Form.Label>Job Type</Form.Label>
        <Form.Control type="text" placeholder="Enter Job Type" required value={jobType} onChange={(e) => setjobType(e.target.value)} />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicRole">
        <Form.Label>Role</Form.Label>
        <Form.Control type="text" placeholder="Enter Role Name" required value={role} onChange={(e) => setrole(e.target.value)} />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicJobDescription">
        <Form.Label>Job Description</Form.Label>
        <Form.Control type="text" placeholder="Job Description" required value={jobDescription} onChange={(e) => setjobDescription(e.target.value)} />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicLocation">
        <Form.Label>Location</Form.Label>
        <Form.Control type="text" placeholder="Enter Location" required value={location} onChange={(e) => setlocation(e.target.value)} />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicEducation">
        <Form.Label>Education</Form.Label>
        <Form.Control type="text" placeholder="Enter Education" required value={education} onChange={(e) => seteducation(e.target.value)} />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicExperience">
        <Form.Label>Experience</Form.Label>
        <Form.Control type="text" placeholder="Enter Experience" required value={experience} onChange={(e) => setexperience(e.target.value)} />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicDepartment">
        <Form.Label>Department</Form.Label>
        <Form.Control type="text" placeholder="Enter Department" required value={department} onChange={(e) => setdepartment(e.target.value)} />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicRoleCategory">
        <Form.Label>Role Category</Form.Label>
        <Form.Control type="text" placeholder="Enter Role Category" required value={roleCategory} onChange={(e) => setroleCategory(e.target.value)} />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicSkillsRequired">
        <Form.Label>Skills Required</Form.Label>
        <Form.Control type="text" placeholder="Enter Skills Required" required value={skillsRequired} onChange={(e) => setskillsRequired(e.target.value)} />
      </Form.Group>

      <Button variant="primary" type="submit">
        Submit
      </Button>
    </Form>
    </FormContainer>
  );
}

export default EditCompanyJobPost;