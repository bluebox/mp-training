
import React, { useEffect } from "react";
import { Button, Form } from "react-bootstrap";
import { addStudent } from "../service/InstructorService";

function Register() {
    const handleSubmit = (e) => {
        e.preventDefault();
        addStudent(e.target)
            .then((result) => {
                alert(result);
                window.location.reload();
            },
                (error) => {
                    alert("Failed to Add Student");
                })
    }
    useEffect(() => {
        document.title = "Student Register";

    });
    const userLoginStatus = localStorage.getItem('userLoginStatus')
    if (userLoginStatus==='true'){
        window.location.href='/user-dashboard';
    }
    return (
        <div className="container mt-4">
            <div className="row">
                <div className="col-6 offset-3">
                    <div className="card">
                        <h5 className="card-header">
                            Student Register
                        </h5>
                        <div className="card-body">
                            <Form onSubmit={handleSubmit}>
                                <Form.Group controlId="first_name">
                                    <Form.Label>First Name</Form.Label>
                                    <Form.Control type="text" name="first_name" required placeholder="Enter Your FirstName" />
                                </Form.Group>
                                <Form.Group controlId="last_name">
                                    <Form.Label>Last Name</Form.Label>
                                    <Form.Control type="text" name="last_name" required placeholder="Enter Your LastName" />
                                </Form.Group>
                                <Form.Group controlId="email">
                                    <Form.Label>Email</Form.Label>
                                    <Form.Control type="email" name="email" required placeholder="username@example.com" />
                                </Form.Group>
                                <Form.Group controlId="password">
                                    <Form.Label>Password</Form.Label>
                                    <Form.Control type="password" name="password" required placeholder="Minimun 8 characters" />
                                </Form.Group>
                                <Form.Group controlId="qualification">
                                    <Form.Label>Quailification</Form.Label>
                                    <Form.Control type="text" name="qualification" required placeholder="Highest Qualifiation" />
                                </Form.Group>
                                <Form.Group controlId="interested_category">
                                    <Form.Label>Interested to learn</Form.Label>
                                    <Form.Control type="text" name="interested_category" required placeholder="Banking, Development etc." />
                                </Form.Group>
                                <Form.Group controlId="mobile_no">
                                    <Form.Label>Contact</Form.Label>
                                    <Form.Control type="text" name="mobile_no" required placeholder="Phone Number" />
                                </Form.Group>
                                <Form.Group controlId="address">
                                    <Form.Label>Address</Form.Label>
                                    <Form.Control as="textarea" name="address" required placeholder="Region and Country" />
                                </Form.Group>
                                <Form.Group>
                                    <p></p>
                                    <Button variant="primary" type="submit">
                                        Submit
                                    </Button>
                                </Form.Group>
                            </Form>
                        </div>
                    </div>
                </div>
            </div >
        </div >
    )
}

export default Register;