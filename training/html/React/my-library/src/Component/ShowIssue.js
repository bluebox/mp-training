import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function Issue(props){
    return (
    <tr>
        <td>{props.sample.issueId}</td>
        <td>{props.sample.bookId}</td>
        <td>{props.sample.memberId}</td>
        <td>{props.sample.statusrec}</td>
        <td>{props.sample.issueDate}</td>
        <td>{props.sample.returnDate}</td>
        <td><Link to={`/returnIssue/${props.sample.issueId}`}><button>Delete</button></Link></td>
    </tr>
    );
}
function ShowIssues(){
    const[issue,setIssue]=useState([]);
    useEffect(()=>{
        fetch("http://localhost:8000/issueBook/showIssue", {
            method: "GET"
        })
        .then((res)=>{
            if(!res.ok) {
                alert("Failed to retrieve data");
            }
            return res.json();
        })
        .then((data)=>{
            setIssue(data);
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
    return(
        <div>
            <table>
                <thead>
                    <tr>
                        <th>Issue ID</th>
                        <th>Book ID</th>
                        <th>Member ID</th>
                        <th>Status</th>
                        <th>Issue Date</th>
                        <th>Return Date</th>
                    </tr>
                </thead>
                    <tbody>
                        {issue.map((x)=>(<Issue sample={x}/>))}
                    </tbody>
            </table>
            <Link to="/addIssue"><button>Add Issue</button></Link>
        </div>
    );
}
export default ShowIssues;
