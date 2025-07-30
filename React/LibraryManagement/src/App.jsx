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
import IsAdmin from './components/IsAdmin';
import ViewBooks from './pages/ViewBooks';
import BorrowedBooks from './pages/BorrowedBooks';
import Signup from './pages/Signup';

function App() {
  return (
    <Router>
      <Navbar />
      <div className="p-4">
        <Routes>
          <Route path="/" element={<ProtectedRoute><Profile/></ProtectedRoute>} />
          <Route path="/books" element={<IsAdmin><ProtectedRoute><Books /></ProtectedRoute></IsAdmin>} />
          <Route path="/members" element={<IsAdmin><ProtectedRoute><Members /></ProtectedRoute></IsAdmin>} />
          <Route path="/issues" element={<IsAdmin><ProtectedRoute><Issues /></ProtectedRoute></IsAdmin>} />
          <Route path='/books/:id' element={<IsAdmin><ProtectedRoute><BookInfo/></ProtectedRoute></IsAdmin>}/>
          <Route path='/viewbooks' element={<ProtectedRoute><ViewBooks/></ProtectedRoute>}/>
          <Route path='/borrowedbooks' element={<ProtectedRoute><BorrowedBooks/></ProtectedRoute>}/>
          <Route path='/signup' element={<Signup/>}/>
          <Route path="/login" element={<Login />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
