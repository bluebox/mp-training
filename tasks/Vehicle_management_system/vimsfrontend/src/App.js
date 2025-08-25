import './App.css';
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from "./Home";
import Login from "./Login";
import SignupPage from "./SignupPage";
import ViewInsurances from "./ViewInsurances";
import AdminDashBoard from './AdminDashBoard';
import Requests from "./Requests";
import Insurance from "./Insurances";
import IssueInsuranceDashboard from './IssueInsuranceDashboard';
import IssueInsurance from './IssueInsurance';
import IssueInsuranceform from './IssueInsuranceform';
import './IssueInsurance.css';
function App() {
  return (
    <div className="App">
      
      <Router>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/Register" element={<SignupPage />} />
          <Route path="/ViewInsurances/:type" element={<ViewInsurances />} />
          <Route path="/AdminDashBoard" element={<AdminDashBoard />} />
          <Route path="/InsuranceOps" element={<Insurance />} />
          <Route path="/Requests" element={<Requests />} />
          <Route path="/IssueInsurance" element={<IssueInsurance />} />
          <Route path="/IssueInsuranceDashboard" element={<IssueInsuranceDashboard />} />
          <Route path="/IssueInsuranceform" element={<IssueInsuranceform />}/>
        </Routes>
      </Router>
    </div>
  );
}
export default App;