import React from 'react';
import { Routes, Route } from 'react-router-dom';
import { AuthProvider } from './context/AuthContext';
import Navbar from './components/Navbar';
import Home from './components/Home';
import EnrollForm from './components/EnrollForm';
import ViewRequests from './components/ViewRequests';
import ViewActiveMembers from './components/ViewActiveMembers';
import LoginForm from './components/LoginForm';
import Profile from './components/Profile';
import ChangePassword from './components/ChangePassword';
import AssignRoles from "./components/AssignRoles";
import DisableRoles from "./components/DisableRoles.jsx";
import "./App.css";


function NotFound() { return <div>404:-Not Found</div>; }

function App() {
return (
<AuthProvider>
<div>
<Navbar />
<div className='content'>
<Routes>
<Route path="/" element={<Home />} />
<Route path="/enroll" element={<EnrollForm />} />
<Route path="/requests" element={<ViewRequests />} />
<Route path="/viewallactivemembers" element={<ViewActiveMembers />} />
<Route path="/login" element={<LoginForm />} />
<Route path="/profile" element={<Profile />} />
<Route path="/change-password" element={<ChangePassword />} />
<Route path="/assign-roles/:id" element={<AssignRoles />} />
<Route path="/disable-roles/:id" element={<DisableRoles />} />
<Route path="*" element={<NotFound />} />
</Routes>
</div>

</div>
</AuthProvider>
);
}


export default App;