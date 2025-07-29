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
        fetch("http://10.129.241.187:8000/policy/allRequestedPolicies", {
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
        fetch(`http://10.129.241.187:8000/policy/updatePolicyRequested?policyId=${policyId}&status=${status}&approvedBy=${approvedBy}`,{
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
        <div style={{textAlign:"center",marginLeft:"650px",marginRight:"650px",position:"fixed"}}>
            <br/><br/>
            <h1>All Policy Requests to be Approved</h1>
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
export default ShowRequestedPolicy;
