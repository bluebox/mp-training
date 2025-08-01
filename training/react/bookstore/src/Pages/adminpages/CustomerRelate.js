import React, { useEffect, useState } from "react";
import { data, useNavigate } from "react-router-dom";
import { getCSRFToken } from './csrf';
function CustomerRelated(){
  const navigate=useNavigate()
console.log("came into customerView")
const [customerList,setcustomerList]=useState([])

const getAccessToken = async () => {
  const response = await fetch("http://127.0.0.1:8000/bookStore/api/token/refresh/",{
      method:'POST',headers: {'Content-Type': 'application/json', },body: JSON.stringify({refresh: localStorage.getItem('refresh')})});
     if (response.status === 401 ||
      response.status === 403 ){
      alert("your session was expired")
      localStorage.removeItem('access')
      localStorage.removeItem('refresh')
      navigate('/')
      }
    const data = await response.json();
    localStorage.setItem('access', data.access);
    handleClick()   
  }

const handleClick = async () => {
  try {
    const response = await fetch("http://127.0.0.1:8000/bookStore/customers/",{
      method:'GET',headers: {'Content-Type': 'application/json','Authorization': 'Bearer ' + localStorage.getItem('access') }});
    
    if (response.status === 401 ||
      response.status === 403 ){
         getAccessToken()
      }


      const data = await response.json()  
         if (!response.ok) {
      throw new Error("Failed to fetch");
    }
    
    

    
    setcustomerList(data)
    console.log("Customer data:", customerList);
    

  } catch (error) {
    console.error("Fetch error:", error);
  }
 
};

useEffect(()=>{

  handleClick()
},[])


const columns = ['id','name','username','age','gender','email','address','password','confirmPassword'];

const handleDelete =async (id) =>{ 
  try{
            const response=await fetch("http://127.0.0.1:8000/bookStore/customers/"+String(id),{method:'DELETE',
        headers: {'Content-Type': 'application/json','X-CSRFToken': getCSRFToken(),'Authorization': 'Bearer ' + localStorage.getItem('access') }});
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
   navigate('/CustomerRegisterPage', {state: { 'username':username, from: "admin" }}); }

 return (<>
 <nav className="nav-link">
  <button className='nav-button' onClick={() => navigate('/AdminHomePage')}>Home</button>
<button className="nav-button" onClick={() => navigate('/CustomerRegisterPage', {state: {  from: "admin" }})}>Add customer</button>

</nav>

{customerList.length ===0 ? <p>No data available to show</p> :
 (<table border="5" cellPadding="10" style={{ borderCollapse: 'collapse', marginTop: '10px',marginLeft: '30px' }}>
           <thead> <tr>{columns.map( (i) => (<td key={i}> <b>{i}</b></td>))}<td><b>Action</b></td></tr>
        </thead>
        <tbody>
       { customerList.map(( customer) => <tr key={customer}> <td key={customer.id}>{customer.id}</td><td key={customer.name}>{customer.name}</td><td key={customer.username}>{customer.username}</td><td key={customer.age}>{customer.age}</td><td key={customer.gender}>{customer.gender}</td><td key={customer.mail}>{customer.email}</td><td key={customer.address}>{customer.address}</td><td key={customer.password}>{customer.password}</td><td key={customer.confirmPassword}>{customer.confirmPassword}</td>
       {<td> <button className='edit-button' onClick={() => handleDelete(customer.id)} >DELETE</button><button className='edit-button' onClick={() => handleEdit(customer.username)} >Edit</button></td>}
                            </tr>)}
        </tbody>
    </table>)
}

</>)
}
export default CustomerRelated