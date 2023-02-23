import React from "react";
import { Link } from "react-router-dom";

function Sidebar() {
    return (
        <div className="card">
            <div className="list-group list-group-flush">
                <Link to='/user-dashboard' className="list-group-item list-group-item-action ">Dashboard</Link>
                <Link to='/user-mycourse' className="list-group-item ">Courses Enrolled</Link>
                <Link to='/user-favcourse' className="list-group-item">Favourite Course</Link>
                <Link to='/user-profilesetting' className="list-group-item">Profile Setting</Link>
                <Link to='/user-logout' className="list-group-item text-danger">Logout</Link>
            </div>
        </div>

    )

}

export default Sidebar;