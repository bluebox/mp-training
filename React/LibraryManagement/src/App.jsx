import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import Books from './pages/Books';
import Members from './pages/Members';
import Issues from './pages/Issues';
import Login from './pages/Login';
import BookInfo from './pages/BookInfo'
import ProtectedRoute from './components/Protected';
import Profile from './pages/Profile';

function App() {
  return (
    <Router>
      <Navbar />
      <div className="p-4">
        <Routes>
          <Route path="/" element={<ProtectedRoute><Profile/></ProtectedRoute>} />
          <Route path="/books" element={<ProtectedRoute><Books /></ProtectedRoute>} />
          <Route path="/members" element={<ProtectedRoute><Members /></ProtectedRoute>} />
          <Route path="/issues" element={<ProtectedRoute><Issues /></ProtectedRoute>} />
          <Route path='/books/:id' element={<ProtectedRoute><BookInfo/></ProtectedRoute>}/>
          <Route path="/login" element={<Login />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
