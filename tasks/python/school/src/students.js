import React, { useEffect, useState } from "react";
import "./display.css";
import { Link, useNavigate } from "react-router-dom";
import customAXIOS from "./apis";
import { STUDENTS } from "./urls";

function Student() {
    const [students, setStudents] = useState([]);
    const redirect = useNavigate();
    const [loading,setLoading] = useState(false);
    const [currentPage, setCurrentPage] = useState(1);
    const [count, setCount] = useState(0);
    const [pageSize] = useState(5)
   const fetchUsers = async (page = 1) => {
        try {
            setLoading(true);
            const response = await customAXIOS(STUDENTS, { page }, "get", null, redirect);
            setStudents(response.results || []);
            setCount(response.count || 0);
        } catch (err) {
            console.log("users fetch failed", err);
        } finally {
            setLoading(false);
        }
    };


    const refresh = () => {
        fetchUsers(currentPage);
    };

    useEffect(() => {
        fetchUsers(currentPage);
    }, [currentPage]);

    const handleDelete = async (id) => {
        try {
            await customAXIOS(STUDENTS, { id: id }, "delete", null, redirect);
        } catch (error) {
            console.log("Error in delete", error);
        }
        refresh();
    };

    const totalPages = Math.ceil(count / pageSize);

    return loading ? (
        <p>Loading...</p>
    ) : (
        <div className="display-container">
        <h2>Students</h2>
        <button onClick={() => fetchUsers(currentPage)} className="alter-btn">
            Refresh
        </button>
        <table className="user-table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Class Representative</th>
                <th>Attendance</th>
                <th>Status</th>
                <th>Class</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            {students.length > 0 ? (
                students.map((u) => (
                <tr key={u.user}>
                    <td>{u.Name}</td>
                    <td>{u.is_class_representative ? "Yes" : "No"}</td>
                    <td>{u.attendance}</td>
                    <td>{u.status}</td>
                    <td>{u.Class}</td>
                    <td>
                    <Link to={"/studentRegister"} state={{ id: u.user }}>
                        <button className="alter-btn">Alter</button>
                    </Link>
                    <button className="delete-btn" onClick={() => handleDelete(u.user)}>
                        Delete
                    </button>
                    </td>
                </tr>
                ))
            ) : (
                <tr>
                <td colSpan="6" style={{ textAlign: "center" }}>
                    No users found
                </td>
                </tr>
            )}
            </tbody>
        </table>

        {/* Pagination Buttons */}
        <div className="pagination">
            {Array.from({ length: totalPages }, (_, i) => i + 1).map((pg) => (
            <button
                key={pg}
                onClick={() => setCurrentPage(pg)}
                className={pg === currentPage ? "active" : ""}
            >
                {pg}
            </button>
            ))}
        </div>
        </div>
    );
}

export default Student;