import React, {useState, useEffect} from 'react'
import { Form, Button, Row, Col, Image, Container} from 'react-bootstrap'
import Message from '../components/Message'
import Loader from '../components/Loader'
import { useDispatch, useSelector } from 'react-redux'
import { getCompanyProfileDetails, updateCompanyDetailProfile } from '../actions/companyActions'
import { useNavigate } from 'react-router-dom'
import { UPDATE_COMPANY_DETAIL_PROFILE_RESET } from '../constants/companyConstants'


function CompanyProfileScreen() {

    const [industryType, setIndustryType] = useState('')
    const [companyName, setCompanyName] = useState('')
    const [description, setDescription] = useState('')
    const [established, setEstablished] = useState('')
    const [imageFile, setImageFile] = useState({})
    const [image, setImage] = useState('')


    const dispatch = useDispatch();

    const navigate = useNavigate();

    const userDetails = useSelector(state => state.companyDetailedProfile)
    const {error, loading, company} = userDetails

    const userLogin = useSelector(state => state.companyLogin)
    const {companyInfo} = userLogin

    const userUpdateProfile = useSelector(state => state.companyDetailsProfileUpdate)
    const {success} = userUpdateProfile

    useEffect(() => {
        if(image){
            console.log(image)
        }
        if(!companyInfo){
            navigate('/login')
        }else{
            if(!company || !company.company_name || success  ){
                dispatch({type: UPDATE_COMPANY_DETAIL_PROFILE_RESET })
                dispatch(getCompanyProfileDetails())
            }else{
                setCompanyName(company.company_name)
                setIndustryType(company.industry_type)
                setDescription(company.description)
                setEstablished(company.Established)
                setImage(company.image)
            }
        }
    }, [dispatch, navigate, companyInfo, company, image])

    const submitHandler = (e) => {
        e.preventDefault()
            dispatch(updateCompanyDetailProfile(industryType, companyName, description, established, imageFile))
        }


    
  return (
    <Container>
            <h2>Company Profile</h2>

            {error && <Message>{error}</Message>}
            {loading && <Loader />}
            <Form onSubmit={submitHandler}>

                <Form.Group controlId='companyName'>
                    <Form.Label>Company Name</Form.Label>
                    <Form.Control
                        required
                        type='name'
                        placeholder='Enter Company Name'
                        value={companyName}
                        onChange={(e) => setCompanyName(e.target.value)}
                    >
                    </Form.Control>
                </Form.Group>

                <Form.Group controlId='industryType'>
                    <Form.Label>Industry Type</Form.Label>
                    <Form.Control
                        required
                        type='text'
                        placeholder='Enter Industry Type'
                        value={industryType}
                        onChange={(e) => setIndustryType(e.target.value)}
                    >
                    </Form.Control>
                </Form.Group>

                <Form.Group controlId='descrition'>
                    <Form.Label>Description</Form.Label>
                    <Form.Control
                        
                        as="textarea" rows={3}
                        placeholder='Enter Company Description'
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}
                    >
                    </Form.Control>
                </Form.Group>


                <Form.Group controlId='established'>
                    <Form.Label>Established Date</Form.Label>
                    <Form.Control
                        
                        type='date'
                        placeholder='Confirm Password'
                        value={established}
                        onChange={(e) => setEstablished(e.target.value)}
                    >
                    </Form.Control>
                </Form.Group>


                <Form.Group controlId="formFile" className="mb-3">
                    <Form.Label>Company Image: </Form.Label>
                    <Image src={'http://127.0.0.1:8000' + image} style={{width:'30px', height:'30px'}} alt='There is no Image'></Image>
                    <Form.Control
                        type="file" 
                        onChange={(e) => {setImageFile(e.target.files[0])}}
                        required
                    >
                    </Form.Control>
                </Form.Group>


                <Button type='submit' variant='primary'>Update</Button>
            </Form>
    </Container>
  )
}

export default CompanyProfileScreen