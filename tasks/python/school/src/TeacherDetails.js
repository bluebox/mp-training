import React, { useEffect, useState } from "react";
import customAXIOS from "./apis";
import { TEACHERS } from "./urls";
import { Link, useNavigate } from "react-router-dom";

function TeacherDetails({userId}) {
    const [teachers, setTeachers] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        try{
            console.log(TEACHERS+String(userId)+"/")
            customAXIOS(TEACHERS+String(userId)+"/",null,'get',null,navigate)
            .then(res=>setTeachers(res))
       }catch(ex)
       {
            console.log("Error: ",ex)
            alert("Error in fetching details")
       }
    }, []);

    if (teachers.length === 0) return <p>Loading or no teachers found...</p>;
    return (
        <div className="display-container">
            <h1>Teacher Details</h1>
                <div className="teacher-card">
                    <Link to={`/editTeacherDetails`} state={{ userId: userId }}>
                        <button className="alter-btn">Edit</button>
                    </Link>
                    <table className="user-table">
                        <thead>
                            <tr>
                                <th>Field</th>
                                <th>Value</th>
                            </tr>
                        </thead>
                        <tbody>
                            {Object.entries(teachers).map(([key, value]) => (
                                <tr key={key}>
                                    <td>{key}</td>
                                    <td>{value}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
        </div>
    );
}

export default TeacherDetails;
