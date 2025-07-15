import React, {useEffect, useState } from 'react';
import axios from 'axios';

const AllUsers = () => {
  const [users, setUsers] = useState([]);
  const [empty, setEmpty] = useState();
  const [success, setSuccess] = useState(0);

  const makeAdmin = (userId) => {
    return () => {
      axios.put(`http://localhost:8080/api/user/makeAdmin/${userId}`)
        .then(response => {
          setSuccess(1);
          const updatedUser = response.data; 
          console.log("Updated:", updatedUser);
          setUsers(prevUsers =>
            prevUsers.map(user =>
              user.userId === userId
                ? { ...user, role: 'ADMIN', modifiedAt: updatedUser.modifiedAt, modifiedBy: updatedUser.modifiedBy }
                : user
            )
          );
        })
        .catch(error => {
          setSuccess(-1);
        });
      setTimeout(() => {
        setSuccess(0);
      }, 3000);
    }
  };

  useEffect(()=>{
    axios.get("http://localhost:8080/api/user/allUsers")
    .then((response)=> {
      if (Array.isArray(response.data)) {
        setUsers(response.data);
      } else if (response.data && typeof response.data === 'object') {
        setUsers(Object.values(response.data));
      } else {
        setUsers([]);
      }
    })
    .catch((error) => {
      console.error(error);
      setUsers([]);
    })
  },[]);

  useEffect(() => {
    if (success === 1) {
      axios.get("http://localhost:8080/api/user/allUsers")
        .then((response) => {
          if (Array.isArray(response.data)) {
            setUsers(response.data);
          } else if (response.data && typeof response.data === 'object') {
            setUsers(Object.values(response.data));
          } else {
            setUsers([]);
          }
        })
        .catch((error) => {
          console.error(error);
          setUsers([]);
        });
    }
  }, [success]);

  useEffect(() => {
    setEmpty(users.length === 0);
  }, [users]);

 

  return (
    <div className="questionsdiv">
      {success === 1 && <div className="success">Updated successfully!</div>}
      {success === -1 && <div className="error">Error making user admin!</div>}
      {empty && <div>
        <h2>No Users Available</h2>
      </div>}
      {!empty && 
      <div className="questionsdiv">
        <h2>All Users</h2>
        <table border="1" cellPadding="8" style={{ borderCollapse: "collapse" }}>
          <thead>
            <tr>
              <th>Id</th>
              <th>Username</th>
              <th>Role</th>
              <th>Status</th>
              {/* <th>Created At</th>
              <th>Modified At</th>
              <th>Modified By</th> */}
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
          {users.map((each)=>(
               <tr key={each.userId} className={each.status === "INACTIVE" ? "inactive" : ""}>
              <td>
                {each.userId}
              </td>
              <td>
                {each.username}
              </td>
              <td>
                  {each.role}
              </td>
              <td>
                {each.status}
              </td>
              {/* <td>
                {each.createdAt}
              </td>
              
              <td>
                {each.modifiedAt}
              </td>
              <td>
                {each.modifiedBy}
              </td> */}
              {each.role !== 'ADMIN' && each.status==="ACTIVE" && <td className="makeadmin" id={each.userId} onClick={makeAdmin(each.userId)}>Make Admin
              </td>}
              {each.role === 'ADMIN' && each.status==="ACTIVE" && <td className="makeadmin" onClick={makeAdmin(each.userId)} >Make User
              </td>}
              {each.status==="INACTIVE" && <td>--- Inactive User ---
              </td>}
            </tr>
            )
          )
          }
          </tbody>
        </table>
      </div>}  
      <a href="/admin/home" style={{cursor: 'pointer'}}>Back to home</a>
    </div>
  );
};

export default AllUsers;
