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
if(!localStorage.getItem("user")){
  return (
    <div className="navbar">
        <Link to="/login"><p style={{display:'inline', marginLeft:"10px", color:"white"}}>Login</p></Link>
    </div>
  );
}
else if(JSON.parse(localStorage.getItem("user")).role === 'admin'){
  return (
      <div className="navbar">
        <Link to="/home" style={{display:'inline', marginLeft:"20px", color:"white"}}>Home</Link>
        <Link to="/register"><p style={{display:'inline', color:"white", marginLeft:"10px"}}>Register</p></Link>
        <Link to="/dashboard" style={{display:'inline', marginLeft:"10px", color:"white"}}>Dashboard</Link>
        <Link to="/admin" style={{display:'inline', marginLeft:"10px", color:"white"}}>Admin</Link>
        <Link to="/create-task" style={{display:'inline', marginLeft:"10px", color:"white"}}>create task</Link>
        <Link to="/task-list" style={{display:'inline', marginLeft:"10px", color:"white"}}>TaskList</Link>
        <Link to="/view-all" style={{display:'inline', marginLeft:"10px", color:"white"}}>ViewAll</Link>
        <Link to="/login" style={{display:'inline', marginLeft:"10px", color:"white"}} onClick={handleLogout}>Logout</Link>
        <Link to="/my-profile" style={{display:'inline', marginLeft:"900px", marginRight:"10px", color:"white"}}>My Profile</Link>
      </div>
  )
}

else if(JSON.parse(localStorage.getItem("user")).role === 'lead'){
  return (
      <div className="navbar">
        <Link to="/home" style={{display:'inline', marginLeft:"10px", color:"white"}}>Home</Link>
        <Link to="/dashboard" style={{display:'inline', marginLeft:"10px", color:"white"}}>Dashboard</Link>
        <Link to="/create-task" style={{display:'inline', marginLeft:"10px", color:"white"}}>create task</Link>
        <Link to="/task-list" style={{display:'inline', marginLeft:"10px", color:"white"}}>TaskList</Link>
        <Link to="/view-all" style={{display:'inline', marginLeft:"10px", color:"white"}}>ViewAll</Link>
        <Link to="/login" style={{display:'inline', marginLeft:"10px", color:"white"}} onClick={handleLogout}>Logout</Link>
        <Link to="/my-profile" style={{display:'inline', marginLeft:"900px", marginRight:"10px", color:"white"}}>My Profile</Link>
      </div>
  )
}
else if(JSON.parse(localStorage.getItem("user")).role === 'member'){
  return (
      <div className="navbar">
        <Link to="/home" style={{display:'inline', marginLeft:"10px", color:"white"}}>Home</Link>
        <Link to="/dashboard" style={{display:'inline', marginLeft:"10px", color:"white"}}>Dashboard</Link>
        <Link to="/my-team" style={{display:'inline', marginLeft:"10px", color:"white"}}>MyTeam</Link>
        
        <Link to="/login" style={{display:'inline', marginLeft:"10px", color:"white"}} onClick={handleLogout}>Logout</Link>
        <Link to="/my-profile" style={{display:'inline', marginLeft:"900px", marginRight:"10px", color:"white"}}>My Profile</Link>
      </div>
  )
}
};

export default Navbar;
