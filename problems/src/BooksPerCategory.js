import React, { useEffect, useState } from 'react';
import { useCookies } from 'react-cookie';


function BooksPerCategory() {
  const [Data, setData] = useState([]);
  const [message,setMessage]=useState('');
  const [cookies, setCookie] = useCookies(['name']);
  useEffect(() => {
    fetch('/http://localhost:8070/Reports/BooksperCategory') 
      .then(response => response.json())
      .then(data => setData(data))
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
   {Object.Keys(Data).map((key) => (
      <tr key={key}>
        <td>{key}</td>
        <td>{Data[key]}</td>
      </tr>
    ))}
  </tbody>
</table>
      )}
      <p>@ {cookies.name}</p>
    </div>
  );
};

export default BooksPerCategory;
