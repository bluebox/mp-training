import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";

const baseUrl = 'http://127.0.0.1:8000/api';
function TeacherDetail() {
    const [teacherData, setTeacherData] = useState([]);
    const [courseData, setCourseData] = useState([]);
    let { master_id } = useParams();
    useEffect(() => {
        try {
            axios.get(baseUrl + '/teacher/' + master_id + '/')
                .then((res) => {
                    setTeacherData(res.data);
                    setCourseData(res.data.teacher_courses);
                });
        }
        catch (error) {
            console.log(error);
        }
    }, []);
    console.log(teacherData);
    console.log(courseData);
    return (
        <div className="container mt-4">
            <div className="row">

                <div className="col-8">
                    <h3>{teacherData.first_name}</h3>
                    <p>{teacherData.email}</p>
                    <p><b>Qualification :- </b>{teacherData.qualification}</p>
                    <p><b>Skills: </b>{teacherData.skills}
                    </p>
                    <p className="fw-bold">Rating: 4.5/5</p>
                </div>
            </div>
            {/*Course List */}
            <div className="card mt-4">
                <h4 className="card-header">
                    Course list by the particular insturctor
                </h4>
                {courseData && courseData.map(course =>
                    <div className="list-group list-group-flush">
                        <Link to={`/detail/${course.id}`} className="list-group-item list-group-item-action">{course.title}</Link>
                    </div>
                )}
            </div>
        </div>
    )
}

export default TeacherDetail;