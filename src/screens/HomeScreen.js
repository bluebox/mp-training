import React, {useEffect} from 'react'
import {Row, Col, Container} from 'react-bootstrap'
import {useDispatch, useSelector} from 'react-redux' 
import { listJobPosts } from '../actions/postsActions'
import Loader from '../components/Loader'
import Message from '../components/Message'
import Post from '../components/Post'
import { useLocation } from 'react-router-dom'
import Paginate from '../components/Paginate'
import FilterComponentByCompany from '../components/FilterComponentByCompany'
import FilterComponentByLocation from '../components/FilterComponentByLocation'

function HomeScreen() {
  const dispatch = useDispatch()
  const jobPostList = useSelector(state => state.jobPostsList)
  const userLogin = useSelector(state => state.userLogin)
  

  const {error, loading, jobs, page, pages} = jobPostList
  const {userInfo} = userLogin


  const { search } = useLocation()
  const keyword = new URLSearchParams(search).get("keyword") || '';
  const currentPage = new URLSearchParams(search).get("page") || 1;
  useEffect(() => {
    dispatch(listJobPosts(keyword, currentPage))
  }, [dispatch, keyword, currentPage])

  return (
    <div style={{marginLeft: '2rem', marginRight: '2rem'}}>
    <Row >
      <Col md={2}>
        <Row>
        {<FilterComponentByCompany/>}
        </Row>
        <Row>
        { <FilterComponentByLocation/>}
        </Row>
      </Col>
        
        <Col>
        {keyword ? <div>Results for "{keyword}"</div> : <h4>Latest Jobs</h4>}
        
        {loading ? <Loader/>
          : error ? <Message variant='danger'>{error}</Message>
            :  jobs.length === 0 ? <Message variant='info'>There are No results based on Your Search</Message>
                : 
                  <div>
                    
                  <Row>
                      {jobs.map(job => (
                        <Col key={job.id} sm={12} md={6} lg={4} xl={3}>
                            <Post post={job} />
                        </Col>
                        
                  ))}
                  </Row>
                  
                  </div>
        }  
        <div className='my-4'>
      <Paginate page={page} pages={pages} keyword={keyword}/>
      </div>
        </Col>
      
    </Row>
    </div>
    
  )
}

export default HomeScreen

{/* <div>
        
        <h1>Latest Jobs</h1>
        <Row>
          <Col md={4}>
        {userInfo && <FilterComponentByCompany/>}
          </Col>
          <Col md={4}>
        {userInfo && <FilterComponentByLocation/>}
        </Col>
        </Row>
        {loading ? <Loader/>
          : error ? <Message variant='danger'>{error}</Message>
            :  jobs.length === 0 ? <Message variant='info'>There are No results based on Your Search</Message>
                : 
                  <div>
                    {keyword && <h4>Results for {keyword}</h4>}
                  <Row>
                      {jobs.map(job => (
                        <Col key={job.id} sm={12} md={6} lg={4} xl={3}>
                            <Post post={job} />
                        </Col>
                        
                  ))}
                  </Row>
                  
                  </div>
        }  
      <div className='my-4'>
      <Paginate page={page} pages={pages} keyword={keyword}/>
      </div>
    </div> */}