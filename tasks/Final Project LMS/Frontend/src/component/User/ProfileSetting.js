import React from "react";
import Sidebar from "./Sidebar";
import { useState, useEffect } from "react";
import { Button, Form } from "react-bootstrap";
import { updateStudent } from "../service/InstructorService";
import axios from "axios";
import Swal from "sweetalert2";
const baseUrl = "http://127.0.0.1:8000/api/student/";
function MProfileSetting() {
    const [studentData, setStudentData] = useState({
        'first_name':'',
        'last_name':'',
        'email':'',
        'password':'',
        'qualification':'',
        'interested_category':'',
        'mobile_no':'',
        'address':'',
});
    const student_id = localStorage.getItem('userId');
    useEffect(()=>{
        try{
            axios.get(baseUrl+student_id+'/')
            .then((res)=>{
                setStudentData({
                    first_name:res.data.first_name,
                    last_name:res.data.last_name,
                    email:res.data.email,
                    password: res.data.password,
                    qualification:res.data.qualification,
                    interested_category:res.data.interested_category,
                    mobile_no:res.data.mobile_no,
                    address:res.data.address,
                });
            });
        }catch(error){
            console.log(error);
        }
    },[]);
    const handleSubmit = (e) => {
        e.preventDefault();
        updateStudent(e.target, student_id)
            .then((result) => {
                console.log(result.id)
                if(result.id){
                    Swal.fire({
                        title: "Data Updated Successfully",
                        confirmButtonText: 'Back To Dashboard',
                    }).then((result) =>{
                        if(result.isConfirmed){
                            window.location.href='/user-dashboard';
                        }
                    })
                }
            },(error) => {
                    alert(error);
                })
    }
    const handlechange = (e) =>{
        setStudentData({
            ...studentData,[e.target.name]: e.target.value
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
                                    <Form.Control type="text" name="first_name" value={studentData.first_name} onChange={handlechange} />
                                </Form.Group>
                                <Form.Group controlId="last_name">
                                    <Form.Label className="fw-bold">Last Name</Form.Label>
                                    <Form.Control type="text" name="last_name" value={studentData.last_name} onChange={handlechange}/>
                                </Form.Group>
                                <Form.Group controlId="email">
                                    <Form.Label className="fw-bold">Email</Form.Label>
                                    <Form.Control type="email" name="email" value={studentData.email} onChange={handlechange}/>
                                </Form.Group>
                                <Form.Group controlId="password">
                                    <Form.Label className="fw-bold">Password</Form.Label>
                                    <Form.Control type="text" name="password" value={studentData.password} onChange={handlechange}/>
                                </Form.Group>
                                <Form.Group controlId="qualification">
                                    <Form.Label className="fw-bold">Quailification</Form.Label>
                                    <Form.Control type="text" name="qualification" value={studentData.qualification} onChange={handlechange}/>
                                </Form.Group>
                                <Form.Group controlId="interested_category">
                                    <Form.Label className="fw-bold">Interested Category</Form.Label>
                                    <Form.Control type="text" name="interested_category" value={studentData.interested_category} onChange={handlechange}/>
                                </Form.Group>
                                <Form.Group controlId="mobile_no">
                                    <Form.Label className="fw-bold">Contact</Form.Label>
                                    <Form.Control type="text" name="mobile_no" value={studentData.mobile_no} onChange={handlechange}/>
                                </Form.Group>
                                <Form.Group controlId="address">
                                    <Form.Label className="fw-bold">Address</Form.Label>
                                    <Form.Control as="textarea" name="address" value={studentData.address} onChange={handlechange}/>
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
                    <Sidebar />
                </aside>
            </div>
        </div>
    )
}

export default MProfileSetting;