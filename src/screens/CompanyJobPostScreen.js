import React, {useState, useEffect} from 'react'
import { Link} from 'react-router-dom';
import {Row, Col, Image, ListGroup, Button, Card, Modal, Container} from 'react-bootstrap';
import { useParams, useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { listCompanyJobPostDetails } from '../actions/companyPostsActions';
import {appliedJobPostCandidatesDetails, deleteCompanyJobPost} from '../actions/companyPostsActions'
import { viewAppliedCandidates } from '../actions/userActions';
import Loader from '../components/Loader';
import Message from '../components/Message';
import axios from 'axios';



function CompanyJobPostScreen() {
    const [obtainCandidates, setObtainCandidates] = useState(false);
    const {id} = useParams()
    const dispatch = useDispatch()
    const jobPostDetails = useSelector(state => state.companyJobPostDetails)
    const appliedCandidatesOfAPost = useSelector(state => state.appliedCandidatesForCompany)
    const {loading, error, companyJob} = jobPostDetails
    const {appliedCandidates} = appliedCandidatesOfAPost
    console.log(appliedCandidates)
    const navigate = useNavigate();

    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);

    const handleShow = () => setShow(true);

    
    useEffect(() => {
        dispatch(listCompanyJobPostDetails(id))
    }, [dispatch, id])

    useEffect(() => {
        dispatch(viewAppliedCandidates(id))
    }, [dispatch, id])


    const viewAppliedCandiates = (e) => {
        e.preventDefault()
        setObtainCandidates(true)
    }

    function viewCandidateDetails(candidate, e){
        e.preventDefault()
        dispatch(appliedJobPostCandidatesDetails(candidate))
        navigate(`/candidate-details?id=${candidate}`)
    }

    const editJobPost = (e) => {
        e.preventDefault()
        navigate(`/edit-job-post?job_id=${id}`)
    }

    const deleteJobPost = (e) => {
        e.preventDefault()
        dispatch(deleteCompanyJobPost(id))
        navigate('/home-screen')
    }

    const forceDownload = (response, title, extension) =>{
        console.log(response)
        const url = window.URL.createObjectURL(new Blob([response.data]))
        const link = document.createElement('a')
        link.href = url
        console.log(title)
        console.log(extension)
        link.setAttribute('download', title + "." + extension)
        document.body.appendChild(link)
        link.click()


    }

    const downloadWithAxios = (url, title)=>{
        const regexAll = /[^\\]*\.(\w+)$/;
        console.log("url", url)

        const total = url.match(regexAll);
        const extension = total[1];
        console.log("Extension",extension)
        axios({
            method: 'get',
            url,
            responseType: 'arraybuffer'
        }).then((response)=>{
            forceDownload(response, title, extension)
        }).catch((error)=> console.log(error))

    }

  return (
    <Container>
        <Link to='/home-screen' className='btn btn-light my-3'>Go Back</Link>
        {loading ?
            <Loader/>
            : error
                ? <Message variant='danger'>{error}</Message>
                : (
        <Row>
            <Col md={6}>
                <Image src={'http://localhost:8000' + companyJob.company.image} alt={companyJob.company.company_name} fluid style={{height:'25%', width:'25%'}}/>
                <ListGroup variant='flush'>
                    <ListGroup.Item>
                        <h3>{companyJob.company.company_name}</h3>
                    </ListGroup.Item>

                    <ListGroup.Item>
                        Company Name : {companyJob.company.company_name}
                    </ListGroup.Item>

                    <ListGroup.Item>
                        Description : {companyJob.company.description}
                    </ListGroup.Item>
                </ListGroup>
            </Col>

            <Col md={3}>
                <ListGroup variant='flush'>
                    <ListGroup.Item>
                        <h3>{companyJob.role}</h3>
                    </ListGroup.Item>

                    <ListGroup.Item>
                        Company Name : {companyJob.company.company_name}
                    </ListGroup.Item>

                    <ListGroup.Item>
                        Job Type : {companyJob.job_type}
                    </ListGroup.Item>

                    <ListGroup.Item>
                        Description : {companyJob.job_description}
                    </ListGroup.Item>

                    <ListGroup.Item>
                        Skills Required : {companyJob.skills_required}
                    </ListGroup.Item>
                </ListGroup>
            </Col>

            <Col md={3}>
                <Card>
                    <ListGroup variant='flush'>
                        <ListGroup.Item>
                            <Row>
                                <Button 
                                onClick={viewAppliedCandiates}
                                className='btn-block' 
                                type='button'
                                >
                                    View Applied Canditates
                                </Button>
                            </Row>
                        </ListGroup.Item>

                        <ListGroup.Item>
                            <Row>
                                <Button 
                                onClick={editJobPost}
                                className='btn-block' 
                                type='button'
                                >
                                    Edit Job Post
                                </Button>
                            </Row>
                        </ListGroup.Item>

                        <ListGroup.Item>
                            <Row>
                                <Button 
                                onClick={handleShow}
                                className='btn-block' 
                                type='button'
                                >
                                    Delete Job Post
                                </Button>
                            </Row>
                        </ListGroup.Item>

                        
                    </ListGroup>
                </Card>
            </Col>
        </Row>
                )
        }

        <>
        <Modal show={show} onHide={handleClose}>
          <Modal.Header closeButton>
            <Modal.Title>Are you sure to delete ?? </Modal.Title>
          </Modal.Header>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleClose}>
              Close
            </Button>
            <Button onClick={deleteJobPost}>
                Confirm
            </Button>
          </Modal.Footer>
        </Modal>
      </>

    {obtainCandidates && (<Row>
        <Col md={8}>
            <h1>Applied Candidates</h1>
                { appliedCandidates.length === 0 ? (<Message>
                    No one Applied Yet !!! 
                </Message>)
                : (
                    <ListGroup variant='flush'>
                        {appliedCandidates.map(item => (
                            <ListGroup.Item key={item.applicant.id}>
                                <Row>
                                    <Col md={3}>
                                        {item.applicant.username}
                                    </Col>
    
                                    <Col md={2}>
                                        {item.applicant.first_name}
                                    </Col>

                                    <Col md={2} style={{marginRight:'10px'}}>
                                        <Row>
                                        <Button 
                                            onClick={(e) => viewCandidateDetails(item.applicant.id, e)}
                                            className='btn-block' 
                                            type='button'
                                            size='sm'
                                            >
                                                View Details
                                        </Button>
                                        </Row>
                                    </Col>


                                    <Col md={2}>
                                        <Row>
                                            <Button 
                                                onClick={(e) => downloadWithAxios('http://localhost:8000' + item.resume, item.applicant.first_name)}
                                                className='btn-block' 
                                                type='button'
                                                size='sm'
                                                variant='success'
                                                >
                                                    Download Resume
                                            </Button>
                                        </Row>
                                    </Col>
    
                                </Row>
                            </ListGroup.Item>
                            
                        ))}
                    </ListGroup>
                )}
        </Col>
    </Row>)}
    </Container>
  )
}

export default CompanyJobPostScreen