import React,{useState} from 'react'

function HookCounterTwo() {
    const initaiCount=0
    const [count, setCount] = useState(initaiCount)

    const incrementFive = () =>{
        for(let i=0;i<5;i++){
            setCount(prevCount => prevCount +1)
        }
    }
   
  return (
    <div>
        <div>
        Count: {count}
        </div>
        <button onClick={()=>setCount(prevCount => prevCount +1)}>Increment</button>
        <button onClick={()=>setCount(prevCount => prevCount -1)}>Decrement</button>
        <button onClick={()=>setCount(initaiCount)}>Reset</button>
        <button onClick={incrementFive}>incrementFive</button>

        </div>
  )
}

export default HookCounterTwo