import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes, NavLink } from 'react-router-dom';
import Login from './login';
import Logout from './Logout';
import PrivateRoute from './private';
import StudentRegister from './studentRegister';
import './App.css';
import logo from './logo.svg'; 
import Student from './students';
import StudentResultsDashboard from './studentDashboard';

function App() {
  const [login, setLogin] = useState(false);
  const [access, setAccess] = useState([]);
  const [id, setId] = useState(-1);

  return (
    <Router>
      <div>
        <nav>
          {login ? (
            <nav>

              {access === 'teacher' && (
                <nav>
                  <NavLink to="/studentRegister" className={({ isActive }) => (isActive ? 'active' : '')}>
                    Student Registration
                  </NavLink>
                  <NavLink to="/" className={({ isActive }) => (isActive ? 'active' : '')}>Students</NavLink>
                </nav>
              )}
              {access === 'student' && (
                <nav>
                  <NavLink to="/results" className={({isActive})=>(isActive?'active':'')}>Results</NavLink>
                </nav>
              )}
              <NavLink to="/logout" className={({ isActive }) => (isActive ? 'active' : '')}>
                Logout
              </NavLink>
            </nav>
          ) : (
            <NavLink to="/login" className={({ isActive }) => (isActive ? 'active' : '')}>
              Login
            </NavLink>
          )}
        </nav>

        <Routes>
        
          <Route element={<PrivateRoute login={login} />}>
            <Route path='/studentRegister' element={<StudentRegister userId={id} />} />
            <Route path='/' element={<Student/>}/>
            <Route path='/results' element={< StudentResultsDashboard userId={id}/>}/>
          </Route>

          <Route path="/login" element={
            <Login
              login={login}
              setLogin={setLogin}
              setId={setId}
              setAccess={setAccess}
            />
          } />
          <Route path="/logout" element={<Logout setLogin={setLogin} />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
