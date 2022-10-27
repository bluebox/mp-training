import React, {useState} from 'react'

function HookCounter() {
    const [count,setCount] = useState(0);

   function handleCount (){
        setCount(count + 1)
    }

  return (
    <div>
        <button onClick={handleCount}>count {count}</button>
    </div>
  )
}

export default HookCounter