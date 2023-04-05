import axios from "axios";
import React, { useEffect, useState } from "react";
import { Button, Form } from "react-bootstrap";
import { Link } from "react-router-dom";
import { useParams } from "react-router-dom";
import Swal from "sweetalert2";
import {addReview, studentEnrollCourse, studentFavouriteCourse, studentRemoveFavouriteCourse } from "./service/InstructorService";
const baseUrl = 'http://127.0.0.1:8000/api/';
const baseUrl1 = 'http://127.0.0.1:8000/api/fetch_enroll_status/';
function CourseDetial() {
    const [topicData, setTopicData] = useState([]);
    const [userData, setUserData] = useState([]);
    const [teacherData, setTeacherData] = useState([]);
    const [courseData, setCourseData] = useState([]);
    const [userLoginStatus, setUserLoginStatus] = useState();
    const [ratingStatus, setRatingStatus] = useState();
    const [enrollStatus, setEnrollStatus] = useState();
    const [rating, setRating] = useState(0);
    const [favoriteStatus, setFavoriteStatus] = useState();
    let { course_id } = useParams();
    const studentId = localStorage.getItem('studentId');
    const teacherlogin = localStorage.getItem('teacherLoginStatus');
    useEffect(() => {
        if(courseData.length === 0){
            try {
                axios.get(baseUrl + 'allcourse/' + course_id + '/')
                    .then((res) => {
                        console.log(res.data.topic_videos);
                        setUserData(res.data.teacher.user);
                        setCourseData(res.data);
                        setTeacherData(res.data.teacher);
                        setTopicData(res.data.topic_videos);
                        // eslint-disable-next-line
                        if (res.data.course_rating != '' && res.data.course_rating != null) {
                            setRating(res.data.course_rating);
                        }
    
                    })
                    .catch((error) => {
                        console.log(error.response);
                    });
            }
            catch (error) {
                console.log(error);
            };
        }

        //Fetch enroll Status
        if (studentId != null) {
            try {
                axios.get(baseUrl1 + studentId + '/' + course_id)
                    .then((res) => {
                        // eslint-disable-next-line
                        if (res.data.bool == true) {
                            setEnrollStatus('success');
                        }
                    });
            }
            catch (error) {
                console.log(error);
            };
            //Fetch rating Status
            try {
                axios.get(baseUrl + 'fetch_rating_status/' + studentId + '/' + course_id)
                    .then((res) => {
                        if (res.data.bool) {
                            setRatingStatus('success');
                        }
                        else
                            setRatingStatus('');
                    });
            }
            catch (error) {
                console.log(error);
            };
            //Fetch favourite Status
            try {
                axios.get(baseUrl + 'fetch-favorite-status/' + studentId + '/' + course_id)
                    .then((res) => {
                        if (res.data.bool) {
                            setFavoriteStatus('success');
                        }
                        else
                            setFavoriteStatus('');
                    });
            }
            catch (error) {
                console.log(error);
            };
        }
        const studentLoginStatus = localStorage.getItem('userLoginStatus');
        if (studentLoginStatus === "true") {
            setUserLoginStatus('success')
        }

    }, []);
    const enrollCourse = () => {
        // eslint-disable-next-line
        studentEnrollCourse(studentId, course_id)
            .then((result) => {
                if (result === "Student Enrolled Successfully") {
                    Swal.fire({
                        title: 'Enrolled in the course Successfully',
                        icon: 'success',
                        toast: true,
                        timer: 2000,
                        position: 'top-right',
                        timerProgressBar: true,
                        showConfirmButton: false
                    });
                    setEnrollStatus('success');
                }
            }, (err) => {
                alert(err);
            })
    };
    const markAsFav = () => {
        console.log('student:'+ studentId +"course:"+course_id);
        studentFavouriteCourse(studentId, course_id)
            .then((result) => {
                // eslint-disable-next-line
                if (result.status == 200 || result.status == 201) {
                    Swal.fire({
                        title: "Course Added to Favorite",
                        confirmButtonText: 'Ok',
                    }).then((result) => {
                        if (result.isConfirmed) {
                            setEnrollStatus('success');
                            window.location.reload();
                        }
                    })
                }
            }, (err) => {
                alert(err);
            })
    };
    const removeFav = () => {
        studentRemoveFavouriteCourse(studentId, course_id)
            .then((result) => {
                // eslint-disable-next-line
                if (result.status == 200 || result.status == 201) {
                    Swal.fire({
                        title: "Course Removed From Favorite",
                        confirmButtonText: 'Ok',
                    }).then((result) => {
                        if (result.isConfirmed) {
                            setEnrollStatus('');
                            window.location.reload();
                        }
                    })
                }
            }, (err) => {
                alert(err);
            })
    };
    const submitForm = (e) => {
        e.preventDefault();
        addReview(e.target, course_id, studentId)
            .then((result) => {
                // eslint-disable-next-line
                if (result.status == 200 || result.status == 201) {
                    Swal.fire({
                        title: "Rating Added Successfully",
                        confirmButtonText: 'Ok',
                    }).then((result) => {
                        if (result.isConfirmed) {
                            window.location.reload();
                        }
                    })
                }
            }, (err) => {
                alert(err);
            });
    }
    return (
        <div className="container mt-4">
            <div className="row">
                <div className="col-4">
                    <img src={courseData.image} className="img-thumbnail" alt="Loading" />
                </div>
                <div className="col-8">
                    <h3>{courseData.title}</h3>
                    <p>{courseData.description}</p>
                    <p className="fw-bold">Course By:<Link className="ms-4" to={`/master-detail/${teacherData._id}`}><Button variant="secondary" className="btn btn-sm">{userData.first_name}</Button></Link></p>
                    <p><b>Total Enrolled Student(s):</b> {courseData.total_enrolled_students}</p>
                    <p className="fw-bold">Rating: {rating}/5
                        {userLoginStatus === 'success' && enrollStatus === 'success' &&
                            <>
                                {ratingStatus !== 'success' &&
                                    <button className="btn btn-warning btn-sm ms-4" data-bs-toggle='modal' data-bs-target='#ratingModal'>Rating</button>
                                }
                                {ratingStatus === 'success' &&
                                    <Button className="btn btn-warning btn-sm ms-4" disabled> Already reviewed</Button>
                                }
                                <div className="modal fade" id="ratingModal" tabindex="-1" aria-labelledby="ratingModalLabel" aria-hidden="true">
                                    <div className="modal-dialog modal-lg">
                                        <div className="modal-content">
                                            <div className="modal-header">
                                                <h1 className="modal-title fs-5" id="ratingModalLabel">Rate for {courseData.title}</h1>
                                                <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div className="modal-body">
                                                <div>
                                                    <Form onSubmit={submitForm}>
                                                        <Form.Group controlId="rating">
                                                            <Form.Label>Rating</Form.Label>
                                                            <Form.Select className="form-control">
                                                                <option>Rate out of 5</option>
                                                                <option value="1">One</option>
                                                                <option value="2">Two</option>
                                                                <option value="3">Three</option>
                                                                <option value="4">Four</option>
                                                                <option value="5">Five</option>
                                                            </Form.Select>
                                                        </Form.Group>
                                                        <Form.Group controlId="review">
                                                            <Form.Label>Review</Form.Label>
                                                            <Form.Control type="text" name="review" required placeholder="Any Review you want to add" />
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
                                            <div className="modal-footer">
                                                <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </>
                        }
                    </p>
                    {enrollStatus === 'success' && userLoginStatus === 'success' &&
                        <p><Button className="btn btn-success" disabled>Already Enrolled</Button></p>
                    }
                    {userLoginStatus === 'success' && enrollStatus !== 'success' &&
                        <p><button type="button" onClick={enrollCourse} className="btn btn-success">Enroll in this course</button></p>

                    }
                    {userLoginStatus === 'success' && favoriteStatus !== 'success' &&
                        <p><button type="button" onClick={markAsFav} className="btn btn-light"><i className="bi bi-heart">Mark as Favorite</i></button></p>

                    }
                    {userLoginStatus === 'success' && favoriteStatus === 'success' &&
                        <p><button type="button" onClick={removeFav} className="btn btn-light"><i className="bi bi-heart-fill">Remove From Favorite</i></button></p>

                    }
                    {
                        teacherlogin !== 'true' && userLoginStatus !== 'success' &&
                        <>
                            <p><Link className="btn btn-success" to='/login' title="Click Here!!">Please login as student to enroll in this course</Link></p>
                            <p><Link className="btn btn-success" to='/user-register' title='Click Here!!'>Register as student to enroll in this course</Link></p>

                        </>
                    }
                </div>
            </div>
            {/*Course Videos */}
            {userLoginStatus === 'success' && enrollStatus === 'success' &&
                <div className="card mt-4">
                    <h4 className="card-header">
                        Course Videos
                    </h4>
                    <ul className="list-group list-group-flush">
                        {topicData.length !==0 && topicData.map((topic, index) =>
                            <li className="list-group-item">{topic.title}
                                <span className="float-end">
                                    <button className="btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#videoModal1">
                                        <a href={topic.url}><i className="bi bi-play"></i></a>
                                    </button>
                                </span>
                            </li>
                        )}
                        {topicData.length === 0 && 
                        <p className="text-danger text-center fs-1">There are no videos added yet</p>
                        }
                    </ul>
                </div>
            }
        </div>
        
    )
}
export default CourseDetial