import React, { useEffect, useState } from "react";
import customAXIOS from "./apis";
import { TEACHERS } from "./urls";
import { Link, useNavigate } from "react-router-dom";

function AdminTeacherDetails() {
    const [teachers, setTeachers] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchTeachers = async () => {
            try {
                const res = await customAXIOS(TEACHERS, null, 'get', null, navigate);
                if (Array.isArray(res)) {
                    setTeachers(res);
                } else {
                    console.error("Expected array but got:", res);
                }
            } catch (err) {
                alert("Error fetching teachers.");
                console.error("Error:", err);
            }
        };

        fetchTeachers();
    }, []);

    if (teachers.length === 0) return <p>Loading or no teachers found...</p>;

    return (
        <div className="display-container">
            <h1>Teacher Details</h1>
            {teachers.map((teacher, index) => (
                <div key={index} className="teacher-card">
                    {/* <Link to={`/editTeacherDetails/${teacher.user}`}>
                        <button className="alter-btn">Edit</button>
                    </Link> */}
                    <table className="user-table">
                        <thead>
                            <tr>
                                <th>Field</th>
                                <th>Value</th>
                            </tr>
                        </thead>
                        <tbody>
                            {Object.entries(teacher).map(([key, value]) => (
                                <tr key={key}>
                                    <td>{key}</td>
                                    <td>{value}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            ))}
        </div>
    );
}

export default AdminTeacherDetails;
