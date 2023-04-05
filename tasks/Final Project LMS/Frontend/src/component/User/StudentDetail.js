import axios from "axios";
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
const baseUrl = 'http://127.0.0.1:8000/api/student-ins/';
function StudentDetail() {
    const [studentData, setStudentData] = useState([]);
    let { student_id } = useParams();
    useEffect(() => {
        if (studentData.length === 0) {
            try {
                axios.get(baseUrl + student_id + '/')
                    .then((res) => {
                        setStudentData(res.data);
                    });
            }
            catch (error) {
                console.log(error);
            }
        }
    }, [studentData]);
    return (
        <div className="card">
            <div className="card-header">My Courses</div>
            <div className="card-body">
                <table className="table table-bordered">
                    <thead>
                        <tr>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>E-mail</th>
                            <th>Qualification</th>
                            <th>Interested Category</th>
                            <th>Contact Number</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            {studentData.user &&
                                <>
                                    <td>{studentData.user.first_name}</td>
                                    <td>{studentData.user.last_name}</td>
                                    <td>{studentData.user.email}</td>
                                </>
                            }
                            <td>{studentData.qualification}</td>
                            <td>{studentData.interested_category}</td>
                            <td>{studentData.mobile_no}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default StudentDetail