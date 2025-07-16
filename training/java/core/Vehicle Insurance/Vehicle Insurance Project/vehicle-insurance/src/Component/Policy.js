import { useNavigate } from "react-router-dom";

function Policy(){
    const nav=useNavigate();
    function setData(event){
        event.preventDefault();
        const form=event.target;
        const formData=new FormData(form);
        const formObj=Object.fromEntries(formData.entries());
        alert(JSON.stringify(formObj));
        console.log(JSON.stringify(formObj));
        return fetch("http://localhost:8000/policy/add",{
            method:"POST",
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
            console.log("Policy is requested successfully"+data);
            nav("/policy/show");
        })
        .catch(()=>{
            alert("Error occured");
            console.log("Error occured");
        })
    }
    return(
        <form onSubmit={setData} style={{margin:"50px",marginLeft:"670px",marginRight:"670px",textAlign:"center"}}>
            <table>
                <thead>
                    <tr><td colSpan={2}><h1 style={{textAlign:"center"}}>Policy Details</h1></td></tr>
                </thead>
                <tbody>
                    <tr>
                        <td><label htmlFor="policyTerm">Policy Term : </label></td>
                        <td><input type="number" id="policyTerm" name="policyTerm"/></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="policyType">Policy Type</label></td>
                        <td>
                            <select id="policyType" name="policyType">
                                <option value={"silver"}>Silver</option>
                                <option value={"gold"}>Gold</option>
                                <option value={"platinum"}>Platinum</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label htmlFor="vehicleId">Vehicle ID : </label></td>
                        <td><input type="number" id="vehicleId" name="vehicleId"/></td>
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
export default Policy;