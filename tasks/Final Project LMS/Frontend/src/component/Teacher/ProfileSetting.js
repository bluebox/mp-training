import React from "react";
import MSidebar from "./MSidebar";
import { useState, useEffect } from "react";
import { Button, Form } from "react-bootstrap";
import { updateInstructor } from "../service/InstructorService";
import axios from "axios";
import Swal from "sweetalert2";
const baseUrl = "http://127.0.0.1:8000/api/teacher/";
function MProfileSetting() {
    const accessToken = localStorage.getItem('accessToken');
    const refreshToken = localStorage.getItem('refreshToken');
    const [instructorData, setInstructorData] = useState({
        'username': '',
        'first_name': '',
        'last_name': '',
        'email': '',
        'password': '',
        'qualification': '',
        'designation': '',
        'mobile_no': '',
        'skills': '',
    });
    const teacher_id = localStorage.getItem('teacherId');
    useEffect(() => {
        if (instructorData.first_name === "") {
            try {
                axios.get(baseUrl + teacher_id + '/', {
                    headers: { 'Authorization': `Bearer ${accessToken}` }
                })
                    .then((res) => {
                        setInstructorData({
                            username: res.data.user.username,
                            first_name: res.data.user.first_name,
                            last_name: res.data.user.last_name,
                            email: res.data.user.email,
                            password: res.data.userpassword,
                            qualification: res.data.qualification,
                            designation: res.data.designation,
                            mobile_no: res.data.mobile_no,
                            skills: res.data.skills,
                        });
                    }).catch((error) => {
                        console.log(error.response);
                        if (error.response.status === 401) {
                            axios.post('http://127.0.0.1:8000/api/api/token/refresh/', { refresh: refreshToken })
                                .then((response) => {
                                    const newAccessToken = response.data.access;
                                    localStorage.setItem('accessToken', newAccessToken);
                                    axios.get(baseUrl + teacher_id + '/', {
                                        headers: { 'Authorization': `Bearer ${newAccessToken}` }
                                    })
                                        .then((res) => {
                                            setInstructorData({
                                                username: res.data.user.username,
                                                first_name: res.data.user.first_name,
                                                last_name: res.data.user.last_name,
                                                email: res.data.user.email,
                                                password: res.data.userpassword,
                                                qualification: res.data.qualification,
                                                designation: res.data.designation,
                                                mobile_no: res.data.mobile_no,
                                                skills: res.data.skills,
                                            });
                                        })
                                        .catch((error) => {
                                            console.log(error.response);
                                        });
                                })
                                .catch((error) => {
                                    if (error.response.status === 401 && error.response.data.code === 'token_not_valid') { // Unauthorized - refresh token expired
                                        window.location.href = '/user-logout';
                                    }
                                });
                        }
                        else {
                            console.log(error);
                        }
                    });
            } catch (error) {
                console.log(error);
            }
        }

    }, []);
    const [formErrors, setFormErrors] = useState({});
    const handleSubmit = (e) => {
        e.preventDefault();
        const errors = {}
        const name_regex = /^[a-zA-Z0-9@_]+$/;
        const email_regex = /^([a-zA-Z0-9\.-]{4,30})([@][a-zA-Z-]{3,10})([\.][a-z]{2,10})$/;/* eslint-disable-line */
        const phone_regex = /^[0-9]{10}$/;
        const quades_regex = /^[a-zA-Z-\.]+$/;/* eslint-disable-line */
        const password_regex = /^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$/;
        const username_regex = /^[a-zA-Z]+$/;
        const skills_regex = /^\w+(, \w+)*$/
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
        if (e.target.password.value !== "") {
            if (!password_regex.test(e.target.password.value)) {
                errors.password = "Password should be of min 8 letter, with at least a symbol, upper and lower case letters and a number!";
            }
        }
        else {
            errors.password = "This field must not be empty. Enter old/new password"
        }
        setFormErrors(errors);
        if (Object.keys(errors).length === 0) {
            try {
                console.log(e.target);
                console.log(teacher_id);
                updateInstructor(e.target, teacher_id)
                    .then((result) => {
                        if (result === 'success') {
                            Swal.fire({
                                title: "Data Updated Successfully",
                                confirmButtonText: 'Go Back',
                            }).then(() => {
                                window.location.href = '/master-mycourse';
                            })
                        }
                    }, (error) => {
                        alert(error);
                    })
                    .catch((error) => {
                        console.log(error);
                    })
            } catch (error) {
                Swal.fire({
                    title: "Data Updated Successfully",
                    confirmButtonText: 'Go Back',
                }).then(() => {
                    window.location.href = '/master-mycourse';
                })
            }
        }
    }
    const handlechange = (e) => {
        setInstructorData({
            ...instructorData, [e.target.name]: e.target.value
        });
    }
    return (
        <div className="container mt-4">
            <div className="row">
                <section className="col-md-9">
                    <div className="card">
                        <h5 className="card-header"> Update Profile</h5>
                        <div className="card-body">
                            <Form onSubmit={handleSubmit}>
                                <Form.Group controlId="username">
                                    <Form.Label className="fw-bold">UserName</Form.Label>
                                    <Form.Control type="text" name="username" value={instructorData.username} onChange={handlechange} />
                                </Form.Group>
                                {formErrors.username &&
                                    <p className="text-danger">{formErrors.username}</p>
                                }
                                <Form.Group controlId="first_name">
                                    <Form.Label className="fw-bold">First Name</Form.Label>
                                    <Form.Control type="text" name="first_name" value={instructorData.first_name} onChange={handlechange} />
                                </Form.Group>
                                {formErrors.first_name &&
                                    <p className="text-danger">{formErrors.first_name}</p>
                                }
                                <Form.Group controlId="last_name">
                                    <Form.Label className="fw-bold">Last Name</Form.Label>
                                    <Form.Control type="text" name="last_name" value={instructorData.last_name} onChange={handlechange} />
                                </Form.Group>
                                {formErrors.last_name &&
                                    <p className="text-danger">{formErrors.last_name}</p>
                                }
                                <Form.Group controlId="email">
                                    <Form.Label className="fw-bold">Email</Form.Label>
                                    <Form.Control type="text" name="email" value={instructorData.email} onChange={handlechange} />
                                </Form.Group>
                                {formErrors.email &&
                                    <p className="text-danger">{formErrors.email}</p>
                                }
                                <Form.Group controlId="password">
                                    <Form.Label className="fw-bold">Password</Form.Label>
                                    <Form.Control type="password" name="password" placeholder="Enter Password" onChange={handlechange} />
                                </Form.Group>
                                {formErrors.password &&
                                    <p className="text-danger">{formErrors.password}</p>
                                }
                                <Form.Group controlId="qualification">
                                    <Form.Label className="fw-bold">Quailification</Form.Label>
                                    <Form.Control type="text" name="qualification" value={instructorData.qualification} onChange={handlechange} />
                                </Form.Group>
                                {formErrors.qualification &&
                                    <p className="text-danger">{formErrors.qualification}</p>
                                }
                                <Form.Group controlId="designation">
                                    <Form.Label className="fw-bold">Designation</Form.Label>
                                    <Form.Control type="text" name="designation" value={instructorData.designation} onChange={handlechange} />
                                </Form.Group>
                                {formErrors.designation &&
                                    <p className="text-danger">{formErrors.designation}</p>
                                }
                                <Form.Group controlId="mobile_no">
                                    <Form.Label className="fw-bold">Contact</Form.Label>
                                    <Form.Control type="text" name="mobile_no" value={instructorData.mobile_no} onChange={handlechange} />
                                </Form.Group>
                                {formErrors.mobile_no &&
                                    <p className="text-danger">{formErrors.mobile_no}</p>
                                }
                                <Form.Group controlId="skills">
                                    <Form.Label className="fw-bold">Skills</Form.Label>
                                    <Form.Control as="textarea" name="skills" value={instructorData.skills} onChange={handlechange} />
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
                </section>
                <aside className="col-md-3">
                    <MSidebar />
                </aside>
            </div>
        </div>
    )
}

export default MProfileSetting;