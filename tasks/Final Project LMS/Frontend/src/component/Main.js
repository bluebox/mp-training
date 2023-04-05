import Header from './Header';
import Home from './Home';
import Footer from './Footer';
import React from "react";
import CourseDetial from './CourseDetail';
import TeacherDetail from './TeacherDetail';
import { Routes, Route } from 'react-router-dom';
//
import Register from './User/Register';
import Dashboard from './User/Dashboard';
import FavCourse from './User/FavCourse';
import MyCourse from './User/MyCourse';
import ProfileSetting from './User/ProfileSetting';
// Instructor
import MLogout from './Teacher/MLogout';
import MLogin from './Teacher/Login';
import MRegister from './Teacher/Register';
import MDashboard from './Teacher/Dashboard';
import EnrolledStudents from './Teacher/EnrolledStudents';
import AddCourse from './Teacher/AddCourse';
import MCourse from './Teacher/MCourse';
import MProfileSetting from './Teacher/ProfileSetting';
//List Pages
import AllCourses from './AllCourse';
//Test Pages
import TestPage from './TestPage';
import AddVideos from './Teacher/AddVideos';
import TopicVideos from './Teacher/TopicVideos';
import Logout from './User/Logout';
import EnrolledStudentDetails from './Teacher/EnrolledStudentDetails';
import StudentDetail from './User/StudentDetail';
import Search from './Search';
import ProtectedInstructor from './Teacher/ProtectedInstructor';
import ProtectedUser from './User/ProtectedUser';
import Page404 from './Page404';
import EditCourse from './Teacher/EditCourse';
import EditVideo from './Teacher/EditVideo';




function Main() {
  return (
    <div className="App">
      <Header />
      <Routes>
         <Route path='/' element={<Home />} />
        <Route path='/detail/:course_id' element={<CourseDetial />}/>
        <Route path='/search/:searchData' element={<Search/>}/>
        <Route path='/all-courses' element={<AllCourses/>}/>
        <Route path='/test_page' element={<TestPage/>}/>
        <Route path='/login' element={<MLogin />}/>

        {/* -----------User */}
        <Route path='/user-register' element={<Register />}/>
        <Route path='/user-dashboard' element={<ProtectedUser Component={Dashboard}  />}/>
        <Route path='/user-favcourse' element={<ProtectedUser Component={FavCourse}/> }/>
        <Route path='/user-mycourse' element={<ProtectedUser Component={MyCourse}/>}/>
        <Route path='/user-profilesetting' element={<ProtectedUser Component={ProfileSetting}/>}/>
        <Route path='/user-logout' element={<Logout/>}/> 
        {/* ------Master */}
        <Route path='/master-register' element={<MRegister/>}/>
        <Route path='/master-dashboard' element={< ProtectedInstructor Component={MDashboard}/>}/>
        <Route path='/master-user' element={<ProtectedInstructor Component={EnrolledStudents}/>}/>
        <Route path='/master-add-course' element={<ProtectedInstructor Component={AddCourse}/>}/>
        <Route path='/master-mycourse' element={<ProtectedInstructor Component={MCourse}/>}/>
        <Route path='/master-logout' element={<MLogout/>}/>
        <Route path='/master-profilesetting' element={<ProtectedInstructor Component={MProfileSetting}/>}/>
        <Route path='/master-detail/:master_id' element={<ProtectedInstructor Component={TeacherDetail}/>}/>
        <Route path='/master-edit-course/:course_id' element={<ProtectedInstructor Component={EditCourse}/>}/>
        <Route path='/master-add-video/:course_id/:course_title' element={<ProtectedInstructor Component={AddVideos}/>}/>
        <Route path='/all_topic_videos/:course_id' element={<ProtectedInstructor Component={TopicVideos}/>}/>
        <Route path='/master_edit_video/:topic_id' element={<ProtectedInstructor Component={EditVideo}/>}/>
        <Route path='/enrolled_students/:course_id' element={<ProtectedInstructor Component={EnrolledStudentDetails}/>}/>
        <Route path='/student/:student_id' element={<ProtectedInstructor Component={StudentDetail}/>}/>
        <Route path='/*' element={<Page404/>}/>
      </Routes>
      <Footer />
    </div>
  );
}

export default Main;