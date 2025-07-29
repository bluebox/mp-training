import { useNavigate } from "react-router-dom";

function User(){
    const nav=useNavigate();
    function setData(event){
        event.preventDefault();
        const form=event.target;
        const formData=new FormData(form);
        const formObj=Object.fromEntries(formData.entries());
        const formDetails=JSON.stringify(formObj);
        alert(JSON.stringify(formObj));
        console.log(JSON.stringify(formObj));
        return fetch("http://10.129.241.187:8000/user/add",{
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
                return res.text();
            }
        })
        .then((data)=>{
                alert(data);
                nav("/admin");
        })
        .catch(()=>{
            alert("Error occured");
            console.log("Error occured");
        })
    }
    if(localStorage.getItem("customerId")===null){
        return(
            <form onSubmit={setData}>
                <table>
                    <thead>
                        <tr><td colSpan={2}><h1>User Details</h1></td></tr>
                    </thead>
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
                            <td><input type="text" id="passwordUpdatedBy" name="passwordUpdatedBy" value={localStorage.getItem("username")} style={{visibility:"hidden"}}/></td>
                        </tr>
                        <tr>
                            <td><label htmlFor="customerId2">Customer ID : </label></td>
                            <td><input type="number" id="customerId2" name="customerId"/></td>
                        </tr>
                        <tr>
                            <td colSpan={2}><input type="submit"/></td>
                        </tr>
                    </tbody>
                </table>
            </form>
        )
    }
    return(
        <form onSubmit={setData} style={{position:"fixed"}}>
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
                        <td><input type="text" id="passwordUpdatedBy" name="passwordUpdatedBy" value={localStorage.getItem("username")} style={{visibility:"hidden"}}/></td>
                    </tr>
                    <tr>
                        <td><input type="number" id="customerId2" name="customerId" value={localStorage.getItem("customerId")}  style={{visibility:"hidden"}}/></td>
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