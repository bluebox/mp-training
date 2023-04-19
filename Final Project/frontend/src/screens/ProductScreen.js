import React, {useState, useEffect} from 'react'
import {Row, Col, Image, ListGroup, Button, Form} from 'react-bootstrap'
import {Link, useParams, useNavigate} from 'react-router-dom'
import Rating from '../components/Rating'
import { useDispatch, useSelector } from 'react-redux'
import { createProductReview, detailProducts } from '../actions/productActions'
import Loader from '../components/Loader'
import Message from '../components/Message'
import { PRODUCT_CREATE_REVIEW_RESET } from '../constants/productConstants'
import { addToCart } from '../services/cartServices'


function ProductScreen() {
    const {id} = useParams()
    const {userInfo:user} = useSelector(state=>state.userLogin)
    const navigate = useNavigate()
    const dispatch = useDispatch()
    const productdetail = useSelector(state => state.productDetails)
    const {loading, error, product} = productdetail 
    const [qty, setQty] = useState(1)
    const [rating, setRating] = useState(0)
    const [comment, setComment] = useState('')
    const {userInfo} = useSelector(state=>state.userLogin)
    const {loading: loadingReview, error: errorReview, success: successReview} 
        = useSelector(state =>state.productCreateReview)
    const [successUpdate, setSuccessUpdate] = useState(false)

    useEffect(() =>{
        if(successReview){
            setRating(0)
            setComment('')
        }
        dispatch({type: PRODUCT_CREATE_REVIEW_RESET})        
        dispatch(detailProducts(id))       
    }, [dispatch, id,successReview, userInfo])

    useEffect(()=>{
        if(successUpdate){
            navigate(`/cart`)
        }
    }, [successUpdate])
    
    const addToCartHandler = (e) =>{
        e.preventDefault()
        if (!user || (user && !user.username)){
            navigate('/login')
        }
        addToCart(id, qty, setSuccessUpdate)
    }
    const submitHandler = (e) =>{
        e.preventDefault()
        dispatch(createProductReview(id, {rating, comment}))
    }
  return (
    <div>
        <Link className="btn btn-light py-2 m-4" to="/">Go Back</Link>
        {loading? <Loader/>
        :error? <Message variant="danger">{error}</Message>
        :(
            <div>
                <Row>
                    <Col md={6}>
                        <Image src={`http://127.0.0.1:8000${product.image}`} alt={product.name} fluid style={{maxHeight:"50vh"}}/>
                    </Col>
                    <Col md={3}>
                        <ListGroup variant="flush">
                            <ListGroup.Item>
                                <h3>{product.name}</h3>
                            </ListGroup.Item>
                            <ListGroup.Item>
                                <Rating color="#ffd700" value={product.rating} text={`${product.numReviews} review`}/>
                            </ListGroup.Item>
                            <ListGroup.Item>
                                Price: ${product.price}
                            </ListGroup.Item>
                            <ListGroup.Item>
                                Description: {product.description}
                            </ListGroup.Item>
                        </ListGroup>
                    </Col>
                    <Col md={3}>
                        <ListGroup>
                            <ListGroup.Item>
                                <Row>
                                    <Col>Price:</Col>
                                    <Col>
                                        <strong>${product.price}</strong>
                                    </Col>
                                </Row>
                            </ListGroup.Item>
                            <ListGroup.Item>
                                <Row>
                                    <Col>Status:</Col>
                                    <Col>
                                        {product.countInStock > 0 ? 'In Stock': 'Out of Stock'}
                                    </Col>
                                </Row>
                            </ListGroup.Item>
                            {product.countInStock > 0 && (
                                <ListGroup.Item>
                                    <Row>
                                        <Col>Quantity</Col>
                                        <Col xs="auto" className="my-1">
                                            <Form.Control
                                                as="select"
                                                value={qty}
                                                onChange = {(e) => setQty(e.target.value)}
                                            >{
                                                [...Array(product.countInStock).keys()].map((x) => (
                                                    <option key={x+1} value={x+1}>
                                                        {x+1}
                                                    </option>
                                                ))
                                            }
                                            </Form.Control>
                                        </Col>
                                    </Row>
                                </ListGroup.Item>
                            )}
                            <ListGroup.Item>
                                <Row>
                                    <Button className='btn-block' disabled={product.countInStock == 0 || (userInfo && userInfo.isAdmin)} onClick={(e)=>addToCartHandler(e)}>Add to Cart</Button>
                                </Row>
                            </ListGroup.Item>
                        </ListGroup>
                    </Col>
                </Row>
                <Row>
                    <Col md={6} style={{margin: "16px"}}>
                        <h4>Reviews</h4>
                        {product.reviews.length === 0 && <Message variant='info'>No Reviews</Message>}

                        <ListGroup variant='flush'>
                            {product.reviews.map((review)=>(
                                <ListGroup.Item key={review.id}>
                                    <strong>{review.name}</strong>
                                    <Rating value={review.rating} color='#f8e825'/>
                                    <p>{review.created_at.substring(0,10)}</p>
                                    <p>{review.comment}</p>
                                </ListGroup.Item>
                            ))}

                            <ListGroup.Item style={{margin: "16px"}}>
                                <h4>Write a review</h4>
                                {loadingReview && <Loader/>}
                                {successReview && <Message variant='success'>Review Submitted</Message>}
                                {errorReview && <Message variant='info'>{errorReview}</Message>}

                                {userInfo?(
                                    <Form onSubmit={submitHandler}>
                                        <Form.Group controlId='rating' style={{margin: "16px"}}>
                                            <Form.Select
                                                value={rating}
                                                onChange={(e)=>setRating(e.target.value)}
                                            >
                                                <option value='0'>Select ...</option>
                                                <option value='1'>1 - Poor</option>
                                                <option value='2'>2 - Fair</option>
                                                <option value='3'>3 - Good</option>
                                                <option value='4'>4 - Very Good</option>
                                                <option value='5'>5 - Excellent</option>
                                            </Form.Select>
                                        </Form.Group>
                                        <Form.Group controlId='comment' style={{margin: "16px"}}>
                                            <Form.Label>Review</Form.Label>
                                            <Form.Control
                                                as='textarea'
                                                value={comment}
                                                onChange={(e)=>setComment(e.target.value)}
                                            >

                                            </Form.Control>
                                        </Form.Group>
                                        {console.log()}
                                        <Button
                                            disabled={!(Boolean(comment)) || (userInfo && userInfo.isAdmin)}
                                            type='submit'
                                            variant='primary'
                                            style={{margin: "16px"}}
                                        >
                                            Submit
                                        </Button>
                                    </Form>
                                ):(
                                    <Message variant='info'>Please <Link to='/login'>Login</Link> to Write a review</Message>
                                )}
                            </ListGroup.Item>
                        </ListGroup>
                    </Col>
                </Row>
            </div>
        )
    }
    </div>
  )
}

export default ProductScreen