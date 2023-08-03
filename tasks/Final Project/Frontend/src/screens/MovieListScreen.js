import React, {useState, useEffect} from 'react'
import { Link, Navigate, useLocation, useNavigate} from 'react-router-dom'
import { LinkContainer } from 'react-router-bootstrap'
import { Table, Button, Row, Col } from 'react-bootstrap'
import { useDispatch, useSelector } from 'react-redux'
import Loader from '../components/Loader'
import Message from '../components/Message'
import { listMovies, deleteMovie, createMovie } from '../actions/movieActions'
import { MOVIE_CREATE_RESET } from '../constants/movieConstants'

function MovieListScreen() {

    const dispatch = useDispatch()
    const navigate = useNavigate()
    const movieList = useSelector(state => state.movieList)
    const {loading, error, movies} = movieList

    const movieDelete = useSelector(state => state.movieDelete)
    const {loading: loadingDelete, error: errorDelete, success:successDelete} = movieDelete

    const movieCreate = useSelector(state => state.movieCreate)
    const {loading: loadingCreate, error: errorCreate, success:successCreate, movie:createdMovie} = movieCreate

    const userLogin = useSelector(state => state.userLogin)
    const { userInfo } = userLogin


    useEffect(() => {
        dispatch({type:MOVIE_CREATE_RESET})

        if(!userInfo.isAdmin){
            navigate('/login')
        }
        if(successCreate){
            navigate(`/admin/movie/${createdMovie._id}`)
        }else{
            dispatch(listMovies())
        }
        // dispatch(listUsers())
    }, [dispatch, userInfo, successDelete, successCreate, createMovie])

    const deleteHandler = (id) => {

        if(window.confirm('Are you sure you want to delete the product?')){
            dispatch(deleteMovie(id))
        }
        
    }

    const createMovieHandler = () =>{
        dispatch(createMovie())
    }


  return (
    <div>
        <Row className='align-items-center'>
            <Col>
                <h1>Movies</h1>
            </Col>
            <Col className="text-right">
                <Button className='my-3' onClick={createMovieHandler}>
                   <i className='fas fa-plus'></i> Create Movie
                </Button>
            </Col>
        </Row>

        {loadingDelete && <Loader/>}
        {errorDelete && <Message variant='danger'>{errorDelete}</Message>}

        {loadingCreate && <Loader/>}
        {errorCreate && <Message variant='danger'>{errorCreate}</Message>}

       {loading
        ? (<Loader/>)
        : error
            ? (<Message variant='danger'>{error}</Message>)
            :(
                <Table striped bordered hover responsive className='table-sm'>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>NAME</th>
                            <th>PRICE</th>
                            <th>CATEGORY</th>
                            <th>Production House</th>
                            <th> </th>
                        </tr>
                    </thead>
                    <tbody>
                        {movies.map(movie => (
                            <tr key={movie._id}>
                                <td>{movie._id}</td>
                                <td>{movie.name}</td>
                                <td>Rs.{movie.price}</td>
                                <td>{movie.category}</td>
                                <td>{movie.productionHouse}</td>
                                <td>
                                    <LinkContainer to={`/admin/movie/${movie._id}`}>
                                        <Button variant='light' className='btn-sm'>
                                            <i className='fas fa-edit'></i>
                                        </Button>
                                    </LinkContainer>
                                    <Button variant='danger' className='btn-sm' onClick={() => deleteHandler(movie._id)}>
                                            <i className='fas fa-trash'></i>
                                    </Button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </Table>
            )}
    </div>
  )
                        }
export default MovieListScreen