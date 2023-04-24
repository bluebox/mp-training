import React, { useEffect} from 'react'
import {Row, Col, Container} from 'react-bootstrap'
import {useDispatch, useSelector} from 'react-redux' 
import { listCompanyJobPosts } from '../actions/companyPostsActions'
import Loader from '../components/Loader'
import Message from '../components/Message'
import CompanyPost from '../components/CompanyPost'

function CompanyHomeScreen() {

  
  const dispatch = useDispatch()
  const companyJobPostList = useSelector(state => state.companyJobPostsList)

  const {error, loading, companyJobs} = companyJobPostList


  useEffect(() => {
    dispatch(listCompanyJobPosts())
  }, [dispatch])

  return (
    <Container>
        <h4>Latest Jobs</h4>
        {loading ? <Loader/>
          : error ? <Message variant='danger'>{error}</Message>
            : companyJobs.length === 0 ? <Message><h5>No Jobs Posted Yet!!!</h5></Message>
            : <Row>
                {companyJobs.map(job => (
                  <Col key={job.id} sm={12} md={6} lg={4} xl={3}>
                      <CompanyPost post={job} />
                  </Col>
            ))}
        </Row>
        }  
    </Container>
  )
}

export default CompanyHomeScreen