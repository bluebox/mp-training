import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import customAXIOS from "./apis";
import { SUBJECTTEACHER } from "./urls";

function SubjectTeachers() {
    const [teachers, setTeachers] = useState([]);
    const navigator = useNavigate();

    useEffect(() => {
        customAXIOS(SUBJECTTEACHER, null, 'get', null, navigator)
            .then(res => {
                const parsed = Object.entries(res).map(([id, data]) => ({
                    id,
                    Name: data.Name,
                    subjects: data.subjects
                }));
                setTeachers(parsed);
            })
            .catch(err => console.log("API error:", err));
    }, []);

    return (
        <div className="display-container">
            <h1>Subject Teachers</h1>
            <table className="user-table">
                <thead>
                    <tr>
                        <th>Teacher Name</th>
                        <th>Teacher ID</th>
                        <th>Subjects</th>
                    </tr>
                </thead>
                <tbody>
                    {teachers.map((t) => (
                        <tr key={t.id}>
                            <td>{t.Name}</td>
                            <td>{t.id}</td>
                            <td>
                                {t.subjects
                                    .map(sub => Object.values(sub)[0])
                                    .filter(Boolean)
                                    .join(", ")}
                            </td>
                        </tr>
                    ))}
                    {teachers.length === 0 && (
                        <tr>
                            <td colSpan="3" style={{ textAlign: "center" }}>No teachers found</td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    );
}

export default SubjectTeachers;
