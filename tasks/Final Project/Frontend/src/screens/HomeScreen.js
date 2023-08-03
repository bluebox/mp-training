import React, {useState, useEffect } from 'react'
import {Row, Col} from 'react-bootstrap'
import movies from '../product'
import { useDispatch, useSelector } from 'react-redux'
import Movie from '../components/Movie'
import { useLocation } from 'react-router-dom'
import Loader from '../components/Loader'
import Message from '../components/Message'
import Paginate from '../components/Paginate'
import { listMovies } from '../actions/movieActions'
import Suicide  from '../components/Suicide.jpg'
import images1  from '../components/images1.jpeg'
import Manjhi  from '../components/Manjhi.jpeg'
import {Link} from 'react-router-dom'
import Slider from '../components/Slider'


// import axios from 'axios'


function HomeScreen() {
  const dispatch = useDispatch()
  //const [movies, setMovies] = useState([])
  const {search} = useLocation()
  const movieList = useSelector(state => state.movieList)
  const { error, loading, movies, page, pages } = movieList
  let keyword = new URLSearchParams(search).get('keyword') || ''
  let pageNo = new URLSearchParams(search).get('page') || 1

  useEffect(()=> {
      dispatch(listMovies(keyword, pageNo))
  }, [dispatch, keyword, pageNo])

  // const movies = []
  return (
    <div>
       <h1>Upcoming Movies</h1>
        <Slider />          
          

      <section className='section'>
       {loading ? <Loader/>
        : error ? <Message variant='danger'>{error}</Message>
          :
          <div>
          <Row>
            {movies.map(movie => (
              <Col key={movie._id} sm={12} md={6} lg={4} xl={3}>
                  <Movie movie={movie} />
              </Col>
            ))}
          </Row>
          <Paginate page={page} pages={pages} keyword={keyword}/>
          </div>
        }
        </section>
    </div>
    
  )
}

export default HomeScreen
