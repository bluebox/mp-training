import Header from './Header';
import Home from './Home';
import Footer from './Footer';
import About from './About';
import React from "react";
import CourseDetial from './CourseDetail';
import TeacherDetail from './TeacherDetail';
import { Routes, Route } from 'react-router-dom';
//
import Login from './User/Login';
import Register from './User/Register';
import Dashboard from './User/Dashboard';
import FavCourse from './User/FavCourse';
import MyCourse from './User/MyCourse';
import ProfileSetting from './User/ProfileSetting';
import ChangePass from './User/ChangePass';
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
import PopularCourses from './PopularCourse';
import CategoryCourses from './CategoryCourse';
//Test Pages
import TestPage from './TestPage';
import AddVideos from './Teacher/AddVideos';
import TopicVideos from './Teacher/TopicVideos';
import Logout from './User/Logout';
import EnrolledStudentDetails from './Teacher/EnrolledStudentDetails';
import StudentDetail from './User/StudentDetail';
import Search from './Search';



function Main() {
  return (
    <div className="App">
      <Header />
      <Routes>
        <Route path='/' element={<Home />} />
        <Route path='/about' element={<About />}/>
        <Route path='/detail/:course_id' element={<CourseDetial />}/>
        <Route path='search/:searchData' element={<Search/>}/>
        {/* -----------User */}
        <Route path='/user-login' element={<Login />}/>
        <Route path='/user-register' element={<Register />}/>
        <Route path='/user-dashboard' element={<Dashboard />}/>
        <Route path='/user-favcourse' element={<FavCourse/> }/>
        <Route path='/user-mycourse' element={<MyCourse/>}/>
        <Route path='/user-profilesetting' element={<ProfileSetting/>}/>
        <Route path='/user-change-pass' element={<ChangePass/>}/>
        <Route path='/user-logout' element={<Logout/>}/>
        {/* ------Master */}
        <Route path='/master-login' element={<MLogin />}/>
        <Route path='/master-register' element={<MRegister/>}/>
        <Route path='/master-dashboard' element={<MDashboard/>}/>
        <Route path='/master-user' element={<EnrolledStudents/>}/>
        <Route path='/master-add-course' element={<AddCourse/>}/>
        <Route path='/master-mycourse' element={<MCourse/>}/>
        <Route path='/master-profilesetting' element={<MProfileSetting/>}/>
        <Route path='/master-detail/:master_id' element={<TeacherDetail/>}/>
        <Route path='/all-courses' element={<AllCourses/>}/>
        <Route path='/popular-course' element={<PopularCourses/>}/>
        <Route path='/category/:category_slug' element={<CategoryCourses/>}/>
        <Route path='/test_page' element={<TestPage/>}/>
        <Route path='/master-logout' element={<MLogout/>}/>
        <Route path='/master-add-video/:course_id' element={<AddVideos/>}/>
        <Route path='/all_topic_videos/:course_id' element={<TopicVideos/>}/>
        <Route path='/enrolled_students/:course_id' element={<EnrolledStudentDetails/>}/>
        <Route path='/student/:student_id' element={<StudentDetail/>}/>
      </Routes>
      <Footer />
    </div>
  );
}

export default Main;