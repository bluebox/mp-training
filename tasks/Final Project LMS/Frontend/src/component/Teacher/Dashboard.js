import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import MSidebar from "./MSidebar";
const baseUrl = 'http://127.0.0.1:8000/api/teacher/dashboard/';
function MDashboard() {
    const [dashboardData, setDashboardData] = useState([]);
    const teacherId = localStorage.getItem('teacherId');
    useEffect(() => {
        try {
            axios.get(baseUrl + teacherId + '/')
                .then((res) => {
                    setDashboardData(res.data);
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
                    <div className="row">
                        <div className="col-md-4">
                            <div className="card border-primary">
                                <h5 className="card-header bg-primary">Total Courses</h5>
                                <div className="card-body">
                                    <h3><Link to='/master-mycourse'>{dashboardData.total_teacher_courses}</Link></h3>
                                </div>
                            </div>
                        </div>
                        <div className="col-md-4">
                            <div className="card border-success">
                                <h5 className="card-header bg-success">Total Chapters</h5>
                                <div className="card-body">
                                    <h3><Link to='/master-mycourse'>{dashboardData.total_teacher_chapters}</Link></h3>
                                </div>
                            </div>
                        </div>
                        <div className="col-md-4">
                            <div className="card border-info">
                                <h5 className="card-header bg-info">Total Students</h5>
                                <div className="card-body">
                                    <h3><Link to='/master-user'>{dashboardData.total_teacher_students}</Link></h3>
                                </div>
                            </div>
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

export default MDashboard;