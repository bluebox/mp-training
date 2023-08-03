import React, {useState, useEffect} from 'react'
import { Navigate, useLocation , useNavigate, Link} from 'react-router-dom'
import { Button, Row, Col, ListGroup, Image, Card} from 'react-bootstrap'
import { useDispatch, useSelector } from 'react-redux'
import Message from '../components/Message'
import CheckoutSteps from '../components/CheckoutSteps'
import { createBooking, getBookingDetails } from '../actions/bookingActions'
import { BOOKING_CREATE_RESET } from '../constants/bookingConstants'

function TicketBookedScreen() {

  const bookingCreate = useSelector(state => state.bookingCreate)
  const {booking:bookingCreated, error, success} = bookingCreate

  const dispatch = useDispatch()
  const navigate = useNavigate()

  const wishlist = useSelector(state => state.wishlist)

  const bookingDetails = useSelector(state => state.bookingDetails)
  const {booking, error:errorBooking, loading} = bookingDetails

  wishlist.itemsPrice = wishlist.wishlistItems.reduce((acc, item) => acc+ item.price * item.qty, 0).toFixed(2)
  wishlist.bookingPrice = (wishlist.itemsPrice > 100 ? 0 : 10).toFixed(2)
  wishlist.taxPrice = Number((0.082) * wishlist.itemsPrice).toFixed(2)

  wishlist.totalPrice = (Number(wishlist.itemsPrice) + Number(wishlist.bookingPrice) + Number( wishlist.taxPrice)).toFixed(2)
  
  if(!wishlist.paymentMethod){
     navigate('/payment')
  }

  useEffect(() => {
    if(success){
      console.log(bookingCreated.booking_id)
      navigate(`/booking/${bookingCreated.booking_id}`)
      dispatch({ type: BOOKING_CREATE_RESET })
    }
    
  }, [dispatch, success]
  )
  
  const ticketBooked = () => {
    dispatch(createBooking({
      bookingItems: wishlist.wishlistItems,
      bookingAddress: wishlist.bookingAddress,
      paymentMethod: wishlist.paymentMethod,
      itemsPrice: wishlist.itemsPrice,
      bookingPrice: wishlist.bookingPrice,
      taxPrice: wishlist.taxPrice,
      totalPrice: wishlist.totalPrice,
  }))
  }
  return (
    <div>
      <CheckoutSteps step1 step2 step3 step4 />
      <Row>
        <Col md={8}>
          <ListGroup variant='flush'>
            <ListGroup.Item>
              <h2>Booking</h2>

              <p>
                <strong>Booking: </strong>
                {wishlist.bookingAddress.address}, {wishlist.bookingAddress.city}
                {' '}
                {wishlist.bookingAddress.postalCode},
                {' '}
                {wishlist.bookingAddress.country},
              </p>
            </ListGroup.Item>

            <ListGroup.Item>
              <h2>Payment Method</h2>

              <p>
                <strong>Method: </strong>
                {wishlist.paymentMethod}
                
              </p>
            </ListGroup.Item>

            <ListGroup.Item>
              <h2>Order Items</h2>
              {wishlist.wishlistItems.length === 0 ? <Message variant='info'>
                  Your wishlist is empty
              </Message> : (
                <ListGroup variant='flush'>
                  {wishlist.wishlistItems.map((item, index) =>
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
                  <Col>${wishlist.itemsPrice}</Col>
                </Row>
              </ListGroup.Item>

              <ListGroup.Item>
                <Row>
                  <Col>Tickets</Col>
                  <Col>${wishlist.bookingPrice}</Col>
                </Row>
              </ListGroup.Item>
              <ListGroup.Item>
                <Row>
                  <Col>Tax:</Col>
                  <Col>${wishlist.taxPrice}</Col>
                </Row>
              </ListGroup.Item>

            <ListGroup.Item>
                <Row>
                  <Col>Total:</Col>
                  <Col>${wishlist.totalPrice}</Col>
                </Row>
              </ListGroup.Item>

              <ListGroup.Item>
                {error && < Message variant='danger'>{error}</Message>}
              </ListGroup.Item>

              <ListGroup.Item>
                <Button
                  type='button'
                  className='btn-block'
                  disabled={wishlist.wishlistItems === 0}
                  onClick={ticketBooked}
                  >
                    Ticket Booked
                  </Button>
              </ListGroup.Item>
            </ListGroup>
          </Card>
        </Col>
      </Row>
    </div>
  )
}

export default TicketBookedScreen
