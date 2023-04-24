import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';
import { useNavigate } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import { deleteUserAddress, getAddressDetails } from '../actions/addressActions';

function Address({address, index}) {

  const navigate = useNavigate()
  const dispatch = useDispatch()
  const companyDetails = useSelector(state => state.companyLogin)
  const { companyInfo} = companyDetails


  const editAddress = () => {
    navigate(`/editAddress?address=${address._id}`)
  }

  function deleteAddress(address_id){
    dispatch(deleteUserAddress(address_id))
  }
  return (
    <>
    <Table striped="columns my-3" >
        <h3>Address {index}</h3>
        <tbody>
        <tr>
        <th>House No</th>
        <td>{address.house_no}</td>
      </tr>
      <tr>
        <th>Street</th>
        <td>{address.street}</td>
      </tr>
      <tr>
        <th>City</th>
        <td>{address.city}</td>
      </tr>
      <tr>
        <th>State</th>
        <td>{address.state}</td>
      </tr>
      <tr>
        <th>Country</th>
        <td>{address.country}</td>
      </tr>
        </tbody>
    </Table>
    {
      companyInfo ? <></>
      :  <div >
        <Button variant='primary' onClick={editAddress} size='sm'>Edit</Button> {' '} 
        <Button variant='primary' onClick={() => deleteAddress(address._id)} size='sm'>Delete</Button>
      </div>
    }
    
    </>
  );
}

export default Address;