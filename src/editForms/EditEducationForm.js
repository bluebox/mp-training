import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { useNavigate, useLocation } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux'
import React, {useState, useEffect} from 'react';
import FormContainer from '../components/FormContainer';
import Loader from '../components/Loader';
import Message from '../components/Message';
import { editEducation } from '../actions/educationActions';

function BasicExample() {
  const { search } = useLocation();
  const education_id = new URLSearchParams(search).get("education") || '0';
  const userEducations = useSelector(state => state.getUserEducation) 
  const { loading, error, getEducations} = userEducations
  const founcedEducation = getEducations.find(element => element._id == education_id);

  const [major, setMajor] = useState(founcedEducation.major)
  const [degree, setDegree] = useState(founcedEducation.degree)
  const [university, setUniversity] = useState(founcedEducation.university)
  const [percentage, setPercentage] = useState(founcedEducation.percentage)


  const dispatch = useDispatch();

  const navigate = useNavigate();


  const submitHandler = (e) => {
      e.preventDefault()
      dispatch(editEducation(education_id, major, degree, university, percentage))
      navigate('/education')

  }


  return (
  <FormContainer>
        {error && <Message>{error}</Message>}
        {loading && <Loader />}
    <Form onSubmit={submitHandler}>
      <Form.Group className="mb-3" controlId="formBasiMajor">
        <Form.Label>Major</Form.Label>
        <Form.Control type="text" placeholder="Enter Major" required value={major} onChange={(e) => setMajor(e.target.value)} />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicDegree">
        <Form.Label>Degree</Form.Label>
        <Form.Control type="text" placeholder="Enter Job Location" required value={degree} onChange={(e) => setDegree(e.target.value)} />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicUniversity">
        <Form.Label>University</Form.Label>
        <Form.Control type="text" placeholder="Enter University" required value={university} onChange={(e) => setUniversity(e.target.value)} />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicPercentage">
        <Form.Label>CGPA</Form.Label>
        <Form.Control type="number" placeholder="Enter CGPA" required value={percentage} onChange={(e) => setPercentage(e.target.value)} />
      </Form.Group>

      <Button variant="primary" type="submit">
        Submit
      </Button>
    </Form>
    </FormContainer>
  );
}

export default BasicExample;