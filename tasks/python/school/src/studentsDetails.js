import React, { useEffect, useState } from "react";
import customAXIOS from "./apis";
import { STUDENTDETAILS } from "./urls";
import {  Link, useNavigate } from "react-router-dom";

function StudentDetails({userId}){
    const [data,setData] = useState(null)
    const navigate = useNavigate()
    useEffect(()=>{
        customAXIOS(STUDENTDETAILS,{id:userId},'get',null,navigate)
        // .then(res=>res.json())
        .then(res=>{
            setData(res)
            console.log(data);
        })
    },[userId]
    )
    if (!data) return <p>Loading...</p>;
    return(
        <div className="display-container">
            <h1>Details</h1>
            <Link to={"/editDetails"} userId={userId}><button className="alter-btn">Edit</button></Link>
            <table className="user-table">
                <thead>
                    <tr>
                        <th>Header</th>
                        <th>Details</th>
                    </tr>
                </thead>
                <tbody>
                    {Object.keys(data).map(key=>(
                        <tr>
                            <td>{key}</td>
                            <td>{data[key]}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )


}
export default StudentDetails