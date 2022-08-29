import React from "react";


// function Greet() {
//   return (
//     <h1>Hello Irfan</h1>
//   )
// }

const Greet = ({name,heroname,children}) =>  //(props)=> props.name
{
    return(
        <>
<h1>Hello {name} a.k.a {heroname}</h1>
{children}
</>
    )
}
export default Greet