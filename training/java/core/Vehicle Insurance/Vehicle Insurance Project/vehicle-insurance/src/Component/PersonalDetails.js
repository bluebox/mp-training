// import { encode } from "base-64";
// var base64=require("base-64");
// import { useEffect, useState } from "react";
import {useNavigate} from "react-router-dom";
function UserRegistration(){
    const nav=useNavigate();
    // const [formDetails,setFormDetails]=useState({
    //     "name":"",
    //     "email":"",
    //     "contact":"5000000000",
    //     "gender":"MALE",
    //     "age":18,
    //     "occupation":"",
    //     "income":0,
    //     "address":"",
    //     "createdBy":""
    // });
    // const email=formDetails.contact;
    // const [customerId,setCustomerId]=useState(0);
    // useEffect(()=>{
    //     fetch("http://localhost:8000/customer/getByEmail?email="+{email}, {
    //         method: "GET"
    //     })
    //     .then((res)=>{
    //         if(!res.ok) {
    //             alert("Failed to retrieve data");
    //         }
    //         return res;
    //     })
    //     .then((data)=>{
    //         setCustomerId(data);
    //     })
    //     .catch((err)=>{
    //         alert("Error occured",err);
    //     });
    // },[email]);
    // console.log(customerId);
    function setData(event){
        event.preventDefault();
        const form=event.target;
        const formData=new FormData(form);
        const formObj=Object.fromEntries(formData.entries());
        const formDetails=JSON.stringify(formObj);
        console.log(formDetails);
        alert(formDetails);
        // setFormDetails(JSON.stringify(formObj));
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        // headers.append('Accept', 'application/json');
        // headers.append('Access-Control-Allow-Origin', 'http://localhost:3000');
        // headers.append('Access-Control-Allow-Credentials', 'true');
        // headers.append('GET', 'POST', 'OPTIONS');
        // headers.append("Authorization","Basic"+base64.encode("srinu"+":"+"43434"));
        fetch("http://localhost:8000/customer/add",{
            method:"POST",
            headers:headers,
            body:formDetails
        })
        .then((res)=>{
            if(!res.ok){
                throw new Error("Failed to fetch data");
            }
            else{
                console.log(JSON.stringify(res));
                return res;
            }
        })
        .then((data)=>{
            alert("Book added successfully");
            nav("/user/vehicle");
        })
        .catch(()=>{
            alert("Exception occured");
        })
    }
    return(
        <form onSubmit={setData}>
            <table>
                <tbody>
                    <tr>
                        <td><label htmlFor="name">Customer name : </label></td>
                        <td><input type="text" id="name" name="name" required/></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="email">Email : </label></td>
                        <td><input type="email" id="email" name="email"/></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="contact">Phone no : </label></td>
                        <td><input type="text" id="contact" name="contact" pattern="[5-9]{1}[0-9]{9}"/></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="gender">Gender : </label></td>
                        <td>
                            <select id="gender" name="gender">
                                <option hidden>Enter your gender</option>
                                <option value={"MALE"}>Male</option>
                                <option value={"FEMALE"}>Female</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label htmlFor="age">Age : </label></td>
                        <td><input type="number" name="age" min={18}/></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="occupation">Occupation : </label></td>
                        <td><input type="text" id="occupation" name="occupation"/></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="income">Income : </label></td>
                        <td><input type="number" id="income" name="income"/></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="address">Address : </label></td>
                        <td><input type="text" id="address" name="address"/></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="createdBy">Created By : </label></td>
                        <td><input type="text" id="createdBy" name="createdBy"/></td>
                    </tr>
                    <tr>
                        <td colSpan={2}><input type="submit"/></td>
                    </tr>
                </tbody>
            </table>
        </form>
    )
}
export default UserRegistration;