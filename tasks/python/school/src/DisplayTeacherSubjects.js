import React, { useEffect, useState } from "react";
import customAXIOS from "./apis";
import { SUBJECTTEACHERCLASS, TEACHERSUBJECTSTUDENTS } from "./urls";

function DisplayTeacherSubjects({userId}){
    const [subjects, setSubjects] = useState([])
    useEffect(()=>{
        customAXIOS(SUBJECTTEACHERCLASS,{id:userId},'get',null,navigator)
        .then(res=>{
            setSubjects(res);
        })
    },[])

    return(
        <div className="display-container">
            <h2>Subjects</h2>
            <table className="user-table">
                <thead>
                    <tr>
                        <th>Class</th>
                        <th>Section</th>
                        <th>Subject Id</th>
                        <th>Subject Name</th>
                    </tr>
                </thead>
                <tbody>
                    {subjects.length > 0 ? (
                        subjects.map((r) => (
                            <tr>
                                <td>{r.class_id}</td>
                                <td>{r.section}</td>
                                <td>{r.subject_id}</td>
                                <td>{r.subject_name}</td>
                            </tr>
                        ))
                    ) : (
                        <tr>
                            <td colSpan="4" style={{ textAlign: "center" }}>No results found</td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    )
}
export default DisplayTeacherSubjects