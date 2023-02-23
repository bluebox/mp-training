import React from "react";
import { Link } from "react-router-dom";
import MSidebar from "./MSidebar";
import { useState, useEffect } from "react";
import axios from "axios";
import Swal from "sweetalert2";
const baseUrl = 'http://127.0.0.1:8000/api/teacher_courses/';
const baseUrl1 = 'http://127.0.0.1:8000/api/teacher_courses_delete/';
function MCourse() {
    const [courseData, setCourseData] = useState([]);
    const teacherId = localStorage.getItem('teacherId');
    useEffect(() => {
        try {
            axios.get(baseUrl + teacherId)
                .then((res) => {
                    setCourseData(res.data);
                });
        }
        catch (error) {
            console.log(error);
        }

    }, []);
    const handleDelete = (course_id) =>{
        Swal.fire({
            title: 'Do you want to delete the course',
            showCancelButton: true,
            confirmButtonText: 'Delete',
          }).then((result) => {
            if (result.isConfirmed) {
              try{
                axios.delete(baseUrl1+course_id)
                .then((res)=>{
                    console.log(res)
                    Swal.fire({
                        title: "Course Deleted",
                        confirmButtonText: 'Ok',
                    }).then((result) => {
                        if (result.isConfirmed) {
                            window.location.reload();
                        }
                    })
                })
              }
              catch(error){
                console.log(error);
              }
            } else if (result.isDenied) {
              Swal.fire('Course Not deleted', '', 'info')
            }
          })
    }
    return (
        <div className="container mt-4">
            <div className="row">
                <section className="col-md-9">
                    <div className="card">
                        <div className="card-header">My Courses</div>
                        <div className="card-body">
                            {courseData.length !== 0 &&
                                <table className="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Image</th>
                                            <th>Total Enrolled</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {courseData.map((course, index) =>

                                            <tr>
                                                <td><Link to={`/all_topic_videos/` + course.id}>{course.title}</Link></td>
                                                <td><img src={course.image} width="80" className="rounded" alt={course.title} /></td>
                                                <td>
                                                    <Link to={`/enrolled_students/` + course.id}>{course.total_enrolled_students}</Link>
                                                </td>
                                                <td>
                                                    <Link to={`/master-add-video/` + course.id} className="btn btn-primary active btn-sm">
                                                        Add Videos
                                                    </Link>
                                                    <button onClick={()=>handleDelete(course.id)} className="btn btn-sm active btn-danger ms-1">
                                                        Delete
                                                    </button>
                                                </td>
                                            </tr>

                                        )}
                                    </tbody>
                                </table>
                            }
                            {courseData.length === 0 &&
                                <h2 className="text-danger">You have not added any courses yet</h2>
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

export default MCourse;