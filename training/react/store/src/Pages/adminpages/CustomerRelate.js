import React, { useEffect, useState } from "react";
import { data, useNavigate,useLocation } from "react-router-dom";
import { getCSRFToken } from './csrf';
// import './adminpages.css';


function CustomerRelated(){

const navigate=useNavigate()
const location=useLocation();
const [customerList,setcustomerList]=useState([])
const [pageCount, setPageCount] = useState(0);
const { username="", from = "" } = location.state || {};

// const [loading, setLoading] = useState(true);



const handleClick = async () => {
  try {
    const response = await fetch("http://127.0.0.1:8000/store/getCustomers/")
       if (!response.ok) {
      throw new Error("Failed to fetch");
    }
    const data = await response.json()  

    setcustomerList(data)

  } catch (error) {
    console.error("Fetch error:", error);
  }
 
};

  useEffect(()=>{

  handleClick()
},[]);


const columns = ['id','name','username','age','gender','email','address'];

const handleDelete =async (id) =>{ 
  try{const confirmDelete = window.confirm("Are you sure you want to delete this order?");
    if (!confirmDelete) {
      return;
    }
    const response_access=await fetch("http://127.0.0.1:8000/store/GetAccessToken/"+localStorage.getItem('id'))
    const response_access_json=await response_access.json()
    if (response_access_json.access ==="Session expired !!! please login again"){
      navigate('/')
      return;
    }
        const response=await fetch("http://127.0.0.1:8000/store/CustomUserView/"+String(id),{method:'DELETE',
        headers: {'Content-Type': 'application/json','X-CSRFToken': getCSRFToken(),'Authorization': 'Bearer ' + response_access_json.access }});
            if (!response.ok) {
      throw new Error("Failed to fetch");
    }
    else{
      alert("Customer deleted");
      handleClick()
    }

}


catch{
  alert("got an error");
}}

const handleEdit =(username) =>{
   navigate('/CustomerRegisterPage', {state: { 'username':username, 'from': "admin" }}); }




 return (<>
 <nav className="nav-link">

  <button className='nav-button' onClick={() => navigate('/AdminHomePage',{state:{'username':username}})}>Home</button>
<button className="nav-button" onClick={() => navigate('/CustomerRegisterPage', {state: {  from: "admin" }})}>Add customer</button>

</nav>
{/* {  customerList.length === 0 && loading ? <p>Loading...</p> : null} */}
{customerList.length ===0 ? <p>No data available to show</p> :
 (<>
 <table border="5" cellPadding="10" style={{ borderCollapse: 'collapse', marginTop: '10px',marginLeft: '30px' }}>
           <thead> <tr>{columns.map( (i) => (<td key={i}> <b>{i}</b></td>))}<td><b>Action</b></td></tr>
        </thead>
        <tbody>
       { customerList.map(( customer) => <tr key={customer}> <td >{customer.id}</td><td >{customer.name}</td><td >{customer.username}</td><td >{customer.age}</td><td >{customer.gender}</td><td >{customer.email}</td><td >{customer.address}</td>
       {<td> <button className='edit-button' onClick={() => handleDelete(customer.id)} >DELETE</button><button className='edit-button' onClick={() => handleEdit(customer.username)} >Edit</button></td>}
                            </tr>)}
        </tbody>
    </table>
    </>)
}
 

</>)
}
export default CustomerRelated;