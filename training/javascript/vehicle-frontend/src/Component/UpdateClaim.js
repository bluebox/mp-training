import { useParams } from "react-router-dom";

function UpdateClaim(){
    const { claimId }=useParams();
    function setData(event){
        event.preventDefault();
        const form=event.target;
        const formData=new FormData(form);
        const formObj=Object.fromEntries(formData.entries());
        alert(JSON.stringify(formObj));
        console.log(JSON.stringify(formObj));
        return fetch(`http://localhost:8080/claim/approveClaim?claimId=${claimId}&claimAmount=${formObj.reqAmount}&status=${formObj.status}&approvedBy=${formObj.approvedBy}`,{
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
                return res;
            }
        })
        .then(()=>{
            alert("Claim updated successfully");
            console.log("Claim updated successfully");
        })
        .catch(()=>{
            alert("Error occured");
            console.log("Error occured");
        })
    }
    return(
        <form onSubmit={setData}>
            <table>
                <tbody>
                    <tr>
                        <td><label htmlFor="reqAmount">Requested Amount : </label></td>
                        <td><input type="number" id="reqAmount" name="reqAmount"/></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="status">Status : </label></td>
                        <input type="radio" id="status" name="status" value="A"/>Accept
                        <input type="radio" id="status" name="status" value="R"/>Reject
                    </tr>
                    <tr>
                        <td><label htmlFor="approvedBy">Approved By : </label></td>
                        <td><input type="text" id="approvedBy" name="approvedBy"/></td>
                    </tr>
                    <tr>
                        <td colSpan={2}><input type="submit" value="Submit"/></td>
                    </tr>
                </tbody>
            </table>
        </form>
    )
}
export default UpdateClaim;