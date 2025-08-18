import React ,{use, useEffect, useState} from "react";
import { useNavigate,useLocation } from "react-router-dom";





function AuthorsPage(){
 const [booksList,setbooksList]=useState([])
 const location = useLocation();
 const username = location.state;

console.log("username",username)

  const handleGetBooks =async (e) =>{
      try {
        const response = await fetch("http://127.0.0.1:8000/store/books/?id="+localStorage.getItem('id'));
        if (!response.ok) {
          throw new Error("Failed to fetch");
        }
    
        const data = await response.json();
        setbooksList(data)
        
    
      } catch (error) {
        console.error("Fetch error:", error);
      }
     
    };

useEffect(()=>{
  handleGetBooks()
},[])

    const columns = ['title','content','price'];

  const handleLogOut =async (e) => {
    const confirmLogout = window.confirm("Are you sure you want to log out?");
    if (!confirmLogout) {

      return;}
    const response=await fetch("http://127.0.0.1:8000/store/logout/"+localStorage.getItem('id'))
    navigate("/")
}
 

const navigate=useNavigate();
const handleMyProfile =  (e) => {
  navigate('/MyProfileAuthors', { state: username });
}

    return (<>

            <nav className="nav-link">
     <button className='nav-button' onClick={() => navigate('/AuthorsPage',{state:username})}>Home</button>
      {/* <button className='nav-button' onClick={() => handleGetBooks()}>my Books</button> */}
     <button className='nav-button' onClick={() => handleMyProfile()}>My Profile</button>
      <button className='nav-button'  onClick={handleLogOut}>LogOut</button>
</nav>
   
    {(booksList.length ===0 ? <p>Sorry,No Books are mapped to you!!!</p> :
 (<> <h1 style={{textAlign:'center'}}>You have the access to following books</h1>
 <table border="5" cellPadding="20" style={{ borderCollapse: 'collapse', marginTop: '10px',marginLeft: '50px' ,position:'center'}}>
           <thead> <tr>{columns.map( (i) => (<td key={i}> <b>{i}</b></td>))}<td><b>ACTION</b></td></tr>
        </thead>
        <tbody>
       { booksList.map(( book) => <tr key={book}> <td key={book.title}>{book.title}</td><td key={book.content}>{book.content}</td><td key={book.price}>{book.price}</td>
       {<td> <label>  </label>
        <button className='edit-button' onClick={() =>navigate('/Handlebookedit',{state: {'book':book,'username':username}})} >Edit content</button></td>}
                            </tr>)}
        </tbody>
    </table></>)
  )} 
</>)
}export default AuthorsPage;


