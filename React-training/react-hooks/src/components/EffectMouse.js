import React,{useState, useEffect} from 'react'

function EffectMouse() {
    const [x, setX]=useState(0)
    const [y, setY] = useState(0)

    const logMouseEvent = e =>{
        console.log("Mouse Event")
        setX(e.clientX)
        setY(e.clientY)
    }
    useEffect(()=>{
        console.log("useEffect Called")
        window.addEventListener('mousemove',logMouseEvent)

        return ()=>{
            console.log('component unmounting code')
            window.removeEventListener('mousemove', logMouseEvent)
        }
    },[])
  return (
    <div>
        X- {x} Y-{y}
    </div>
  )
}

export default EffectMouse