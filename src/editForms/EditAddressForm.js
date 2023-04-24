import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import {editAddress} from '../actions/addressActions'
import { useNavigate, useLocation } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux'
import React, {useState, useEffect} from 'react';
import FormContainer from '../components/FormContainer';
import Loader from '../components/Loader';
import Message from '../components/Message';

function EditAddressForm() {
  const { search } = useLocation();
  const addressId = new URLSearchParams(search).get("address") || '0';
  const userAddresses = useSelector(state => state.getUserAddress) 
  const { loading, error, getAddresses} = userAddresses
  const foundedAddress = getAddresses.find(element => element._id == addressId);

  const [houseNo, setHouseNo] = useState(foundedAddress.house_no)
  const [streetNo, setStreetNo] = useState(foundedAddress.street)
  const [city, setCity] = useState(foundedAddress.city)
  const [state, setState] = useState(foundedAddress.state)
  const [country, setCountry] = useState(foundedAddress.country)


  const dispatch = useDispatch();

  const navigate = useNavigate();


  const submitHandler = (e) => {
      e.preventDefault()
      dispatch(editAddress(addressId, houseNo, streetNo, city, state, country))
      navigate('/address')

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

export default EditAddressForm;