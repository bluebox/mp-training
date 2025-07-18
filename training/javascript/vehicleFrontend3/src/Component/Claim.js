import { useNavigate } from "react-router-dom";

function Claim(){
    const nav=useNavigate();
    function setData(event){
        event.preventDefault();
        const form=event.target;
        const formData=new FormData(form);
        const formObj=Object.fromEntries(formData.entries());
        alert(JSON.stringify(formObj));
        console.log(JSON.stringify(formObj));
        return fetch("http://localhost:8000/claim/add",{
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
            nav("/claim/show");
        })
        .catch(()=>{
            alert("Error occured");
            console.log("Error occured");
        })
    }
    return(
        <form onSubmit={setData} style={{margin:"50px",marginLeft:"670px",marginRight:"670px",textAlign:"center"}}>
            <table>
                <tbody>
                    <tr>
                        <td colSpan={2} style={{textAlign:"center"}}><h1>Claim Details</h1></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="reqAmount">Requested Amount : </label></td>
                        <td><input type="number" id="reqAmount" name="reqAmount"/></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="damageType">Type of damage</label></td>
                        <td><input type="text" id="damageType" name="damageType"/></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="policyId">Policy ID : </label></td>
                        <td><input type="number" id="policyId" name="policyId"/></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="approvedBy" hidden>Approved By : </label></td>
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
export default Claim;