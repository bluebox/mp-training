import React from "react";


function MLogout(){
    localStorage.setItem('teacherLoginStatus',false)
    localStorage.setItem('userLoginStatus', false)
    localStorage.setItem('loginstatus', false)
    localStorage.removeItem('teacherId');
    localStorage.removeItem('userId');
    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');
    localStorage.removeItem('studentId');
    window.location.href='/';
    return(
        <div></div>
    )
}

export default MLogout;