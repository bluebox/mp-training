import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function RequestedPolicy(props){
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
        <td><button onClick={() => props.onUpdate(props.sample.policyId,"A",localStorage.getItem("username"))}>Accept</button></td>
        <td><button onClick={() => props.onUpdate(props.sample.policyId,"I",localStorage.getItem("username"))}>Reject</button></td>
    </tr>
    );
}
function ShowRequestedPolicy(){
    const[policy,setPolicy]=useState([]);
    useEffect(()=>{
        fetch("http://localhost:8080/policy/allRequestedPolicies", {
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

    function UpdatePolicy(policyId,status,approvedBy){
        fetch(`http://localhost:8080/policy/updatePolicyRequested?policyId=${policyId}&status=${status}&approvedBy=${approvedBy}`,{
        method:"PUT",
        credentials:"include"
        })
        .then(res => res.text())
        .then(msg => {
        alert(msg,approvedBy);
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
                        {policy.map((x)=>(<RequestedPolicy sample={x} onUpdate={UpdatePolicy}/>))}
                    </tbody>
            </table>
            <Link to="/user/policy"><button>Add Policy</button></Link>
        </div>
    );
}
export default ShowRequestedPolicy;
