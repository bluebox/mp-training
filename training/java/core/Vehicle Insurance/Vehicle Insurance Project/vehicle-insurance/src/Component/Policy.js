function Policy(){
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
            body:JSON.stringify(formObj)
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
            alert("Policy is requested successfully");
            console.log("Policy is requested successfully");
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
export default Policy;