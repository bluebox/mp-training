import React, { useEffect, useState } from "react";
import customAXIOS from "./apis";
import { ALLTEACHERSP, TEACHERS } from "./urls";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";

function Teachers(){
    const [teacher,setTeacher] = useState([])
    const [currentPage, setCurrentPage] = useState(1);
    const [count, setCount] = useState(0);
    const [pageSize] = useState(5)
    const navigator = useNavigate()
    useEffect(()=>{
        customAXIOS(ALLTEACHERSP,{page:currentPage},'get',null,navigator)
        .then(res=>{
            setTeacher(res.results||[])
            setCount(res.count || 0)
        })
    },[currentPage])


    const level = {'l':'Lower-School','h':'High-School','p':'Primary-School'}

    const handleDelete = async (id) => {
        try {
            await customAXIOS(TEACHERS+String(id)+"/",null, 'delete', null, navigator);
            setTeacher(prev => prev.filter(t => t.user_id !== id)); 
        } catch (err) {
            console.log("Error:", err);
        }
    };
    const totalPages = Math.ceil(count / pageSize);

    return(
        <div className="display-container">
            <h2>Teachers</h2>
            <table className="user-table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Level</th>
                        <th>Joining Date</th>
                        <th>Experience</th>
                        <th>Class_Teacher</th>
                    </tr>
                </thead>
                <tbody>
                    {teacher?.map((u) => (
                        <tr key={u.user_id}>
                            <td>{u.Name}</td>
                            <td>{level[u.level]}</td>
                            <td>{u.joining_date}</td>
                            <td>{u.experience}</td>
                            <td>{u.Class_teacher?"Yes":"No"}</td>
                            <td>
                                {/* <button className="alter-btn" onClick={"/${u.id.toString()}"}>Alter</button> */}
                                <Link to={"/editTeacherDetails"} state={{ userId: u.user_id }}>
                                {console.log("User::",u)}
                                    <button className="alter-btn">Alter</button>
                                </Link>
                                {/* <Link to="/" state={{"id": u.id.toString()}}>Alter</Link> */}

                                <button className="delete-btn" onClick={() => handleDelete(u.user_id)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                    {teacher.length === 0 && (
                        <tr>
                            <td colSpan="9" style={{ textAlign: "center" }}>No teacher found</td>
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
    )


}
export default Teachers