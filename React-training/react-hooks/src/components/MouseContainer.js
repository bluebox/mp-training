import React, {useState, useEffect} from 'react'
import EffectMouse from './EffectMouse'

function MouseContainer() {
    const [display, setDisplay] = useState(true)
    
  return (
    <div>
        <button onClick={()=>setDisplay(!display)}>Toggle Display</button>
        {display && <EffectMouse />}
    </div>
  )
}

export default MouseContainer