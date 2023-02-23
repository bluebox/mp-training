
import axios from "axios";
import React, { useEffect, useState } from "react";
const baseUrl = "http://127.0.0.1:8000/api/user/student-login/";
function Login() {
    const [userLoginData, setUserLoginData] = useState({
        email: '',
        password: ''
    });
    const [errorMsg, setErrorMsg] = useState('');
    const handleChange = (e) => {
        setUserLoginData({
            ...userLoginData,
            [e.target.name]: e.target.value
        });
    }

    const submitForm = () => {
        const userFormData=new FormData();
        userFormData.append('email',userLoginData.email)
        userFormData.append('password',userLoginData.password)
        try{
            axios.post(baseUrl,userFormData)
            .then((res)=>{
                if(res.data.bool===true){
                    localStorage.setItem('userLoginStatus',true);
                    localStorage.setItem('userId', res.data.student_id);
                    window.location.href='/user-dashboard';
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
    const userLoginStatus = localStorage.getItem('userLoginStatus')
    if (userLoginStatus==='true'){
        window.location.href='/user-dashboard';
    }
    useEffect(() => {
        document.title = 'User Login'
    })
    return (
        <div className="container mt-4">
            <div className="row">
                <div className="col-6 offset-3">
                    <div className="card">
                        <h5 className="card-header">
                            Student Login
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

export default Login;