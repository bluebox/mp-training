import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import {addNewJobPostFromCompany, resetAddedJobPost} from '../actions/postsActions'
import { useNavigate } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux'
import React, {useState, useEffect} from 'react';
import FormContainer from '../components/FormContainer';
import Loader from '../components/Loader';
import Message from '../components/Message';

function JobPostForm() {

  const [location, setlocation] = useState('')
  const [jobType, setjobType] = useState('')
  const [skillsRequired, setskillsRequired] = useState('')
  const [role, setrole] = useState('')
  const [jobDescription, setjobDescription] = useState('')
  const [experience, setexperience] = useState('')
  const [education, seteducation] = useState('')
  const [department, setdepartment] = useState('')
  const [roleCategory, setroleCategory] = useState('')


  const dispatch = useDispatch();

  const navigate = useNavigate();


  const newJobPost = useSelector(state => state.addPost)
  const {error, loading, addedJobPost} = newJobPost

  useEffect(() => {
      if(addedJobPost){
        dispatch(resetAddedJobPost())
        navigate('/home-screen')
      }
  }, [dispatch, navigate, addedJobPost])

  const submitHandler = (e) => {
      e.preventDefault()
      dispatch(addNewJobPostFromCompany(location, jobType, skillsRequired, role, jobDescription, experience, education, department, roleCategory))

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

export default JobPostForm;