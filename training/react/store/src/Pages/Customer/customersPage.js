import React ,{useEffect, useState} from "react";
import { useNavigate,useLocation } from "react-router-dom";





function CustomerPage(){
 const [booksList,setbooksList]=useState([])
 const [get_book,setGet]=useState(false)
 const location = useLocation();
 const [showProfilePic, setShowProfilePic] = useState(false);
 const username = location.state;

  const handleGetBooks =async (e) =>{
    setGet(true)
      try {
        const response = await fetch("http://127.0.0.1:8000/store/books/")
      
        if (!response.ok) {
          throw new Error("Failed to fetch");
        }
    
        const data = await response.json();
        setbooksList(data)
        console.log("books data:", data);
        
    
      } catch (error) {
        console.error("Fetch error:", error);
      }
     
    };
    useEffect(()=>{
      console.log(username)
    })

    const columns = ['id','title','price'];

    const handleOrder = async (book) =>{
      const response = await fetch("http://127.0.0.1:8000/store/GetAccessToken/"+localStorage.getItem('id'))
      const response_access_json = await response.json()
      if (response_access_json.access === "Session expired !!! please login again") {
        alert("Session expired !!! please login again")
        const response_logout = await fetch("http://127.0.0.1:8000/store/logout/"+localStorage.getItem('id'))
        navigate("/")
        return
      }
      const response1=await fetch("http://127.0.0.1:8000/store/orders/",{method:"POST",headers: {
        'Content-Type': 'application/json','Authorization':'Bearer ' +response_access_json.access
      },body: JSON.stringify({'quantity':book.quantity,'customer':localStorage.getItem('id'),'book':book.id,})})
      
      alert("your order was successfully placed")
      window.location.reload()
      handleGetBooks()
    }
const handleMyProfile = async (e) => {
  navigate('/MyProfile', { state: username });
}

const handleLogOut = (e) => {
    const confirmation = window.confirm("Are you sure you want to log out?");
    if (!confirmation) {
      e.preventDefault();
      return;
    }
   const response= fetch("http://127.0.1:8000/store/logout/"+localStorage.getItem('id'))
  navigate("/")
}
 
const navigate=useNavigate();


    return (<>

            <nav className="nav-link">
      <button className='nav-button' onClick={() => navigate('/Customerpage',{state:username})}>Home</button>
      <button className='nav-button' onClick={() => navigate('/MyOrders',{state :username})}>my Orders</button>
      <button className='nav-button' onClick={() => handleGetBooks()}>available books</button>
      <button className='nav-button' onClick={() => handleMyProfile()}>My Profile</button>
      <button className='nav-button'  onClick={handleLogOut}>LogOut</button>
</nav>  
<h1 style={{textAlign:'center'}}>Welcome {username} have a good day</h1>
<div className="customer-page-container">
<img src="/image2.avif"  style={{margin:'10px',width:'1199px',height:'380px'}}  alt="Profile" /></div>
 <img src="/image.jpg" onClick={()=>setShowProfilePic(true)} alt="Profile"  className="profile-image2" />
  {showProfilePic && <div className="Clicked-outside" style={{zIndex: '1000'}}>
   <div >
    <img src="/image.jpg" alt="Profile" />
    <button title='Close' onClick={() => setShowProfilePic(false)} className="Close-button-onImage" >X</button>
    </div></div>}
    {get_book? 
    (booksList.length ===0 ? <p>No data available to show</p> :
 (<table border="5" cellPadding="20" style={{ borderCollapse: 'collapse', marginTop: '10px',marginLeft: '300px' ,position:'center'}}>
           <thead> <tr>{columns.map( (i) => (<td key={i}> <b>{i}</b></td>))}<td><b>Action</b></td></tr>
        </thead>
        <tbody>
       { booksList.map(( book) => <tr key={book}> <td key={book.id}>{book.id}</td><td key={book.title}>{book.title}</td><td key={book.price}>{book.price}</td>
       {<td> <label> <input className="inputs-gap" type="numbrer" name="quantity" placeholder="Quantity" onChange={(e) => book.quantity = e.target.value}/> </label>
        <button className='edit-button' onClick={() => handleOrder(book)} >Order it</button></td>}
                            </tr>)}
        </tbody>
    </table>)
  ) : <></>
}</>)
}export default CustomerPage;