import React, { useEffect } from "react";
import Swal from "sweetalert2";
import 'animate.css';
function Page404() {
    useEffect(() => {
        Swal.fire({
            title: 'Page Not Found!',
            text: 'Invalid Url',
            icon: 'error',
            confirmButtonText: 'Ok',
            showClass: {
                popup: 'animate__animated animate__fadeInDown'
            },
            hideClass: {
                popup: 'animate__animated animate__fadeOutUp'
            }
        })
    })
    return (
        <div>
            <img style={{width: '100%', height: '80%'}} src='./404Error.jpg' alt='Loading...' />
        </div>
    )
}
export default Page404;