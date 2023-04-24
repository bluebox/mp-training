import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';
import { useNavigate } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import { deleteEducation } from '../actions/educationActions';

function Education({education, index}) {
  const navigate = useNavigate()
  const dispatch = useDispatch()
  const companyDetails = useSelector(state => state.companyLogin)
  const { companyInfo} = companyDetails

  const editEducation = () => {
    navigate(`/editEducation?education=${education._id}`)
  }

  function deleteUserEducation(education_id) {
    dispatch(deleteEducation(education_id))
  }

  return (
    <>
    <Table striped="columns my-3">
        <h3>Education {index}</h3>
        <tbody>
        <tr>
        <th>Major</th>
        <td>{education.major}</td>
      </tr>
      <tr>
        <th>Degree</th>
        <td>{education.degree}</td>
      </tr>
      <tr>
        <th>University</th>
        <td>{education.university}</td>
      </tr>
      <tr>
        <th>CGPA</th>
        <td>{education.percentage} CGPA</td>
      </tr>
        </tbody>
    </Table>
    {
      companyInfo ? <></>
      :  <div >
        <Button variant='primary' onClick={editEducation} size='sm'>Edit</Button> {' '} 
        <Button variant='primary' onClick={() => deleteUserEducation(education._id)} size='sm'>Delete</Button>
      </div>
    }
    
    </>
  );
}

export default Education;