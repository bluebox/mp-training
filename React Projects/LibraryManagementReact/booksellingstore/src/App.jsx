import {React ,  useState } from 'react'

import './App.css'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import Home from './pages/Home';
import AddBookForm from './pages/AddBookForm';
import AddMemberForm from './pages/AddMemberForm';
import AddOrder from './pages/AddOrder';
import ViewAllBooks from './pages/ViewAllBooks';
import ViewAllMembers from './pages/ViewAllMembers';
import ViewOrderHistory from './pages/ViewOrderHistory';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/add-book" element={<AddBookForm />} />
        <Route path="/add-member" element={<AddMemberForm />} />
        <Route path="/add-order" element={<AddOrder />} />
        <Route path="/view-books" element={<ViewAllBooks />} />
        <Route path="/view-members" element={<ViewAllMembers />} />
        <Route path="/view-orders" element={<ViewOrderHistory />} />
      </Routes>
    </Router>
  );
}

export default App;
