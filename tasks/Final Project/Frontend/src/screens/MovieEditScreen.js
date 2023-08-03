import React, {useState, useEffect} from 'react'
import axios from 'axios'
import { Link, useLocation , useNavigate, useParams } from 'react-router-dom'
import { Form, Button } from 'react-bootstrap'
import { useDispatch, useSelector } from 'react-redux'
import Loader from '../components/Loader'
import Message from '../components/Message'
import FormContainer from '../components/FormContainer'

import { listMovieDetails, updateMovie } from '../actions/movieActions'
import { MOVIE_UPDATE_RESET } from '../constants/movieConstants'

function MovieEditScreen() {

    const {id} = useParams()
    const[name, setName] = useState(0)
    const[price, setPrice] = useState('')
    const[image, setImage] = useState('')
    const[productionHouse, setProductionHouse] = useState('')
    const[category, setCategory] = useState('')
    const[number_of_screens, setNumberOfScreens] = useState(0)
    const[description, setDescription] = useState('')
    const[uploading, setUploading] = useState(false)

    const location = useLocation()
    const navigate = useNavigate()

    const dispatch = useDispatch()
 
    const movieDetails = useSelector(state => state.movieDetails)
    const {error, loading, movie} = movieDetails

    const movieUpdate = useSelector(state => state.movieUpdate)
    const {error:errorUpdate, loading:loadingUpdate, success:successUpdate} = movieUpdate



    useEffect(() => {

        if(successUpdate){
          dispatch({type:MOVIE_UPDATE_RESET})
          navigate('/admin/movielist')
        }else{
          if(!movie.name || movie._id !== Number(id)){ 
            dispatch(listMovieDetails(id))
          }
           else{
            setName(movie.name)
            setPrice(movie.price)
            setImage(movie.image)
            setProductionHouse(movie.productionHouse)
            setCategory(movie.category)
            setNumberOfScreens(movie.number_of_screens)
            setDescription(movie.description)
           }
        }

    }, [dispatch, movie, id, successUpdate])

    const submitHandler = (e) => {
      e.preventDefault()
      dispatch(updateMovie({
          _id:id,
          name,
          price,
          image,
          productionHouse,
          category,
          number_of_screens,
          description
      }))
    }

    const uploadFileHandler = async(e) => {
      const file = e.target.files[0]
      const formData = new FormData()

      formData.append('image', file)
      formData.append('movie_id', id)

      setUploading(true)

      try{
          const config = {
            headers:{
              'Content-Type':'multipart/form-data'
            }
          }

          const {data} = await axios.post('/api/product/upload/', formData,config)

          setImage(data)
          setUploading(false)

      } catch(error){
          setUploading(false)
      }
    }
  return (
  <div>

    <Link to='/admin/movielist'>
        Go Back
    </Link>
    <FormContainer>
      <h1>Edit Movie</h1>
      {loadingUpdate && <Loader />}
      {errorUpdate && <Message variant='danger'>{errorUpdate}</Message>}
      {loading ? <Loader /> : error ? <Message variant='danger'>{error}</Message>
           : (
            <Form onSubmit={submitHandler}>

            <Form.Group controlId='name'>
                <Form.Label>Name</Form.Label>
                <Form.Control
                  type='name'
                  placeholder='Enter name'
                  value={name}
                  onChange={(e) => setName(e.target.value)}
                  >
                </Form.Control>
            </Form.Group>

            <Form.Group controlId='price'>
                <Form.Label>Price</Form.Label>
                <Form.Control
                  type='number'
                  placeholder='Enter price'
                  value={price }
                  onChange={(e) => setPrice(e.target.value)}
                  >
                </Form.Control>
            </Form.Group>

            <Form.Group controlId='image'>
                <Form.Label>Image</Form.Label>
                <Form.Control
                  type='text'
                  placeholder='Enter image'
                  value={image}
                  onChange={(e) => setImage(e.target.value)}
                  >
                </Form.Control>

                <input
                  type='file'
                  id ='image-file'
                  label='Choose File'
                  custom
                  onChange={uploadFileHandler}
                  >

                  </input>

                {uploading && <Loader />}
            </Form.Group>

            <Form.Group controlId='productionHouse'>
                <Form.Label>ProductionHouse</Form.Label>
                <Form.Control
                  type='text'
                  placeholder='Enter Production House'
                  value={productionHouse}
                  onChange={(e) => setProductionHouse(e.target.value)}
                  >
                </Form.Control>
            </Form.Group>

            <Form.Group controlId='number_of_screens'>
                <Form.Label>Screens</Form.Label>
                <Form.Control
                  type='number'
                  placeholder='Enter screens'
                  value={number_of_screens}
                  onChange={(e) => setNumberOfScreens(e.target.value)}
                  >
                </Form.Control>
            </Form.Group>

            <Form.Group controlId='category'>
                <Form.Label>Category</Form.Label>
                <Form.Control
                  type='text'
                  placeholder='Enter Category'
                  value={category}
                  onChange={(e) => setCategory(e.target.value)}
                  >
                </Form.Control>
            </Form.Group>

            <Form.Group controlId='description'>
                <Form.Label>Description</Form.Label> 
                <Form.Control
                  type='text'
                  placeholder='Enter description'
                  value={description}
                  onChange={(e) => setDescription(e.target.value)}
                  >
                </Form.Control>
            </Form.Group>

    
            <Button type='submit' variant='primary'>
              Update
            </Button>
          </Form>
      )}
      
    </FormContainer>
  </div>
    
  )
}

export default MovieEditScreen
