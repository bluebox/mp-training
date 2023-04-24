import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';
import { useNavigate } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import { deleteUserExperience } from '../actions/experienceActions'

function Experience({experience, index}) {
  const navigate = useNavigate()
  const dispatch = useDispatch()
  const companyDetails = useSelector(state => state.companyLogin)
  const { companyInfo} = companyDetails

  const editExperience = () => {
    navigate(`/editExperience?experience=${experience._id}`)
  }

  function deleteExperience(experience_id){
    dispatch(deleteUserExperience(experience_id))
  }
  return (
    <>
    <Table striped="columns my-3">
        <h3>Expereince {index}</h3>
        <tbody>
        <tr>
        <th>Company Name</th>
        <td>{experience.company_name}</td>
      </tr>
      <tr>
        <th>Job Location</th>
        <td>{experience.job_location}</td>
      </tr>
      <tr>
        <th>Job Description</th>
        <td>{experience.description}</td>
      </tr>
      <tr>
        <th>Experience</th>
        <td>{experience.experience} yrs</td>
      </tr>
        </tbody>
      
    </Table>
    {
      companyInfo ? <></>
      :  <div >
        <Button variant='primary' onClick={editExperience} size='sm'>Edit</Button> {' '} 
        <Button variant='primary' onClick={() => deleteExperience(experience._id)} size='sm'>Delete</Button>
      </div>
    }
    </>
  );
}

export default Experience;