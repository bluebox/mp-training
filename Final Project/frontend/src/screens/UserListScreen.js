
import React, {useState, useEffect} from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { LinkContainer } from 'react-router-bootstrap';
import { Table, Button } from 'react-bootstrap';
import Message from '../components/Message';
import Loader from '../components/Loader';
import { listUser } from '../actions/userActions';
import { useNavigate } from 'react-router-dom';
import { deleteUser } from '../services/userServices';

function UserListScreen() {
  const {error, users, loading} = useSelector(state=>state.userList)
  const {userInfo} = useSelector(state=>state.userLogin)
  const [successDelete, setSuccessDelete] = useState(false)
  const [errorDelete, setErrorDelete] = useState('')
  
  const dispatch = useDispatch()
  const navigate = useNavigate()

  const deleteHandler = (id) =>{
    if (window.confirm('Are you sure you want to delete this user?')){
      deleteUser(id, setSuccessDelete, setErrorDelete)
    }
  }

  useEffect(()=>{
    if (userInfo && userInfo.isAdmin){
      dispatch(listUser())
    }else{
      navigate('/login')
    }
  }, [dispatch, navigate, userInfo, successDelete])
 
  
  return (
    <div>
    <h1>Users</h1>
    {loading?(
      <Loader/>
    ) : error || errorDelete? (
      <Message variant='danger'>{error?error:errorDelete}</Message>
    ) :(
      <Table striped bordered hover responsive className='table-sm'>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Staff</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {users.map(user => (
            <tr key={user.id}>
              <td>{user.id}</td>
              <td>{user.first_name + " " + user.last_name}</td>
              <td>{user.email}</td>
              <td>{user.isAdmin ?
                (<i className='fas fa-check' style={{color: 'green'}}></i>)
                : (<i className='fas fa-check' style={{color: 'red'}}></i>)}</td>
              <td>
                <LinkContainer to={`/admin/user/${user.id}`}>
                  <Button className="btn-sm" variant='light'>
                    <i className='fas fa-edit'></i>
                  </Button>
                </LinkContainer>
                <Button variant='danger' className='btn-sm' onClick={()=> deleteHandler(user.id)}>
                  <i className='fas fa-trash'></i>
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    )}</div>
  )
}

export default UserListScreen