import { useState } from 'react';
function Counter() {
  const [count, setCount] = useState(0);

  return (
    <div>
        <h1>Counter</h1>
        <p>{count}</p>
        <button onClick={() => {
            setCount(count => count + 1);
        }} style={{ marginRight: '10px' }}>add</button>

        <button onClick={() => {
            setCount(count => 0);
        }} style={{ marginRight: '10px' }}>reset</button>

        <button onClick={() => {
            setCount(count => count - 1);
        }}>sub</button>
    </div>
  );
}


export default Counter;