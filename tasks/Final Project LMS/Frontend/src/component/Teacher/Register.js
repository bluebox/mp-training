import React from "react";
import axios from "axios";
import { useEffect, useState } from "react";
import { Button, Form } from "react-bootstrap";
import { addInstructor } from "../service/InstructorService";

const baseUrl = 'http://127.0.0.1:8000/api/teacher/';
function MRegister() {

    const handleSubmit = (e) => {
        e.preventDefault();
        addInstructor(e.target)
            .then((result) => {
                alert(result);
                window.location.reload();
            },
                (error) => {
                    alert("Failed to Add Student");
                })
    }
    {/* End Submit form function */ }
    useEffect(() => {
        document.title = "Instructor Register";

    });
    const instructorLoginStatus = localStorage.getItem('instructorLoginStatus')
    if (instructorLoginStatus=='true'){
        window.location.href='/teacher-dashboard';
    }
    return (
        <div className="container mt-4">
            <div className="row">
                <div className="col-6 offset-3">
                    <div className="card">
                        <h5 className="card-header">
                            Instructor Register
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
                                <Form.Group controlId="designation">
                                    <Form.Label>Designation</Form.Label>
                                    <Form.Control type="text" name="designation" required placeholder="How do you identify" />
                                </Form.Group>
                                <Form.Group controlId="mobile_no">
                                    <Form.Label>Contact</Form.Label>
                                    <Form.Control type="text" name="mobile_no" required placeholder="Phone Number" />
                                </Form.Group>
                                <Form.Group controlId="skills">
                                    <Form.Label>Skills</Form.Label>
                                    <Form.Control as="textarea" name="skills" required placeholder="Extertise In!!" />
                                    <Form.Text className="text-muted">
                                        Html , Css , Javascript etc.
                                    </Form.Text>
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

export default MRegister;