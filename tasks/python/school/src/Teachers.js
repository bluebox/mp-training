import React, { useEffect, useState } from "react";
import customAXIOS from "./apis";
import { ALLTEACHERS, TEACHERS } from "./urls";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";

function Teachers(){
    const [teacher,setTeacher] = useState([])
    const navigator = useNavigate()
    useEffect(()=>{
        customAXIOS(ALLTEACHERS,null,'get',null,navigator)
        .then(res=>setTeacher(res))
    },[])


    const level = {'l':'Lower-School','h':'High-School','p':'Primary-School'}

    const handleDelete = async (id) => {
        try {
            await customAXIOS(TEACHERS, { id: id }, 'delete', null, navigator);
            setTeacher(prev => prev.filter(t => t.user_id !== id)); // ✅ Remove from local list
        } catch (err) {
            console.log("Error:", err);
        }
    };


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
                    {teacher.map((u) => (
                        <tr key={u.user_id}>
                            <td>{u.Name}</td>
                            <td>{level[u.level]}</td>
                            <td>{u.joining_date}</td>
                            <td>{u.experience}</td>
                            <td>{u.Class_teacher?"Yes":"No"}</td>
                            <td>
                                {/* <button className="alter-btn" onClick={"/${u.id.toString()}"}>Alter</button> */}
                                <Link to={"/"} state = {{id:u.user_id}}>
                                    <button className="alter-btn">Alter</button>
                                </Link>
                                {/* <Link to="/" state={{"id": u.id.toString()}}>Alter</Link> */}

                                <button className="delete-btn" onClick={() => handleDelete(u.user)}>Delete</button>
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
        </div>
    )


}
export default Teachers