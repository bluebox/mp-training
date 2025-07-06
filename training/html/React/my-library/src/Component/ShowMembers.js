import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function Members(props){
    return (
    <tr>
        <td>{props.sample.memberId}</td>
        <td>{props.sample.name}</td>
        <td>{props.sample.email}</td>
        <td>{props.sample.mobile}</td>
        <td>{props.sample.gender}</td>
        <td>{props.sample.address}</td>
        <td><Link to={`/updateMember/${props.sample.memberId}`}><button>Update</button></Link></td>
        <td><Link><button onClick={()=>props.onDelete(props.sample.memberId)}>Delete</button></Link></td>
    </tr>
    );
}
function ShowMembers(){
    const[member,setMember]=useState([]);
    useEffect(()=>{
        fetch("http://localhost:8000/member/show", {
            method: "GET"
        })
        .then((res)=>{
            if(!res.ok) {
                alert("Failed to retrieve data");
            }
            return res.json();
        })
        .then((data)=>{
            setMember(data);
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
    function DeleteBooks(memberId){
        fetch(`http://localhost:8000/member/delete?memberId=${memberId}`,{
        method:"DELETE",
        })
        .then(res => res.text())
        .then(msg => {
        alert(msg);
        window.location.reload();
        });
    }

    return(
        <div>
            <table>
                <tr>
                    <th>Member ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone no</th>
                    <th>Gender</th>
                    <th>Address</th>
                </tr>
                {member.map((x)=>(<Members sample={x} onDelete={DeleteBooks}/>))}
            </table>
            <Link to="/addMember"><button>Add Member</button></Link>
        </div>
    );
}
export default ShowMembers;
