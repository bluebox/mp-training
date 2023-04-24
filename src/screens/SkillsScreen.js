import { Link } from 'react-router-dom'
import { useNavigate } from 'react-router-dom';
import { getSkillSet, getSkillsList, resetAddedSkill, addNewSkill } from '../actions/skillsActions';
import { useDispatch, useSelector } from 'react-redux';
import React, { useEffect, useState } from 'react';
import Loader from '../components/Loader';
import { Container, Nav, Navbar, Form, Button } from 'react-bootstrap';
import Skills from '../components/Skills';


function SkillsScreen() {
    const navigate = useNavigate()
    const [selectedSkill, setSelectedSkill] = useState(0)
    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(resetAddedSkill());
        
    }, [dispatch])

    const total_skills_list = useSelector(state => state.getSkillsList)
    
    const {totalSkills} = total_skills_list
    
    const user_skills_list = useSelector(state => state.getUserSkills)
    const {loading} = user_skills_list
    

    useEffect(() => {
        dispatch(getSkillSet())
    }, [dispatch])


    useEffect(() => {
        dispatch(getSkillsList())
    }, [dispatch])

    const submitHandler = () => {
      dispatch(addNewSkill(selectedSkill))
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

        {
          loading ? <Loader/>
          : <Skills />
        }
        <Form onSubmit={submitHandler}>
          <Form.Select aria-label="Default select example" onChange={(e) => setSelectedSkill(e.target.value)}>
            <option value={0} > --select skill-- </option>
            {
              totalSkills.map((skill) => (
                <option value={skill._id} >{skill.skill_name}</option>
              ))
            }
          </Form.Select>
          <Button variant="primary" type="submit" className='my-3'>
            Add Skill
          </Button>
        </Form>
    </Container>
  );
}

export default SkillsScreen;