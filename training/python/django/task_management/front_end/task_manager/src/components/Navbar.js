// import React from "react";
import { Link, useNavigate} from "react-router-dom";


const Navbar = () => {
const navigate = useNavigate();

const handleLogout = () => {
  localStorage.removeItem("access");
  localStorage.removeItem("refresh");
  localStorage.removeItem("user");
  navigate("/login");
};
if(!localStorage.getItem("refresh")){
  return (
    <div className="navbar">
        <Link to="/login"><p style={{display:'inline', marginLeft:"10px", color:"white"}}>Login</p></Link>
    </div>
  );
}
else if(JSON.parse(localStorage.getItem("user")).role === 'admin'){
  return (
      <div className="navbar">
        <Link to="/home" style={{display:'inline', marginLeft:"10px", color:"white"}}>Home</Link>
        <Link to="/register"><p style={{display:'inline', color:"white", marginLeft:"10px"}}>Register</p></Link>
        <Link to="/dashboard" style={{display:'inline', marginLeft:"10px", color:"white"}}>Dashboard</Link>
        <Link to="/admin" style={{display:'inline', marginLeft:"10px", color:"white"}}>Admin</Link>
        <Link to="/login" style={{display:'inline', marginLeft:"10px", color:"white"}} onClick={handleLogout}>Logout</Link>
      </div>
  )
}

else{
  return (
      <div className="navbar">
        <Link to="/home" style={{display:'inline', marginLeft:"10px", color:"white"}}>Home</Link>
        <Link to="/dashboard" style={{display:'inline', marginLeft:"10px", color:"white"}}>Dashboard</Link>
        {/* <Link to="/admin" style={{display:'inline', marginLeft:"10px", color:"white"}}>Admin</Link> */}
        <Link to="/login" style={{display:'inline', marginLeft:"10px", color:"white"}} onClick={handleLogout}>Logout</Link>
      </div>
  )
}
};

export default Navbar;
