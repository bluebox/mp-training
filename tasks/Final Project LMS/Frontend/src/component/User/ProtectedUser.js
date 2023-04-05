import React, { useEffect } from "react";
import Swal from "sweetalert2";

function ProtectedUser(props) {
    const { Component } = props;
    let userLogin = localStorage.getItem('userLoginStatus');
    useEffect(() => {

        if (userLogin === 'false') {
            Swal.fire({
                title: "User not logged In!!",
                icon: 'error',
                confirmButtonText: 'Ok',
            })
        }
    });
    return (
        <div >
            {userLogin === 'true' &&
                <Component />}
            <div>
                {userLogin === 'false' &&
                    <img style={{width: '100%', height: '80%'}} src='./404Error.jpg' alt='Loading...' />
                }
            </div>

        </div>
    )
}
export default ProtectedUser;