import React, { useEffect, useState } from 'react'

function IntervalHookCounter() {
    const [count,setCount ] = useState(0)

    const tick =()=>{
        setCount(prevCount =>prevCount + 1)
    }
    useEffect(()=>{
        const interval = setInterval(tick,1000)

        return ()=>{
            clearInterval(interval)
        }
    },[])
  return (
    <h1>{count}</h1>
  )
}

export default IntervalHookCounter