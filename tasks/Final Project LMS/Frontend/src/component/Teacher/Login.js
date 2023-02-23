
import axios from "axios";
import React, { useEffect, useState } from "react";
const baseUrl = "http://127.0.0.1:8000/api/teacher/instructor-login/"
function MLogin() {
    const [instructorLoginData, setInstructorLoginData] = useState({
        email: '',
        password: ''
    });
    const [errorMsg, setErrorMsg] = useState('');
    const handleChange = (e) => {
        setInstructorLoginData({
            ...instructorLoginData,
            [e.target.name]: e.target.value
        });
    }

    const submitForm = () => {
        const teacherFormData=new FormData();
        teacherFormData.append('email',instructorLoginData.email)
        teacherFormData.append('password',instructorLoginData.password)
        try{
            axios.post(baseUrl,teacherFormData)
            .then((res)=>{
                if(res.data.bool===true){
                    localStorage.setItem('teacherLoginStatus',true);
                    localStorage.setItem('teacherId', res.data.teacher_id);
                    window.location.href='/master-dashboard';
                }
                else{
                    setErrorMsg('Invalid Email or Password!!')
                }
            });
        }
        catch(err){
            console.log(err);
        }
    }
    const teacherLoginStatus = localStorage.getItem('teacherLoginStatus')
    if (teacherLoginStatus==='true'){
        window.location.href='/master-dashboard';
    }
    useEffect(() => {
        document.title = 'Instructor Login'
    })
    return (
        <div className="container mt-4">
            <div className="row">
                <div className="col-6 offset-3">
                    <div className="card">
                        <h5 className="card-header">
                            Instructor Login
                        </h5>
                        <div className="card-body">
                            {errorMsg &&<p className="text-danger">{errorMsg}</p>}
                            <div className="mb-3">
                                <label for="inputEmailI" className="form-label">Email Name</label>
                                <input type="email" name="email" onChange={handleChange}  className="form-control" id="inputEmailI" aria-describedby="emailHelp" />
                            </div>
                            <div className="mb-3">
                                <label for="inputPasswordI" className="form-label">Password</label>
                                <input type="password" name="password" onChange={handleChange} className="form-control" id="inputPasswordI" />
                            </div>
                            <button type="submit" onClick={submitForm} className="btn btn-primary">Login</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default MLogin;