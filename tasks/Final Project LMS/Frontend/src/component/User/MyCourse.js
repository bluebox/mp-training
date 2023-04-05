import React from "react";
import { Link } from "react-router-dom";
import Sidebar from "./Sidebar";
import { useState, useEffect } from "react";
import axios from "axios";
import { Button } from "react-bootstrap";
const baseUrl = 'http://127.0.0.1:8000/api/fetch_enrolled_courses/';
function MyCourse() {
    const [courseData, setCourseData] = useState([]);
    const studentId = localStorage.getItem('studentId');
    const accessToken = localStorage.getItem('accessToken');
    const refreshToken = localStorage.getItem('refreshToken');
    useEffect(() => {
        if (courseData.length === 0) {
            try {
                axios.get(baseUrl + studentId, {
                    headers: { 'Authorization': `Bearer ${accessToken}` }
                })
                    .then((res) => {
                        setCourseData(res.data);
                    })
                    .catch((error) => {
                        console.log(error.response);
                        if (error.response.status === 401) {
                            axios.post('http://127.0.0.1:8000/api/api/token/refresh/', { refresh: refreshToken })
                                .then((response) => {
                                    const newAccessToken = response.data.access;
                                    localStorage.setItem('accessToken', newAccessToken);
                                    axios.get(baseUrl + studentId, {
                                        headers: { 'Authorization': `Bearer ${newAccessToken}` }
                                    })
                                        .then((res) => {
                                            console.log(res.data);
                                            setCourseData(res.data);
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
            catch (error) {
                console.log(error);
            }
        }
    }, []);
    return (
        <div className="container mt-4">
            <div className="row">
                <aside className="col-md-9">
                    <div className="card">
                        <div className="card-header">Enrolled Courses</div>
                        <div className="card-body">
                            {courseData.length !== 0 &&
                                <table className="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Created By</th>
                                            <th>Enrolled Date , Time</th>
                                        </tr>
                                    </thead>

                                    {courseData.map(course =>
                                        <tbody>
                                            <td>{course.course.title}</td>
                                            {course &&
                                                <td>
                                                    <Link to={`/master-detail/${course.course.teacher._id}`}><Button variant="info">{course.course.teacher.user.first_name}</Button></Link>
                                                </td>
                                            }
                                            <td>{course.enrolled_time ? new Date(course.enrolled_time).toLocaleString() : 'Loading...'}</td>
                                        </tbody>
                                    )}
                                </table>
                            }
                            {courseData.length === 0 &&
                                <h2 className="text-danger">You are not enrolled in any courses</h2>
                            }
                        </div>
                    </div>
                </aside>
                <section className="col-md-3">
                    <Sidebar />
                </section>

            </div>
        </div>

    )
}

export default MyCourse;