import React, { useEffect, useState } from 'react'
import "./styles.css"
import { Link } from 'react-router-dom'
import axios from 'axios'
const GetMembers = () => {
  const [members,setMembers] = useState([])
    const [error,setError] = useState("")

     useEffect(() => {
        axios.get("http://localhost:8080/api/member/members")
            .then((response) => {
                setMembers(response.data);
            })
            .catch((err) => {
                setError(err.message);
            });
    }, []);

    if (error) return <h3 style={{color : 'red'}}>Error : {error}</h3>;
    
  return (
    <div>
    <h1>All Members</h1>
      <table className='table'>
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
             {                  
                    members.map((member)=>(
                        <tr>
                        <td key={member.memberId}>{member.memberId}</td>
                        <td>{member.name}</td>
                        <td>{member.email}</td>
                        <td>{member.mobile}</td>
                        <td>{member.gender}</td>
                        <td>{member.address}</td>
                        </tr>
                    ))
                }
        </tbody>
      </table>
      <Link className="btn btn-dark" to="/">Go to Home</Link>
    </div>
  )
}

export default GetMembers
