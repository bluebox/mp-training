import axios from "axios";
import React, { useEffect, useState } from "react";
import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import Sidebar from "./Sidebar";
const baseUrl = 'http://127.0.0.1:8000/api/fetch-favorite-course/';

function FavCourse() {
    const [favCourseData, setFavCourseData] = useState([]);
    const studentId = localStorage.getItem('studentId');
    const accessToken = localStorage.getItem('accessToken');
    const refreshToken = localStorage.getItem('refreshToken');
    useEffect(() => {
        if (favCourseData.length === 0) {
            try {
                axios.get(baseUrl + studentId, {
                    headers: { 'Authorization': `Bearer ${accessToken}` }
                })
                    .then((res) => {
                        setFavCourseData(res.data);
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
                                            setFavCourseData(res.data);
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
                <section className="col-md-9">
                    <div className="card">
                        <div className="card-header">Favourite Courses</div>
                        <div className="card-body">
                            {favCourseData.length !== 0 &&
                                <table className="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Created By</th>
                                        </tr>
                                    </thead>
                                    {favCourseData.map(favCourse =>
                                        <tbody>
                                            <td>{favCourse.course.title}</td>
                                            {favCourse &&
                                                <td>
                                                    <Link to={`/master-detail/${favCourse.course.teacher._id}`}><Button variant="info">{favCourse.course.teacher.user.first_name}</Button></Link>
                                                </td>
                                            }
                                        </tbody>
                                    )}
                                </table>
                            }
                            {favCourseData.length === 0 &&
                                <h2 className="text-danger">There is no course marked as Favorite</h2>
                            }
                        </div>
                    </div>
                </section>
                <aside className="col-md-3">
                    <Sidebar />
                </aside>
            </div>
        </div>

    )
}

export default FavCourse;