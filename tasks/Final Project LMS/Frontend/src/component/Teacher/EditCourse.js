import React from "react";
import MSidebar from "./MSidebar";
import { useState, useEffect } from "react";
import { Button, Form } from "react-bootstrap";
import { updateCourse } from "../service/InstructorService";
import axios from "axios";
import Swal from "sweetalert2";
import { useParams } from "react-router-dom";
const baseUrl = "http://127.0.0.1:8000/api/course/";
function EditCourse() {
    const accessToken = localStorage.getItem('accessToken');
    const refreshToken = localStorage.getItem('refreshToken');
    const [picture, setPicture] = useState();
    const [courseData, setCourseData] = useState({
        'title': '',
        'description': '',
        'language': '',
        'image': '',
    });
    const { course_id } = useParams();

    useEffect(() => {
        if (courseData.title === "") {
            try {
                axios.get(baseUrl + course_id + '/', {
                    headers: { 'Authorization': `Bearer ${accessToken}` }
                })
                    .then((res) => {
                        setCourseData(res.data);
                    }).catch((error) => {
                        console.log(error.response);
                        if (error.response.status === 401) {
                            axios.post('http://127.0.0.1:8000/api/api/token/refresh/', { refresh: refreshToken })
                                .then((response) => {
                                    const newAccessToken = response.data.access;
                                    localStorage.setItem('accessToken', newAccessToken);
                                    axios.get(baseUrl + course_id + '/', {
                                        headers: { 'Authorization': `Bearer ${newAccessToken}` }
                                    })
                                        .then((res) => {
                                            setCourseData(res.data);
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
        const title_regex = /^.{5,120}$/; /* eslint-disable-line */
        const description_regex = /^.{10,500}$/;
        const language_regex = /^.{5,30}$/; /* eslint-disable-line */
        const image_regex = /\.(jpe?g|png|gif|bmp)$/i;
        if (!title_regex.test(e.target.title.value)) {
            errors.title = "Enter title between 5 - 120 characters";
        }
        if (!description_regex.test(e.target.description.value)) {
            errors.description = "Enter description between 10 - 500 characters";
        }
        if (e.target.image.value.length > 0 && !image_regex.test(e.target.image.value)) {
            errors.image = "Select a proper format image with extension jpg, jpeg, png, gif, bmp";
        }
        if (!language_regex.test(e.target.language.value)) {
            errors.language = "Enter language between 5-30 characters";
        }
        setFormErrors(errors);
        if (Object.keys(errors).length === 0) {
            updateCourse(e.target, course_id, picture)
                .then((result) => {
                    console.log(result);
                    if (result === 'Course Updated Successfully') {
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
        }
    }
    const handlechange = (e) => {
        setCourseData({
            ...courseData, [e.target.name]: e.target.value
        });
    }

    const handleImageUpload = (e) => {
        setPicture(e.target.files[0]);


    }
    return (
        <div className="container mt-4">
            <div className="row">
                <section className="col-md-9">
                    <div className="card">
                        <h5 className="card-header"> Update Course Details</h5>
                        <div className="card-body">
                            <Form onSubmit={handleSubmit}>
                                <Form.Group controlId="title">
                                    <Form.Label className="fw-bold">Title</Form.Label>
                                    <Form.Control type="text" name="title" value={courseData.title} onChange={handlechange} />
                                </Form.Group>
                                {formErrors.first_name &&
                                    <p className="text-danger">{formErrors.title}</p>
                                }
                                <Form.Group controlId="description">
                                    <Form.Label className="fw-bold">Description</Form.Label>
                                    <Form.Control type="text" name="description" value={courseData.description} onChange={handlechange} />
                                </Form.Group>
                                {formErrors.description &&
                                    <p className="text-danger">{formErrors.description}</p>
                                }
                                <Form.Group controlId="language">
                                    <Form.Label className="fw-bold">Language</Form.Label>
                                    <Form.Control type="text" name="language" value={courseData.language} onChange={handlechange} />
                                </Form.Group>
                                {formErrors.language &&
                                    <p className="text-danger">{formErrors.language}</p>
                                }

                                <Form.Group controlId="image">
                                    <Form.Label className="fw-bold">Image</Form.Label><br />
                                    <input type="file" accept="image/png, image/jpeg" onChange={handleImageUpload} name="image" placeholder="Choose Image" /><br />
                                    <Form.Text className="text">
                                        <b>Selected Image :</b><img src={`http://127.0.0.1:8000` + courseData.image} width="80" className="rounded ms-4" alt={courseData.title} />

                                    </Form.Text>
                                </Form.Group>
                                {formErrors.image &&
                                    <p className="text-danger">{formErrors.image}</p>
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
            </div >
        </div >
    )
}

export default EditCourse;