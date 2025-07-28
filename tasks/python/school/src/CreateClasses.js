import React, { useEffect, useState } from "react";
import customAXIOS from "./apis";
import { CLASSES, TEACHERS } from "./urls";
import { useNavigate } from "react-router-dom";

function CreateClasses(){
    const navigator = useNavigate()
    const [teachers,setTeacher] = useState([])
    const [classes,setClasses] = useState([])
    useEffect(()=>{
        const data = []
        customAXIOS(TEACHERS,null,'get',null,navigator)
        .then(res=>{
            res.map(r=>{
                const key = r.user
                const value = r.Name
                const item = {id:key,Name:value}
                data.push(item);
            })
            console.log(data)
            setTeacher(data)
        })
        
        customAXIOS(CLASSES,null,'get',null,navigator)
        .then(res=>{
            console.log("classes::",res)
            setClasses(res)
        })
    },[])


    return(
        <div>
            <form>
                <label>Teacher: 
                    <select>
                        <option value="">Select Teacher</option>
                        {teachers.map(teacher=>{
                            <option value={teacher.id}>{teacher.Name}</option>
                        })}
                    </select>
                </label>
            </form>
        </div>
    )
}
export default CreateClasses
