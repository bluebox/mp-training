import React from "react";
import { Link } from "react-router-dom";
import MSidebar from "./MSidebar";
import { useState, useEffect } from "react";
import axios from "axios";
const baseUrl = 'http://127.0.0.1:8000/api/fetch_enrolled_students/';
function EnrolledStudents() {
    const [studentData, setStudentData] = useState([]);
    const teacherId = localStorage.getItem('teacherId');
    useEffect(() => {
        try {
            axios.get(baseUrl)
                .then((res) => {
                    setStudentData(res.data.filter(stu => stu.course.teacher.id == teacherId));
                });
        }
        catch (error) {
            console.log(error);
        }
    }, []);

    return (

        <div className="container mt-4">
            <div className="row">
                <section className="col-md-9">
                    <div className="card">
                        <div className="card-header">My Courses</div>
                        <div className="card-body">
                            {studentData.length !== 0 &&
                                <table className="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Course Taken</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        {studentData.map(student =>
                                            <tr>
                                                <td>{student.student.first_name}</td>
                                                <td>{student.course.title}</td>
                                            </tr>
                                        )}
                                    </tbody>
                                </table>
                            }
                            {studentData.length === 0 &&
                                <h2 className="text-danger">No Students have enrolled in any of your courses yet!!</h2>
                            }
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

export default EnrolledStudents;