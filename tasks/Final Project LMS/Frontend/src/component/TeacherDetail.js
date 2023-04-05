import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";

const baseUrl = 'http://127.0.0.1:8000/api';
function TeacherDetail() {
    const [teacherData, setTeacherData] = useState([]);
    const [courseData, setCourseData] = useState([]);
    let { master_id } = useParams();
    console.log(master_id);
    useEffect(() => {
        if (teacherData.length === 0)
            try {
                axios.get(baseUrl + '/teacher-detail/' + master_id + '/')
                    .then((res) => {
                        console.log(res.data);
                        setTeacherData(res.data);
                        setCourseData(res.data.teacher_courses);
                    });
            }
            catch (error) {
                console.log(error);
            }
    }, []);
    return (
        <div className="container mt-4">
            <div className="row">

                <div className="col-8">
                    {teacherData.user &&
                        <>
                            <h1 className='text-danger'>{teacherData.user.first_name}</h1>
                            <p><b>E-mail</b> :-{teacherData.user.email}</p>
                        </>
                    }
                    <p><b>Qualification :- </b>{teacherData.qualification}</p>
                    <p><b>Skills: </b>{teacherData.skills}
                    </p>
                    <p className="fw-bold">Rating: 4.5/5</p>
                </div>
            </div>
            {/*Course List */}
            <div className="card mt-4">
                <h4 className="card-header">
                    Courses by
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