import React, { useState, useEffect } from 'react'
import { Button, Row, Col, ListGroup, Image, Card } from 'react-bootstrap'
import { Link, useParams, useNavigate } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux'
import Message from '../components/Message'
import Loader from '../components/Loader'
import { getOrderDetails } from '../actions/orderActions'
import Swal from 'sweetalert2'
import { deliverOrder, payOrder } from '../services/orderServices'

function OrderScreen() {
    const {id: orderId} = useParams()
    const navigate = useNavigate()

    const dispatch = useDispatch()

    const orderDetails = useSelector(state => state.orderDetail)
    const { order, error, loading } = orderDetails


    const [loadingDeliver, setLoadingDeliver] = useState(false)
    const [errorDeliver, setErrorDeliver] = useState('')
    const [successDeliver, setSuccessDeliver] = useState(false)

    const [erroPay, setErrorPay] = useState('')
    const [successPay, setSuccessPay] = useState(false)

    const userLogin = useSelector(state => state.userLogin)
    const { userInfo } = userLogin


    if (!loading && !error) {
        order.itemsPrice = order.orderItems.reduce((acc, item) => acc + item.price * item.qty, 0).toFixed(2)
    }


    const successPaymentHandler = () => {
        Swal.fire({
            title: 'Are you sure you want to pay $' + order.totalPrice,
            text: "Make sure amount is correct!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Pay $' + order.totalPrice 
          }).then((result) => {
            if (result.isConfirmed) {
              Swal.fire(
                'Payment Successful',
                'Thank you for Shopping',
                'success'
              )
              payOrder(orderId, setSuccessPay, setErrorPay)
            }else{
                Swal.fire(
                    'Payment Denied',
                    'You Denied Payment',
                    'warning'
                )
            }
          })
        
    }

    const deliverHandler = () => {
        deliverOrder(order, setLoadingDeliver, setSuccessDeliver, setErrorDeliver)
    }

    useEffect(() => {

        if (!userInfo) {
            navigate('/login')
        }
        console.log(successPay)
        if (!order || successPay || order.id !== Number(orderId) || successDeliver) {
            dispatch(getOrderDetails(orderId))
        }
    }, [dispatch, orderId, successPay, successDeliver])

    return loading ? (
        <Loader />
    ) : error ? (
        <Message variant='danger'>{error}</Message>
    ) : (
                <div>
                    <h1>Order: {orderId}</h1>
                    <Row>
                        <Col md={8}>
                            <ListGroup variant='flush'>
                                <ListGroup.Item>
                                    <h2>Shipping</h2>
                                    <p><strong>Name: </strong> {order.user.first_name + " " + order.user.last_name}</p>
                                    <p><strong>Email: </strong><a href={`mailto:${order.user.email}`}>{order.user.email}</a></p>
                                    <p>
                                        <strong>Shipping: </strong>
                                        {order.shippingAddress.address},  {order.shippingAddress.city}
                                        {'  '}
                                        {order.shippingAddress.postalCode},
                                        {'  '}
                                        {order.shippingAddress.country}
                                    </p>

                                    {order.isDelivered ? (
                                        <Message variant='success'>Delivered on {order.deliveredAt.substring(0,10)}</Message>
                                    ) : (
                                            <Message variant='warning'>Not Delivered</Message>
                                        )}
                                </ListGroup.Item>

                                <ListGroup.Item>
                                    <h2>Payment Method</h2>
                                    <p>
                                        <strong>Method: </strong>
                                        {order.paymentMethod}
                                    </p>
                                    {order.isPaid ? (
                                        <Message variant='success'>Paid on {order.paidAt.substring(0,10)}</Message>
                                    ) : (
                                            <Message variant='warning'>Not Paid</Message>
                                        )}

                                </ListGroup.Item>

                                <ListGroup.Item>
                                    <h2>Order Items</h2>
                                    {order.orderItems.length === 0 ? <Message variant='info'>
                                        Order is empty
                                    </Message> 
                                        : (
                                            <ListGroup variant='flush'>
                                                {order.orderItems.map((item, index) => (
                                                    <ListGroup.Item key={index}>
                                                        <Row>
                                                            <Col md={1}>
                                                                <Image src={`http://127.0.0.1:8000${item.image}`} alt={item.name} fluid rounded />
                                                            </Col>

                                                            <Col>
                                                                <Link to={`/product/${item.product}`}>{item.name}</Link>
                                                            </Col>

                                                            <Col md={4}>
                                                                {item.qty} X ${item.price} = ${(item.qty * item.price).toFixed(2)}
                                                            </Col>
                                                        </Row>
                                                    </ListGroup.Item>
                                                ))}
                                            </ListGroup>
                                        )}
                                </ListGroup.Item>

                            </ListGroup>

                        </Col>

                        <Col md={4}>
                            <Card>
                                <ListGroup variant='flush'>
                                    <ListGroup.Item>
                                        <h2>Order Summary</h2>
                                    </ListGroup.Item>

                                    <ListGroup.Item>
                                        <Row>
                                            <Col>Items:</Col>
                                            <Col>${order.itemsPrice}</Col>
                                        </Row>
                                    </ListGroup.Item>

                                    <ListGroup.Item>
                                        <Row>
                                            <Col>Shipping:</Col>
                                            <Col>${order.shippingPrice}</Col>
                                        </Row>
                                    </ListGroup.Item>

                                    <ListGroup.Item>
                                        <Row>
                                            <Col>Tax:</Col>
                                            <Col>${order.taxPrice}</Col>
                                        </Row>
                                    </ListGroup.Item>

                                    <ListGroup.Item>
                                        <Row>
                                            <Col>Total:</Col>
                                            <Col>${order.totalPrice}</Col>
                                        </Row>
                                    </ListGroup.Item>

                                    {userInfo.username == order.user.username && !order.isPaid && (
                                        <ListGroup.Item>
                                            <Row>
                                                <Button variant='primary' onClick={successPaymentHandler}>Pay now</Button>
                                            </Row>
                                        </ListGroup.Item>
                                    )}
                                    {loadingDeliver && <Loader />}
                                    {userInfo && userInfo.isAdmin && order.isPaid && !order.isDelivered && (
                                        <ListGroup.Item>
                                        <Row>
                                            <Button
                                                type='button'
                                                className='btn btn-block'
                                                onClick={deliverHandler}
                                            >
                                                Mark As Delivered
                                            </Button>
                                        </Row>
                                            
                                        </ListGroup.Item>
                                    )}
                                </ListGroup>
                                
                            </Card>
                        </Col>
                    </Row>
                </div>
            )
}

export default OrderScreen