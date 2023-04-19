import React from 'react'
import { Container, Row, Col} from 'react-bootstrap'

function Footer() {
  return (
    <footer>
    <Container className="text-center py-3">
        <Row>
            <Col>Copyright &copy; NextStore</Col>
        </Row>
    </Container>
    </footer>
  )
}

export default Footer