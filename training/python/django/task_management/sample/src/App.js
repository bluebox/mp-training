import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Dashboard from "./pages/Dashboard";
import AdminView from "./pages/AdminView";
import Register from "./components/Register";
import Login from "./components/Login";
import Home from "./pages/Home";
import Navbar from "./components/Navbar";
const App = () => {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/admin" element={<AdminView />} />
      </Routes>
    </Router>
  );
};

export default App;
