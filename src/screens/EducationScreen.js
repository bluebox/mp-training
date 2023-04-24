import { Link } from 'react-router-dom'
import Button from 'react-bootstrap/Button';
import { useNavigate } from 'react-router-dom';
import Message from '../components/Message';
import { getEducationDetails } from '../actions/educationActions';
import Education from '../components/Education'
import { useDispatch, useSelector } from 'react-redux';
import React, { useEffect } from 'react';
import Loader from '../components/Loader';
import { Row, Col, Container, Nav, Navbar } from 'react-bootstrap';




function EducationScreen() {
    const dispatch = useDispatch()
    const educationList = useSelector(state => state.getUserEducation)

    const {error, loading, getEducations} = educationList

    const educationDeleteStatus = useSelector(state => state.delEducation)
    const {deleting , educationDeleted} = educationDeleteStatus

    useEffect(() => {
        dispatch(getEducationDetails())
    }, [dispatch, educationDeleted,])



    const navigate = useNavigate()
    const addEducation = () => {
        navigate('/addEducation?added=false')
    }

  return (
    <Container>
      <Navbar bg="light" variant="light" className='details-nav-bar'>
        <Container>
          <Nav className="me-auto">
            <Link to="/address" className='personal-information'>Address</Link>
            <Link to="/experience" className='personal-information'>Experience</Link>
            <Link to="/education" className='personal-information'>Education</Link>
            <Link to="/skills" className='personal-information'>Skills</Link>
          </Nav>
        </Container>
      </Navbar>

        {loading ? <Loader/>
          :deleting ? <Loader/>
          : error ? <Message variant='danger'>{error}</Message>
            : <Col>
                {getEducations.map((education, index) => (
                  <Row key={education.id} sm={12} md={6} lg={4} xl={3} >
                      <Education education={education} index={index + 1} />
                  </Row>
            ))}
        </Col>
        }  
    <div className='my-3'>
      <Button variant='primary' onClick={addEducation}>Add New Education</Button>
    </div>
    </Container>
  );
}

export default EducationScreen;