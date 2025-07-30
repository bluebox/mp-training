import React ,{useState}from "react";
import {  useNavigate} from 'react-router-dom';
import './link.css'
function Navigation( ) {

const isLogin=localStorage.getItem('IsLogin')==='true';
  const navigate = useNavigate();
  return (
 <nav className="nav-link">
{isLogin?
(<>
     
     <button className='nav-button' onClick={() => navigate('/Home')}>Home</button>
      <button className='nav-button' onClick={() => navigate('/Registration')}>Registration</button>
      
      <button className='nav-button' onClick={() => navigate('/customerView')}>customers details</button>
      <button className='nav-button' onClick={() => navigate('/BooksView')}>books deatils</button>
      <button className='nav-button' onClick={() => navigate('/AuthorsView')}>Authors deatils</button>
       <button className='nav-button' onClick={() => navigate('/OrderItemssView')}>Order Items deatils</button>
      <button className='nav-button' onClick={() => navigate('/DjangoLogin')}>Login into Django</button>
      <button className='nav-button' style={{position:'relative' ,right:'0px'}} onClick={() => navigate('/Logout')}>LogOut</button>
     </>):(<>
     <button className='nav-button' onClick={() => navigate('/Registration')}>Rgister</button>
     <button className='nav-button' onClick={() => navigate('/Login')}>Login</button> 
</>)}
 </nav>
  )};export default Navigation;

















  
      
      
