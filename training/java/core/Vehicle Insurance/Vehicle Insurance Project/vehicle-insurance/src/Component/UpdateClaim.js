import { useState,useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";

function UpdateClaim(){
    const nav=useNavigate();
    const { claimId }=useParams();
    const [claim,setClaim]=useState([]);
    const [prevAmount,setPrevAmount]=useState(0);
    useEffect(()=>{
        fetch(`http://10.129.241.187:8000/claim/claimById?claimId=${claimId}`,{
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
            setPrevAmount(data.reqAmount);
        })
        .catch((err)=>{
            alert("Error occured",err);
        });
    },[claimId]);
    function setData(event){
        event.preventDefault();
        const form=event.target;
        const formData=new FormData(form);
        const formObj=Object.fromEntries(formData.entries());
        if(formObj.reqAmount>prevAmount){
            alert("The amount issued is more than asked");
        }
        else{
            return fetch(`http://10.129.241.187:8000/claim/approveClaim?claimId=${claimId}&claimAmount=${formObj.reqAmount}&status=${formObj.status}&approvedBy=${formObj.approvedBy}`,{
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
                nav("/claim/show");
            })
            .catch(()=>{
                alert("Error occured");
                console.log("Error occured");
            })
        }
    }
    return(
        <form onSubmit={setData} style={{margin:"50px",marginLeft:"670px",marginRight:"670px",textAlign:"center",position:"fixed"}}>
            <table>
                <thead>
                    <tr>
                        <td colSpan={2}><h1>Approve Policy</h1></td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><label htmlFor="reqAmount">Requested Amount : </label></td>
                        <td><input type="number" id="reqAmount" name="reqAmount" defaultValue={claim.reqAmount}/></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="status">Status : </label></td>
                        <td>
                        <td><input type="radio" id="status" name="status" value="A" style={{textAlign:"right"}}/></td>
                        <td>Accept</td>
                        <td><input type="radio" id="status" name="status" value="R"/></td>
                        <td>Reject</td>
                        </td>
                    </tr>
                    <tr>
                        <td><input type="text" id="approvedBy" name="approvedBy" value={localStorage.getItem("username")} style={{visibility:"hidden"}}/></td>
                    </tr>
                    <tr>
                        <td colSpan={2}><input type="submit"/></td>
                    </tr>
                </tbody>
            </table>
        </form>
    )
}
export default UpdateClaim;