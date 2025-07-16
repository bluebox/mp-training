import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function Policy(props){
    if(props.sample.policyStatus==="Active"){
        return (
        <tr>
            <td>{props.sample.policyId}</td>
            <td>{props.sample.policyTerm}</td>
            <td>{props.sample.policyType}</td>
            <td>{props.sample.premiumAmount}</td>
            <td>{props.sample.policyAmount}</td>
            <td>{props.sample.startDate}</td>
            <td>{props.sample.endDate}</td>
            <td>{props.sample.policyStatus}</td>
            <td>{props.sample.vehicleId}</td>
            <td>{props.sample.approvedBy}</td>
            <td><Link  to={`/policy/update/${props.sample.policyId}`}><button>Renew Policy</button></Link></td>
            <td><button onClick={() => props.onDelete(props.sample.policyId)}>Delete</button></td>
        </tr>
        );
    }
    else if(props.sample.policyStatus==="InActive"){
        return (
        <tr>
            <td>{props.sample.policyId}</td>
            <td>{props.sample.policyTerm}</td>
            <td>{props.sample.policyType}</td>
            <td>{props.sample.premiumAmount}</td>
            <td>{props.sample.policyAmount}</td>
            <td>{props.sample.startDate}</td>
            <td>{props.sample.endDate}</td>
            <td>{props.sample.policyStatus}</td>
            <td>{props.sample.vehicleId}</td>
            <td>{props.sample.approvedBy}</td>
            <td><Link  to={`/policy/update/${props.sample.policyId}`}><button>Renew Policy</button></Link></td>
            <td><button onClick={() => props.onDelete(props.sample.policyId)}>Restart</button></td>
        </tr>
        );
    }
}
function ShowPolicy(){
    const[policy,setPolicy]=useState([]);
    useEffect(()=>{
        fetch("http://localhost:8000/policy/showAll", {
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
            setPolicy(data);
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
    function DeletePolicy(policyId){
        fetch(`http://localhost:8000/policy/delete?policyId=${policyId}`,{
        method:"PUT",
        credentials:"include"
        })
        .then(res => res.text())
        .then(msg => {
        alert(msg);
        window.location.reload();
        });
    }
    return(
        <div style={{marginLeft:"470px",marginRight:"400px",textAlign:"center"}}>
            <h1>All Policies</h1>
            <table>
                <thead>
                    <tr>
                        <th>Policy ID</th>
                        <th>Policy Term</th>
                        <th>Policy Type</th>
                        <th>Premium Amount</th>
                        <th>Policy Amount</th>
                        <th>Start Date</th>
                        <th>Last Paid Date</th>
                        <th>Status</th>
                        <th>Vehicle ID</th>
                        <th>Approved By</th>
                    </tr>
                </thead>
                    <tbody>
                        {policy.map((x)=>(<Policy sample={x} onDelete={DeletePolicy}/>))}
                    </tbody>
            </table>
            <br/>
            <Link to="/user/policy"><button>Add Policy</button></Link>
            <div colSpan={2}>
                <br/>
                <Link to="/admin" style={{padding:"20px"}}><button>Go to Home Page</button></Link>&nbsp;&nbsp;
                <Link to="/logout" style={{padding:"20px"}}><button>Logout</button></Link>
            </div>
        </div>
    );
}
export default ShowPolicy;
