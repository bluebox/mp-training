// import { useState } from "react";

import { useState } from "react"

// export default function useCustomHook(){
//     const [val,setVal]=useState(0)
//     const multiply=(a,b)=>{
//          setVal(a*b)
//     }
//     return {val,multiply}
// }

const useCustomHook=()=>{
    const [val,setVal]=useState(0)
    const multiply=(a,b)=>{
        setVal(a*b)
    }
    return {val,multiply}
}

export default useCustomHook