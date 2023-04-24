import { Link } from 'react-router-dom'
import Button from 'react-bootstrap/Button';
import { useNavigate } from 'react-router-dom';
import Message from '../components/Message';
import Address from '../components/Address'
import { useDispatch, useSelector } from 'react-redux';
import React, { useEffect } from 'react';
import Loader from '../components/Loader';
import { Row, Col, Container, Nav, Navbar } from 'react-bootstrap';
import { getExperienceDetails } from '../actions/experienceActions';
import Experience from '../components/Experience'



function ExperienceScreen() {
    const dispatch = useDispatch()
    const experienceList = useSelector(state => state.getUserExperience)

    const {error, loading, getExperiences} = experienceList

    const experienceDeletingStatus = useSelector(state => state.delExperience)
    const {deleting, deleteExperience} = experienceDeletingStatus

    useEffect(() => {
        dispatch(getExperienceDetails())
    }, [dispatch, deleteExperience])

    const navigate = useNavigate()
    const addExperience = () => {
        navigate('/addExperience?added=false')
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
                {getExperiences.map((experience, index) => (
                  <Row key={experience.id} sm={12} md={6} lg={4} xl={3} >
                      <Experience experience={experience} index={index + 1} />
                  </Row>
            ))}
        </Col>
        }  
    <div className='my-3'>
    <Button variant='primary' onClick={addExperience}>Add New Experience</Button>

    </div>
    </Container>
  );
}

export default ExperienceScreen;