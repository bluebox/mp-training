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
    const accessToken = localStorage.getItem('accessToken');
    const refreshToken = localStorage.getItem('refreshToken');
    useEffect(() => {
        if(courseData.length === 0){
            try {
                axios.get(baseUrl + teacherId, {
                    headers: { 'Authorization': `Bearer ${accessToken}` }
                  }
                  )
                    .then((res) => {
                        setCourseData(res.data);
                    })
                    .catch((error) => {
                        console.log(error.response);
                        if (error.response.status === 401) {
                            axios.post('http://127.0.0.1:8000/api/api/token/refresh/', { refresh: refreshToken })
                                .then((response) => {
                                    const newAccessToken = response.data.access;
                                    localStorage.setItem('accessToken', newAccessToken);
                                    axios.get(baseUrl + teacherId, {
                                        headers: { 'Authorization': `Bearer ${newAccessToken}` }
                                    })
                                        .then((res) => {
                                            setCourseData(res.data);
                                        })
                                        .catch((error) => {
                                            console.log(error.response);
                                        });
                                })
                                .catch((error) => {
                                    console.log(error.response);
                                    if (error.response.status === 401 && error.response.data.code === 'token_not_valid') { 
                                        window.location.href = '/user-logout';
                                    }
                                });
                        }
                        else {
                            console.log(error);
                        }
                    });
            }
            catch (error) {
                console.log(error);
            }
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
                axios.delete(baseUrl1+course_id, {
                    headers: { 'Authorization': `Bearer ${accessToken}` }
                  })
                .then((res)=>{
                    console.log(res)
                    Swal.fire({
                        title: "Course Deleted",
                        confirmButtonText: 'Ok',
                    }).then(() => {
                            window.location.reload();
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
                                                <td><img src={course.image} width="100" height="100" className="rounded" alt={course.title} /></td>
                                                <td>
                                                    <Link to={`/enrolled_students/` + course.id}>{course.total_enrolled_students}</Link>
                                                </td>
                                                <td>
                                                    <Link to={`/master-add-video/` + course.id +"/"+ course.title} className="btn btn-primary active btn-sm">
                                                    <i class="bi bi-plus-square-fill"></i>
                                                    </Link>
                                                    <Link to={`/master-edit-course/`+ course.id} className="btn btn-sm active btn-success ms-1">
                                                    <i class="bi bi-pencil-square"></i>
                                                    </Link>
                                                    <button onClick={()=>handleDelete(course.id)} className="btn btn-sm active btn-danger ms-1">
                                                    <i class="bi bi-trash3"></i>
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