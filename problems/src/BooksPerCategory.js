import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
const BooksPerCategory = () => {
  const [Data, setData] = useState({});
  const [message,setMessage]=useState('');
  useEffect(() => {
    fetch('/http://localhost:8070/Reports/BooksperCategory') 
      .then((response) => {
        return response.json()
      })
      .then(data=>setData(data))
      .catch(error=>{
        console.error(error);
        setMessage(error);
      })
  }, []);

console.log(Data);

  return (
    <div className="Container">
      <h2>Books Per Category</h2>
      <p>{message}</p>
      {Object.keys(Data).length === 0 ? (
        <p>No data available yet.</p>
      ) : (
   <table>
  <thead>
    <tr>
      <th>Category</th>
      <th>Count</th>
    </tr>
  </thead>
  <tbody>
   {Object.entries(Data).map(([category, count]) => (
      <tr>
        <td>{category}</td>
        <td>{count}</td>
      </tr>
    ))}
  </tbody>
</table>
      )}
    </div>
  );
};

export default BooksPerCategory;
