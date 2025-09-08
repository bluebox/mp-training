import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Login from './pages/Login';
import HomePage from './pages/HomePage';
import Registration from './pages/Registration';
import MainUsers from './pages/MainUsers';
import Requests from './pages/Requests';
import Approval from './pages/Approval';
import ViewRoles from './pages/ViewRoles';
import UpdateUser from './pages/UpdateUser';
import UserProfile from './pages/UserProfile';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/home" element={<HomePage />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Registration />} />
        <Route path="/mainUsers" element={<MainUsers />} />
        <Route path="/requests" element={<Requests />} />
        <Route path="/approve/:requestId" element={<Approval />} />
        <Route path="/viewRoles" element={<ViewRoles />} />
        <Route path="/updateUserRoles/:userName" element={<UpdateUser />} />
        <Route path="/profile" element={<UserProfile />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
