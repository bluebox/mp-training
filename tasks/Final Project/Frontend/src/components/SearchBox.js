import React, {useState} from 'react'
import { Button, Form , Col , Row} from 'react-bootstrap'
import { useNavigate, useLocation, useSearchParams } from 'react-router-dom'
import img1 from "../search.svg";
import SearchIcon from '@material-ui/icons/Search';

function SearchBox() {
    const[keywordC, setKeywordC] = useState('')

    let navigate = useNavigate()

    const submitHandler = (e) =>{
        e.preventDefault()
        if(keywordC){
            navigate(`/?keyword=${keywordC}&page=1`)
        }else{
            navigate('/')
        }
    }
  return (
        <Form onSubmit={submitHandler} >
        <Row>
            <Col>
        <Form.Control
            type='text'
            name='q'
            onChange={(e) => setKeywordC(e.target.value)}
            className='mr-sm-2 ml-sm-5'
        ></Form.Control>
        </Col>

    {/* <Button
        type='submit'
        variant='btn btn-primary'
        className='p-2'
    >
        
        Search
    </Button> */}
    <Col>
        <button type='submit' className='p-2'><SearchIcon/></button>  
    </Col>
    <Col>
       
    </Col>
    </Row>
</Form>

  )
}

export default SearchBox
