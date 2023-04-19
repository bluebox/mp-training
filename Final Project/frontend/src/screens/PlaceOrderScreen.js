import React, {useState, useEffect} from 'react'
import {Image,ListGroup, Form, Button, Col, Row, Card } from 'react-bootstrap'
import { useDispatch, useSelector } from 'react-redux'
import { useNavigate, Link } from 'react-router-dom'
import CheckoutSteps from '../constants/CheckoutSteps'
import Message from '../components/Message'
import Loader from '../components/Loader'
import { createOrder } from '../services/orderServices'
import {getCartItems} from '../actions/cartAction'

function PlaceOrderScreen() {

   const [success, setSuccess] = useState(false)
   const [order, setOrder] = useState({})
   const {cart, loading, error} = useSelector(state=>state.cart)

   const {userInfo} = useSelector(state=>state.userLogin)

   const navigate = useNavigate()
   const dispatch = useDispatch()
   
   const shippingAddress = JSON.parse(localStorage.getItem('shippingAddress'))
   const paymentMethod = JSON.parse(localStorage.getItem('paymentMethod'))
   
   const [price, setPrice] = useState(false)

   useEffect(() =>{
    if(!cart || cart && cart.cartItems.length == 0){
        dispatch(getCartItems())
    }

    if(cart && cart.cartItems){
        cart.itemPrice = cart.cartItems.reduce((acc, item) => acc + item.product.price * item.qty, 0).toFixed(2)
        cart.shippingPrice = (cart.itemPrice < 100 ? 0: 10).toFixed(2)
        cart.taxPrice = Number((0.18)*cart.itemPrice).toFixed(2)
        cart.totalPrice = (Number(cart.itemPrice) + Number(cart.shippingPrice) + Number(cart.taxPrice)).toFixed(2)
        setPrice(true)
    }

    if(success){
        navigate(`/order/${order.id}`)
    }

    if(paymentMethod===null){
        navigate('/payment')
    }

   },[success, navigate, cart, price])
   
   const  placeOrder=() => {
    createOrder({
        orderItems: cart.cartItems,
        shippingAddress: shippingAddress.id,
        paymentMethod: paymentMethod,
        itemPrice: cart.ItemPrice,
        shippingPrice: cart.shippingPrice,
        taxPrice: cart.taxPrice,
        totalPrice: cart.totalPrice
    }, setOrder, setSuccess)
   }
   
  return (
    <div>
        <CheckoutSteps step1 step2 step3 step4/>
       
        {loading?<Loader/>
            :error? <Message>{error}</Message>
            : 
        <Row>
             <Col md={8}>
                <ListGroup variant='flush'>
                    <ListGroup.Item>
                        <h2>Shipping</h2>
                        <p>
                            <strong>Shipping: </strong>
                            {shippingAddress.locality}, {shippingAddress.city}
                            {'  '}
                            {shippingAddress.postalCode},
                            {'  '}
                            {shippingAddress.state}
                        </p>
                    </ListGroup.Item>
                    <ListGroup.Item>
                        <h2>Payment Method</h2>
                        <p>
                            <strong>Method:</strong>
                            {paymentMethod}
                        </p>
                    </ListGroup.Item>
                    <ListGroup.Item>
                        {cart.cartItems.length === 0? <Message variant='info'>
                            Your Cart is Empty
                        </Message>: (
                            <ListGroup variant='flush'>
                                {cart.cartItems.map((item, index)=>(
                                    <ListGroup.Item key={index}>
                                        <Row>
                                            <Col md={1}>
                                                <Image src={`http://127.0.0.1:8000${item.product.image}`} alt={item.product.name} fluid rounded/>
                                            </Col>
                                            <Col>
                                                <Link to={`/product/${item.product.id}`} style={{textDecoration:"none"}}>{item.product.name}</Link>
                                            </Col>
                                            <Col md={4}>
                                                {item.qty} X ${item.product.price} = ${(item.qty * item.product.price).toFixed(2)}
                                            </Col>
                                        </Row>
                                    </ListGroup.Item>
                                ))}
                            </ListGroup> 
                        )}
                    </ListGroup.Item>
                </ListGroup>
            </Col>
            <Col md={4} >
                <Card>
                    <ListGroup variant='flush' style={{padding:"4px"}}>
                        <ListGroup.Item>
                            <h2>Order Summary</h2>
                        </ListGroup.Item>

                        <ListGroup.Item>
                            <Row>
                                <Col>Item: </Col>
                                <Col>${cart.itemPrice}</Col>
                            </Row>
                        </ListGroup.Item>

                        <ListGroup.Item>
                            <Row>
                                <Col>Shipping: </Col>
                                <Col>${cart.shippingPrice}</Col>
                            </Row>
                        </ListGroup.Item>
                        <ListGroup.Item>
                            <Row>
                                <Col>Tax: </Col>
                                <Col>${cart.taxPrice}</Col>
                            </Row>
                        </ListGroup.Item>
                        <ListGroup.Item>
                            <Row>
                                <Col>Total: </Col>
                                <Col>${cart.totalPrice}</Col>
                            </Row>
                        </ListGroup.Item>
                        <ListGroup.Item>
                            <Row>
                            <Button
                                type='button'
                                className='btn-block'
                                disabled={cart.cartItems === 0}
                                onClick={placeOrder}
                            >
                                Place Order
                            </Button>
                            </Row>
                        </ListGroup.Item>
                    </ListGroup>
                </Card>
            </Col>
        </Row>
        }
    </div>
  )
}

export default PlaceOrderScreen