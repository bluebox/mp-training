import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Sidebar from "./Sidebar";
const baseUrl = 'http://127.0.0.1:8000/api/fetch-favorite-course/';

function FavCourse() {
    const [favCourseData, setFavCourseData] = useState([]);
    const studentId = localStorage.getItem('userId')
    useEffect(() => {
        try {
            axios.get(baseUrl + studentId)
                .then((res) => {
                    setFavCourseData(res.data);
                });
        }
        catch (error) {
            console.log(error);
        }
    }, []);
    return (
        <div className="container mt-4">
            <div className="row">
                <section className="col-md-9">
                    <div className="card">
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
                                            <td>
                                                <Link to={`/master-detail/${favCourse.course.teacher.id}`}>{favCourse.course.teacher.first_name}</Link>
                                            </td>
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