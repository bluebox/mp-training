import React ,{useState} from 'react';
import { BrowserRouter, Routes, Route, useNavigate} from 'react-router-dom';
import './index.css';
import './App.css';
import Welcome from './pages/Home';
import Registerpage from './pages/Registerpage';
import LoginPage from './pages/loginpage';
import LogoutPage from './pages/logoutpage';
import Navigation from './links/link';
import Protect from './Protectedroots/protectedroutes';
import CustomerView from './pages/customersView';
import BooksView from  './pages/bookspage';
import AuthorsView from './pages/authorspage';
import DjangoLogin from './pages/djangologin';
import OrderItemssView from './pages/order_items'

function App() {
  return (

    <BrowserRouter>
    <Navigation/>
      <div>
        <Routes>
          <Route path="/" element={ <Protect > <Welcome  /></Protect>} />
          <Route path="/Home" element={ <Protect > <Welcome  /></Protect>} />
          <Route path="/Registration" element={<Registerpage />} />
          <Route path="/login" element={<LoginPage  />}/>
          <Route path="/Logout" element={<LogoutPage  />}/>
          <Route path="/customerView" element={<CustomerView />} />
          <Route path="/BooksView" element={<BooksView />} />
          <Route path="/OrderItemssView" element={<OrderItemssView />} />
          <Route path="/DjangoLogin" element={<DjangoLogin />} />
          <Route path="/AuthorsView" element={<AuthorsView />} />
          <Route path='*' element={<no data fount for this/>}/>
        </Routes>
      </div>
    </BrowserRouter>

  );
} export default App;
