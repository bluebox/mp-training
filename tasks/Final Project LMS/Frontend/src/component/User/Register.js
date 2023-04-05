
import React, { useEffect, useState } from "react";
import { Button, Form } from "react-bootstrap";
import Swal from "sweetalert2";
import { addStudent } from "../service/InstructorService";

function Register() {
    const [formErrors, setFormErrors] = useState({});
    const handleSubmit = (e) => {
        e.preventDefault();
        const errors = {}
        const name_regex = /^[a-zA-Z0-9@_]+$/;
        const email_regex = /^([a-zA-Z0-9\.-]{4,30})([@][a-zA-Z-]{3,10})([\.][a-z]{2,10})$/;/* eslint-disable-line */
        const phone_regex = /^[0-9]{10}$/;
        const password_regex = /^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$/;
        const username_regex = /^[a-zA-Z]+$/;
        const interested_regex = /^\w+(, \w+)*$/;
        if (!name_regex.test(e.target.username.value)) {
            errors.username = "User name can only contain lower case and upper case letters numbers and (@ or _) symbol";
        }
        if (!username_regex.test(e.target.first_name.value)) {
            errors.first_name = "First name can only contain lower case and upper case letters";
        }
        if (!username_regex.test(e.target.last_name.value)) {
            errors.last_name = "Last name can only contain lower case and upper caseletters";
        }
        if (!email_regex.test(e.target.email.value)) {
            errors.email = "Enter a valid email format!";
        }
        if (!phone_regex.test(e.target.mobile_no.value)) {
            errors.mobile_no = "Enter a valid phone number!";
        }
        if (!interested_regex.test(e.target.interested_category.value)) {
            errors.interested_category = "Interested Category should be comma seperated word";
        }
        if (!password_regex.test(e.target.password.value)) {
            errors.password = "Password should be of min 8 letter, with at least a symbol, upper and lower case letters and a number!";
        }
        setFormErrors(errors);
        if (Object.keys(errors).length === 0) {
            addStudent(e.target)
                .then((result) => {
                    if (result === 'Student Data Added Successfully') {
                        Swal.fire({
                            title: "Registered Successfully",
                            confirmButtonText: 'Ok',
                        }).then((result) => {
                            if (result.isConfirmed) {
                                window.location.href = '/';
                            }
                        })
                    }
                },
                    (error) => {
                        Swal.fire({
                            title: "Failed to register",
                            confirmButtonText: 'Ok',
                        }).then(() => {
                                window.location.href = '/';
                        })
                    })
        }
    }
    useEffect(() => {
        document.title = "Student Register";

    });
    const userLoginStatus = localStorage.getItem('userLoginStatus')
    if (userLoginStatus === 'true') {
        window.location.href = '/user-mycourse';
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
                                <Form.Group controlId="username">
                                    <Form.Label>User Name</Form.Label>
                                    <Form.Control type="text" name="username" required placeholder="xyz000" />
                                </Form.Group>
                                {formErrors.username &&
                                    <p className="text-danger">{formErrors.username}</p>
                                }
                                <Form.Group controlId="first_name">
                                    <Form.Label>First Name</Form.Label>
                                    <Form.Control type="text" name="first_name" required placeholder="Enter Your FirstName" />
                                </Form.Group>
                                {formErrors.first_name &&
                                    <p className="text-danger">{formErrors.first_name}</p>
                                }
                                <Form.Group controlId="last_name">
                                    <Form.Label>Last Name</Form.Label>
                                    <Form.Control type="text" name="last_name" required placeholder="Enter Your LastName" />
                                </Form.Group>
                                {formErrors.last_name &&
                                    <p className="text-danger">{formErrors.last_name}</p>
                                }
                                <Form.Group controlId="email">
                                    <Form.Label>Email</Form.Label>
                                    <Form.Control type="email" name="email" required placeholder="username@example.com" />
                                </Form.Group>
                                {formErrors.email &&
                                    <p className="text-danger">{formErrors.email}</p>
                                }
                                <Form.Group controlId="password">
                                    <Form.Label>Password</Form.Label>
                                    <Form.Control type="password" name="password" required placeholder="Minimun 8 characters" />
                                </Form.Group>
                                {formErrors.password &&
                                    <p className="text-danger">{formErrors.password}</p>
                                }
                                <Form.Group controlId="qualification">
                                    <Form.Label>Quailification</Form.Label>
                                    <Form.Control type="text" name="qualification" required placeholder="Highest Qualifiation" />
                                </Form.Group>
                                {formErrors.qualification &&
                                    <p className="text-danger">{formErrors.qualification}</p>
                                }
                                <Form.Group controlId="interested_category">
                                    <Form.Label>Interested to learn</Form.Label>
                                    <Form.Control type="text" name="interested_category" required placeholder="Banking, Development etc." />
                                </Form.Group>
                                {formErrors.interested_category &&
                                    <p className="text-danger">{formErrors.interested_category}</p>
                                }
                                <Form.Group controlId="mobile_no">
                                    <Form.Label>Contact</Form.Label>
                                    <Form.Control type="text" name="mobile_no" required placeholder="Phone Number" />
                                </Form.Group>
                                {formErrors.mobile_no &&
                                    <p className="text-danger">{formErrors.mobile_no}</p>
                                }
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