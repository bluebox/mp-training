import React, { useEffect,useState } from "react";
import "./display.css"
import { Link } from "react-router-dom";

function Display(){
    const [user,setUser] = useState([]);

    useEffect(()=>{
        const localUsers = []
        for(let i = 0;i<localStorage.length;i++)
        {
            const key = localStorage.key(i)
            const data = localStorage.getItem(key)
            try{
                const parsedData = JSON.parse(data)
                parsedData.id = key
                localUsers.push(parsedData)
            }catch(e)
            {
                console.error("Invalid json ",e);
            }
        }
        setUser(localUsers)
    },[]
    );

    const handleDelete = (id)=>{
        localStorage.removeItem(id);
        setUser(user.filter((user) => user.id !== id));
    }

    // const handleAlter = (id)=>{
    //         <Link to="/" state={{"id": id.toString()}}>Alter</Link>
    // }

    return (
        <div className="display-container">
            <h2>Registered Users</h2>
            <table className="user-table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Age</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Branch</th>
                        <th>Languages</th>
                        <th>State</th>
                        <th>City</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {user.map((u) => (
                        <tr key={u.id}>
                            <td>{u.name}</td>
                            <td>{u.age}</td>
                            <td>{u.email}</td>
                            <td>{u.phone}</td>
                            <td>{u.branch}</td>
                            <td>{u.languages.join(", ")}</td>
                            <td>{u.state}</td>
                            <td>{u.city}</td>
                            <td>
                                {/* <button className="alter-btn" onClick={"/${u.id.toString()}"}>Alter</button> */}
                                <Link to={`/${u.id.toString()}`}>
                                    <button className="alter-btn">Alter</button>
                                </Link>
                                {/* <Link to="/" state={{"id": u.id.toString()}}>Alter</Link> */}

                                <button className="delete-btn" onClick={() => handleDelete(u.id)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                    {user.length === 0 && (
                        <tr>
                            <td colSpan="9" style={{ textAlign: "center" }}>No users found</td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    );
}
export default Display