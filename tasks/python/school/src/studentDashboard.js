import React, { useEffect, useState } from "react";
import customAXIOS from "./apis";
import { STUDENTRESULTS } from "./urls";
import { useNavigate } from "react-router-dom";
function StudentResultsDashboard({userId}){
    const navigate = useNavigate()

    const [results,setResults] = useState([])
    useEffect(()=>{
        customAXIOS(STUDENTRESULTS,{id:userId},'get',null,navigate).then((res)=>{
            const data = res.map(item=>({
                Name: item.Name,
                SubjectName: item.results__subject_id__Name,
                Grade: item.results__grade,
                Percentage: item.results__percentage,
        }))
        setResults(data)
        console.log(results)
        }).catch((err)=>console.err(err))
        
        console.log(results)
    },[]
    )


    return (
        <div className="display-container">
            <h2>Results</h2>
            <table className="user-table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Subject</th>
                        <th>Grade</th>
                        <th>percentage</th>
                    </tr>
                </thead>
                <tbody>
                    {results.map((r) => (
                        <tr key={userId}>
                            <td>{r.name}</td>
                            <td>{r.SubjectName}</td>
                            <td>{r.Grade}</td>
                            <td>{r.percentage}</td>
                        </tr>
                    ))}
                    {results.length === 0 && (
                        <tr>
                            <td colSpan="9" style={{ textAlign: "center" }}>No results found</td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    );
}

export default StudentResultsDashboard