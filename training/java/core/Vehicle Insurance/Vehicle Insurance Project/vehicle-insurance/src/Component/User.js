function User(){
    function setData(event){
        event.preventDefault();
        const form=event.target;
        const formData=new FormData(form);
        const formObj=Object.fromEntries(formData.entries());
        alert(JSON.stringify(formObj));
        console.log(JSON.stringify(formObj));
        return fetch("http://localhost:8000/user/add",{
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
            alert("User data added successfully");
            console.log("User data added successfully");
        })
        .catch(()=>{
            alert("Error occured");
            console.log("Error occured");
        })
    }
    //{
//   "username":"Maneesh",
//   "password":"Maneesh@123",
//   "passwordUpdatedBy":"Bhanu",
//   "customerId":1
//}

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
                        <td><label htmlFor="customerId">Customer ID : </label></td>
                        <td><input type="number" id="customerId" name="customerId"/></td>
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