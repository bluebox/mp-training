import React from "react";
import { Link } from "react-router-dom";

function MSidebar() {
    return (
        <div className="card">
            <div className="list-group list-group-flush">
                <Link to='/master-dashboard' className=" card-header list-group-item list-group-item-action ">Dashboard</Link>
                <Link to='/master-mycourse' className="list-group-item ">My Courses</Link>
                <Link to='/master-user' className="list-group-item">Enrolled Students</Link>
                <Link to='/master-add-course' className="list-group-item">Add Course</Link>
                <Link to='/master-profilesetting' className="list-group-item">Update Profile</Link>
                <Link to='/master-logout' className="list-group-item text-danger">Logout</Link>
            </div>
        </div>

    )

}

export default MSidebar;