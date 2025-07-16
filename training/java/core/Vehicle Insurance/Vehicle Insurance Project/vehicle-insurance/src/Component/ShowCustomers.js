import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
function Customer(props){
    return (
    <tr>
        <td>{props.sample.customerId}</td>
        <td>{props.sample.name}</td>
        <td>{props.sample.email}</td>
        <td>{props.sample.contact}</td>
        <td>{props.sample.gender}</td>
        <td>{props.sample.age}</td>
        <td>{props.sample.occupation}</td>
        <td>{props.sample.income}</td>
        <td>{props.sample.address}</td>
        <td>{props.sample.status}</td>
        <td>{props.sample.customerUpdatedOn}</td>
        <td>{props.sample.customerUpdatedBy}</td>
        <td>{props.sample.createdBy}</td>
        <td><Link to={`/customer/update/${props.sample.customerId}`}><button>Update</button></Link></td>
        <td><button onClick={() => props.onDelete(props.sample.customerId)}>Delete</button></td>
    </tr>
    );
}
function ShowCustomers(){
    const[customer,setCustomer]=useState([]);
    useEffect(()=>{
        fetch("http://localhost:8000/customer/showAll", {
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
        })
        .catch((err)=>{
            alert("Error occured",err);
        });
    },[]);
    // for (let i = 0; i < localStorage.length; i++) {
    //     const key = localStorage.key(i);
    //     try {
    //         const item = JSON.parse(localStorage.getItem(key));
    //         if (item && item.bookId !== undefined) {
    //             books.push(item);
    //         }
    //     } catch (e) {
    //         console.warn(`Invalid JSON at key "${key}":`, e);
    //     }
    // }
    function DeleteCustomer(customerId){
        fetch(`http://localhost:8000/customer/delete?customerId=${customerId}`,{
            method:"PUT",
            credentials:"include"
        })
        .then((res)=>{
            return res.text();
        })
        .then((data)=>{
            alert(data);
            window.location.reload();
        })
        .catch((err)=>{
            alert("Error occured",err);
        });
    }
    return(
        <div style={{marginLeft:"200px",marginRight:"200px",textAlign:"center"}}>
            <h1 style={{textAlign:"center"}}>Customer Details</h1>
            <table>
                <thead>
                    <tr>
                        <th>Customer ID</th>
                        <th>Customer Name</th>
                        <th>Email</th>
                        <th>Phone Number</th>
                        <th>Gender</th>
                        <th>Age</th>
                        <th>Occupation</th>
                        <th>Income</th>
                        <th>Address</th>
                        <th>Status</th>
                        <th>Customer Updated On</th>
                        <th>Customer Updated By</th>
                        <th>Customer Created By</th>
                    </tr>
                </thead>
                    <tbody>
                        {customer.map((x)=>(<Customer sample={x} onDelete={DeleteCustomer}/>))}
                    </tbody>
            </table>
            <br/>
            <Link to="/user/personal"><button>Add Customer</button></Link>
        </div>
    );
}
export default ShowCustomers;