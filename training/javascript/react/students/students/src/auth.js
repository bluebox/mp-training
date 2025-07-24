import React from "react";

function Auth(){
    const token = localStorage.getItem('jwtRefreshToken')
    if(token){
        return true;
    }else{
        return false;
    }
}
export default Auth