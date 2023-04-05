import React from "react";
import { useEffect, useState } from "react";
import { Button, Form } from "react-bootstrap";
import { addInstructor } from "../service/InstructorService";
import Swal from "sweetalert2";

function MRegister() {
    const [formErrors, setFormErrors] = useState({});
    const handleSubmit = (e) => {
        e.preventDefault();
        const errors = {}
        const name_regex = /^[a-zA-Z0-9@_]+$/;
        const email_regex = /^([a-zA-Z0-9\.-]{4,30})([@][a-zA-Z-]{3,10})([\.][a-z]{2,10})$/;/* eslint-disable-line */
        const phone_regex = /^[0-9]{10}$/;
        const quades_regex = /^[a-zA-z-\.]+$/;/* eslint-disable-line */
        const password_regex = /^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$/;
        const username_regex = /^[a-zA-Z]+$/;
        const skills_regex = /^\w+(, \w+)*$/;
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
        if (!quades_regex.test(e.target.qualification.value)) {
            errors.qualification = "Qualification should contain upper case and lower case lettes with dot(.) or dash(-)";
        }
        if (!quades_regex.test(e.target.designation.value)) {
            errors.designation = "Designation should contain upper case and lower case lettes with dot(.) or dash(-)";
        }
        if (!skills_regex.test(e.target.skills.value)) {
            errors.skills = "Skills should be comma seperated word";
        }
        if (!password_regex.test(e.target.password.value)) {
            errors.password = "Password should be of min 8 letter, with at least a symbol, upper and lower case letters and a number!";
        }
        setFormErrors(errors);
        if (Object.keys(errors).length === 0) {
            addInstructor(e.target)
                .then((result) => {
                    console.log(result);
                    if (result === 'Instructor Data Added Successfully') {
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
                        }).then((result) => {
                            if (result.isConfirmed) {
                                window.location.href = '/';
                            }
                        })
                    })
        }
    }
    useEffect(() => {
        document.title = "Instructor Register";

    });
    const instructorLoginStatus = localStorage.getItem('instructorLoginStatus')
    if (instructorLoginStatus === 'true') {
        window.location.href = '/teacher-mycourse';
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
                                <Form.Group controlId="designation">
                                    <Form.Label>Designation</Form.Label>
                                    <Form.Control type="text" name="designation" required placeholder="How do you identify" />
                                </Form.Group>
                                {formErrors.designation &&
                                    <p className="text-danger">{formErrors.designation}</p>
                                }
                                <Form.Group controlId="mobile_no">
                                    <Form.Label>Contact</Form.Label>
                                    <Form.Control type="text" name="mobile_no" required placeholder="Phone Number" />
                                </Form.Group>
                                {formErrors.mobile_no &&
                                    <p className="text-danger">{formErrors.mobile_no}</p>
                                }
                                <Form.Group controlId="skills">
                                    <Form.Label>Skills</Form.Label>
                                    <Form.Control as="textarea" name="skills" required placeholder="Extertise In!!" />
                                    <Form.Text className="text-muted">
                                        Html , Css , Javascript etc.
                                    </Form.Text>
                                </Form.Group>
                                {formErrors.skills &&
                                    <p className="text-danger">{formErrors.skills}</p>
                                }
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