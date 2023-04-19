import React, {useState, useEffect, useContext} from 'react'
import { Form, Button } from 'react-bootstrap'
import { useNavigate } from 'react-router-dom'
import {SearchKeywordContext} from '../App'
function SearchBox() {
    const [keyword, setKeyword] = useContext(SearchKeywordContext)
    const navigate = useNavigate()
    const submitHandler = (e) =>{
        e.preventDefault()
        if (keyword){
            navigate(`/?keyword=${keyword}`)
        }else{
            navigate('/')
        }
    }
  return (
    <div>
        <Form onSubmit={submitHandler} className="d-flex">
            <Form.Control 
                type='text'
                name='q'
                onChange={(e) => setKeyword(e.target.value)}
                placeholder='search'
                className='me-sm-2 ms-sm-5'
                value={keyword}
            ></Form.Control>
            <Button 
                type='submit'
                variant='outline-success'
                className='p-2'
            >
                Search
            </Button>
        </Form>
    </div>
  )
}

export default SearchBox