import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function Claim(props){
    return (
    <tr>
        <td>{props.sample.claimId}</td>
        <td>{props.sample.reqAmount}</td>
        <td>{props.sample.damageType}</td>
        <td>{props.sample.claimStatus}</td>
        <td>{props.sample.claimDate}</td>
        <td>{props.sample.policyId}</td>
        <td>{props.sample.approvedBy}</td>
        <td><Link to={`/updateBooks/${props.sample.bookId}`}><button>Update</button></Link></td>
        <td><Link  to={`/updateAvailability/${props.sample.bookId}`}><button>Change Status</button></Link></td>
    </tr>
    );
}
function ShowClaim(){
    const[claim,setClaim]=useState([]);
    useEffect(()=>{
        fetch("http://localhost:8080/claim/allClaims", {
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
                    </tr>
                </thead>
                    <tbody>
                        {claim.map((x)=>(<Claim sample={x}/>))}
                    </tbody>
            </table>
            <Link to="/user/claim"><button>Add Claim</button></Link>
        </div>
    );
}
export default ShowClaim;
