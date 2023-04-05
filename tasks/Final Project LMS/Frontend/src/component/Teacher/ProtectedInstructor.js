import React, { useEffect } from "react";
import { Image } from "react-bootstrap";
import { useParams } from "react-router-dom";
import Swal from "sweetalert2";

function ProtectedInstructor(props) {
    const { Component } = props;
    let InsLogin = localStorage.getItem('teacherLoginStatus');

    const {master_id} = useParams();
    useEffect(() => {
        
        if (InsLogin === 'false' && master_id === 0) {
            Swal.fire({
                title: "User not logged In!!",
                icon: 'error',
                confirmButtonText: 'Ok',
            })
        }
    },[InsLogin, master_id]);
    return (
        <div>
            {(InsLogin === 'true' || master_id > 0) &&
                <Component />}
            {InsLogin === 'false' && master_id === undefined &&
            <Image style={{width: '100%', height: '80%'}} src='./404Error.jpg'/>
            }
        </div>
    )
}
export default ProtectedInstructor;