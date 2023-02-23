import React from "react";


function MLogout(){
    localStorage.setItem('teacherLoginStatus',false)
    localStorage.removeItem('teacherId');
    window.location.href='/master-login';
    return(
        <div></div>
    )
}

export default MLogout;