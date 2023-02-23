import React from "react";
import { Link } from "react-router-dom";
import Sidebar from "./Sidebar";
import { useState, useEffect } from "react";
import axios from "axios";
import { Button } from "react-bootstrap";
const baseUrl = 'http://127.0.0.1:8000/api/fetch_enrolled_courses/';
function MyCourse() {
    const [courseData, setCourseData] = useState([]);
    const studentId = localStorage.getItem('userId')
    useEffect(() => {
        try {
            axios.get(baseUrl + studentId)
                .then((res) => {
                    setCourseData(res.data);
                });
        }
        catch (error) {
            console.log(error);
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
                                            <td>
                                                <Link to={`/master-detail/${course.course.teacher.id}`}><Button variant="info">{course.course.teacher.first_name}</Button></Link>
                                            </td>
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