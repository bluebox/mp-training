import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import {BrowserRouter as Redirect } from 'react-router-dom';
import LoginPage from './components/LoginPage';
import Dashboard from "./components/Dashboard";
import Register from './components/Register';
import Admin from './components/Admin';
import Cookies from 'js-cookie';
import DashboardRoute from './components/DashboardRoute';
import Student from './components/Student';
import Home from './components/Home';
import Faculty from './components/Faculty';
function App() {
  var isAuthenticated;
  var userRole;
  if(Cookies.get('jwt_token')){
 isAuthenticated = Cookies.get('jwt_token');
 userRole = localStorage.getItem('role');
  }
 return (
      <div className="App">
      <Router>

            <Routes>
            <Route path="/" element={<Home/>} />

            <Route path="/login" element={<LoginPage/>} />
             <Route path="/register" element={<Register/>} />
            {/* <Route path = "/dashboard" element={<Dashboard/>}/> */}
             {/* <Route path="/dashboard/*"  element={<DashboardRoute/>}/> */}
          <Route path="/admindashboard" element={isAuthenticated && userRole==="Admin"?<Admin/>:<LoginPage/>} />
          <Route path="/facultydashboard" element={isAuthenticated && userRole==="Faculty"?<Faculty/>:<LoginPage/>} />
          <Route path="/studentdashboard" element={isAuthenticated && userRole==="Student"?<Student/>:<LoginPage/>} />
          <Route path="/dashboard" element={<DashboardRoute/>} />

            </Routes>

      </Router>
      </div>
  );
}
export default App;