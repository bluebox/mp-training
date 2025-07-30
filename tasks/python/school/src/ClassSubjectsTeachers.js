import React, { useEffect, useState } from "react";
import customAXIOS from "./apis";
import { SUBJECTTEACHERCLASS } from "./urls";
import { useNavigate } from "react-router-dom";

function ClassSubjectsTeachers(){
    const [data, setData] = useState([])
    const navigator = useNavigate()
    useEffect(()=>{
        customAXIOS(SUBJECTTEACHERCLASS,null,'get',null,navigator)
        .then(res =>{
            setData(res)
        })
    },[])

    function handleDelete(id)
    {
        console.log("Delete id:",id)
        customAXIOS(SUBJECTTEACHERCLASS,{id:id},'delete',null,navigator)
        .then(res=>{
            console.log(res);
            alert("Successfully deleted");
            const prev_data = data
            const curr_data = prev_data.filter((d)=>(
                d.id !== id
            ))
            setData(curr_data)
        }).catch(
            err=>{
                alert("Deletion failed")
                console.log("Error:",err)
            }
        )
    }

    return(
        <div>
            <table className="user-table">
                <thead>
                    <tr>
                        <th>Subject Id</th>
                        <th>Subject Name</th>
                        <th>Class</th>
                        <th>Section</th>
                        <th>Teacher Id</th>
                        <th>Teacher Name</th>
                    </tr>
                </thead>
                <tbody>
                    {data.length>0 && data.map((d) => (
                        <tr key={d.id}>
                            <td>{d.subject_id}</td>
                            <td>{d.subject_name}</td>
                            <td>{d.class_id}</td>
                            <td>{d.section}</td>
                            <td>{d.teacher_id}</td>
                            <td>{d.teacher_name}</td>
                            <td>

                                <button className="delete-btn" onClick={() => handleDelete(d.id)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                    {data.length === 0 && (
                        <tr>
                            <td colSpan="9" style={{ textAlign: "center" }}>No data found</td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    )
}
export default ClassSubjectsTeachers