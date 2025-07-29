import axios from "axios";
import { useEffect, useState } from "react"

export default function ViewAll(){

    const [users, setUsers] =useState([]);

    useEffect(() => {
    const access = localStorage.getItem("access");
      axios
      .get("http://localhost:8000/api/profiles/all/", {
        headers: {
          Authorization: 'Bearer '+access,
        },
      })
      .then((res) => setUsers(res.data))
      .catch(console.log("error in retrieving tasks"));
  }, []);
    return (
        <div>
            <table>
                <tr>
                    <th>Username</th>
                    <th>Email</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Role</th>
                </tr>
                {users.map((user) =>(
                <tr key={user.id}>
                    <td>{user.username}</td>
                    <td>{user.email}</td>
                    <td>{user.first_name}</td>
                    <td>{user.last_name}</td>
                    <td>{user.role}</td>
                </tr>
              ))}
            </table>
        </div>
    )
}