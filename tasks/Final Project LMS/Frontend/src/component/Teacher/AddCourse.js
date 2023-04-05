import React from "react";
import MSidebar from "./MSidebar";
import { useState, useEffect } from "react";
import axios from "axios";
import { Form, Button } from "react-bootstrap";
import { addCourse } from "../service/InstructorService";
import Swal from "sweetalert2";
const baseUrl = "http://127.0.0.1:8000/api/sub_category/";
function AddCourse() {
    const [formErrors, setFormErrors] = useState({});
    const [subcategory, setSubCategory] = useState([]);
    const [picture, setPicture] = useState();
    const accessToken = localStorage.getItem('accessToken');
    const refreshToken = localStorage.getItem('refreshToken');
    useEffect(() => {
        if (subcategory.length === 0) {
            try {
                axios.get(baseUrl, {
                    headers: { 'Authorization': `Bearer ${accessToken}` }
                }).then((res) => {
                    setSubCategory(res.data);
                }).catch((error) => {
                    if (error.response.status === 401) {
                        axios.post('http://127.0.0.1:8000/api/api/token/refresh/', { refresh: refreshToken })
                            .then((response) => {
                                const newAccessToken = response.data.access;
                                localStorage.setItem('accessToken', newAccessToken);
                                axios.get(baseUrl, {
                                    headers: { 'Authorization': `Bearer ${newAccessToken}` }
                                })
                                    .then((res) => {
                                        setSubCategory(res.data);
                                    })
                                    .catch((error) => {
                                        console.log(error.response);
                                    });
                            })
                            .catch((error) => {
                                if (error.response.status === 401 && error.response.data.code === 'token_not_valid') {
                                    window.location.href = '/user-logout';
                                }
                            });
                    }
                    else {
                        console.log(error);
                    }
                });
            }
            catch (err) {
                console.log(err);
            }
        }

    }, [picture]);
    // console.log(subcategory);

    const submitForm = (e) => {
        const errors = {};
        e.preventDefault();
        const title_regex = /^[a-zA-Z0-9 @_.:']{5,100}$/;
        const image_regex = /\.(jpe?g|png|gif|bmp)$/i;
        const description_regex = /^.{5,500}$/;
        const language_regex = /^\w+(, \w+)*$/;
        if (!title_regex.test(e.target.title.value)) {
            errors.title = "Title can only contain lower case and upper case letters numbers and @ : ' . _ symbol";
        }
        if (!description_regex.test(e.target.description.value)) {
            errors.description = "Description can only contain 5-500 characters";
        }
        if (!image_regex.test(e.target.image.value)) {
            errors.image = "Select a proper format image with extension jpg, jpeg, png, gif, bmp";
        }
        if (!language_regex.test(e.target.language.value)) {
            errors.language = "Language should be comma seperated word";
        }
        setFormErrors(errors);
        if (Object.keys(errors).length === 0) {
            addCourse(e.target, picture)
                .then((result) => {
                    if (result === 'Course Data Added Successfully') {
                        Swal.fire({
                            title: "Course Added",
                            confirmButtonText: 'Ok',
                        }).then(() => {
                            window.location.href = '/master-mycourse';
                        })
                    }
                }, (err) => {
                    alert(err);
                })
        }
    }

    const handleImageUpload = (e) => {
        setPicture(e.target.files[0]);


    }

    return (
        <div className="container mt-4">
            <div className="row">
                <section className="col-9">
                    <div className="card">
                        <h5 className="card-header">
                            About Course
                        </h5>
                        <div className="card-body">
                            <Form onSubmit={submitForm}>
                                <Form.Group controlId="subcategory">
                                    <Form.Label>Sub-Category <span className="text-danger">*</span></Form.Label>
                                    <Form.Select>{subcategory.map((name, id) => { return <option key={id} value={name.id}>{name.name}</option> })}</Form.Select>
                                </Form.Group>
                                <Form.Group controlId="title">
                                    <Form.Label>Title <span className="text-danger">*</span></Form.Label>
                                    <Form.Control type="text" name="title"  placeholder="Title of your course" />
                                </Form.Group>
                                {formErrors.title &&
                                    <p className="text-danger">{formErrors.title}</p>
                                }
                                <Form.Group controlId="description">
                                    <Form.Label>Description <span className="text-danger">*</span></Form.Label>
                                    <Form.Control type="text" name="description"  placeholder="Description about your course" />
                                </Form.Group>
                                {formErrors.description &&
                                    <p className="text-danger">{formErrors.description}</p>
                                }
                                <Form.Group controlId="image" className="mt-2">
                                    <Form.Label>Featured Image <span className="text-danger">*</span></Form.Label><br />
                                    <input type="file" accept="image/png, image/jpeg" onChange={handleImageUpload} name="image" placeholder="Choose Image for thumbnail" />
                                </Form.Group>
                                {formErrors.image &&
                                    <p className="text-danger">{formErrors.image}</p>
                                }
                                <Form.Group controlId="language">
                                    <Form.Label>Language <span className="text-danger">*</span></Form.Label>
                                    <Form.Control type="text" name="language"  placeholder="English, Hindi , Japanese etc." />
                                </Form.Group>
                                {formErrors.language &&
                                    <p className="text-danger">{formErrors.title}</p>
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

export default AddCourse;