import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.min.css';

import Header from './components/common/header';
import Login from './pages/login';
import EmployeeHome from './pages/Employee/AddSubmission';
import MySubmissions from './pages/Employee/MySubmissions';
import ViewSubmissions from './pages/Manager/ViewSubmissions';
import Dashboard from './pages/Dashboard';
import AddMember from './pages/Admin/AddMember';
import ViewMembers from './pages/Admin/ViewMembers';

function App() {
  
  const [currRole,setCurrRole] = useState("ROLE_ANONYMOUS");
  
  useEffect(()=>{
    const currentRole = localStorage.getItem("role");
    
    setCurrRole(currentRole);
  },[])
  
  return (
    <Router>
      <Header role={currRole} setRole={setCurrRole} />
      
        <Routes>
          <Route path="/" element={<Navigate to="/dashboard" />} />
          
          <Route path="/login" element={localStorage.getItem("role")==="ROLE_ANONYMOUS" ? <Login setRole={setCurrRole}/> : <Navigate to="/dashboard"/>}  />
          <Route path="/dashboard" element={localStorage.getItem("role")!=="ROLE_ANONYMOUS" ? <Dashboard currRole={currRole}/> : <Navigate to="/login"/>}/>

          <Route path="/add-submission" element={localStorage.getItem("role")==="ROLE_EMPLOYEE"? <EmployeeHome/> : <Navigate to="/login"/>} />
          <Route path="/my-submissions" element={localStorage.getItem("role")==="ROLE_EMPLOYEE"? <MySubmissions/> : <Navigate to="/login"/>} />
          <Route path="/view-submissions" element={localStorage.getItem("role")==="ROLE_MANAGER"? <ViewSubmissions/> : <Navigate to="/login"/>} />


          <Route path="/add-member" element={localStorage.getItem("role")==="ROLE_ADMIN"? <AddMember/> : <Navigate to="/login"/>} />
          <Route path="/view-members" element={localStorage.getItem("role")==="ROLE_ADMIN"? <ViewMembers/> : <Navigate to="/login"/>} />
          <Route path="/*" element={<Navigate to="/dashboard" />} />
          
        </Routes>
    </Router>
  );
}

export default App;
