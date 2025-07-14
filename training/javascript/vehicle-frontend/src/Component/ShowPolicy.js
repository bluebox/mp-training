import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function Policy(props){
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
        <td><Link to={`/policy/renew/${props.sample.policyId}`}><button>Renew Policy</button></Link></td>
        <td><Link  to={`/policy/update/${props.sample.policyId}`}><button>Update Policy</button></Link></td>
        <td><button onClick={() => props.onDelete(props.sample.customerId)}>Delete</button></td>
    </tr>
    );
}
function ShowPolicy(){
    const[policy,setPolicy]=useState([]);
    useEffect(()=>{
        fetch("http://localhost:8080/policy/showAll", {
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
        fetch(`http://localhost:8080/policy/delete?policy=${policyId}`,{
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
        <div>
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
            <Link to="/user/policy"><button>Add Policy</button></Link>
        </div>
    );
}
export default ShowPolicy;
