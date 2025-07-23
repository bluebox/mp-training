import { useState } from "react";

export default function Counter(){
    let [count,setcount]=useState(0)
    function handleClick(){
        setcount(prev=>prev+1)
    }
    return(
        <>
        <h1>The counter is at {count}</h1>
        <button onClick={handleClick}>Click here</button>
        </>
    );
}