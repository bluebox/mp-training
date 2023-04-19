import React, {useEffect, useState} from 'react'
import {Link, useLocation, useNavigate, useParams} from 'react-router-dom'
import {useDispatch, useSelector} from 'react-redux'
import {Row, Col, ListGroup, Image, Form, Button, Card} from 'react-bootstrap'
import { getCartItems} from '../actions/cartAction'
import {  updateCart, removeFromCart} from '../services/cartServices'
import Message from '../components/Message'
import Loader from '../components/Loader'

const  CartScreen = () => {
  const {cart, loading, error} = useSelector(state=>state.cart)
  const {id} = useParams()
  const location = useLocation()
  const history = useNavigate()
  const qty = location.search ? Number(location.search.split('=')[1]) : 1
  const dispatch = useDispatch()
  const [success, setSuccess] = useState(false)
  
  

  useEffect(()=>{
    if(success){
      setSuccess(false)
    }
    dispatch(getCartItems())
  }, [dispatch, success])


  const removeFromCartHandler = (id) =>{
    removeFromCart(id, setSuccess)
  }
  const checkoutHandler = () =>{
    history('/login?redirect=shipping')
  }
  return (
    <>
    {
      loading?<Loader/>
      :error?<Message  variant='danger'>{error}</Message>
      :
      (<Row>
        <Col md={8}>
          <h1>Shopping Cart</h1>
          {cart.cartItems.length === 0? (
            <Message variant='info'>
              Your cart is empty <Link to='/'> Go Back</Link>
            </Message>
          ) : (
              <ListGroup variant="flush">
                {cart.cartItems.map(item => (
                  <ListGroup.Item key={item.product.id}>
                    <Row>
                      <Col md={2}>
                        <Image src={`http://127.0.0.1:8000${item.product.image}`} alt={item.product.name} rounded fluid/>
                      </Col>
                      <Col md={3}>
                        <Link to={`/product/${item.product.id}`} style={{textDecoration: "none"}}>{item.product.name}</Link>
                      </Col>
                      <Col md={2}>
                        ${item.product.price}
                      </Col>
                      <Col>
                      <Form.Control
                          as="select"
                          value={item.qty}
                          onChange = {(e) => updateCart(item.product.id, Number(e.target.value), setSuccess)}
                      >{
                          [...Array(item.product.countInStock).keys()].map((x) => (
                              <option key={x+1} value={x+1}>
                                  {x+1}
                              </option>
                          ))
                      }
                      </Form.Control>
                      </Col>
                      <Col md={1}>
                        <Button
                          type="button"
                          variant = "light"
                          onClick = {()=>removeFromCartHandler(item.product.id)}
                        >
                          <i className='fas fa-trash'></i>
                        </Button>
                      </Col>
                    </Row>
                  </ListGroup.Item>
                ))}
              </ListGroup>
          )}
        </Col>
        <Col md={4}>
          <Card>
            <ListGroup variant='flush'>
              <ListGroup.Item>
                <h2>SUBTOTAL ({cart.cartItems.reduce((acc, item) => acc + item.qty, 0)}) items</h2>
                ${cart.cartItems.reduce((acc, item) => acc + item.qty*item.product.price, 0).toFixed(2)}
              </ListGroup.Item>
              <ListGroup.Item>
               <Row>
                  <Button
                    type="button"
                    className="btn-block"
                    disabled={cart.cartItems.length == 0}
                    onClick={checkoutHandler}
                  >
                    Proceed to Checkout
                    
                  </Button>
                </Row>
              </ListGroup.Item>
            </ListGroup>
          </Card>
        </Col> 
      </Row>)
    }
    </>
  )
}

export default CartScreen