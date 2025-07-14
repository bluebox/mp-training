import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";

function User(props){
    console.log(props.sample);
    return (
    <tr>
        <td>{props.sample.username}</td>
        <td>{props.sample.password}</td>
        <td>{props.sample.userUpdatedOn}</td>
        <td>{props.sample.userUpdatedBy}</td>
        <td>{props.sample.customerId}</td>
        <td><Link to={`/users/update/${props.sample.username}`}><button>Update</button></Link></td>
        <td><button onClick={() => props.onDelete(props.sample.username)}>Delete</button></td>
    </tr>
    );
}
function ShowUser(){
    const nav=useNavigate();
    const[user,setUser]=useState([]);
    useEffect(()=>{
        fetch("http://localhost:8080/user/showAll", {
            method: "GET",
            credentials:"include"
        })
        .then((res)=>{
            if(!res.ok) {
                alert("Failed to retrieve data");
            }
            return res.json();
        })
        .then((data)=>{
            setUser(data);
        })
        .catch((err)=>{
            alert("Error occured",err);
        });
    },[]);
    // for (let i = 0; i < localStorage.length; i++) {
    //     const key = localStorage.key(i);
    //     try {
    //         const item = JSON.parse(localStorage.getItem(key));
    //         if (item && item.bookId !== undefined) {
    //             books.push(item);
    //         }
    //     } catch (e) {
    //         console.warn(`Invalid JSON at key "${key}":`, e);
    //     }
    // }
    function DeleteUser(username){
        fetch(`http://localhost:8080/user/delete?username=${username}`,{
            method:"DELETE",
            credentials:"include"
        })
        .then(res => res.text())
        .then(msg => {
        alert(msg);
        nav("/user/show");
        });
    }
    return(
        <div>
            <table>
                <thead>
                    <tr>
                        <th>User Name</th>
                        <th>Password</th>
                        <th>Password Updated On</th>
                        <th>Password Updated By</th>
                        <th>Customer ID</th>
                    </tr>
                </thead>
                    <tbody>
                        {user.map((x)=>(<User sample={x} onDelete={DeleteUser}/>))}
                    </tbody>
            </table>
            <Link to="/user/userDetails"><button>Add User</button></Link>
        </div>
    );
}
export default ShowUser;
