import './App.css';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Register from "./components/Register";
import Login from "./components/Login";
import Home from "./pages/Home";
import Navbar from "./components/Navbar";
import Dashboard from './pages/Dashboard';
import TaskCreate from './components/TaskForm';
import TaskList from './components/TaskList';
import ViewAll from './pages/ViewAll';
import MyProfile from './pages/MyProfile';
import MyTeams from './pages/MyTeams';
import AssignTask from './components/AssignCreatedTask';
import AssignToTeam from './pages/AssignToTeam';
import CreateTeam from './pages/CreateTeam';
import NewProject from './pages/NewProject';
import LeadDashboard from './pages/LeadDashboard';

export default function App(){

  if(!localStorage.getItem("refresh")){  
    return <Router>
      <Navbar />
      <Routes>
        <Route path="/home" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/" element={<Login />} />
      </Routes>
    </Router>;
  }
  return (
    <Router>
      <Navbar/>
      <Routes>
        <Route path="/home" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/" element={<Home />} />
        <Route path="/register" element={<Register />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/lead-dashboard" element={<LeadDashboard />} />
        <Route path="/create-task" element={<TaskCreate />} />
        <Route path="/task-list" element={<TaskList />} />
        <Route path="/task-assign" element={<AssignTask />} />
        <Route path="/view-all" element={<ViewAll />} />
        <Route path="/team-assign" element={<AssignToTeam />} />
        <Route path="/my-profile" element={<MyProfile />} />
        <Route path="/my-team" element={<MyTeams />} />
        <Route path="/new-project" element={<NewProject />} />
        <Route path="/create-team" element={<CreateTeam />} />
      </Routes>
    </Router>
  );
};
