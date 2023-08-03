import React, { useEffect } from 'react'
import { Link, useLocation, useParams, useNavigate } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux'
import { Row, Col, ListGroup, Image, Form, Button, Card} from 'react-bootstrap'
import  Message  from '../components/Message'
import { addToWishlist, removeFromWishlist } from '../actions/wishlistActions'



function WishlistScreen() {
    const {id} = useParams()
    const location = useLocation()
    const movieId = id
    const qty = location.search ? Number(location.search.split('=')[1]) : 1
    const navigate = useNavigate()

    const dispatch = useDispatch()

    const wishlist = useSelector(state => state.wishlist)
    const { wishlistItems } = wishlist
    useEffect(() =>{
        if(movieId){
            dispatch(addToWishlist(movieId, qty))
        }
    }, [dispatch, movieId, qty]
    )
    const removeFromWishlistHandler = (id) => {
      dispatch(removeFromWishlist(id))
    }

    const checkoutHandler = () => { 
      navigate('/booking')
    }

  return (
    <Row>
       <Col md={8}>
        <h1> Shopping Wishlist</h1>
        {wishlistItems.length === 0 ? (
          <Message variant='info'>
            Your cart is empty <Link to='/'>Go Back </Link>
          </Message>
        ) : (
          <ListGroup variant='flush'>
            {console.log(wishlistItems)}
            {wishlistItems.map(item => (
              <ListGroup.Item key={item.name}>
                <Row>
                  <Col md={2}>
                    <Image src={item.image} alt={item.name} fluid rounded/>
                  </Col>
                  <Col md={3}>
                    <Link to={`/product/${item.product}`}>{item.name}</Link>
                  </Col>

                  <Col md={2}>
                    ${item.price}
                  </Col>

                  {/* <Col md={3}>
                      <Form.Control
                          as = "select"
                          value={item.qty}
                          onChange = {(e) => dispatch(addToWishlist(item.movie, Number(e.target.value)))}>
                          {
                            [...Array(item.number_of_screens).keys()].map((x) => (
                            <option key={x+1} value={x+1}>
                              {x+1}
                            </option>
                            ) )
                          }
                  </Form.Control>
                      
                  </Col> */}

                  <Col md={1}>
                    <Button type='button'
                      variant='light'
                        onClick= {() => removeFromWishlistHandler(item.movie)}
                        >
                      <i className='fas fa-trash'></i>
                    </Button>

                  </Col>
                  

                </Row>
              </ListGroup.Item>
            ))
}

          </ListGroup>
        )}
       </Col>

       <Col md={4}>
        <Card>
          <ListGroup variant='flush'>
            <ListGroup.Item>
              <h2>Subtotal ({wishlistItems.reduce((acc, item) => acc + item.qty, 0)}) </h2>
              ${wishlistItems.reduce((acc, item) => acc + item.qty * item.price, 0).toFixed(2)} 
            </ListGroup.Item>
          </ListGroup>

          <ListGroup.Item>
            <Button
              type='button'
              className='btn-block'
              disabled = {wishlistItems.length === 0}
              onClick= {checkoutHandler}
            >
              Proceed To Checkout
            </Button>
          </ListGroup.Item>
        </Card>

       </Col>
    </Row>
  )
}
export default WishlistScreen
