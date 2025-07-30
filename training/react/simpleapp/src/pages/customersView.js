import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

function CustomerView(){
  const navigate=useNavigate()
console.log("came into customerView")
const [customerList,setcustomerList]=useState([])


// useEffect ( async ()=>{
//   handleClick()
// },[])

const handleClick = async () => {
  try {
    const response = await fetch("http://127.0.0.1:8000/Web_World/customers/");
    if (!response.ok) {
      throw new Error("Failed to fetch");
    }

    const data = await response.json();
    setcustomerList(data)
    console.log("Customer data:", customerList);
    

  } catch (error) {
    console.error("Fetch error:", error);
  }
 
};
const columns = ['id','name','email'];

const handleDelete =async (id) =>{ 
  try{
            const response=await fetch("http://127.0.0.1:8000/Web_World/customers/"+String(id),{method:'DELETE'});
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

const handleEdit =(user) =>{
   navigate('/Registration', { state: { user } });
}

 return (<>
<button className="submit-button1" onClick={handleClick}>get all customers</button>



{customerList.length ===0 ? <p>No data available to show</p> :
 (<table border="5" cellPadding="20" style={{ borderCollapse: 'collapse', marginTop: '10px',marginLeft: '300px' ,position:'center'}}>
           <thead> <tr>{columns.map( (i) => (<td key={i}> <b>{i}</b></td>))}<td><b>Action</b></td></tr>
        </thead>
        <tbody>
       { customerList.map(( customer) => <tr key={customer}> <td key={customer.id}>{customer.id}</td><td key={customer.name}>{customer.name}</td><td key={customer.mail}>{customer.email}</td>{<td><button className='nav-button' onClick={() => handleDelete(customer.id)}>Delete</button><button className='edit-button' onClick={() => handleEdit(customer)} >Edit</button></td>}
                            </tr>)}
        </tbody>
    </table>)
}

</>)
}
export default CustomerView