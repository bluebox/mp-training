function User(){
    function setData(event){
        event.preventDefault();
        const form=event.target;
        const formData=new FormData(form);
        const formObj=Object.fromEntries(formData.entries());
        const formDetails=JSON.stringify(formObj);
        alert(JSON.stringify(formObj));
        console.log(JSON.stringify(formObj));
        return fetch("http://localhost:8080/user/add",{
            method:"POST",
            headers:{
                "Content-Type":"application/json"
            },
            body:formDetails,
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
        .then((data)=>{
            alert("User data added successfully");
            console.log("User data added successfully");
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
                        <td><label htmlFor="username">User name : </label></td>
                        <td><input type="text" id="username" name="username"/></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="password">Password : </label></td>
                        <td><input type="text" id="password" name="password"/></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="passwordUpdatedBy">Password Updated By : </label></td>
                        <td><input type="text" id="passwordUpdatedBy" name="passwordUpdatedBy"/></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="customerId2">Customer ID : </label></td>
                        <td><input type="number" id="customerId2" name="customerId" /></td>
                    </tr>
                    <tr>
                        <td colSpan={2}><input type="submit"/></td>
                    </tr>
                </tbody>
            </table>
        </form>
    )
}
export default User;