import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

function Authorrelatedpage(){
  const navigate=useNavigate()
const [authorsList,setauthorsList]=useState([])



const handleClick = async () => {
  try {
    const response = await fetch("http://127.0.0.1:8000/bookStore/authors/");
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

const columns = ['id','name','username','age','gender','email','address','books','password','confirmPassword'];

const handleDelete =async (id) =>{ 
  try{
            const response=await fetch("http://127.0.0.1:8000/bookStore/authors/"+String(id),{method:'DELETE'});
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
  <button className='nav-button' onClick={() => navigate('/AdminHomePage')}>Home</button>
<button className="nav-button" onClick={() => navigate('/AuthorRegisterPage', {state: {  from: "admin" }})}>Add Author</button>

</nav>

{authorsList.length ===0 ? <p>No data available to show</p> :
 (<table border="5" cellPadding="20" style={{ borderCollapse: 'collapse', marginTop: '10px',marginLeft: '0px' ,position:'center'}}>
           <thead> <tr>{columns.map( (i) => (<td key={i}> <b>{i}</b></td>))}<td><b>Action</b></td></tr>
        </thead>
        <tbody>
       { authorsList.map(( author) => <tr key={author}> <td key={author.id}>{author.id}</td><td key={author.name}>{author.name}</td><td key={author.username}>{author.username}</td><td key={author.age}>{author.age}</td><td key={author.gender}>{author.gender}</td><td key={author.mail}>{author.email}</td><td key={author.address}>{author.address}</td><td key={author.books}>{author.books}</td><td key={author.password}>{author.password}</td><td key={author.confirmPassword}>{author.confirmPassword}</td>{<td> 
        <button className='edit-button' onClick={() => handleDelete(author.id)} >Delete</button>
        <button className='edit-button' onClick={() => handleEdit(author.username)} >Edit</button></td>}
                            </tr>)}
        </tbody>
    </table>)
}

</>)
}
export default Authorrelatedpage;