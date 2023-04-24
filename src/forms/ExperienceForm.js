import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { useNavigate, useLocation } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux'
import React, {useState, useEffect} from 'react';
import FormContainer from '../components/FormContainer';
import Loader from '../components/Loader';
import Message from '../components/Message';
import { addNewExperience, resetAddedExperience } from '../actions/experienceActions';

function ExperienceForm() {

  const [companyName, setCompanyName] = useState('')
  const [jobLocation, setJobLocation] = useState('')
  const [jobDescription, setJobDescription] = useState('')
  const [experience, setExperience] = useState('')


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

  const submitHandler = (e) => {
      e.preventDefault()
      dispatch(addNewExperience(companyName, jobLocation, jobDescription, experience))

  }


  return (
  <FormContainer>
        {error && <Message>{error}</Message>}
        {loading && <Loader />}
    <Form onSubmit={submitHandler}>
      <Form.Group className="mb-3" controlId="formBasicCompanyName">
        <Form.Label>Company Name</Form.Label>
        <Form.Control type="text" placeholder="Enter Company Name" required value={companyName} onChange={(e) => setCompanyName(e.target.value)} />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicJobLocation">
        <Form.Label>Job Location</Form.Label>
        <Form.Control type="text" placeholder="Enter Job Location" required value={jobLocation} onChange={(e) => setJobLocation(e.target.value)} />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicJobDescription">
        <Form.Label>Job Description</Form.Label>
        <Form.Control type="text" placeholder="Enter Description" required value={jobDescription} onChange={(e) => setJobDescription(e.target.value)} />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicExperience">
        <Form.Label>Experience</Form.Label>
        <Form.Control type="text" placeholder="Enter Experience in yrs" required value={experience} onChange={(e) => setExperience(e.target.value)} />
      </Form.Group>

      <Button variant="primary" type="submit">
        Submit
      </Button>
    </Form>
    </FormContainer>
  );
}

export default ExperienceForm;