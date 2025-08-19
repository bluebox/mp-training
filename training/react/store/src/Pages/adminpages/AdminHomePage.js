import React, { use, useEffect, useState } from "react";
import {useNavigate ,useLocation, Link} from 'react-router-dom';
import './adminpages.css'




function AdminHomePage(){

  const navigate = useNavigate();
  const [OrdersList,setOrderList]=useState([])
  const isOrderButtonClickes=useState(false)
 const [Customers_Count,setCustomers_Count]=useState(0);
 const [Books_Count,setBooks_Count]=useState(0);
 const [Authors_Count,setAuthors_Count]=useState(0);
 const [Orders_Count,setOrders_Count]=useState(0);
 const location = useLocation();
 const [loading, setLoading] = useState(true);
const { username="", from = "" } = location.state || {};



const adminpage=async ()=>{

    const response = await fetch("http://127.0.0.1:8000/store/getCustomers/")
    const data=await response.json()
    setLoading(false)
    setCustomers_Count(data.length) 

    const response1=await fetch("http://127.0.0.1:8000/store/books/")    
    const data1=await response1.json()
    setBooks_Count(data1.length)

    const response2=await fetch("http://127.0.0.1:8000/store/orders/")
    const data2=await response2.json()
    setOrders_Count(data2.length)

    const response3=await fetch("http://127.0.0.1:8000/store/getAuthors/")
    const data3=await response3.json()
    setAuthors_Count(data3.length)


};

useEffect(()=>{
    adminpage()
},[])


const handleOrderButton = async(e) => {
    const response=await fetch("http://127.0.0.1:8000/store/orders/")
    const data=await response.json()
    if(data.length===0){
    alert("No orderss are vailable yet")

    }
    setOrderList(data)
}

const columns=['order date','quantity','customer','book']

const handleDelete =async (id) =>{
    const confirmDelete = window.confirm("Are you sure you want to delete this order?");
    if (!confirmDelete) {
      return;
    }
    const response_access=await fetch("http://127.0.0.1:8000/store/GetAccessToken/"+localStorage.getItem('id'))
    const response_access_json=await response_access.json()
    if (response_access_json.access ==="Session expired !!! please login again"){
      navigate('/')
      return;
    }
    const response=await fetch("http://127.0.0.1:8000/store/orders/"+String(id),{method:'DELETE',
       headers:{ 'Authorization':'Bearer ' + response_access_json.access}
    })
    if (!response.ok){
        alert("cant delete this order") 
    }
    else{
        alert("order deleted successfully")
        handleOrderButton()
    }
}


const handleLogOut = async (e) => {
    const confirmLogout = window.confirm("Are you sure you want to log out?");
    if (!confirmLogout) {
      return;
    }
    const response = await fetch("http://127.0.0.1:8000/store/logout/"+localStorage.getItem('id')) 
    navigate("/")
}

 
return (
<div className='home-body'>
    {(loading) ? <h1>Loading...</h1> : null}
     <nav className="nav-link">
     <button className='nav-button' onClick={() => navigate('/AdminHomePage')}>Home</button>
      <button className='nav-button' onClick={() => navigate('/CustomerRelated')}>customers</button>
      <button className='nav-button' onClick={() => navigate('/BooksPage')}>books</button>
      <button className='nav-button' onClick={handleOrderButton}>Orders</button>
      <button className='nav-button' onClick={() => navigate('/Authorrelatedpage')}>Authors</button>
      <button className='nav-button' style={{marginLeft: 'auto' }} onClick={handleLogOut}>LogOut</button>
</nav>


<h1 className="welcome-message " >Welcome {username} have a good day</h1>

{OrdersList.length ===0 ?
<table border="2" cellPadding="20" style={{ borderCollapse: 'collapse', marginTop: '10px',marginLeft: '300px' ,position:'left'}}>
    <thead>
        <tr key="head">
            <td><b>SI.no</b></td><td><b>parameter</b></td><td><b>Count</b></td>
        </tr>
    </thead>
    <tr key="Customers">
            <td>1</td><td><b><Link to='/CustomerRelated '>Customers</Link></b></td><td>{Customers_Count}</td>
        </tr>
        <tr key="Books">
            <td>2</td><td><b><Link to='/BooksPage '>Books</Link></b></td><td>{Books_Count}</td>
        </tr>
        <tr key="Orders">
            <td>3</td><td><b><button onClick={handleOrderButton}>Orders</button></b></td><td>{Orders_Count}</td>
        </tr>
        <tr key="Authors">
            <td>4</td><td><b><Link to='/Authorrelatedpage '>Authors</Link></b></td><td>{Authors_Count}</td>
        </tr>

</table>
:
 (<table border="5" cellPadding="10" className="table" style={{  borderCollapse: 'collapse',textAlign:'center', marginTop: '10px',marginLeft: '10px',marginRight: '10px',position:'center',backgroundColor:'gainsboro'}}>
   <thead> <tr>{columns.map( (i) => (<td key={i}> <b>{i}</b></td>))}<td><b>Action</b></td></tr>
        </thead>
        <tbody>
       { OrdersList.map(( order) => <tr key={order}> <td key={order.order_date}>{order.order_date}</td><td key={order.quantity}>{order.quantity}</td><td key={order.customer__username}>{order.customer__username}</td><td key={order.book__title}>{order.book__title}</td>
       <td><button className='nav-button' onClick={() => handleDelete(order.id)}>Delete</button></td>
       
                            </tr>)}
        </tbody>
    </table>)
}   
</div>)


}export default AdminHomePage;
