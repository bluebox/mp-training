import React, { useEffect, useState } from "react";
import customAXIOS from "./apis";
import { SUBJECTS, TEACHERSUBJECTSTUDENTS } from "./urls";
import { useNavigate } from "react-router-dom";

function ResultsUpload({userId}){

    const [students,setStudents] = useState(null)
    const [subjects,setSubjects] = useState(null)
    navigator = useNavigate()
    useEffect(()=>{
        const fetchData = async()=>{

            result = await customAXIOS(TEACHERSUBJECTSTUDENTS,{id:userId},'get',null,navigator)
            setStudents(Object.keys(result));
            data = []
            subjects = await customAXIOS(SUBJECTS,null,'get',null, navigator)
            for(let i of Object.keys(result)){
                data.push(subjects[i])
            }
            setSubjects(data);
        }
        
    },[userId])

    useEffect(()=>{
        customAXIOS(SUBJECTS,null,'get',null,navigator)
        .then(res=>{
            setStudents(res)
        })
    },[])

    return(
        <div>
            <h1>Results Upload</h1>
            <form>
                <label>Subject
                    <select name="subject">
                        {subjects.map(sub=>{
                            <option>sub</option>
                        })}
                    </select>
                </label>
            </form>
        </div>
    )
}