// import React from 'react';
// import Table from './table/table';
import Table from './students/students';
import AddStudent from './students/AddStudent';
import AddDepartment from './department/addDepartment';
import { Route, Routes ,Link} from 'react-router-dom';
import Data from './data/data';
import './App.css';
import Create from './new/new';
import AddCourses from './courses/addcourses';
import AddTeachers from './teachers/addteachers';

function NavBar(){
  return (
    <nav className='navbar'>
        
        <Link to="/" >Home </Link>

        <Link to='/create'>new </Link>

        <Link to='/data'>Data   </Link>

    </nav>
  )
}
function App() {
  return (
    <div>
      <NavBar />
      <Routes>
       <Route path="/studentsdata" element={<Table resource="students" fields={['student_id', 'first_name', 'last_name', 'email', 'dept']} pk={"student_id"}/>} />
       <Route path='/departmentsdata' element={<Table resource="departments" fields={['dept_id', 'dept_name']} pk={"dept_id"} />} />
       <Route path='/coursesdata' element={<Table resource="courses" fields={['course_id', 'course_name', 'credits', 'dept']} pk={"course_id"}/>} />
       <Route path='/teachersdata' element={<Table resource="teachers" fields={['teacher_id', 'first_name', 'last_name', 'email', 'dept']} pk={"teacher_id"}/>} />       
       <Route path="/data" element={<Data/>}/>
       <Route path='/createstudents' element={<AddStudent resources="students"/>} />
       <Route path='/createdepartments' element={<AddDepartment resources="departments"/>} />
       <Route path='/createcourses' element={<AddCourses resources="courses"/>} />
       <Route path='/createteachers' element={<AddTeachers resources="teachers"/>} />
       <Route path="/create" element={<Create/>}/>
       <Route path="*" element={<h2>404 Not Found</h2>} />
     </Routes>

      {/* <Table
        resource="student-profiles"
        fields={['student', 'dob', 'address', 'phone']}
      />
      <Table
        resource="departments"
        fields={['dept_id', 'dept_name']}
      />
      <Table
        resource="courses"
        fields={['course_id', 'course_name', 'credits', 'dept']}
      />
      <Table
        resource="teachers"
        fields={['teacher_id', 'first_name', 'last_name', 'email', 'dept']}
      />
      <Table
        resource="enrollments"
        fields={[ 'student', 'course', 'internal_marks', 'external_marks']}
      />
      <Table
        resource="teacher-courses"
        fields={[ 'teacher', 'course']}
      /> */}
    </div>
  );
}

export default App;
