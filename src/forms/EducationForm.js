import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { useNavigate, useLocation } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux'
import React, {useState, useEffect} from 'react';
import FormContainer from '../components/FormContainer';
import Loader from '../components/Loader';
import Message from '../components/Message';
import { addNewEducation, resetAddedEducation } from '../actions/educationActions';

function EducationForm() {

  const [major, setMajor] = useState('')
  const [degree, setDegree] = useState('')
  const [university, setUniversity] = useState('')
  const [percentage, setPercentage] = useState('')


  const dispatch = useDispatch();

  const navigate = useNavigate();


  const newEducation = useSelector(state => state.userEducation)
  const {error, loading, addedEducation} = newEducation

  useEffect(() => {
      if(addedEducation){
        dispatch(resetAddedEducation())
        navigate('/education')
      }
  }, [dispatch, navigate, addedEducation])

  const submitHandler = (e) => {
      e.preventDefault()
      dispatch(addNewEducation(major, degree, university, percentage))

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

export default EducationForm;