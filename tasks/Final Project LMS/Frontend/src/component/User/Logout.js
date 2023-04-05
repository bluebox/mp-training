import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";


function Logout(){
    const navigate = useNavigate();
    useEffect(() => {
        
        Swal.fire({
            icon: 'warning',
            title: 'Session Timed Out. Please Login again',
            timer: 2000,
            showConfirmButton: false,
            timerProgressBar: true,
          }).then((result) => {
            if(result.dismiss === Swal.DismissReason.timer){
                localStorage.setItem('teacherLoginStatus', false);
                localStorage.setItem('userLoginStatus',false);
                localStorage.setItem('loginstatus', false)
                localStorage.removeItem('userId');
                localStorage.removeItem('accessToken');
                localStorage.removeItem('refreshToken');
                localStorage.removeItem('studentId');
                navigate('/login');
            }
        })
    })
    return(
        <div></div>
    )
}

export default Logout;