import React from "react";


function Logout(){
    localStorage.setItem('userLoginStatus',false);
    localStorage.removeItem('userId');
    window.location.href='/user-login';
    return(
        <div></div>
    )
}

export default Logout;