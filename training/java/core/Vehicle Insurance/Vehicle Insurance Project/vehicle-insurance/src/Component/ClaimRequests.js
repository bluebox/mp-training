import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function RequestedClaim(props){
    return (
    <tr>
        <td>{props.sample.claimId}</td>
        <td>{props.sample.reqAmount}</td>
        <td>{props.sample.damageType}</td>
        <td>{props.sample.claimStatus}</td>
        <td>{props.sample.claimDate}</td>
        <td>{props.sample.policyId}</td>
        <td>{props.sample.approvedBy}</td>
        <td><Link to={`/claim/approve/${props.sample.claimId}`}><button>Approve/Reject</button></Link></td>
    </tr>
    );
}
function ShowRequestedClaim(){
    const[claim,setClaim]=useState([]);
    useEffect(()=>{
        fetch("http://10.129.241.187:8000/claim/allIntiatedClaims", {
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
            setClaim(data);
        })
        .catch((err)=>{
            alert("Error occured"+err.message);
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
        <div style={{textAlign:"center",marginLeft:"620px",marginRight:"620px"}}>
            <h1>All Requested Claims</h1>
            <table style={{border:"2px solid"}}>
                <thead>
                    <tr>
                        <th>Claim ID</th>
                        <th>Requested Amount</th>
                        <th>Damage Type</th>
                        <th>Claim Status</th>
                        <th>Claim Date</th>
                        <th>Policy ID</th>
                        <th>Approved By</th>
                        <th>Action</th>
                    </tr>
                </thead>
                    <tbody>
                        {claim.map((x)=>(<RequestedClaim sample={x}/>))}
                        {/* {claim.map((x) => (<RequestedClaim key={x.claimId} sample={x}/>))} */}
                    </tbody>
            </table>
            <br/>
            <Link to="/user/claim" style={{marginLeft:"20px"}}><button>Add Claim</button></Link>
            <div style={{textAlign:"center"}} colSpan={2}>
                <br/>
                <Link to="/admin" style={{padding:"20px"}}><button>Go to Home Page</button></Link>&nbsp;&nbsp;
                <Link to="/logout" style={{padding:"20px"}}><button>Logout</button></Link>
            </div>
        </div>
    );
}
export default ShowRequestedClaim;
