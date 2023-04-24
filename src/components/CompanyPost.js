import React from 'react'
import {Card} from 'react-bootstrap'
import Button from 'react-bootstrap/Button';
import { Link } from 'react-router-dom'

function CompanyPost({post}) {

    const editJobPost = () => {
        console.log('editJobPoss')
    }

    const deleteJobPost = () => {
        console.log('deleteJobPoss')
    }
  return (
  
        <Card  className='my-3 p-3 rounded ' >
            <Link to={`/company-post/${post.id}`}>
            <Card.Img style={{height: '9rem'}} src={'http://localhost:8000' + post.company.image} />
            </Link>
        

            <Card.Body>
                <Link to={`/company-post/${post.id}`}>
                    <Card.Title as='div'>
                        <strong>{post.role}</strong>
                    </Card.Title>
                </Link>

                <Card.Title as='div'>
                        <strong>{post.company.company_name}</strong>
                    </Card.Title>

                <Card.Text as='div'>
                    <div className='my-3'>
                        Department : {post.department} 
                    </div>
                </Card.Text>

                <Card.Text as='div'>
                <i class="fas fa-calendar-days"></i> Posted on: {post.posted_on}
                </Card.Text>

                <Card.Text as='div'>
                <i className='fas fa-location-dot'></i> location: {post.location}
                </Card.Text>
            </Card.Body>
        
    </Card>

  )
}

export default CompanyPost