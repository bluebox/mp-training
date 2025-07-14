import { useNavigate, useParams } from "react-router-dom";

function RenewPolicy(){
    const nav=useNavigate();
    const { policyId }=useParams();
    function setData(event){
        event.preventDefault();
        const form=event.target;
        const formData=new FormData(form);
        const formObj=Object.fromEntries(formData.entries());
        return fetch(`http://localhost:8000/policy/renew?policyId=${policyId}&policyTerm=${formObj.policyTerm}&approvedBy=${formObj.approvedBy}`,{
            method:"PUT",
            headers:{
                "Content-Type":"application/json"
            },
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
            console.log("Policy is renewed successfully");
            nav("/policy/show");
        })
        .catch(()=>{
            alert("Error occured");
            console.log("Error occured");
        })
    }
    return(
        <form onSubmit={setData}>
            <table>
                <thead>
                    <h1>Renew Policy</h1>
                </thead>
                <tbody>
                    <tr>
                        <td><label htmlFor="policyId" hidden>Policy ID : </label></td>
                        <td><input type="number" id="policyId" name="policyId" value={policyId}/></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="policyTerm">Policy Term : </label></td>
                        <td><input type="number" id="policyTerm" name="policyTerm"/></td>
                    </tr>
                    <tr>
                        <td><input type="text" id="approvedBy" name="approvedBy" value={localStorage.getItem("username")} style={{visibility:"hidden"}}/></td>
                    </tr>
                    <tr>
                        <td colSpan={2}><input type="submit" value="Submit"/></td>
                    </tr>
                </tbody>
            </table>
        </form>
    )
}
export default RenewPolicy;