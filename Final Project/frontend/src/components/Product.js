import React from 'react'
import { Card } from 'react-bootstrap'
import Rating from './Rating'
import {Link} from 'react-router-dom';

function Product({product}) {
  return (
    <Card className="my-3 p-2 rounded">
        <Link to={`product/${product.id}`} style={{textDecoration: "none"}}>
            <Card.Img src={`http://127.0.0.1:8000${product.image}`} style={{minWidth:"18vw", height:"32vh"}}></Card.Img>
        </Link>
        <Card.Body>
            <Link to={`product/${product.id}`} style={{textDecoration: "none"}}>
                <Card.Title style={{height:"7vh", overflow:"clip"}}><b>{product.name}</b></Card.Title>
                <Card.Text as="div">
                    <Rating color="#ffd700" value={product.rating} text={`${product.numReviews} reviews`}/>
                </Card.Text>
                <Card.Text as="h3">
                    ${product.price}
                </Card.Text>
            </Link>
        </Card.Body>
    </Card>
  )
}

export default Product