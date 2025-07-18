import { useState,useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";

function UpdateCustomer() {
    const nav=useNavigate();
    const { customerId } = useParams();
    const [customer,setCustomer]=useState([]);
    const [gender,setGender]=useState([]);
    useEffect(()=>{
        fetch(`http://localhost:8000/customer/show?customerId=${customerId}`,{
            method: "GET",
            credentials:"include"
        })
        .then((res)=>{
            if(!res.ok) {
                alert("Failed to retrieve data");
            }
            return res.json();
        })
        .then((data)=>{
            setCustomer(data);
            setGender(data.gender);
            alert(JSON.stringify(data));
        })
        .catch((err)=>{
            alert("Error occured",err);
        });
    },[customerId]);
    const CustomerData = (event) => {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);
    const formobj = Object.fromEntries(formData.entries());
    formobj.customerId=Number(customerId);
    console.log(customerId);
    fetch(`http://localhost:8000/customer/update?customerId=${customerId}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(formobj),
      credentials:"include"
    })
      .then((response) => {
        if (!response.ok) throw new Error("Failed to fetch data");
        console.log(JSON.stringify(formobj));
        return response.text();
      })
      .then((data) => {
        console.log(data);
        nav("/customer/show");
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  };

  return (
    <form onSubmit={CustomerData} style={{margin:"50px",marginLeft:"670px",marginRight:"670px",textAlign:"center"}}>
      <table>
            <tbody>
                <tr>
                    <td><input type="hidden" name="customerId" value={customerId}/></td>
                </tr>
                <tr>
                    <td><label htmlFor="name">Customer name : </label></td>
                    <td><input type="text" id="name" name="name" defaultValue={customer.name} required/></td>
                </tr>
                <tr>
                    <td><label htmlFor="email">Email : </label></td>
                    <td><input type="email" id="email" name="email" defaultValue={customer.email}/></td>
                </tr>
                <tr>
                    <td><label htmlFor="contact">Phone no : </label></td>
                    <td><input type="text" id="contact" name="contact" pattern="[5-9]{1}[0-9]{9}" defaultValue={customer.contact}/></td>
                </tr>
                <tr>
                    <td><label htmlFor="gender">Gender : </label></td>
                    <td>
                        <select id="gender" name="gender" value={gender} onChange={(event)=>setGender(event.target.value)}>
                            <option hidden>Enter your gender</option>
                            <option defaultChecked="true" value="MALE">Male</option>
                            <option value="FEMALE">Female</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label htmlFor="age">Age : </label></td>
                    <td><input type="number" name="age" min={18} defaultValue={customer.age}/></td>
                </tr>
                <tr>
                    <td><label htmlFor="occupation">Occupation : </label></td>
                    <td><input type="text" id="occupation" name="occupation" defaultValue={customer.occupation}/></td>
                </tr>
                <tr>
                    <td><label htmlFor="income">Income : </label></td>
                    <td><input type="number" id="income" name="income" defaultValue={customer.income}/></td>
                </tr>
                <tr>
                    <td><label htmlFor="address">Address : </label></td>
                    <td><input type="text" id="address" name="address" defaultValue={customer.address}/></td>
                </tr>
                <tr>
                    <td><input type="text" id="customerUpdatedBy" name="customerUpdatedBy" value={localStorage.getItem("username")} style={{visibility:"hidden"}}/></td>
                </tr>
                <tr>
                    <td colSpan={2}><input type="submit"/></td>
                </tr>
            </tbody>
        </table>
    </form>
  );
}

export default UpdateCustomer;
