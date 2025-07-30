import React, { useEffect } from "react";


useEffect(() =>
    fetch("http://127.0.0.1:8000/Web_World/customers/")
        .then((response)  =>{

        if(!response.ok){
            alert("we didn't get the response")
        }
        else{
            
        }
        })

)