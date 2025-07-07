import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';

const ViewAllMembers = () => {

  const [members , setMembers] = useState([]);
  const [loading , setLoading] = useState(true);

  useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch("http://localhost:8080/members/list");

                if(!response.ok){
                    throw new Error(`http error : ${response.status}`);
                }

                const data = await response.json();

                setMembers(data);

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
      <h1>Members List</h1>

      <table border="1">

        <thead>
            <tr>

            <th>Id</th>
            <th>Name</th>
            <th>Mobile</th>
            <th>Gender</th>
            <th>Address</th>
            <th>Action</th>
            

            </tr>
            

        </thead>

        <tbody>
            {
                members.map((mem) => (
                    <tr key={mem.memberId}>

                        <td>{mem.memberId}</td>
                        <td>{mem.name}</td>
                        <td>{mem.mobile}</td>
                        <td>{mem.gender}</td>
                        <td>{mem.address}</td>
                        <td><Link to ={`/edit-member/${mem.memberId}`} >edit</Link></td>
                        
                    </tr>
                ))
            }
        </tbody>
      </table>


    </div>
  )
}

export default ViewAllMembers
