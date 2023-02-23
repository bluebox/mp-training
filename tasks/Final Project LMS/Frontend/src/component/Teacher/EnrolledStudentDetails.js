import React from "react";
import { Link, useParams } from "react-router-dom";
import MSidebar from "./MSidebar";
import { useState, useEffect } from "react";
import axios from "axios";
import { Button } from "react-bootstrap";
const baseUrl = 'http://127.0.0.1:8000/api/fetch_enrolled_students/';
function EnrolledStudentDetails() {
    const [studentData, setStudentData] = useState([]);
    let {course_id} = useParams();
    useEffect(() => {
        try {
            axios.get(baseUrl + course_id)
                .then((res) => {
                    console.log(res.data);
                    setStudentData(res.data);
                });
        }
        catch (error) {
            console.log(error);
        }
        
    }, []);
    return (
        <div className="container mt-4">
            <div className="row">
                <aside className="col-md-3">
                    <MSidebar />
                </aside>
                <section className="col-md-9">
                    <div className="card">
                        <div className="card-header">Student Details</div>
                        <div className="card-body">
                            <table className="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>E-mail</th>
                                        <th>Interested In</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {studentData.map((object, index) => 
                                        
                                        <tr>
                                            <td><Link to={`/student/`+ object.student.id}><Button variant="info">{object.student.first_name}</Button></Link></td>
                                            <td>{object.student.email}</td>
                                            <td>
                                            {object.student.interested_category}
                                            </td>
                                        </tr>
                                       
                                    )}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </section>
            </div>
        </div>

    )
}

export default EnrolledStudentDetails;