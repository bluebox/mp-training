import React, { useEffect, useState } from "react";
import customAXIOS from "./apis";
import { CLASSES, VIEWCLASSES } from "./urls";
import { Link, useNavigate } from "react-router-dom";

function ClassTeachers(){
    const [teachers,setTeachers] = useState([])
    const navigator = useNavigate()
    useEffect(()=>{
        customAXIOS(VIEWCLASSES,null,'get',null, navigator)
        .then(res=>setTeachers(res))
        .catch(err=>{alert("Error: could'nt fetch details")})
    },[])


    function handleDelete(id){
        customAXIOS(CLASSES+String(id)+"/",null,'delete',null)
        .then(res=>{
            alert("Deleted successfully")
        })
        .catch(ex=>{
            console.log("Error",ex)
            alert("Delete failed")
        })
    }

    return(
        <div className="display-container">
            <h2>Class Teachers</h2>
            <table className="user-table">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Class</th>
                        <th>Section</th>
                    </tr>
                </thead>
                <tbody>
                    {teachers.length>0 && teachers.map(teacher=>(
                        <tr>
                            <td>{teacher.teacher_id}</td>
                            <td>{teacher.name}</td>
                            <td>{teacher.class}</td>
                            <td>{teacher.section}</td>
                            <td>
                                {/* <button className="alter-btn" onClick={"/${u.id.toString()}"}>Alter</button> */}
                                <Link to={"/createClass"} state = {{Id:teacher.id}}>
                                    <button className="alter-btn">Alter</button>
                                </Link>
                                {/* <Link to="/" state={{"id": u.id.toString()}}>Alter</Link> */}

                                <button className="delete-btn" onClick={() => handleDelete(teacher.id)}>Delete</button>
                            </td>
                        </tr>
                        
                    ))}
                </tbody>
            </table>
        </div>
    )
}
export default ClassTeachers