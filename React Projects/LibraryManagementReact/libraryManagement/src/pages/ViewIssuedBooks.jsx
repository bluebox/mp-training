import React, { useEffect, useState } from 'react'

const ViewIssuedBooks = () => {

  const [issueBooks , setIssueBooks] = useState([]);
  const [loading , setLoading] = useState(true);

  useEffect(() => {
          const fetchData = async () => {
              try {
                  const response = await fetch("http://localhost:8080/issues/list");
  
                  if(!response.ok){
                      throw new Error(`http error : ${response.status}`);
                  }
  
                  const data = await response.json();
  
                  setIssueBooks(data);
  
                  console.log(data);
              } catch (error) {
                  throw new Error(`http error ${error}`);
              }
              finally{
                  setLoading(false);
              }
              
          };
  
          fetchData();
  
          
    } , []);

    if(loading) return <div>Loading</div>;

  return (
    <div>
      <h1>view issued books</h1>

      <table border="1">

        <thead>
            <tr>

            <th>Issue Id</th>
            <th>Book Id</th>
            <th>Member Id</th>
            <th>Status</th>
            <th>Issue Date</th>
            <th>Return Date</th>
            

            </tr>
            

        </thead>

        <tbody>
            {
                issueBooks.map((issue) => (
                    <tr key={issue.issueId}>

                        <td>{issue.issueId}</td>
                        <td>{issue.bookId}</td>
                        <td>{issue.memberId}</td>
                        <td>{issue.status}</td>
                        <td>{issue.issueDate}</td>
                        <td>{issue.returnDate}</td>
                        
                    </tr>
                ))
            }
        </tbody>
      </table>
    </div>
  )
}

export default ViewIssuedBooks
