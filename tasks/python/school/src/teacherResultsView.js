import React, { useEffect, useState } from "react";
import customAXIOS from "./apis";
import { RESULTS, TEACHERRESULTSVIEW, TEACHERSUBJECTSTUDENTS } from "./urls";
import { Link, useNavigate } from "react-router-dom";

function TeacherResultsView({ userId }) {
    const [results, setResults] = useState([]);
    const [subjects, setSubjects] = useState([]);
    const navigator = useNavigate();


    const fetchRes = async () => {
        const data = [];
        const subData = await customAXIOS(TEACHERSUBJECTSTUDENTS, { id: userId }, 'get', null, navigator);
        const subjectkeys = Object.keys(subData || {});
        setSubjects(subjectkeys);

        for (const sub_id of subjectkeys) {
            const result = await customAXIOS(TEACHERRESULTSVIEW, { id: userId, sub_id }, 'get', null, navigator);
            data.push(...result);
        }

        setResults(data);
    };

    useEffect(() => {
        if (userId) fetchRes();
    }, [userId]);

    const handleDelete = async (id) => {
        try {
            await customAXIOS(RESULTS + String(id) + "/", null, 'delete', null, navigator);
            alert("Successfully deleted");
            fetchRes();
        } catch (err) {
            console.error("Error occurred during deletion", err);
            alert("Failed to delete");
        }
    };

    return (
        <div className="display-container">
            <h2>Students</h2>
            <table className="user-table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>ID</th>
                        <th>Class</th>
                        <th>Subject Name</th>
                        <th>Percentage</th>
                        <th>Grade</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {results.map((u) => (
                        <tr key={u.id}>
                            <td>{u.student__Name}</td>
                            <td>{u.student__user_id}</td>
                            <td>{u.Class}</td>
                            <td>{u.subject__Name}</td>
                            <td>{u.percentage}</td>
                            <td>{u.grade}</td>
                            <td>
                                <button className="delete-btn" onClick={() => handleDelete(u.id)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                    {results.length === 0 && (
                        <tr>
                            <td colSpan="7" style={{ textAlign: "center" }}>No users found</td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    );
}

export default TeacherResultsView;
