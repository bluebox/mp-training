import logo from './logo.svg';
import './App.css';
import { BrowserRouter as Router,Route, Routes, NavLink } from 'react-router-dom';
import Register from './Register';
import Display from './Display';
import PrivateRoute from './private';
import Login from './login';
import { useState } from 'react';
import Logout from './logout';
function App() {
  const [login, setLogin] = useState(false);
  return (
    <Router>
      <div>
        <nav>
          <NavLink to="/" className={({ isActive }) => (isActive?'active':'')}>Register </NavLink>
          <NavLink to="/table" className={({isActive}) => (isActive?'active':'')}>View </NavLink>
          {/* <NavLink to="/login" className={({isActive}) => (isActive?'active':'')}>Login</NavLink> */}
          {login ? (
            <NavLink to="/logout" className={({isActive}) => (isActive? 'active':'')}>Logout</NavLink>
          ) : (
            <NavLink to="/login" className={({ isActive }) => (isActive ? 'active' : '')}>Login</NavLink>
          )}
        </nav>
          <Routes>
            <Route element={<PrivateRoute/>}>
              <Route path="/" element={<Register/>}/>
              <Route path="/:id" element={<Register/>}/>
              <Route path="/table" element={<Display />}/>
            </Route>
            <Route path='/login' element={<Login login={login} setLogin = {setLogin}/>}/>
            <Route path='/logout' element={<Logout setLogin={setLogin}/>}/>
          </Routes>
      </div>
    </Router>
  );
}

export default App;
