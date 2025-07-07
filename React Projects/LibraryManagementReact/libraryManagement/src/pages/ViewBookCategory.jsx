import React, { useEffect, useState } from 'react'

const ViewBookCategory = () => {
  const [books , setBooks] = useState({});
    const [loading , setLoading] = useState(true);
    const [error , setError] = useState(null);
  
    useEffect(()=>{
      const fetchData = async () => {
          try{
              const response = await fetch("http://localhost:8080/books/category-count");
              if(!response.ok){
                  throw new Error(`http error status : ${response.status}`)
  
              }
              const data = await response.json();
              setBooks(data);
              console.log(data);
          }catch(err){
              setError(err);
          }
          finally{
              setLoading(false);
          }
      };
  
      fetchData();
    }, [])
  
    if(loading) return <div>Loading</div>;
    if(error) return <div>Error : {error.message}</div>;
  
    return (
      <div>
        <h1>Books Category List</h1>
  
        <table border="1">
  
          <thead>
              <tr>
  
              <th>category</th>
              <th>count</th>
              
  
              </tr>
              
  
          </thead>
  
          <tbody>
              {
                  Object.entries(books).map(([category ,count] , index) => (
                      <tr key = {index}>
  
                          <td>{category}</td>
                          <td>{count}</td>
                          
                      </tr>
                  ))
              }
          </tbody>
        </table>
  
  
      </div>
    );
}

export default ViewBookCategory
