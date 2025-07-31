import React, { useEffect, useState } from "react";
import customAXIOS from "./apis";
import { SUBJECTTEACHERCLASS, TEACHERSUBJECTSTUDENTS } from "./urls";

function DisplayTeacherSubjects({userId}){
    const [subjects, setSubjects] = useState([])
    const [currentPage,setCurrentPage] = useState(1)
    const [count,setCount] = useState(0)
    const [pageSize] = useState(5) 
    useEffect(()=>{
        customAXIOS(SUBJECTTEACHERCLASS,{id:userId,page:currentPage},'get',null,navigator)
        .then(res=>{
            setSubjects(res.results||[]);
            setCount(res.count||0);
            // console.log(res);
        })
    },[])
    const totalPages = Math.ceil(count/pageSize)
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
    )
}
export default DisplayTeacherSubjects