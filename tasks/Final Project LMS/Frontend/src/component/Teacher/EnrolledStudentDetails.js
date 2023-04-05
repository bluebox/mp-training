import React from "react";
import { Link, useParams } from "react-router-dom";
import MSidebar from "./MSidebar";
import { useState, useEffect } from "react";
import axios from "axios";
import { Button } from "react-bootstrap";
const baseUrl = 'http://127.0.0.1:8000/api/fetch_enrolled_students/';
function EnrolledStudentDetails() {
    const [studentData, setStudentData] = useState([]);
    const accessToken = localStorage.getItem('accessToken');
    const refreshToken = localStorage.getItem('refreshToken');
    let { course_id } = useParams();
    useEffect(() => {
        if (studentData.length === 0) {
            try {
                axios.get(baseUrl + course_id , {
                    headers: {'Authorization': `Bearer ${accessToken}`}
                })
                    .then((res) => {
                        setStudentData(res.data);
                    })
                    .catch((error) => {
                        if (error.response.status === 401) {
                            axios.post('http://127.0.0.1:8000/api/api/token/refresh/', { refresh: refreshToken })
                                .then((response) => {
                                    const newAccessToken = response.data.access;
                                    localStorage.setItem('accessToken', newAccessToken);
                                    axios.get(baseUrl + course_id, {
                                        headers: { 'Authorization': `Bearer ${newAccessToken}` }
                                    })
                                        .then((res) => {
                                            setStudentData(res.data);
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
    },[]);
    console.log(studentData);
    return (
        <div className="container mt-4">
            <div className="row">
                <aside className="col-md-3">
                    <MSidebar />
                </aside>
                <section className="col-md-9">
                    <div className="card">
                        <div className="card-header">Student Details</div>
                        <div className="card-body">
                            <table className="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>E-mail</th>
                                        <th>Interested In</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {studentData.map((object, index) =>

                                        <tr>
                                            <td><Link to={`/student/` + object.student._id}><Button variant="info">{object.student.user.first_name}</Button></Link></td>
                                            <td>{object.student.user.email}</td>
                                            <td>
                                                {object.student.interested_category}
                                            </td>
                                        </tr>

                                    )}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </section>
            </div>
        </div>

    )
}

export default EnrolledStudentDetails;