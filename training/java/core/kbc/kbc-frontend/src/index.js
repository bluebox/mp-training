
import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import LoginPage from "./components/LoginPage";
import RegisterPage from "./components/RegisterPage";
import UserHome from "./components/user/UserHome";
import UserDetails from "./components/user/UserDetails";
import AdminHome from "./components/admin/AdminHome";
import Questions from "./components/admin/Questions";
import UpdateQuestion from "./components/admin/UpdateQuestion";
import AllUsers from "./components/admin/AllUsers";
import AddQuestion from "./components/admin/AddQuestion";
import Configurations from "./components/admin/Configurations";
import DisplayGame from './components/Game/DisplayGame'; 
import ReactDOM from 'react-dom/client';
import App from "./App";
import './App.css';

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
  <BrowserRouter>
        <Routes>
          <Route path="/" element={<App />} />
          <Route path="/home" element={<App />} />
          <Route path="/login-page" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/user/home" element={<UserHome />} />
          <Route path="/user/details" element={<UserDetails />} />
          <Route path="/admin/home" element={<AdminHome />} />
          <Route path="/admin/questions" element={<Questions />} />
          <Route path="/admin/update-question" element={<UpdateQuestion />} />
          <Route path="/admin/all-users" element={<AllUsers />} />
          <Route path="/admin/add-question" element={<AddQuestion />} />
          <Route path="/admin/configurations" element={<Configurations />} />
          <Route path="/game" element={<DisplayGame />} />
          <Route path="*" element={<h2>Page Not Found</h2>} />
        </Routes>
    </BrowserRouter>
);