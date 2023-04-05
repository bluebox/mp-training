import React from "react";
import MSidebar from "./MSidebar";
import { useState, useEffect } from "react";
import axios from "axios";
const baseUrl = 'http://127.0.0.1:8000/api/fetch_enrolled_students/';
function EnrolledStudents() {
    const [studentData, setStudentData] = useState([]);
    const teacherId = localStorage.getItem('teacherId');
    const accessToken = localStorage.getItem('accessToken')
    const refreshToken = localStorage.getItem('refreshToken');
    useEffect(() => {
        if (studentData.length === 0) {
            try {
                axios.get(baseUrl, {
                    headers: { 'Authorization': `Bearer ${accessToken}` }
                })
                    .then((res) => {
                        // eslint-disable-next-line
                        setStudentData(res.data.filter(stu => stu.course.teacher._id == teacherId));
                    })
                    .catch((error) => {
                        console.log(error.response);
                        if (error.response.status === 401) {
                            axios.post('http://127.0.0.1:8000/api/api/token/refresh/', { refresh: refreshToken })
                                .then((response) => {
                                    const newAccessToken = response.data.access;
                                    localStorage.setItem('accessToken', newAccessToken);
                                    axios.get(baseUrl, {
                                        headers: { 'Authorization': `Bearer ${newAccessToken}` }
                                    })
                                        .then((res) => {
                                            // eslint-disable-next-line
                                            setStudentData(res.data.filter(stu => stu.course.teacher._id == teacherId));
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
            }
            catch (error) {
                console.log(error);
            }
        }
    }, []);
    return (

        <div className="container mt-4">
            <div className="row">
                <section className="col-md-9">
                    <div className="card">
                        <div className="card-header">My Courses</div>
                        <div className="card-body">
                            {studentData.length !== 0 &&
                                <table className="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Student</th>
                                            <th>Course Taken</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        {studentData.map(student =>
                                            <tr>
                                                <td>{student.student.user.first_name}</td>
                                                <td>{student.course.title}</td>
                                            </tr>
                                        )}
                                    </tbody>
                                </table>
                            }
                            {studentData.length === 0 &&
                                <h2 className="text-danger">No Students have enrolled in any of your courses yet!!</h2>
                            }
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

export default EnrolledStudents;