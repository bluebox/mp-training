import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Sidebar from "./Sidebar";
const baseUrl = 'http://127.0.0.1:8000/api/student/dashboard/';
function Dashboard() {
    const [dashboardData, setDashboardData] = useState([]);
    const studentId = localStorage.getItem('userId');
    useEffect(() => {
        try {
            axios.get(baseUrl + studentId + '/')
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
                                <h5 className="card-header bg-primary">Enrolled Courses</h5>
                                <div className="card-body">
                                    <h3><Link to='/user-mycourse'>{dashboardData.total_enrolled_courses}</Link></h3>
                                </div>
                            </div>
                        </div>
                        <div className="col-md-4">
                            <div className="card border-success">
                                <h5 className="card-header bg-success">Favourite Courses</h5>
                                <div className="card-body">
                                    <h3><Link to='/user-favcourse'>{dashboardData.total_favorite_courses}</Link></h3>
                                </div>
                            </div>
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

export default Dashboard;