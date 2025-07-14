function Claim(){
    function setData(event){
        event.preventDefault();
        const form=event.target;
        const formData=new FormData(form);
        const formObj=Object.fromEntries(formData.entries());
        alert(JSON.stringify(formObj));
        console.log(JSON.stringify(formObj));
        return fetch("http://localhost:8080/claim/add",{
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
                return res;
            }
        })
        .then(()=>{
            alert("Claim is requested successfully");
            console.log("Claim is requested successfully");
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
                        <td><label htmlFor="damageType">Type of damage</label></td>
                        <td><input type="text" id="damageType" name="damageType"/></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="policyId">Policy ID : </label></td>
                        <td><input type="number" id="policyId" name="policyId"/></td>
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
export default Claim;