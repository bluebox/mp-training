
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import '../styles/Employees.css';
import api from "../api/api";
import { useAuth } from "../context/AuthContext";

const EmployeeAttendence = () => {
    const { id } = useParams();
    const [attendence, setAttendence] = useState([]);
    const [loading, setLoading] = useState(true);
    const [refreshTrigger, setRefreshTrigger] = useState(0);
    const navigate = useNavigate();
    const [status,setStatus]=useState({
        attendancestatus: ""
    });

    useEffect(() => {
        fetchAttendence();
    },[refreshTrigger]);

    const fetchAttendence = async () => {
        try {
            const res = await axios.get(`http://localhost:8080/api/attendance/${id}`,
                { withCredentials: true }
            );
            setAttendence(res.data);
        } catch (err) {
            console.error("Error fetching team members:", err);
        } finally {
            setLoading(false);
        }
    };
    const { user } = useAuth();
    const handleAction = async () => {
        if(user.role==="ROLE_ADMIN"){
            navigate(`/admindashboard/employees`);
        }
        else{   
                navigate(`/managerdashboard/Team-members`);
           
        }
    };

    const handleEdit = async (date,empattendancestatus) => {
        try {
            if(status.attendancestatus===""){
                status.attendancestatus=empattendancestatus;
            }
            await api.put(`/attendance/${id}/edit?date=${date}&status=${status.attendancestatus}`, { withCredentials: true });
        setRefreshTrigger(prev => prev + 1);
      }
     catch (err) {
      console.error("Error updating leave:", err);
    }
    };

    if (loading) return <p>Loading Employee Attenedence...</p>;


    return (
        <div className="employees-container">
            <h2 className="employees-heading">Employee: {id} Attendance</h2>
            <div className="table-responsive">
                <table className="employees-table">
                    <thead>
                        <tr>
                            <th className="border p-2">Date</th>
                            <th className="border p-2">checkIn</th>
                            <th className="border p-2">checkOut</th>
                            <th className="border p-2">Working Hours</th>
                            <th className="border p-2">Status</th>
                            <th className="border p-2">Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                        {attendence.length === 0 ? (
                            <tr>
                                <td colSpan={6} style={{ textAlign: 'center' }}>No Attendence Marked</td>
                            </tr>
                        ) : (attendence.map((attendance) => (
                            <tr key={attendance.id} className="text-center">
                                <td className="border p-2">{attendance.date}</td>
                                <td className="border p-2">{attendance.checkIn}</td>
                                <td className="border p-2">{attendance.checkOut}</td>
                                <td className="border p-2">{attendance.workingHrs}</td>
                                <td className="border p-2">
                                <select className="border p-2" value={attendance.attendenceStatus}
                                onChange={(e) => { setStatus((preVal) => { return ({ ...preVal, attendancestatus: e.target.value }) }) }} >
                               <option value="" disabled>Select Status</option>
                                <option value="ABSENT" >ABSENT</option>

                              <option value="HALF_DAY" >HALF_DAY</option>
                                <option value="PRESENT" >PRESENT</option>

                            </select>  
                                </td>
                                <td><button style={{ backgroundColor: "#088220ea", color: "white", border: "none", fontWeight: "bold" }} onClick={() => handleEdit(attendance.date,attendance.attendenceStatus)} >Edit</button></td>
                            </tr>
                        ))
                        )}
                    </tbody>
                </table>
                <center><div><button style={{ backgroundColor: "#1e40af", color: "white", border: "none", fontWeight: "bold", padding: "10 px", margin: "10 px" }} onClick={() => handleAction()}>Back</button></div></center>
            </div>

        </div>
    );
};

export default EmployeeAttendence;