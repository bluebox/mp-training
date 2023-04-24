import React, {useState, useEffect} from 'react'
import { Link} from 'react-router-dom';
import {Row, Col, Image, ListGroup, Button, Card, Form, Container} from 'react-bootstrap';
import { useParams, useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { listJobPostDetails } from '../actions/postsActions';
import Loader from '../components/Loader';
import { addJobPostToCart, resetAddedPost } from '../actions/jobCartActions'
import { getJobPostsOfAUser } from '../actions/jobCartActions';
import Modal from 'react-bootstrap/Modal';



function JobPostScreen() {
    
    const [show, setShow] = useState(false);
    const [resume, setResume] = useState({})
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    const {id} = useParams()
    const dispatch = useDispatch()
    const jobPostDetails = useSelector(state => state.jobPostDetails)
    const userJobDetails = useSelector(state => state.userJobs)
    const userLoginDetails = useSelector(state => state.userLogin)
    const {error, addedItem} = userJobDetails
    const {userInfo} = userLoginDetails
    const {loading, job} = jobPostDetails
    const navigate = useNavigate();

    const userAppliedJobs = useSelector(state => state.getUserJobs)
    const {load, jobCartItems} = userAppliedJobs

    
    useEffect(() => {
        dispatch(listJobPostDetails(id))
        dispatch(getJobPostsOfAUser())
    }, [dispatch, id])

    useEffect(() => {
        if(addedItem){
            dispatch(resetAddedPost())
            navigate('/job-cart')
        }
    }, [addedItem])

    const addToJobCartHandler2 = (e) => {
        if(userInfo){
            e.preventDefault()
            dispatch(addJobPostToCart(id, resume))
        }else{
            navigate('/login')
        }
        
    }


  return (
    <Container>
        <Link to='/' className='btn btn-light my-3'>Go Back</Link>
        {loading ?
            <Loader/> : load ? <Loader />
                : (
        <Row>
            <Col md={6}>
                <Image src={'http://localhost:8000' + job.company.image} alt={job.company.company_name} fluid style={{height:'30%', width:'25%', backgroundColor:'white'}} />
                <ListGroup variant='flush'>
                    <ListGroup.Item>
                        <h3>{job.company.company_name}</h3>
                    </ListGroup.Item>

                    <ListGroup.Item>
                        Company Name : {job.company.company_name}
                    </ListGroup.Item>

                    <ListGroup.Item>
                        Description : {job.company.description}
                    </ListGroup.Item>
                </ListGroup>
            </Col>

            <Col md={3}>
                <ListGroup variant='flush'>
                    <ListGroup.Item>
                        <h3>{job.role}</h3>
                    </ListGroup.Item>

                    <ListGroup.Item>
                        Company Name : {job.company.company_name}
                    </ListGroup.Item>

                    <ListGroup.Item>
                        Job Type : {job.job_type}
                    </ListGroup.Item>

                    <ListGroup.Item>
                        Description : {job.job_description}
                    </ListGroup.Item>

                    <ListGroup.Item>
                        Skills Required : {job.skills_required}
                    </ListGroup.Item>
                </ListGroup>
            </Col>

            <Col md={3}>
                <Card>
                    <ListGroup variant='flush'>
                        <ListGroup.Item>
                            
                            <Row>
                                {userInfo && jobCartItems.filter(jobItem => jobItem.job_post.id == id).length >= 1 ? 
                                <Button 
                                className='btn-block applied' 
                                type='button'
                                disabled
                                >
                                    Already Applied !
                                </Button> 
                                : userInfo && 
                                <Button 
                                onClick={handleShow}
                                className='btn-block' 
                                type='button'
                                >
                                    Apply to Job
                                </Button> 
                                }

                                {!userInfo && 
                                    <Button
                                        onClick={addToJobCartHandler2}
                                        className='btn-block'
                                        type='button'
                                    >
                                        Apply to Job
                                    </Button>
                                }
                                
                            </Row>
                        </ListGroup.Item>
                    </ListGroup>
                </Card>
            </Col>
        </Row>
                )
        }

        {userInfo && 
        <Modal
            show={show}
            onHide={handleClose}
            backdrop="static"
            keyboard={false}
            size="lg"
            aria-labelledby="contained-modal-title-vcenter"
            centered
        >
            <Modal.Header closeButton>
            <Modal.Title>Upload Resume to continue</Modal.Title>
            </Modal.Header>
            <Modal.Body>
            <Form >
                <Form.Group controlId="resume" className="mb-3">
                    <Form.Label>Upload Resume</Form.Label>
                    <Form.Control 
                        type="file" 
                        onChange={(e) => setResume(e.target.files[0])}/>
                </Form.Group>
            </Form>
            </Modal.Body>
            <Modal.Footer>
            <Button variant="secondary" onClick={handleClose}>
                Cancel
            </Button>
            <Button variant="primary" type='button' onClick={addToJobCartHandler2} >Apply</Button>
            </Modal.Footer>
      </Modal>}

        
    </Container>
  )
}

export default JobPostScreen