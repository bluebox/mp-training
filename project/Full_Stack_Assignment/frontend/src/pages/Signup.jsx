import React from 'react';
import { Container, Row, Col, Card } from 'react-bootstrap';
import SignupForm from '../components/auth/SignupForm';

const Signup = () => {
    return (
        <Container className="mt-5">
            <Row className="justify-content-center">
                <Col md={8}>
                    <Card>
                        <Card.Header as="h4" className="text-center bg-primary text-white">
                            Create an Account
                        </Card.Header>
                        <Card.Body>
                            <SignupForm />
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </Container>
    );
};

export default Signup;
