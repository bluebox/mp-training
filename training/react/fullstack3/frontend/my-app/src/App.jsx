// App.jsx
import React from 'react';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import LandingPage from './LandingPage';
// import PeopleSection from './PeopleSection';
// import PropertiesSection from './PropertiesSection';
// import Form from './Form';
// import LoginForm from './Login';
import './App.css';
import CustomerEnrollment from './CustomerEnrollment';
import AvailableLoans from './AvailableLoans';
import LoanSchedule from './LoanSchedule';
import Payments  from './Payments';
import MakePayment from './ViewPayment.jsx';
import ViewPayment  from './ViewPayment.jsx';

function App() {
  return (
    <BrowserRouter>
      <div className='container' style={{ marginTop: '10px', fontFamily: 'Arial' }}>
      
        {/* <p>--------------Get Loans at mimimal intrest rates-------------</p> */}


        {/* Route-based Rendering */}
        <Routes>
     
          <Route path="/" element={<LandingPage />} />
          <Route path="/create-customer" element={<CustomerEnrollment/>} />
          <Route path="/loans" element={<AvailableLoans/>} />
           <Route path="/data" element={<LoanSchedule/>} /> 
           <Route path="/payments" element={<Payments/>} /> 
           <Route path="/view-payment" element={<ViewPayment/>} /> 
           <Route path="/make-payment" element={<MakePayment/>} /> 
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
