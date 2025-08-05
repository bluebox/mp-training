import React ,{useEffect, useState} from "react";
import { useNavigate,useLocation } from "react-router-dom";





function CustomerPage(){
 const [booksList,setbooksList]=useState([])
 const [get_book,setGet]=useState(false)
 const location = useLocation();
 const username = location.state;

  const handleGetBooks =async (e) =>{
    setGet(true)
      try {
        const response = await fetch("http://127.0.0.1:8000/bookStore/books/",{method:'GET',
       headers:{ 'Authorization':'Bearer ' + localStorage.getItem('access') }});
       if (response.status === 401 ||
      response.status === 403 ){
          alert("your session was expired")
      localStorage.removeItem('access')
      localStorage.removeItem('refresh')
      navigate('/')
      }
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
        console.log("bok id",book.id,"quantity",book.quantity)
      const response=await fetch("http://127.0.0.1:8000/bookStore/customers/?username="+username)
      //   {method:'POST',
      //  headers:{ 'Authorization':'Bearer ' + localStorage.getItem('access') }});

      const data=await response.json()

          console.log("bok id",book.id,"customer id:",data.id,"quantity",book.quantity)
      const response1=await fetch("http://127.0.0.1:8000/bookStore/orders/",{method:"POST",headers: {
        'Content-Type': 'application/json','Authorization':'Bearer ' + localStorage.getItem('access')
      },body: JSON.stringify({'quantity':book.quantity,'customer':data.id,'book':book.id,})})
      
      alert("your order was successfully placed")
      window.location.reload()
      handleGetBooks()
    }

const handleLogOut = (e) => {
    localStorage.removeItem('access');
  localStorage.removeItem('refresh');
  localStorage.removeItem('role')
  navigate("/")
}
 
const navigate=useNavigate();


    return (<>

            <nav className="nav-link">
      <button className='nav-button' onClick={() => navigate('/Customerpage',{state:username})}>Home</button>
      <button className='nav-button' onClick={() => navigate('/MyOrders',{state :username})}>my Orders</button>
      <button className='nav-button' onClick={() => navigate('/CustomerRegisterPage',{state:{'username':username}})}>Edit profile</button>
      <button className='nav-button' onClick={() => handleGetBooks()}>available books</button>
      <button className='nav-button'  onClick={handleLogOut}>LogOut</button>
</nav>  
<h1 style={{textAlign:'center'}}>Welcome {username} have a good day</h1>
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