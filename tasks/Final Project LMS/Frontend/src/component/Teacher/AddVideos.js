import axios from "axios";
import React, { useEffect, useState } from "react";
import { Button, Form } from "react-bootstrap";
import { useParams } from "react-router-dom";
import { addTopic } from "../service/InstructorService";
import MSidebar from "./MSidebar";
const baseUrl="http://127.0.0.1:8000/api/course/";
function AddVideos() {
    let {course_id} = useParams();
    const [course, setCourse]=useState([]);
    useEffect(()=>{
        try{
            axios.get(baseUrl+course_id+'/')
            .then((res)=>{
                  setCourse(res.data); 
            });
        }
        catch(err){
            console.log(err);
        }
    },[]);
    console.log(course);
console.log(course_id);
const submitForm = (e) => {
    e.preventDefault();
    addTopic(e.target,course_id)
    .then((result)=>{
        alert(result);
        window.location.reload();
    },(err)=>{
        alert(err);
    })
}
    return (
        <div className="container mt-4">
            <div className="row">
                <aside className="col-md-3">
                    <MSidebar />
                </aside>
                <section className="col-9">
                    <div className="card">
                        <h5 className="card-header">
                            Course Videos
                        </h5>
                        <div className="card-body">
                        <Form onSubmit={submitForm}>
                        <Form.Group controlId="title">
                                    <Form.Label>Title</Form.Label>
                                    <Form.Control type="text" name="title" required placeholder="Title of your course" />
                                </Form.Group>
                                <Form.Group controlId="course">
                                    <Form.Label>Course</Form.Label>
                                    {/* {course.map((name,id)=>{return<option key={id} value={name.id}>{name.title}</option>})} */}
                                    <Form.Control type="text" name="course" disabled aria-readonly placeholder={course.title}/>
                                </Form.Group>
                                <Form.Group controlId="url">
                                    <Form.Label>URL</Form.Label>
                                    <Form.Control type="url" name="url" required placeholder="https://www.youtube.com" />
                                </Form.Group>
                                <Form.Group>
                                    <p></p>
                                    <Button variant="primary" type="submit">
                                        Submit
                                    </Button>
                                </Form.Group>
                            </Form>
                        </div>
                    </div>
                </section>
            </div>
        </div>

    )
}

export default AddVideos;