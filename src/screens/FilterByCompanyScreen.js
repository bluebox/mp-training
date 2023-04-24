import React, {useEffect} from 'react'
import {Row, Col} from 'react-bootstrap'
import {useDispatch, useSelector} from 'react-redux' 
import { listJobPosts } from '../actions/postsActions'
import Loader from '../components/Loader'
import Message from '../components/Message'
import Post from '../components/Post'
import { useLocation } from 'react-router-dom'
import FilterComponentByCompany from '../components/FilterComponentByCompany'
import FilterComponentByLocation from '../components/FilterComponentByLocation'

function FilterByCompanyScreen() {
  const userLogin = useSelector(state => state.userLogin)
  const companiesByFilter = useSelector(state => state.companiesListByFilter)
  

  const {userInfo} = userLogin
  const {error, listLoading, companiesList} = companiesByFilter

  return (
    <div style={{marginLeft: '2rem', marginRight: '2rem'}} >
      <Row>
        <Col md={2}>
          <Row>
          {<FilterComponentByCompany/>}
          </Row>
          <Row>
          {<FilterComponentByLocation/>}
          </Row>
        </Col>

        <Col>
        <h1>Latest Jobs</h1>
        {listLoading ? <Loader /> 
          : error ? <Message variant='danger'>{error}</Message>
              : companiesList.length === 0 ? <Message variant='info'> There are No results based on selected company</Message>
                : 
                  <div>
                  <Row>
                      {companiesList.map(job => (
                        <Col key={job.id} sm={12} md={6} lg={4} xl={3}>
                            <Post post={job} />
                        </Col>
                        
                  ))}
                  </Row>
                  
                  </div>
        }
        </Col>
      </Row>
    </div>
  )
}

export default FilterByCompanyScreen

{/* <div style={{marginLeft: '2rem', marginRight: '2rem'}}>
    <Row >
      <Col md={2}>
        <Row>
        {userInfo && <FilterComponentByCompany/>}
        </Row>
        <Row>
        {userInfo && <FilterComponentByLocation/>}
        </Row>
      </Col>
        
        <Col>
        <h1>Latest Jobs</h1>
        
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
        </Col>
      
    </Row>
    </div> */}