import React, { useEffect, useState } from 'react';
import './App.css';
import { Link } from 'react-router-dom';

function ActiveBookMembers() {
  const [members, setMembers] = useState([]);
  const [message,setMessage] =useState('');

  useEffect(() => {
    fetch('http://localhost:8070/Reports/ActiveMemberswithbooks')
      .then(response => response.json())
      .then(data => setMembers(data))
      .catch(error => {console.error(error);
        setMessage(error);
      });
  }, []);


console.log(members);



  return (
    <div className="Container">
      <h2>ActiveMembers Table View</h2>
      {message!=null && <p>{message}</p>}
      {members.length === 0 ? (
        <p>No members available yet .</p>
      ) : (
    <table>
  <thead>
    <tr>
      <th>Member ID</th>
      <th>Name</th>
      <th>Email</th>
      <th>Mobile</th>
      <th>Gender</th>
      <th>Address</th>
    </tr>
  </thead>
  <tbody>
    {members.map((member) => (
      <tr key={member.MemberId}>
        <td>{member.MemberId}</td>
        <td>{member.Name}</td>
        <td>{member.Email}</td>
        <td>{member.Mobile}</td>
        <td>{member.gender}</td>
        <td>{member.Address}</td>
      </tr>
    ))}
  </tbody>
</table>
    
      )}
       <Link to="/Memberhome/AddMember">Add New Member</Link>
            <br />
            <Link to="/Memberhome/UpdateMember">Update the Member Details</Link>
            <br />
    </div>
  );
}

export default ActiveBookMembers;