import React, { useState, useEffect, useContext } from 'react';
import useCustomHook from './CustomHooks';
import { ThemeContext } from './ThemeContext';
const FunctionBasedExample = ({ value }) => {
  const [count, setCount] = useState(0);

  const handleIncrement = () => {
    setCount(prev => prev + 1);
  };

  const handleDecrement = () => {
    setCount(prev => prev - 1);
  };

  // Run once (on mount)
  useEffect(() => {
    console.log('jai shree ram');
  }, []);

  // Run on every render
  useEffect(() => {
    console.log('jai hanuman');
  });

  // Run only when `count` changes
  useEffect(() => {
    console.log('jai mahadev');
    document.title="functionalComponent"
    return () => {
      console.log('clean up function');
    };
  }, [count]);

  const { val, multiply } = useCustomHook();
  const{msg}=useContext(ThemeContext)
  return (
    <div className="p-4 max-w-md bg-white shadow-md rounded-md mt-10">
      <h1 className='text-xl font-bold'>{msg}</h1>
      <h1 className="text-xl font-bold text-red-500 mb-4">
        Custom hook value is: {val}
      </h1>
      <button
        onClick={() => multiply(5, 10)}
        className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 mb-4"
      >
        Multiply
      </button>

      <h2 className="text-lg font-semibold text-gray-700 mb-2">{value}</h2>
      <h2 className="text-lg font-semibold text-gray-800 mb-2">Function Based Component</h2>

      <h2 className="text-2xl font-bold text-indigo-700 mb-4">{count}</h2>

      <div className="space-x-2">
        <button
          onClick={handleIncrement}
          className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600"
        >
          Increment
        </button>
        <button
          onClick={handleDecrement}
          className="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600"
        >
          Decrement
        </button>
      </div>
    </div>
  );
};

export default FunctionBasedExample;
