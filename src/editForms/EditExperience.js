import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { useNavigate, useLocation } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux'
import React, {useState, useEffect} from 'react';
import FormContainer from '../components/FormContainer';
import Loader from '../components/Loader';
import Message from '../components/Message';
import { editExperience } from '../actions/experienceActions';

function BasicExample() {
  const { search } = useLocation();
  const experience_id = new URLSearchParams(search).get("experience") || '0';
  const userExperiences = useSelector(state => state.getUserExperience) 
  const { loading, error, getExperiences} = userExperiences
  const foundedExperience = getExperiences.find(element => element._id == experience_id);

  const [companyName, setCompanyName] = useState(foundedExperience.company_name)
  const [jobLocation, setJobLocation] = useState(foundedExperience.job_location)
  const [jobDescription, setJobDescription] = useState(foundedExperience.description)
  const [experience, setExperience] = useState(foundedExperience.experience)


  const dispatch = useDispatch();

  const navigate = useNavigate();


  const submitHandler = (e) => {
      e.preventDefault()
      dispatch(editExperience(experience_id, companyName, jobLocation, jobDescription, experience))
      navigate('/experience')

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

export default BasicExample;