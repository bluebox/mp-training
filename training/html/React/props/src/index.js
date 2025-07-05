import ReactDOM from 'react-dom/client';
import { useState } from 'react';


function Form(){
  const [num1,setNum1]=useState(0);
  const [num2,setNum2]=useState(0);
  const onChangeNum1=(event)=>{
    const val=Number(event.target.value);
    setNum1(val);
  };
  const onChangeNum2=(event)=>{
    const val=Number(event.target.value);
    setNum2(val);
  };
  const Add=(event)=>{
    event.preventDefault();
    console.log(`Sum of ${num1} and ${num2} is ${num1+num2}`);
  }
  return(
    <div>
      <form>
        <label htmlFor="num1" style={{backgroundColor:"red",border:"solid"}}>Give the value of first number</label>
        <input type='number' id="num1" value={num1} onChange={onChangeNum1}/>
        <label htmlFor='num2'>Give the value of second number</label>
        <input type='number' id='num2' value={num2} onChange={onChangeNum2}/>
        <input type='submit' onClick={Add}/>
      </form>
    </div>
  );
}
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<Form/>);
