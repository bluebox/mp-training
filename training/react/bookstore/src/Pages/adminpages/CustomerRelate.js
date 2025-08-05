import React, { useEffect, useState } from "react";
import { data, useNavigate } from "react-router-dom";
import { getCSRFToken } from './csrf';
// import './adminpages.css';


function CustomerRelated(){
const navigate=useNavigate()
console.log("came into customerView")
const [customerList,setcustomerList]=useState([])
const [current, setCurrent] = useState("http://127.0.0.1:8000/bookStore/customers/?page=1");
const [previous, setPrevious] = useState(1);
const [next, setNext] = useState(2);
const [pageCount, setPageCount] = useState(0);
const [loading, setLoading] = useState(true);


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
    console.log("current:", current);
    const response = await fetch(current,{
      method:'GET',headers: {'Content-Type': 'application/json','Authorization': 'Bearer ' + localStorage.getItem('access') }});
    
    if (response.status === 401 ||
      response.status === 403){
         getAccessToken()
      }
    
      const data = await response.json()  
    
       if (data.detail === "Given token not valid for any token type"){
      getAccessToken()
      return;
    }
         if (!response.ok) {
      throw new Error("Failed to fetch");
    }
    console.log("data:", data);
    setNext(data.next);
    setPageCount(data.count);
    setPrevious(data.previous);
    console.log("next:", data.next);
    console.log("previous:", data.previous);
     
    setcustomerList(data.results)

    console.log("Customer data:", customerList);
    

  } catch (error) {
    console.error("Fetch error:", error);
  }
 
};

  useEffect(()=>{

  handleClick()
},[current]);


const columns = ['id','name','username','age','gender','email','address','password','confirmPassword'];

const handleDelete =async (id) =>{ 
  try{const confirmDelete = window.confirm("Are you sure you want to delete this order?");
    if (!confirmDelete) {
      return;
    }
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


const handlePrev = () => {
  if (previous) {
    if (previous[previous.length - 1] === '/') {
    setCurrent(previous+ "?page=1");
  } else {
    setCurrent(previous);
  }}
}
const handleNext = () => {
  if (next) {
    setCurrent(next);
  }}

 return (<>
 <nav className="nav-link">

  <button className='nav-button' onClick={() => navigate('/AdminHomePage')}>Home</button>
<button className="nav-button" onClick={() => navigate('/CustomerRegisterPage', {state: {  from: "admin" }})}>Add customer</button>

</nav>
{  customerList.length === 0 && loading ? <p>Loading...</p> : null}
{customerList.length ===0 ? <p>No data available to show</p> :
 (<>
 <table border="5" cellPadding="10" style={{ borderCollapse: 'collapse', marginTop: '10px',marginLeft: '30px' }}>
           <thead> <tr>{columns.map( (i) => (<td key={i}> <b>{i}</b></td>))}<td><b>Action</b></td></tr>
        </thead>
        <tbody>
       { customerList.map(( customer) => <tr key={customer}> <td >{customer.id}</td><td >{customer.name}</td><td >{customer.username}</td><td >{customer.age}</td><td >{customer.gender}</td><td >{customer.email}</td><td >{customer.address}</td><td >{customer.password}</td><td >{customer.confirmPassword}</td>
       {<td> <button className='edit-button' onClick={() => handleDelete(customer.id)} >DELETE</button><button className='edit-button' onClick={() => handleEdit(customer.username)} >Edit</button></td>}
                            </tr>)}
        </tbody>
    </table>

    <div display='flex' >
      
      <h1>Showing {current[current.length-1]}/ {pageCount /3 } pages</h1>
    
    <button className="prev-button" onClick={handlePrev}>Prev..</button> 
    
    <button className="prev-button" onClick={handleNext}>next..</button> 
</div>   
    </>)
}
 

</>)
}
export default CustomerRelated;