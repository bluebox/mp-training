import { useState } from 'react';
import ReactDOM from 'react-dom/client';
function Greetings(){
  const [name1,setName1]=useState("");
  const onChangeName=(event)=>{
    setName1(event.target.value);
  };
  return (
    <div>
      <label htmlFor="name1">Name</label>
      <input id="name1" onChange={onChangeName}/>
      <h1>Welcome to React {name1}</h1>
    </div>
  )
}
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<Greetings/>);

