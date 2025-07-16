import { useEffect, useState } from "react";
import { data, Link, useNavigate } from "react-router-dom";

function User(props){
    return (
    <tr>
        <td>{props.sample.username}</td>
        <td>{props.sample.password}</td>
        <td>{props.sample.passwordUpdatedOn}</td>
        <td>{props.sample.passwordUpdatedBy}</td>
        <td>{props.sample.customerId}</td>
        <td><Link to={`/user/update/${props.sample.username}`}><button>Update</button></Link></td>
        <td><button onClick={() => props.onDelete(props.sample.username)}>Delete</button></td>
    </tr>
    );
}
function ShowUser(){
    const nav=useNavigate();
    const[user,setUser]=useState([]);
    useEffect(()=>{
        fetch("http://localhost:8000/user/showAll", {
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
        fetch(`http://localhost:8000/user/delete?username=${username}`,{
            method:"DELETE",
            credentials:"include"
        })
        .then((res)=>{
            if(!res){
                throw new Error("Failed to fetch data");
            }
            return res.text();
        })
        .then((data)=>{
            alert(data);
            nav("/users/show");
        })
        .catch((e)=>{
            alert("error occured : "+e.message());
        })
    }
    return(
        <div style={{textAlign:"center",marginLeft:"400px",marginRight:"400px"}}>
            <h1>All Users</h1>
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
            <br/>
            <Link to="/user/userDetails"><button onClick={localStorage.removeItem("customerId")}>Add User</button></Link>
        </div>
    );
}
export default ShowUser;
