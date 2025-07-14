// import { useState } from "react";
import { useNavigate } from "react-router-dom";
function VehicleDetails(){
    const nav=useNavigate();
    function sendData(event){
        event.preventDefault();
        const form=event.target;
        const formData=new FormData(form);
        const formObj=Object.fromEntries(formData.entries());
        const formDetails=JSON.stringify(formObj);
        alert(formDetails);
        console.log("Vehicle added successfully",formDetails);
        fetch("http://localhost:8000/vehicle/add",{
            method:"POST",
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify(formObj),
            credentials:"include"
        })
        .then((res)=>{
            if(!res.ok){
                throw new Error("Failed to fetch data");
            }
            else{
                console.log(res.data);
                return res.text();
            }
        })
        .then((data)=>{
            console.log(data);
            localStorage.setItem("vehicleId",data);
            nav("/user/userDetails");
        })
        .catch(()=>{
            alert("Exception occured");
        })
    }
    if(localStorage.getItem("customerId")!==null){
        return(
        <form onSubmit={sendData}>
            <table>
                <tbody>
                    <tr>
                        <td><label htmlFor="chasisNum">Chasis Number : </label></td>
                        <td><input type="text" id="chasisNum" name="chasisNum"/></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="regNum">Registration Number</label></td>
                        <td><input type="text" id="regNum" name="regNum"/></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="vehicleModel">Model name : </label></td>
                        <td><input type="text" id="vehicleModel" name="vehicleModel"/></td>
                    </tr>
                    <tr>
                        <td><input type="text" id="customerId1" name="customerId" value={localStorage.getItem("customerId")} style={{visibility:"hidden"}}/></td>
                    </tr>
                    <tr>
                        <td><input type="text" id="createdBy1" name="createdBy" value={localStorage.getItem("username")} style={{visibility:"hidden"}}/></td>
                    </tr>
                    <tr>
                        <td colSpan={2}><input type="submit"/></td>
                    </tr>
                </tbody>
            </table>
        </form>
        )
    }
    else{
    return(
        <form onSubmit={sendData}>
            <table>
                <tbody>
                    <tr>
                        <td><label htmlFor="chasisNum">Chasis Number : </label></td>
                        <td><input type="text" id="chasisNum" name="chasisNum"/></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="regNum">Registration Number</label></td>
                        <td><input type="text" id="regNum" name="regNum"/></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="vehicleModel">Model name : </label></td>
                        <td><input type="text" id="vehicleModel" name="vehicleModel"/></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="customerId1">Customer ID : </label></td>
                        <td><input type="text" id="customerId1" name="customerId"/></td>
                    </tr>
                    <tr>
                        <td><input type="text" id="createdBy1" name="createdBy" value={localStorage.getItem("username")} style={{visibility:"hidden"}}/></td>
                    </tr>
                    <tr>
                        <td colSpan={2}><input type="submit"/></td>
                    </tr>
                </tbody>
            </table>
        </form>
    )
    }
}
export default VehicleDetails;