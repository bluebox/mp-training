import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { deleteAddedSkill } from '../actions/skillsActions'
import { useDispatch, useSelector } from 'react-redux';
import { useState, useEffect } from 'react';
import Message from './Message';

function Skills() {
  const dispatch = useDispatch()
  const user_skills_list = useSelector(state => state.getUserSkills)
  const {error, getSkills} = user_skills_list


  const submitHanlder = (e, skill_id) =>  {
      dispatch(deleteAddedSkill(skill_id))
    }

  return (
    <>
    <Table striped="columns my-3" >
        <h3>Skill Set</h3>
        {
          error ? <Message variant='secondary'>{error}</Message>
          : <tbody>
            {
              getSkills.map((skill, index) => (
                  <tr>
                      <th>Skill {index + 1}</th>
                      <td>{skill.skill_name}</td>
                      <td>
                        <Form onSubmit={(e) => submitHanlder(e, skill._id)}>
                          <Button variant='primary' type='submit' size='sm'>Remove</Button> {' '} 
                        </Form>
                      </td>
                  </tr>
              ))
          }
          
      </tbody>
        }
        
    </Table> 
    </>
  );
}

export default Skills;