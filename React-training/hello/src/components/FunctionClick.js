import React from 'react'

function FunctionClick() {
    function clickHandler(){
        console.log("button clicked")
    }
  return (
    <button onClick={clickHandler}>Click</button>
  )
}

export default FunctionClick