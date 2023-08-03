import React, {useState, useEffect} from 'react'
import { Navigate, useParams , useNavigate, Link} from 'react-router-dom'
import { Button, Row, Col, ListGroup, Image, Card} from 'react-bootstrap'
import { useDispatch, useSelector } from 'react-redux'
import Message from '../components/Message'
import Loader from '../components/Loader'
import { getBookingDetails } from '../actions/bookingActions'

function BookingOrderScreen() {
    const {id: bookingId} = useParams()
    const dispatch = useDispatch()
    const navigate = useNavigate()

    const bookingDetails = useSelector(state => state.bookingDetails)
    const {booking, error, loading} = bookingDetails

    if(!loading && !error){
      booking.itemsPrice = booking.bookingItems.reduce((acc, item) => acc+ item.price * item.qty, 0).toFixed(2)
    }
  

  useEffect(() => {
    if(!booking || booking._id !== Number(bookingId)){
        dispatch(getBookingDetails(bookingId))
    }
  }, [dispatch, bookingId]
  )


  return  loading ? (
        <Loader/>
  ): error ? (
    <Message variant='danger'>{error}</Message>
  ) : (
    <div>
    <h1>BookingOrder: {booking._id}</h1>
      <Row>
        <Col md={8}>
          <ListGroup variant='flush'>
            <ListGroup.Item>
              <h2>Booking</h2>
                <p><strong>Name: </strong> {booking.user.name}</p>
                <p><strong>Email: </strong> <a href={`mailto:${booking.user.email}`}></a>{booking.user.email}</p>
              <p>
                <strong>Booking: </strong>
                {booking.bookingAddress.address}, {booking.bookingAddress.city}
                {' '}
                {booking.bookingAddress.postalCode},
                {' '}
                {booking.bookingAddress.country},
              </p>

              {booking.isConfirmed ? (
                <Message variant='success'>Booking Confirmed {booking.confirmedAt}</Message>
              ): (
                <Message variant='warning'>Not Confirmed</Message>
              )}
            </ListGroup.Item>

            <ListGroup.Item>
              <h2>Payment Method</h2>

              <p>
                <strong>Method: </strong>
                {booking.paymentMethod}
                
              </p>
              {booking.isPaid ? (
                <Message variant='success'>Paid on {booking.paidAt}</Message>
              ): (
                <Message variant='warning'>Not Paid</Message>
              )}

            
            </ListGroup.Item>

            <ListGroup.Item>
              <h2>Ticket Items</h2>
              {booking.bookingItems.length === 0 ? <Message variant='info'>
                  Your ticket booking is empty
              </Message> : (
                <ListGroup variant='flush'>
                  {booking.bookingItems.map((item, index) =>
                  (
                    <ListGroup.Item key={index}>
                      <Row>
                        <Col md={1}>
                          <Image src={item.image} alt={item.name} fluid rounded width='100' />
                        </Col>

                        <Col>
                          <Link to='/movie/${item.movie'>{item.name} </Link>
                        </Col>

                        <Col md={4}>
                          {item.qty} X ${item.price} = ${(item.qty*item.price).toFixed(2)}
                        </Col>
                      </Row>
                    </ListGroup.Item>
                  )
                  )}
                </ListGroup>
              )

              }
              
            </ListGroup.Item>

          </ListGroup>
        </Col>

        <Col md={4}>
          <Card>
            <ListGroup variant='flush'>
            <ListGroup.Item>
                <h2>Booking Summary</h2>
            </ListGroup.Item>

              <ListGroup.Item>
                <Row>
                  <Col>Item:</Col>
                  <Col>${booking.itemsPrice}</Col>
                </Row>
              </ListGroup.Item>

              <ListGroup.Item>
                <Row>
                  <Col>Tickets</Col>
                  <Col>${booking.bookingPrice}</Col>
                </Row>
              </ListGroup.Item>
              <ListGroup.Item>
                <Row>
                  <Col>Tax:</Col>
                  <Col>${booking.taxPrice}</Col>
                </Row>
              </ListGroup.Item>

            <ListGroup.Item>
                <Row>
                  <Col>Total:</Col>
                  <Col>${booking.totalPrice}</Col>
                </Row>
              </ListGroup.Item>

            </ListGroup>
          </Card>
        </Col>
      </Row>
    </div>
  )
}

export default BookingOrderScreen