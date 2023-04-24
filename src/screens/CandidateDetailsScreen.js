import { useLocation } from 'react-router-dom'
import { useSelector } from 'react-redux'
import React from 'react';
import Loader from '../components/Loader';
import Message from '../components/Message';
import Address from '../components/Address';
import Education from '../components/Education';
import Experience from '../components/Experience';
import SkillsDetails from '../components/SkillsDetails';
import { Col, Container, Row } from 'react-bootstrap'

function CandidateDetailsScreen() {

  const { search } = useLocation()
  const candidate_id = new URLSearchParams(search).get("id") || '0';


  const appliedCandidateDetails = useSelector(state => state.appliedCandaidatesDetails)
  
  const {error, loading, candidateDetails} = appliedCandidateDetails



  return (

    <Container>
        {loading ? <Loader/>
          : error ? <Message variant='danger'>{error}</Message>
            : 
            <div>
              <h1>Candidate Details</h1>
              <Col>
                {candidateDetails.address.length === 0 ? 
                <Message variant='info'>There are No Address Details</Message>
                :
                candidateDetails.address.map((add, index) => (
                  <Row key={add.id} sm={12} md={6} lg={4} xl={3} >
                      <Address address={add} index={index + 1} />
                  </Row>
              ))}
            </Col>
            <Col>
              {candidateDetails.education.length === 0 ? 
                <Message variant='info'>There are No Educational Details</Message>
                :
                candidateDetails.education.map((edu, index) => (
                  <Row key={edu.id} sm={12} md={6} lg={4} xl={3} >
                      <Education education={edu} index={index + 1} />
                  </Row>
              ))}
                
            </Col>
            <Col>
              {candidateDetails.experience.length === 0 ? 
                <Message variant='info'>There are No Experience Details</Message>
                :
                candidateDetails.experience.map((exp, index) => (
                  <Row key={exp.id} sm={12} md={6} lg={4} xl={3} >
                      <Experience experience={exp} index={index + 1} />
                  </Row>
              ))}
                
            </Col>
            
            <Col>
              {candidateDetails.skills.length === 0 ? 
                <Message variant='info'>There are No Skills Details</Message>
                :
                candidateDetails.skills.map((skillSet) => (
                  <Row key={skillSet.id} sm={12} md={6} lg={4} xl={3} >
                      <SkillsDetails skills={skillSet.skills} />
                  </Row>
              ))}
            </Col>
              
            </div>
            
        }  
    </Container>
  );
}

export default CandidateDetailsScreen;