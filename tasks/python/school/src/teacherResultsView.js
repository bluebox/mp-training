import React, { useEffect, useState } from "react";
import customAXIOS from "./apis";
import { RESULTS, TEACHERRESULTSVIEW, TEACHERSUBJECTSTUDENTS } from "./urls";
import { Link, useNavigate } from "react-router-dom";

function TeacherResultsView({userId})
{
    const [results,setResults] = useState([])
    const [subjects,setSubjects] = useState([])
    const navigator = useNavigate()
    useEffect(()=>{
        const fetchRes = async()=>{    
            const data = []
            const subData = await customAXIOS(TEACHERSUBJECTSTUDENTS,{id:userId},'get',null,navigator)
            const subjectkeys = Object.keys(subData||{})
            setSubjects(subjectkeys)

            for (const sub_id of subjectkeys) {
                const result = await customAXIOS(TEACHERRESULTSVIEW, { id: userId, sub_id }, 'get', null, navigator);
                data.push(...result);  
            }
            setResults(data)
        }
        fetchRes()
        console.log("keys:",subjects)
        console.log("res:",results)
    },[userId])

    function handleDelete(id){
        customAXIOS(RESULTS,{id:id},'delete',null,navigator)
        .then(res=>{
            alert("Successfully deleted");
            console.log("Deleted");
        })
        .catch(err=>{
            console.log("error occured");
        })
    }

    return(
        <div className="display-container">
            <h2>Students</h2>
            <table className="user-table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>ID</th>
                        <th>Class </th>
                        <th>Subject Name</th>
                        <th>Percentage</th>
                        <th>Grade</th>
                    </tr>
                </thead>
                <tbody>
                    {results.map((u) => (
                        <tr key={u.id}>
                            <td>{u.student__Name}</td>
                            <td>{u.student__user_id}</td>
                            <td>{u.Class}</td>
                            <td>{u.subject__Name}</td>
                            <td>{u.grade}</td>
                            <td>{u.percentage}</td>
                            <td>
                                {/* <button className="alter-btn" onClick={"/${u.id.toString()}"}>Alter</button> */}
                                <Link to={"/t"} state = {{id:u.id}}>
                                    <button className="alter-btn">Alter</button>
                                </Link>
                                {/* <Link to="/" state={{"id": u.id.toString()}}>Alter</Link> */}

                                <button className="delete-btn" onClick={() => handleDelete(u.id)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                    {results.length === 0 && (
                        <tr>
                            <td colSpan="9" style={{ textAlign: "center" }}>No users found</td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    )
}
export default TeacherResultsView