import React, { useEffect, useState } from 'react';
import './App.css';
import AddUser from './components/CreateUser/AddUser';
import ViewUserRequests from './components/UserRequest/ViewUserRequests';
import Navbar from './components/Navbar/Navbar';
import MainUsers from './components/MainUser/MainUsers';
import RoleManagement from './components/Role/RoleManagement';
import Login from './components/Login/Login'; 
import UserAssignedRoles from './components/Role/UserAssignedRoles';
import Home from './components/Home/Home';
import ViewRoles from './components/Role/ViewRoles';
import ChangePassword from './components/Login/ChangePassword';
import { Routes, Route, useNavigate } from 'react-router-dom';

const App = () => {
  const navigate = useNavigate();

	 
	 const [auth, setAuth] = useState(() => {
	   const saved = localStorage.getItem('auth');
	   if (saved) {
	     try {
	       return JSON.parse(atob(saved));
	     } catch (err) {
	       console.error("Failed to parse auth:", err);
	       return null;
	     }
	   }
	   return null;
	 });

 
	 const isAuthenticated = !!auth;
	 
  
  useEffect(() => {
    if (auth) {
      const encoded = btoa(JSON.stringify(auth)); 
      localStorage.setItem('auth', encoded);       
    } else {
      localStorage.removeItem('auth');
    }
  }, [auth]);



  const handleNavigate = (page, id = null) => {
    if (id) {
      navigate(`/${page}/${id}`);
    } else {
      navigate(`/${page}`);
    }
  };
  
  
  const handleLogout = () => {
    setAuth(null);
    navigate('/login');
	localStorage.removeItem('auth'); 
  };
  
  const handleAuthenticate = (newAuth) => {
    setAuth(newAuth);
    navigate('/home');
  };

  const renderPage = () => {
	return (
	<Routes>
	  {!isAuthenticated ? (
	    <>
		 <Route path="/login" element={<Login onAuthenticate={handleAuthenticate} 
		 onNavigate={handleNavigate} />} />
		 
		  <Route path="/changePassword" element={<ChangePassword auth={null} 
		  onNavigate={handleNavigate} />} />
		  
	      <Route path="*" element={<Login onAuthenticate={handleAuthenticate} />} />
	    </>
	  ) : (
	    <>
	      <Route path="/home" element={<Home auth={auth} />} />
		  
	      <Route path="/adduser" element={<AddUser auth={auth} />} />
		  
	      <Route path="/viewUserRequests" element={<ViewUserRequests 
			onNavigate={handleNavigate} auth={auth} />} />
			
	      <Route path="/mainUsers" element={<MainUsers
	        onUpdateClick={(userId) => handleNavigate('roleManagement', userId)}
	        onViewAssignedRoles={(userId) => handleNavigate('viewAssignedRoles', userId)}
	        onNavigate={handleNavigate}
	        auth={auth}
	      />} />
	      <Route path="/roleManagement/:userId" element={<RoleManagement 
			auth={auth} onNavigate={handleNavigate} />} />
		  
	      <Route path="/viewAssignedRoles/:userId" element={<UserAssignedRoles 
			auth={auth} onNavigate={handleNavigate}/>} />
			
	      <Route path="/viewroles" element={<ViewRoles auth={auth} />} />
		  
	      <Route path="/changePassword/:userId" element={<ChangePassword auth={auth} />} />
		  
	      <Route path="*" element={<Home auth={auth} />} />
	    </>
	  )}
	</Routes>
	);
  };
  
  
  

  return (
    <div>
      {isAuthenticated && <Navbar onNavigate={handleNavigate} onLogout={handleLogout} onhandlechangepassword={(useId) => handleNavigate('changePassword', useId)} auth={auth} />}
      {renderPage()}
	  </div>
  );
};

export default App;
