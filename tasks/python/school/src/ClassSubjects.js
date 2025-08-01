import React, { useEffect, useState } from "react";
import customAXIOS from "./apis";
import { CLASSSUBJECTS } from "./urls";
import { useNavigate } from "react-router-dom";

function ClassSubjects(){
    const [classes,setClasses] = useState([])
    const [loading,setLoading] = useState(false)
    const [count,setCount] = useState(0)
    const [pageSize] = useState(5)
    const [currentPage,setCurrentPage] = useState(1)
    const navigator = useNavigate()
    const fetch_details = async()=>{
        try{
            setLoading(true)
            const res = await customAXIOS(CLASSSUBJECTS,{page:currentPage},'get',null,navigator)
            setClasses(res.results)
            console.log("result",res.results)
            setCount(res.count)
        }catch(ex){
            alert("Error in getting details.")
        }
        setLoading(false)
    }
    useEffect(()=>{
        fetch_details()
    },[currentPage])

    const totalPages = Math.ceil(count/pageSize);
    if(loading){
        return <p>Loading...</p>
    }

    return(
        <div className="display-container">
            <h2>Classes</h2>
            <table className="user-table">
                <thead>
                    <tr>
                        <th>Class</th>
                        <th>Class ID</th>
                        <th>Section</th>
                        <th>Teacher ID</th>
                        <th>Teacher Name</th>
                        <th>Subject ID</th>
                        <th>Subject Name</th>
                    </tr>
    
                </thead>
                <tbody>
                    {classes?.length > 0 ? (
                        classes.map((cls) => (
                            <tr key={`${cls.class_id}-${cls.section}`}>
                                <td>{cls.class}</td>
                                <td>{cls.class_id}</td>
                                <td>{cls.section}</td>
                                <td>{cls.teacher_id}</td>
                                <td>{cls.teacher_name}</td>
                                <td>{cls.subject_id}</td>
                                <td>{cls.subject_name}</td>
                            </tr>
                        ))
                    ) : (
                        <tr>
                            <td colSpan="7" style={{ textAlign: "center" }}>No results found</td>
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
    );
}
export default ClassSubjects