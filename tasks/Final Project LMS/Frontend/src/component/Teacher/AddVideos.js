import React, { useState } from "react";
import { Button, Form } from "react-bootstrap";
import { useParams } from "react-router-dom";
import Swal from "sweetalert2";
import { addTopic } from "../service/InstructorService";
import MSidebar from "./MSidebar";

function AddVideos() {
    const [formErrors, setFormErrors] = useState({});
    let { course_id } = useParams();
    let { course_title } = useParams();
    const submitForm = (e) => {
        e.preventDefault();
        const errors = {};
        const name_regex = /^[a-zA-Z0-9 @_.:']+$/;
        const url_regex = /^https?:\/\/(?:www\.)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b(?:[-a-zA-Z0-9()@:%_\+.~#?&\/=]*)$/;/* eslint-disable-line */
        if (!name_regex.test(e.target.title.value)) {
            errors.title = "First name can only contain lower case and upper case letters numbers and @ or _ symbol";

        }
        if (!url_regex.test(e.target.url.value)) {
            errors.url = "Url format is (https://www.)";

        }
        setFormErrors(errors);
        if (Object.keys(errors).length === 0) {
            addTopic(e.target, course_id)
                .then((result) => {
                    if (result === 'Topic Data Added Successfully') {
                        Swal.fire({
                            title: "Topic Added",
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
    return (
        <div className="container mt-4">
            <div className="row">
                <aside className="col-md-3">
                    <MSidebar />
                </aside>
                <section className="col-9">
                    <div className="card">
                        <h5 className="card-header">
                            Course Videos
                        </h5>
                        <div className="card-body">
                            <Form onSubmit={submitForm}>
                                <Form.Group controlId="title">
                                    <Form.Label>Title <span className="text-danger">*</span></Form.Label>
                                    <Form.Control type="text" name="title" placeholder="Title of your course" />
                                </Form.Group>
                                {formErrors.title &&
                                    <p className="text-danger">{formErrors.title}</p>
                                }
                                <Form.Group controlId="course">
                                    <Form.Label>Course </Form.Label>
                                    <Form.Control type="text" name="course" disabled aria-readonly placeholder={course_title} />
                                </Form.Group>
                                <Form.Group controlId="url">
                                    <Form.Label>URL <span className="text-danger">*</span></Form.Label>
                                    <Form.Control type="text" name="url" placeholder="https://www.youtube.com" />
                                </Form.Group>
                                {formErrors.url &&
                                    <p className="text-danger">{formErrors.url}</p>
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
            </div>
        </div>

    )
}

export default AddVideos;