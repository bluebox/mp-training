import { Link } from 'react-router-dom'
import Button from 'react-bootstrap/Button';
import { useNavigate } from 'react-router-dom';
import Message from '../components/Message';
import { getAddressDetails } from '../actions/addressActions';
import Address from '../components/Address'
import { useDispatch, useSelector } from 'react-redux';
import React, { useEffect , useState} from 'react';
import Loader from '../components/Loader';
import { Row, Col, Container, Nav, Navbar } from 'react-bootstrap';




function AddressScreen() {
    const dispatch = useDispatch()
    const addressList = useSelector(state => state.getUserAddress)

    const {error, loading, getAddresses} = addressList

    const addressDeleteStatus = useSelector(state => state.delAddress)
    const {deleting, addressDeleted} = addressDeleteStatus

    useEffect(() => {
        dispatch(getAddressDetails())
    }, [dispatch, addressDeleted])



    const navigate = useNavigate()
    const addAddress = () => {
        navigate('/addAddress?added=false')
    }

  return (
    <Container>
      <Navbar bg="light" variant="light" className='details-nav-bar'>
        <>
          <Nav className="me-auto">
            <Link to="/address" className='personal-information'>Address</Link>
            <Link to="/experience" className='personal-information'>Experience</Link>
            <Link to="/education" className='personal-information'>Education</Link>
            <Link to="/skills" className='personal-information'>Skills</Link>
          </Nav>
        </>
      </Navbar>

        {loading ? <Loader/>
          : deleting ? <Loader />
          : error ? <Message variant='danger'>{error}</Message>
            : <Col>
                {getAddresses.map((address, index) => (
                  <Row key={address.id} sm={12} md={6} lg={4} xl={3} >
                      <Address address={address} index={index + 1} />
                  </Row>
            ))}
        </Col>
        }  
    <div className='my-3'>
      <Button variant='primary' onClick={addAddress} >Add New Address</Button>    
    </div>
    </Container>
  );
}

export default AddressScreen;