import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function Vehicle(props){
    return (
    <tr>
        <td>{props.sample.vehicleId}</td>
        <td>{props.sample.chasisNum}</td>
        <td>{props.sample.regNum}</td>
        <td>{props.sample.vehicleModel}</td>
        <td>{props.sample.purchaseDate}</td>
        <td>{props.sample.vehicleUpdatedOn}</td>
        <td>{props.sample.vehicleUpdatedBy}</td>
        <td>{props.sample.status}</td>
        <td>{props.sample.customerId}</td>
        <td>{props.sample.createdBy}</td>
        <td><Link to={`/updateBooks/${props.sample.bookId}`}><button>Update</button></Link></td>
        <td><Link  to={`/updateAvailability/${props.sample.bookId}`}><button>Change Status</button></Link></td>
        <td><button onClick={() => props.onDelete(props.sample.vehicleId)}>Delete</button></td>
    </tr>
    );
}
function ShowVehicle(){
    const[vehicle,setVehicle]=useState([]);
    useEffect(()=>{
        fetch("http://localhost:8000/vehicle/showAll", {
            method: "GET"
        })
        .then((res)=>{
            if(!res.ok) {
                alert("Failed to retrieve data");
            }
            return res.json();
        })
        .then((data)=>{
            setVehicle(data);
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
    function DeleteVehicle(vehicleId){
        fetch(`http://localhost:8000/customer/delete?customerId=${vehicleId}`,{
        method:"PUT",
        })
        .then(res => res.text())
        .then(msg => {
        alert(msg);
        window.location.reload();
        });
    }
    return(
        <div>
            <table>
                <thead>
                    <tr>
                        <th>Vehicle Number</th>
                        <th>Chasis Number</th>
                        <th>Registration Number</th>
                        <th>Vehicle Model</th>
                        <th>Purchase Date</th>
                        <th>Vehicle Details Updated On</th>
                        <th>Vehicle Details Updated By</th>
                        <th>Status</th>
                        <th>Customer ID</th>
                        <th>Customer Created By</th>
                    </tr>
                </thead>
                    <tbody>
                        {vehicle.map((x)=>(<Vehicle sample={x} onDelete={DeleteVehicle}/>))}
                    </tbody>
            </table>
            <Link to="/addBooks"><button>Add Book</button></Link>
        </div>
    );
}
export default ShowVehicle;

//{
//    "chasisNum":2875,
//    "regNum":"fghiuthg8",
//    "vehicleModel":"fgfhgj",
//    "purchaseDate":"2025-07-09T23:24:44",
//    "vehicleUpdatedOn":"2025-07-09T23:24:44",
//    "vehicleUpdatedBy":"Bhanu",
//    "status":"A",
//    "customerId":1,
//    "createdBy":"Bhanu"
//}
