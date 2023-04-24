import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import {Form, Button} from 'react-bootstrap'

function SearchBox() {

  const [keyWord, setKeyWord] = useState('')

  const  navigate = useNavigate()

  const submitHandler = (e) => {
    e.preventDefault();
    if(keyWord){
        navigate(`/?keyword=${keyWord}&page=1`)
        setKeyWord('')
    }
  }
  return (
    <Form onSubmit={submitHandler} className="d-flex" >
        <input
            type='text'
            name='q'
            value={keyWord}
            placeholder="Search By role, Company"
            onChange={(e) => setKeyWord(e.target.value)}
            className='rounded search-box'
        ></input>

        <Button 
            type='submit'
            variant='outline-success'
            className='p-1 mx-2'
        >
            <i class="fas fa-magnifying-glass"></i>
        </Button>
    </Form> 
  )
}

export default SearchBox
