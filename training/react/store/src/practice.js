import React, { useState ,useRef}  from "react";


function Practice() {
  const myDivRef =  (null);

  const handleClick = () => {
    console.log("Element touched:", myDivRef.current);
    // You can now manipulate or inspect the element
  };

  return (
    <div ref={myDivRef} onClick={handleClick} style={{margin: '300px', padding: '10px', border: '1px solid black' }}>
      Touch or click me!
    </div>
  );
}export default Practice;






