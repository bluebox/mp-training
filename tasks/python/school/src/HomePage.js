import React from "react";

function HomePage(){
    return(

        <div className="App">
            <h1>Welcome {localStorage.getItem('username')}</h1>
        </div>
    )
}
export default HomePage