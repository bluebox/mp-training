import React, { useEffect, useState } from "react";
import customAXIOS from "./apis";
import { STUDENTSUBJECTS } from "./urls";
import { useNavigate } from "react-router-dom";

function ViewClasses({ userId }) {
    const [subjects, setSubjects] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        customAXIOS(STUDENTSUBJECTS, { id: userId }, 'get', null, navigate)
            .then(res => {
                setSubjects(res.subjects);
                console.log(res.subjects);
            });
    }, [userId]);

    if (!subjects) return <p>Loading...</p>;

    return (
        <div className="display-container">
            <h1>Subjects</h1>
            <table className="user-table">
                <thead>
                    <tr>
                        <th>Subject</th>
                        <th>Teacher</th>
                    </tr>
                </thead>
                <tbody>
                    {subjects.map((item, index) => (
                        <tr key={index}>
                            <td>{item.subject}</td>
                            <td>{item.teacher}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default ViewClasses;
