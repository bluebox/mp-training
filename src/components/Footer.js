import React from 'react'
import { Container, Row, Col } from 'react-bootstrap';


function Footer() {
  return (
    <div id='footer'>
        <footer>
            <Container>
                <Row>
                    <Col className='text-center'>Copyright &copy; jobmart </Col>
                </Row>
            </Container>
        </footer>
    </div>
  )
}

export default Footer