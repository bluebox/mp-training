import React, { useEffect, useState } from 'react';
import './App.css';
import { Link } from 'react-router-dom';

function ViewIssues() {
  const [books, setBooks] = useState([]);
  const [message,setMessage]=useState('');

  useEffect(() => {
    fetch('http://localhost:8070/Issues/viewIssues')
      .then(response => response.json())
      .then(data => setBooks(data))
      .catch(error => {console.error(error);
        setMessage(error);
      });
  }, []);


console.log(books);



  return (
    <div className="Container">
      <h2>IssueRecords Table View</h2>
      <p>{message}</p>
      {books.length === 0 ? (
        <p>No books available yet .</p>
      ) : (
    <table>
  <thead>
    <tr>
      <th>Book Id</th>
      <th>Member Id</th>
      <th>Issue Status</th>
      <th>Issue Date</th>
      <th>Return Date</th>
    </tr>
  </thead>
  <tbody>
    {books.map((book) => (
      <tr key={book.IssueRecordId}>
        <td>{book.BookId}</td>
        <td>{book.MemberId}</td>
        <td>{book.status}</td>
        <td>{book.issueDate}</td>
        <td>{book.ReturnDate}</td>
      </tr>
    ))}
  </tbody>
</table>
    
      )}
       <Link to="/IssueRecordshome/CreateIssue">Create new Issue</Link>
            <br />
            <Link to="/IssueRecordshome/Return">Return Book</Link>
            <br />
    </div>
  );
}

export default ViewIssues;
