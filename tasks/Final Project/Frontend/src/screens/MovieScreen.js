import React, { useState, useEffect} from 'react' 
import { Link, useNavigate } from 'react-router-dom'
import { Row, Col, Image, ListGroup, Button, Card, Form } from 'react-bootstrap'
import Rating from '../components/Rating'
import Loader from '../components/Loader'
import Message from '../components/Message'
import { useDispatch, useSelector } from 'react-redux'
// import movies from '../product'
import { useParams } from 'react-router-dom'
import { listMovieDetails, createMovieReview } from '../actions/movieActions'
import { MOVIE_CREATE_REVIEW_RESET } from '../constants/movieConstants'
// import axios from 'axios'

 function MovieScreen() {
  const [qty, setQty] = useState(1)
  const [rating, setRating] = useState(0)
  const [comment, setComment] = useState('')

  const navigate = useNavigate()

  const dispatch = useDispatch()
  const movieDetails = useSelector(state => state.movieDetails)
  const { loading, error, movie} = movieDetails

  const userLogin = useSelector(state => state.userLogin)
  const {userInfo} = userLogin

  const movieReviewCreate = useSelector(state => state.movieReviewCreate)
  const { loading:loadingMovieReview, 
    error:errorMovieReview , 
    success: successMovieReview,
    } = movieReviewCreate


  // const [movie, setMovie] = useState([])
  const {id} = useParams()
  useEffect(()=> {
      if(successMovieReview){
        setRating(0)
        setComment('')
        dispatch({type:MOVIE_CREATE_REVIEW_RESET})
      }
      dispatch(listMovieDetails(id))
      // async function fetchMovie(){

      // const {data} = await axios.get(`/api/product/${id}`)
      // setMovie(data)

    // fetchMovie()
  }, [dispatch, successMovieReview])

  const addToWishlistHandler = () =>{
    if (userInfo){
      navigate(`/wishlist/${id}?qty=${qty}`)
    }
    else{
      navigate('/login')
    }
    
  }

  // let movie = {}
  const submitHandler = (e) => {
    e.preventDefault()
    dispatch(createMovieReview(
      id, {
      rating,
      comment,
      }
    ))
  }




    // const {id} = useParams()
    // const movie = movies.find((p) => p._id == id)
  return (
    <div>
      <Link to='/' className='btn btn-light my-3'>Previous</Link>
        {loading ? <Loader />
          : error
            ? <Message variant='danger'>{error}</Message>
          :(
            <div>
              <Row>
            <Col md={6}>
              <Image style={{ width:500, height:300 }} src={movie.image} fluid />
            </Col>
    
            <Col md={3}>
              <ListGroup variant="flush">
                <ListGroup.Item>
                  <h3>{movie.name}</h3>
                </ListGroup.Item>
              
    
              <ListGroup.Item>
                <Rating value={movie.rating} text={`${movie.numReviews} ratings`} color={`#f8e825`} />
              </ListGroup.Item>
    
              <ListGroup.Item>
                  Price: ${movie.price}
              </ListGroup.Item>
    
              <ListGroup.Item>
                  Description: {movie.description}
              </ListGroup.Item>
    
              <ListGroup.Item>
                  Genre: {movie.genre}
              </ListGroup.Item>
            </ListGroup>
          </Col>
    
          <Col md={3}>
            <Card>
              <ListGroup variant='flush'>
                <ListGroup.Item>
                  <Row>
                    <Col>Price:</Col>
                    <Col>
                      <strong>${movie.price}</strong>
                    </Col>
                  </Row>
                </ListGroup.Item>
    
                <ListGroup.Item>
                  <Row>
                    <Col>Number of Screens:</Col>
                    <Col>
                      {movie.number_of_screens > 0 ? 'Booking available': 'No booking'}
                    </Col>
                  </Row>
                </ListGroup.Item>
                {movie.number_of_screens > 0 && (
                  <ListGroup.Item>
                    <Row>
                      <Col> Number of seats to Book</Col>
                      <Col xs='auto' className='my-1'>
                      <Form.Control
                          as = "select"
                          value = {qty}
                          onChange = {(e) => setQty(e.target.value)}
                        >
                          {
                            [...Array(movie.number_of_screens).keys()].map((x) => (
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
                  <Button
                  onClick={addToWishlistHandler}
                  className='btn-block'
                  disabled={movie.number_of_screens===0}
                  type='button'>
                    Book the Show
                  </Button>
                </ListGroup.Item>
              </ListGroup>
            </Card>
          </Col>
              </Row>

              <Row>
                <Col md={6}>
                  <h4>Reviews</h4>
                  {movie.reviews.length === 0 && <Message variant='info'> No Reviews</Message>}
                  <ListGroup variant='flush'>
                    {movie.reviews.map((review) => (
                      <ListGroup.Item key={review._id}>
                        <strong>{review.name}</strong>
                        <Rating value={review.rating} color='#f8e825'/>
                        <p> {review.createdAt.substring(0,10)}</p>
                        <p> {review.comment}</p>
                      </ListGroup.Item>
                    ))}

                    <ListGroup.Item>
                      <h4>Write a review </h4>
                      {loadingMovieReview && <Loader/>}
                      {successMovieReview && <Message variant='success'>Review Submitted</Message>}
                      {errorMovieReview && <Message variant='danger'>{errorMovieReview}</Message>}
                      {userInfo ? (
                        <Form onSubmit={submitHandler}>
                          <Form.Group controlId='rating'>
                            <Form.Label>Rating</Form.Label>
                            <Form.Control
                              as='select'
                              value={rating}
                              onChange={(e) => setRating(e.target.value)}
                              >
                            <option value=''>Select...</option>
                            <option value='1'>1 - Poor Movie</option>
                            <option value='2'>2 - Fair Movie</option>
                            <option value='3'>3 - Good Movie</option>
                            <option value='3'>4 - Very Good Movie</option>
                            <option value='3'>5 - Excellent Movie</option>
                          </Form.Control>
                        </Form.Group>

                        <Form.Group controlId='comment'>
                          <Form.Label>Review</Form.Label>
                          <Form.Control
                            as='textarea'
                            rows='5'
                            value={comment}
                            onChange={(e) => setComment(e.target.value)}
                          >
                            </Form.Control>
                        </Form.Group>
                        <Button
                          disabled={loadingMovieReview}
                          type = 'submit'
                          variant = 'primary'
                        >
                          Submit
                        </Button>
                        </Form>
                      ):(
                        <Message variant='info'>Please <Link to='/login'>login</Link> to write a review</Message>
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

export default MovieScreen
