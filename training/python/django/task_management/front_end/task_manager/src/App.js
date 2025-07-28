import './App.css';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AdminView from "./pages/AdminView";
import Register from "./components/Register";
import Login from "./components/Login";
import Home from "./pages/Home";
import Navbar from "./components/Navbar";
import Dashboard from './pages/Dashboard';

export default function App(){


  if(!localStorage.getItem("refresh")){  
    return <Router>
      <Navbar />
      <Routes>
        <Route path="/home" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/" element={<Login />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/register" element={<Register />} />
        <Route path="/admin" element={<AdminView />} />
      </Routes>
    </Router>;
  }
  return (
    <Router>
      <Navbar/>
      <Routes>
        <Route path="/home" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/admin" element={<AdminView />} />
      </Routes>
    </Router>
  );
};
