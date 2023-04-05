
import axios from "axios";
import React, { useEffect, useState } from "react";
const baseUrl = "http://127.0.0.1:8000/api/login/"
function MLogin() {
    const [instructorLoginData, setInstructorLoginData] = useState({
        username: '',
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
        const teacherFormData = new FormData();
        teacherFormData.append('username', instructorLoginData.username)
        teacherFormData.append('password', instructorLoginData.password)
        try {
            axios.post(baseUrl, teacherFormData)
                .then((res) => {
                    if (res.data.bool[0] === true && res.data.staff === true) {
                        localStorage.setItem('teacherLoginStatus', true);
                        localStorage.setItem('loginstatus', true);
                        localStorage.setItem('userId', res.data.id);
                        localStorage.setItem('teacherId', res.data._id);
                        localStorage.setItem('accessToken', res.data.access);
                        localStorage.setItem('refreshToken', res.data.refresh);
                        window.location.href = '/master-mycourse';
                    }
                    else if (res.data.bool[0] === true && res.data.staff === false) {
                        localStorage.setItem('userLoginStatus', true);
                        localStorage.setItem('loginstatus', true);
                        localStorage.setItem('userId', res.data.id);
                        localStorage.setItem('studentId', res.data._id);
                        localStorage.setItem('accessToken', res.data.access);
                        localStorage.setItem('refreshToken', res.data.refresh);
                        window.location.href = '/user-mycourse';

                    }
                    else {
                        setErrorMsg('Invalid Email or Password!!')
                    }
                });
        }
        catch (err) {
            if (err.response && err.response.data && err.response.data.detail) {
                setErrorMsg(err.response.data.detail);
            } else {
                setErrorMsg('Something went wrong. Please try again later.');
            }
        }
    }
    const teacherLoginStatus = localStorage.getItem('teacherLoginStatus')
    if (teacherLoginStatus === 'true') {
        window.location.href = '/master-mycourse';
    }
    const userLoginStatus = localStorage.getItem('userLoginStatus')
    if (userLoginStatus === 'true') {
        window.location.href = '/user-mycourse';
    }
    useEffect(() => {
        document.title = 'Login'
    })
    return (
        <div className="container mt-4">
            <div className="row">
                <div className="col-6 offset-3">
                    <div className="card">
                        <h5 className="card-header" style={{ textAlign: "center" }}>
                            Login
                        </h5>
                        <div className="card-body">
                            {errorMsg && <p className="text-danger">{errorMsg}</p>}
                            <div className="mb-3">
                                <label for="inputEmailI" className="form-label">Username</label>
                                <input type="text" name="username" onChange={handleChange} className="form-control" id="username" aria-describedby="emailHelp" />
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