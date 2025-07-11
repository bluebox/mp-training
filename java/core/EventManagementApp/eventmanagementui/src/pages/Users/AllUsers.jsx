import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'

const AllUsers = () => {
    const [users,setusers] = useState([])
    const [error,setError] = useState("")
     useEffect(() => {
        axios.get("http://localhost:8080/api/user/users")
            .then((response) => {
                setusers(response.data);
            })
            .catch((err) => {
                setError(err.message);
            });
    }, []);


    if (error) return <h3 style={{color : 'red'}}>Error : {error}</h3>;
    
    return (
    <div>
      <h1>All Users</h1>

      <table className='table'>
        <thead>
            <tr>
                <th>User ID</th>
                <th>Name</th>
                <th>Mobile Number</th>
                <th>Email</th>
                <th>Role</th>
                <th>Gender</th>
                <th>Status</th>
                <th>Department</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
                {                  
                    users.map((user)=>(
                        <tr>
                        <td key={user.user_id}>{user.user_id}</td>
                        <td>{user.name}</td>
                        <td>{user.phn_number}</td>
                        <td>{user.email}</td>
                        <td>{user.role}</td>
                        <td>{user.gender}</td>
                        <td>{user.status}</td>
                        <td>{user.department}</td>
                        <td><Link className="btn btn-dark" to="/updateUser">Edit</Link> <Link className="btn btn-dark">Delete</Link></td>
                        </tr>
                    ))
                }
         </tbody>
      </table>  
    </div>
  )
}

export default AllUsers




