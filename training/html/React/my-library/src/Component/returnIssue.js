import { useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";

function ReturnIssue(){
    const nav=useNavigate();
    const {issueId}=useParams();
    useEffect(()=>{
        fetch(`http://localhost:8000/issueBook/return?issueId=${issueId}`,{
            method:"PUT",
            headers: { "Content-Type": "application/json" }
        })
        .then((response) => {
            if (!response.ok) throw new Error("Failed to fetch data");
            return response.text();
        })
        .then((data) => {
            alert({data});
            nav("/showIssue");
        })
        .catch((error) => {
            console.error("Error:", error);
        });
    },[issueId,nav]);
    return <p>Returned successfully</p>
}

export default ReturnIssue;