import React from 'react'
import { Card } from 'react-bootstrap'
import Rating from './Rating'
import { Link } from 'react-router-dom'

function Movie({movie}) {
  return (
    <Card className="my-3 p-3 rounded">
      <Link to={`/product/${movie._id}`}>
        <Card.Img src={movie.image} />
      </Link>
      
      <Card.Body>
        <Link to={`/product/${movie._id}`}>
          <Card.Title as="div">
            <strong>{movie.name}</strong>
          </Card.Title>
        </Link>
        <Card.Text as="div">
          <div className="my-3">
            <Rating value={movie.rating} text={`${movie.numReviews} reviews`} color={`#f8e825`} />
          </div>
          </Card.Text>

          <Card.Text as="h3">
            Rs.{movie.price}
          </Card.Text>
      </Card.Body>
    </Card>
  )
}

export default Movie
