import React, {useState}from 'react';
import { useEffect } from 'react';
import {Link} from 'react-router-dom'
import {useDispatch, useSelector} from 'react-redux'
import {Row, Col, ListGroup, Image, Button, Modal, Container} from 'react-bootstrap'
import { getJobPostsOfAUser, deleteAppliedJobPost } from '../actions/jobCartActions';
import Message from '../components/Message';
import Loader from '../components/Loader';
import { useRef } from 'react';



function MyJobsScreen() {

    let jobPostRef = useRef('')

    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);

    function handleShow(post_id){
        jobPostRef.current = post_id;
        setShow(true)
    }

    const dispatch = useDispatch();
    const [applicationDeleted, setApplicationDeleted] = useState(false)

    const userAppliedJobs = useSelector(state => state.getUserJobs)
    const {loading, error, jobCartItems} = userAppliedJobs

    const deletedApplication = useSelector(state => state.deleteApplication)
    const {deleting, deleted} = deletedApplication


    function deleteApplication(job_post_id) {
        setShow(false)
        dispatch(deleteAppliedJobPost(job_post_id))
    }

    useEffect(() =>{
        dispatch(getJobPostsOfAUser())
    }, [dispatch, deleted]);

  return (
    <Container>
        <Row>
        <Col md={8}>
            <h1>Applied Jobs</h1>
            {loading ? <Loader/> 
                : deleting ? <Loader />
                : error ? <Message variant='danger'>{error}</Message> 
                : jobCartItems.length === 0 ? (<Message>
                    You have Not Applied any Jobs Yet {' '}<Link to='/'> Go Back</Link>
                </Message>)
                : (
                    <ListGroup variant='flush'>
                        {jobCartItems.map(item => (
                            <ListGroup.Item key={item.id}>
                                <Row>
                                    <Col md={2}>
                                        <Image src={'http://localhost:8000' + item.job_post.company.image} alt={'error_loading'} fluid rounded/>
                                    </Col>
                                    <Col md={3}>
                                        <Link to={`/post/${item.job_post.id}`}>{item.job_post.role}</Link>
                                    </Col>
    
                                    <Col md={2}>
                                        {item.job_post.company.company_name}
                                    </Col>
    
                                    <Col md={2}>
                                    Posted On :  {item.job_post.posted_on}
                                    
                                    </Col>

                                    <Col md={2}>
                                        <Button onClick={() => handleShow(item.job_post.id)}>
                                            Delete Application
                                        </Button>
                                    </Col>


                                </Row>
                            </ListGroup.Item>
                        ))}
                    </ListGroup>
                )}
        </Col>
       </Row>

        <>
        <Modal show={show} onHide={handleClose}>
          <Modal.Header closeButton>
            <Modal.Title>Are you sure to delete ?? </Modal.Title>
          </Modal.Header>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleClose}>
              Close
            </Button>
            <Button onClick={() => deleteApplication(jobPostRef.current)}>
                Confirm
            </Button>
          </Modal.Footer>
        </Modal>
      </>

    </Container>
  )
}

export default MyJobsScreen
