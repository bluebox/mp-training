import React, { useEffect, useState } from "react";
import customAXIOS from "./apis";
import { CLASSES, SUBJECTS, TEACHERS } from "./urls";
import { useNavigate } from "react-router-dom";

function SubjectAssaign(){
    const [classes,setClasses]=useState([])
    const [subjects, setSubjects]=useState([])
    const [teachers, setTeachers]=useState([])
    const navigator = useNavigate()
    useEffect(()=>{
        customAXIOS(CLASSES,null,'get',null,navigator)
        .then(res=>{
            setClasses(res)
            console.log("res:",res);
        })
        .catch(err=>{
            alert("Error in fetching classes")
            console.log("Error:",err);
        })
    })

    useEffect(()=>{
        customAXIOS(SUBJECTS,null,'get',null,navigator)
        .then(res=>{
            setSubjects(res)
            console.log("res:",res);
        })
        .catch(err=>{
            alert("Error in fetching subjects")
            console.log("Error:",err)
        })
    })

    useEffect(()=>{
        customAXIOS(TEACHERS,null,'get',null,navigator)
        .then(res=>{
            setTeachers(res)
            console.log("res:",res);
        })
        .catch(err=>{
            alert("Error in fetching Teachers")
            console.log("Error:",err)
        })
    })
}