import React, {useState,useEffect} from 'react'

function HookEffectOne() {
    const [count,setCount] = useState(0)
    const [name,setName] = useState('')
    useEffect(()=>{
        console.log('useEffect--- Updating documnet title')
        document.title = `you Clicked ${count} Times`
    },[count])
  return (
    <div>
        <input type='text' value={name} onChange={e => setName(e.target.value)} />
        <button onClick={()=> setCount(count + 1)}>Clicked {count} times</button>
    </div>
  )
}

export default HookEffectOne