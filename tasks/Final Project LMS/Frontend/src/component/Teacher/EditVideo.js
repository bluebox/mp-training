import React from "react";
import MSidebar from "./MSidebar";
import { useState, useEffect } from "react";
import { Button, Form } from "react-bootstrap";
import { updateTopic } from "../service/InstructorService";
import axios from "axios";
import Swal from "sweetalert2";
import { useParams } from "react-router-dom";
const baseUrl = "http://127.0.0.1:8000/api/topic/";
function EditVideo() {
    const accessToken = localStorage.getItem('accessToken');
    const refreshToken = localStorage.getItem('refreshToken');
    const [topicData, setTopicData] = useState({
        'title': '',
        'url': '',
    });
    const { topic_id } = useParams();

    useEffect(() => {
        if (topicData.title === "") {
            try {
                axios.get(baseUrl + topic_id + '/', {
                    headers: { 'Authorization': `Bearer ${accessToken}` }
                })
                    .then((res) => {
                        setTopicData(res.data);
                    })
                    .catch((error) => {
                        console.log(error.response);
                        if (error.response.status === 401) { // Unauthorized - access token expired
                            axios.post('http://127.0.0.1:8000/api/api/token/refresh/', { refresh: refreshToken })
                                .then((response) => {
                                    const newAccessToken = response.data.access;
                                    // Update the access token in localStorage or state
                                    localStorage.setItem('accessToken', newAccessToken);
                                    // Make the original request again with the new access token
                                    axios.get(baseUrl + topic_id + '/', {
                                        headers: { 'Authorization': `Bearer ${newAccessToken}` }
                                    })
                                        .then((res) => {
                                            setTopicData(res.data);
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

    }, [topicData]);
    const [formErrors, setFormErrors] = useState({});
    const handleSubmit = (e) => {
        e.preventDefault();
        const errors = {}
        const title_regex = /^.{5,120}$/; /* eslint-disable-line */
        const url_regex = /^(http(s):\/\/.)[-a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,6}\b([-a-zA-Z0-9@:%_\+.~#?&//=]*)$//* eslint-disable-line */
        if (!title_regex.test(e.target.title.value)) {
            errors.title = "Enter title between 5 - 120 characters";
        }
        if (!url_regex.test(e.target.url.value)) {
            errors.url = "Enter valid url";
        }
        setFormErrors(errors);
        if (Object.keys(errors).length === 0) {
            updateTopic(e.target, topic_id)
                .then((result) => {
                    console.log(result);
                    if (result === 'Topic Updated Successfully') {
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
        setTopicData({
            ...topicData, [e.target.name]: e.target.value
        });
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
                                    <Form.Control type="text" name="title" value={topicData.title} onChange={handlechange} />
                                </Form.Group>
                                {formErrors.first_name &&
                                    <p className="text-danger">{formErrors.title}</p>
                                }
                                <Form.Group controlId="url">
                                    <Form.Label className="fw-bold">Url</Form.Label>
                                    <Form.Control type="text" name="url" value={topicData.url} onChange={handlechange} />
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
                <aside className="col-md-3">
                    <MSidebar />
                </aside>
            </div >
        </div >
    )
}

export default EditVideo;