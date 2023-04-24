import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import {addNewAddress, resetAddedAddress} from '../actions/addressActions'
import { useNavigate } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux'
import React, {useState, useEffect} from 'react';
import FormContainer from '../components/FormContainer';
import Loader from '../components/Loader';
import Message from '../components/Message';

function AddressForm() {

  const [houseNo, setHouseNo] = useState('')
  const [streetNo, setStreetNo] = useState('')
  const [city, setCity] = useState('')
  const [state, setState] = useState('')
  const [country, setCountry] = useState('')


  const dispatch = useDispatch();

  const navigate = useNavigate();


  const newAddress = useSelector(state => state.userAddress)
  const {error, loading, addedAddress} = newAddress

  useEffect(() => {
      if(addedAddress){
        dispatch(resetAddedAddress())
        navigate('/address')
      }
  }, [dispatch, navigate, addedAddress])

  const submitHandler = (e) => {
      e.preventDefault()
      dispatch(addNewAddress(houseNo, streetNo, city, state, country))

  }


  return (
  <FormContainer>
        {error && <Message>{error}</Message>}
        {loading && <Loader />}
    <Form onSubmit={submitHandler}>
      <Form.Group className="mb-3" controlId="formBasicHouseNo">
        <Form.Label>House NO</Form.Label>
        <Form.Control type="text" placeholder="Enter House No" required value={houseNo} onChange={(e) => setHouseNo(e.target.value)} />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicStreet">
        <Form.Label>Street</Form.Label>
        <Form.Control type="text" placeholder="Enter Street" required value={streetNo} onChange={(e) => setStreetNo(e.target.value)} />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicCity">
        <Form.Label>City</Form.Label>
        <Form.Control type="text" placeholder="Enter City" required value={city} onChange={(e) => setCity(e.target.value)} />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicState">
        <Form.Label>State</Form.Label>
        <Form.Control type="text" placeholder="Enter State" required value={state} onChange={(e) => setState(e.target.value)} />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicCountry">
        <Form.Label>Country</Form.Label>
        <Form.Control type="text" placeholder="Enter Country" required value={country} onChange={(e) => setCountry(e.target.value)} />
      </Form.Group>
      <Button variant="primary" type="submit">
        Submit
      </Button>
    </Form>
    </FormContainer>
  );
}

export default AddressForm;