import React from 'react'
import "../styles/homepage.css"
function Homeline({inpuhead,inputtext,inputimg,check=false}) {
  return (
<div className="mainmain">
    {check?<div className="homerow">
    <div className="rowinside">
        <h1>{inpuhead}</h1>
        <h5>{inputtext}</h5>
    </div>
    <div className="img">
        <img src={inputimg} alt="" />
    </div>
</div>:<div className="homerow">
        <div className="img">
            <img src={inputimg} alt="" />
        </div>
        <div className="rowinside">
            <h1>{inpuhead}</h1>
            <h5>{inputtext}</h5>
        </div>
    </div>}
    </div>
  )
}

export default Homeline