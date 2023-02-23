import React from "react";
import {  Link } from "react-router-dom";
import MSidebar from "./MSidebar";
import { useState, useEffect } from "react";
import axios from "axios";
import { Form, Button } from "react-bootstrap";
import { addCourse } from "../service/InstructorService";
import Swal from "sweetalert2";
const baseUrl="http://127.0.0.1:8000/api/sub_category/";
const baseUrl1="http://127.0.0.1:8000/api/course/";
function AddCourse() {
    const[subcategory, setSubCategory]=useState([]);
    const [picture, setPicture]=useState(null);

    useEffect(()=>{
        try{
            axios.get(baseUrl)
            .then((res)=>{
                  setSubCategory(res.data); 
            });
        }
        catch(err){
            console.log(err);
        }
    },[]);
    // console.log(subcategory);

    const submitForm = (e) => {
        e.preventDefault();
        addCourse(e.target)
        .then((result)=>{
            console.log(result);
            if (result === 'Course Data Added Successfully') {
                Swal.fire({
                    title: "Course Added",
                    confirmButtonText: 'Ok',
                }).then((result) => {
                    if (result.isConfirmed) {
                        window.location.href = '/master-mycourse';
                    }
                })
            }
        },(err)=>{
            alert(err);
        })
    }
    return (
        <div className="container mt-4">
            <div className="row">
                <section className="col-9">
                    <div className="card">
                        <h5 className="card-header">
                            About Course
                        </h5>
                        <div className="card-body">
                        <Form onSubmit={submitForm}>
                                <Form.Group controlId="subcategory">
                                    <Form.Label>Sub-Category</Form.Label>
                                    <Form.Select>{subcategory.map((name,id)=>{return<option key={id} value={name.id}>{name.name}</option>})}</Form.Select>
                                </Form.Group>
                                <Form.Group controlId="title">
                                    <Form.Label>Title</Form.Label>
                                    <Form.Control type="text" name="title" required placeholder="Title of your course" />
                                </Form.Group>
                                <Form.Group controlId="description">
                                    <Form.Label>Description</Form.Label>
                                    <Form.Control type="text" name="description" required placeholder="Description about your course" />
                                </Form.Group>
                                <Form.Group controlId="image">
                                    <Form.Label>Featured Image</Form.Label>
                                    <Form.Control type="file" accept="image/png, image/jpeg" onChange={(e)=>setPicture(e.target.files)} name="image" placeholder="Choose Image for thumbnail" />
                                </Form.Group>
                                <Form.Group controlId="language">
                                    <Form.Label>Language</Form.Label>
                                    <Form.Control type="text" name="language" required placeholder="English, Hindi , Japanese etc." />
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

export default AddCourse;