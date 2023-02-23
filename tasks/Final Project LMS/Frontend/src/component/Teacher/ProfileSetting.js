import React from "react";
import MSidebar from "./MSidebar";
import { useState, useEffect } from "react";
import { Button, Form } from "react-bootstrap";
import { updateInstructor } from "../service/InstructorService";
import axios from "axios";
import Swal from "sweetalert2";
const baseUrl = "http://127.0.0.1:8000/api/teacher/";
function MProfileSetting() {
    const [instructorData, setInstructorData] = useState({
        'first_name':'',
        'last_name':'',
        'email':'',
        'password':'',
        'qualification':'',
        'designation':'',
        'mobile_no':'',
        'skills':'',
});
    const teacher_id = localStorage.getItem('teacherId');
    useEffect(()=>{
        try{
            axios.get(baseUrl+teacher_id+'/')
            .then((res)=>{
                setInstructorData({
                    first_name:res.data.first_name,
                    last_name:res.data.last_name,
                    email:res.data.email,
                    password: res.data.password,
                    qualification:res.data.qualification,
                    designation:res.data.designation,
                    mobile_no:res.data.mobile_no,
                    skills:res.data.skills,
                });
            });
        }catch(error){
            console.log(error);
        }
    },[]);
    const handleSubmit = (e) => {
        e.preventDefault();
        updateInstructor(e.target, teacher_id)
            .then((result) => {
                if(result == 'success'){
                    Swal.fire({
                        title: "Data Updated Successfully",
                        confirmButtonText: 'Back To Dashboard',
                    }).then((result) =>{
                        if(result.isConfirmed){
                            window.location.href='/master-dashboard';
                        }
                    })
                }
            },(error) => {
                    alert(error);
                })
    }
    const handlechange = (e) =>{
        setInstructorData({
            ...instructorData,[e.target.name]: e.target.value
        });
    }
    return (
        <div className="container mt-4">
            <div className="row">
                <section className="col-md-9">
                    <div className="card">
                        <h5 className="card-header"> Update Profile</h5>
                        <div className="card-body">
                            <Form onSubmit={handleSubmit}>
                                <Form.Group controlId="first_name">
                                    <Form.Label className="fw-bold">First Name</Form.Label>
                                    <Form.Control type="text" name="first_name" value={instructorData.first_name} onChange={handlechange} />
                                </Form.Group>
                                <Form.Group controlId="last_name">
                                    <Form.Label className="fw-bold">Last Name</Form.Label>
                                    <Form.Control type="text" name="last_name" value={instructorData.last_name} onChange={handlechange}/>
                                </Form.Group>
                                <Form.Group controlId="email">
                                    <Form.Label className="fw-bold">Email</Form.Label>
                                    <Form.Control type="email" name="email" value={instructorData.email} onChange={handlechange}/>
                                </Form.Group>
                                <Form.Group controlId="password">
                                    <Form.Label className="fw-bold">Password</Form.Label>
                                    <Form.Control type="text" name="password" value={instructorData.password} onChange={handlechange}/>
                                </Form.Group>
                                <Form.Group controlId="qualification">
                                    <Form.Label className="fw-bold">Quailification</Form.Label>
                                    <Form.Control type="text" name="qualification" value={instructorData.qualification} onChange={handlechange}/>
                                </Form.Group>
                                <Form.Group controlId="designation">
                                    <Form.Label className="fw-bold">Designation</Form.Label>
                                    <Form.Control type="text" name="designation" value={instructorData.designation} onChange={handlechange}/>
                                </Form.Group>
                                <Form.Group controlId="mobile_no">
                                    <Form.Label className="fw-bold">Contact</Form.Label>
                                    <Form.Control type="text" name="mobile_no" value={instructorData.mobile_no} onChange={handlechange}/>
                                </Form.Group>
                                <Form.Group controlId="skills">
                                    <Form.Label className="fw-bold">Skills</Form.Label>
                                    <Form.Control as="textarea" name="skills" value={instructorData.skills} onChange={handlechange}/>
                                    <Form.Text className="text-muted">
                                        Html , Css , Javascript etc.
                                    </Form.Text>
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
                <aside className="col-md-3">
                    <MSidebar />
                </aside>
            </div>
        </div>
    )
}

export default MProfileSetting;