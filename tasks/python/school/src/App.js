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
import StudentDetails from './studentsDetails';
import EditDetails from './editDetails';
import ViewClasses from './viewClasses';
import TeacherResultsView from './teacherResultsView';
import Teachers from './Teachers';
import SubjectTeachers from './SubjectTeachers';
import CreateClasses from './CreateClasses';
import ResultsUpload from './resultsUpload';
import TeacherDetails from './AdminTeacherDetails';
import AdminTeacherDetails from './AdminTeacherDetails';

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
                  {/* <NavLink to="/studentRegister" className={({ isActive }) => (isActive ? 'active' : '')}>
                    Student Registration
                  </NavLink> */}
                  <NavLink to="/students" className={({ isActive }) => (isActive ? 'active' : '')}>Students</NavLink>
                  <NavLink to="/teacherResults" className={({isActive})=>(isActive ? 'active':'')}>Results</NavLink>
                  <NavLink to='/uploadResults' className={({isActive})=>(isActive ? 'active':'')}>Upload Results</NavLink>
                  <NavLink to='/teacherDetails' className={({isActive})=>(isActive ? 'active':'')}>Details</NavLink>
              
                </nav>
              )}
              {access === 'student' && (
                <nav>
                  <NavLink to="/results" className={({isActive})=>(isActive?'active':'')}>Results</NavLink>
                  <NavLink to="/studentDetails" className={({isActive})=>(isActive?'active':'')}>Details</NavLink>
                  <NavLink to='/subjects' className={({isActive})=>(isActive?'active':'')}>Subjects</NavLink>
                </nav>
              )}
              {access === 'admin' &&(
                <nav>
                  <NavLink to="/studentRegister" className={({ isActive }) => (isActive ? 'active' : '')}>
                    Student Registration
                  </NavLink>
                  <NavLink to="/students" className={({ isActive }) => (isActive ? 'active' : '')}>Students</NavLink>
                  <NavLink to="/allTeachers" className={({isActive})=>(isActive ? 'active':'')}>Teachers</NavLink>
                  <NavLink to='/subjectTeachers' className={({isActive})=>(isActive ? 'active':'')}>Subject & Teachers</NavLink>
                  <NavLink to='/createClass' className={({isActive})=>(isActive ? 'active':'')}>Create Class</NavLink>
                   <NavLink to='/adminTeacherDetails' className={({isActive})=>(isActive ? 'active':'')}>Details</NavLink>
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
            <Route path='/students' element={<Student/>}/>
            <Route path='/results' element={< StudentResultsDashboard userId={id}/>}/>
            <Route path='/studentDetails' element={<StudentDetails userId={id}/>}/>
            <Route path='/editDetails' element={<EditDetails userId={id}/>}/>
            <Route path='/subjects' element={<ViewClasses userId={id}/>}/>
            <Route path='/teacherResults' element={<TeacherResultsView userId={id}/>}></Route>
            <Route path='/allTeachers' element={<Teachers/>}/>
            <Route path='/subjectTeachers' element={<SubjectTeachers/>}/>
            <Route path='/createClass' element={<CreateClasses/>}/>
            <Route path='/uploadResults' element={<ResultsUpload userId={id}/>}/>
            <Route path='/adminTeacherDetails' element={<AdminTeacherDetails userId={id}/>}/>
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
