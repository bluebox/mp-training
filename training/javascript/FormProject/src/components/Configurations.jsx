import { useState } from "react";

export default function Configurations(){
    const [num_rows,setrows]=useState(0);
    function handleChange(e){
        console.log(e.target.value)
        // setrows(e.target.value)

    }
    return (<>
        <h1>Configuration Settings</h1>
        <form>
            <label>Numer of rows:</label>
            <input onChange={(e)=>handleChange(e)} ></input>
        </form>
        </>
    );  
}

