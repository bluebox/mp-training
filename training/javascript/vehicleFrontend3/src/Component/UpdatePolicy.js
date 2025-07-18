import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

function UpdatePolicy(){
    const [policy,setPolicy]=useState([]);
    const nav=useNavigate();
    const { policyId }=useParams();
    useEffect(()=>{
        fetch(`http://localhost:8000/policy/showById?policyId=${policyId}`,{
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
    },[policyId]);
    function setData(event){
        event.preventDefault();
        const form=event.target;
        const formData=new FormData(form);
        const formObj=Object.fromEntries(formData.entries());
        alert(JSON.stringify(formObj));
        console.log(JSON.stringify(formObj));
        formObj.policyId=policyId;
        fetch("http://localhost:8000/policy/update",{
            method:"PUT",
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify(formObj),
            credentials:"include"
        })
        .then((res)=>{
            if(!res.ok) {
                throw new Error("Failed to fetch data");
            }
            else{
                return res.text();
            }
        })
        .then((data)=>{
            alert(data);
            nav("/policy/show")
        })
        .catch(()=>{
            alert("Error occured");
            console.log("Error occured");
        })
    }
    return(
    <form onSubmit={setData} style={{margin:"50px",marginLeft:"670px",marginRight:"670px",textAlign:"center"}}>
            <h1>Update Policy</h1>
            <table>
                <tbody>
                    <tr>
                        <td><label htmlFor="policyId" hidden>Policy ID : </label></td>
                        <td><input type="number" id="policyId" name="policyId" style={{visibility:"hidden"}} defaultValue={policyId}/></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="policyTerm">Policy Term : </label></td>
                        <td><input type="number" id="policyTerm" name="policyTerm" defaultValue={policy.policyTerm}/></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="policyType">Policy Type</label></td>
                        <td>
                            <select id="policyType" name="policyType" defaultValue={policy.policyType}>
                                <option value={"silver"}>Silver</option>
                                <option value={"gold"}>Gold</option>
                                <option value={"platinum"}>Platinum</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><input type="text" id="approvedBy" name="approvedBy" defaultValue={localStorage.getItem("username")} style={{visibility:"hidden"}}/></td>
                    </tr>
                    <tr>
                        <td colSpan={2}><input type="submit"/></td>
                    </tr>
                </tbody>
            </table>
        </form>
    )
}
export default UpdatePolicy;