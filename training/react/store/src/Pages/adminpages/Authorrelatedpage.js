import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { getCSRFToken } from './csrf';

function Authorrelatedpage(){
  const navigate=useNavigate()
  const location=useLocation();

const [authorsList,setauthorsList]=useState([])
const { username="", from = "" } = location.state || {};



const handleClick = async () => {
  try {
    const response = await fetch("http://127.0.0.1:8000/store/getAuthors/");
    if (!response.ok) {
      throw new Error("Failed to fetch");
    }

    const data = await response.json();
    setauthorsList(data)
    

  } catch (error) {
    console.error("Fetch error:", error);
  }
 
};

useEffect(()=>{

  handleClick()
},[])

const columns = ['name','username','age','gender','email','address'];

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
   navigate('/AuthorRegisterPage', {state: { 'username':username, from: "admin" }
});
}

 return (<>
 <nav className="nav-link">
  <button className='nav-button' onClick={() => navigate('/AdminHomePage',{state:{'username':username}})}>Home</button>
<button className="nav-button" onClick={() => navigate('/AuthorRegisterPage', {state: {  from: "admin" }})}>Add Author</button>

</nav>

{authorsList.length ===0 ? <p>No data available to show</p> :
 ( <table border="5" cellPadding="10" style={{ borderCollapse: 'collapse', marginTop: '10px',marginLeft: '30px' }}>
          <thead> <tr>{columns.map( (i) => (<td key={i}> <b>{i}</b></td>))}<td><b>Action</b></td></tr>
        </thead>
        <tbody>
       { authorsList.map(( author) => <tr key={author}> <td key={author.name}>{author.name}</td><td key={author.username}>{author.username}</td><td key={author.age}>{author.age}</td><td key={author.gender}>{author.gender}</td><td key={author.mail}>{author.email}</td><td key={author.address}>{author.address}</td>{<td> 
        <button className='edit-button' onClick={() => handleDelete(author.id)} >Delete</button>
        <button className='edit-button' onClick={() => handleEdit(author.username)} >Edit</button></td>}
                            </tr>)}
        </tbody>
    </table>)
}

</>)
}
export default Authorrelatedpage;