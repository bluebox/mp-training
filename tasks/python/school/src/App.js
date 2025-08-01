import React, { useEffect, useState } from 'react';
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
import TeacherDetails from './TeacherDetails';
import EditTeacherDetails from './EditTeacherDetails';
import ClassTeachers from './ClassTeachers';
import DisplayTeacherSubjects from './DisplayTeacherSubjects';
import SubjectAssaign from './SubjectAssaign';
import ClassSubjectsTeachers from './ClassSubjectsTeachers';
import StudentRoleAuth from './studentRoleAuth';
import Unauthorized from './Unauthorized';
import TeacherRoleAuth from './teacherRoleAuth';
import AdminRoleAuth from './adminRoleAuth';
import AdminTeacherCombiAuth from './adminTeacherCombiAuth';
import LoginRestrict from './loginRestrict';
import HomePage from './HomePage';
import AdminStudentCombiAuth from './adminStudentCombiAuth';
import ClassSubjects from './ClassSubjects';
// import AdminTeacherDetails from './AdminTeacherDetails';

function App() {
  // localStorage.getItem('isLogin')==='true'
  const [login, setLogin] = useState(localStorage.getItem('isLogin')==='true');
  const [access, setAccess] = useState([]);
  const [id, setId] = useState(localStorage.getItem('id'));

  // useEffect(()=>)

  return (
    <Router>
      <div>
        <nav>
          {login ? (
            <nav>

              {localStorage.getItem('access') === 'teacher' && (
                <nav>
                  {/* <NavLink to="/studentRegister" className={({ isActive }) => (isActive ? 'active' : '')}>
                    Student Registration
                  </NavLink> */}
                  <NavLink to="/students" className={({ isActive }) => (isActive ? 'active' : '')}>Students</NavLink>
                  <NavLink to="/teacherResults" className={({isActive})=>(isActive ? 'active':'')}>Results</NavLink>
                  <NavLink to='/uploadResults' className={({isActive})=>(isActive ? 'active':'')}>Upload Results</NavLink>
                  <NavLink to='/teacherDetails' className={({isActive})=>(isActive ? 'active':'')}>Details</NavLink>
                  <NavLink to='/teachers/subjects' className={({isActive})=>(isActive ? 'active':'')}>Subjects</NavLink>
              
                </nav>
              )}
              {localStorage.getItem('access') === 'student' && (
                <nav>
                  <NavLink to="/results" className={({isActive})=>(isActive?'active':'')}>Results</NavLink>
                  <NavLink to="/studentDetails" className={({isActive})=>(isActive?'active':'')}>Details</NavLink>
                  <NavLink to='/subjects' className={({isActive})=>(isActive?'active':'')}>Subjects</NavLink>
                </nav>
              )}
              {localStorage.getItem('access') === 'admin' && (
                <div className="nav-dropdown-container">
                  <div className="dropdown">
                    <button className="dropbtn">Students</button>
                    <div className="dropdown-content">
                      <NavLink to="/studentRegister" className={({ isActive }) => (isActive ? 'active' : '')}>Student Registration</NavLink>
                      <NavLink to="/students" className={({ isActive }) => (isActive ? 'active' : '')}>Students</NavLink>
                    </div>
                  </div>

                  <div className="dropdown">
                    <button className="dropbtn">Teachers</button>
                    <div className="dropdown-content">
                      <NavLink to="/allTeachers" className={({ isActive }) => (isActive ? 'active' : '')}>Teachers</NavLink>
                      <NavLink to="/registerTeacher" className={({ isActive }) => (isActive ? 'active' : '')}>Teacher Registration</NavLink>
                      <NavLink to="/subjectTeachers" className={({ isActive }) => (isActive ? 'active' : '')}>Subject & Teachers</NavLink>
                      <NavLink to="/createClass" className={({ isActive }) => (isActive ? 'active' : '')}>Create Class</NavLink>
                      <NavLink to="/teachers/classes" className={({ isActive }) => (isActive ? 'active' : '')}>Class Teachers</NavLink>
                      <NavLink to="/teachers/subjects/assaign" className={({ isActive }) => (isActive ? 'active' : '')}>Assign Subjects</NavLink>
                      <NavLink to="/subjects/teachers/classes" className={({ isActive }) => (isActive ? 'active' : '')}>Teacher Subjects</NavLink>
                      <NavLink to="/classes/subjects" className={({ isActive }) => (isActive ? 'active' : '')}>Class Subjects</NavLink>
                    </div>
                  </div>
                </div>
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
            <Route path='/' element={<HomePage/>}/>
            <Route element={<StudentRoleAuth/>}>
              <Route path='/editDetails' element={<EditDetails userId={id}/>}/>
              <Route path='/results' element={< StudentResultsDashboard userId={id}/>}/>
              <Route path='/studentDetails' element={<StudentDetails userId={id}/>}/>
              <Route path='/subjects' element={<ViewClasses userId={id}/>}/>
            </Route>

            <Route element={<TeacherRoleAuth/>}>
              
              <Route path='/teacherResults' element={<TeacherResultsView userId={id}/>}></Route>
              <Route path='/uploadResults' element={<ResultsUpload userId={id}/>}/>
              <Route path='/teacherDetails' element={<TeacherDetails userId={id}/>}/>
              <Route path='/teachers/subjects' element={<DisplayTeacherSubjects userId={id}/>}/>
            </Route>

            <Route element={<AdminRoleAuth/>}>
              
              <Route path='/studentRegister' element={<StudentRegister userId={id} />} />
              <Route path='/allTeachers' element={<Teachers/>}/>
              <Route path='/subjectTeachers' element={<SubjectTeachers/>}/>
              <Route path='/createClass' element={<CreateClasses/>}/>
              <Route path='/registerTeacher' element={<EditTeacherDetails/>} access={access}/>
              <Route path='/teachers/classes' element={<ClassTeachers/>}/>   
              <Route path='/teachers/subjects/assaign' element={<SubjectAssaign/>}/>
              <Route path='/subjects/teachers/classes' element={<ClassSubjectsTeachers/>}/>
              <Route path='/classes/subjects' element={<ClassSubjects/>}/>
            </Route>
            
            <Route element={<AdminTeacherCombiAuth/>}>
              <Route path='/students' element={<Student/>}/>
              <Route path='/editTeacherDetails' element={<EditTeacherDetails userId={id}/>}/>
            </Route>
              {/* <Route element={<AdminStudentCombiAuth/>}>
              
            </Route> */}
            
            
            
            
            
            
            
            
          </Route>
          <Route element={<LoginRestrict/>}>
              <Route path="/login" element={
                <Login
                  login={login}
                  setLogin={setLogin}
                  setId={setId}
                  setAccess={setAccess}
                />
              } />
          </Route>
          
          <Route path="/logout" element={<Logout setLogin={setLogin} />} />
          <Route path="/unauthorized" element={<Unauthorized/>}/>
        </Routes>
      </div>
    </Router>
  );
}

export default App;
