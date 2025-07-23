import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router,Route, Routes, NavLink } from 'react-router-dom';
import Register from './Register';
import Display from './Display';
function App() {
  return (
    <Router>
      <div>
        <nav>
          <NavLink to="/" className={({ isActive }) => (isActive?'active':'')}>Register </NavLink>
          <NavLink to="/table" className={({isActive}) => (isActive?'active':'')}>View </NavLink>
        </nav>
          <Routes>
            <Route path="/" element={<Register/>}/>
            <Route path="/:id" element={<Register/>}/>

            <Route path="/table" element={<Display />}></Route>
          </Routes>
      </div>
    </Router>
  );
}

export default App;
