import React, { useEffect, useState } from "react";
import { Form } from "react-bootstrap";
import { Link } from "react-router-dom";
function Header() {
  const [searchData, setSearchData] = useState({
    'search': '',
  });
  const teacherLoginStatus = localStorage.getItem('teacherLoginStatus');
  const loginStatus = localStorage.getItem('loginstatus');
  useEffect(() => {
    document.title = "Main Home Page";
  });

  const handleChange = (e) => {
    setSearchData(e.target.value);
  }
  const searchCourse = (e) => {
    e.preventDefault();
    window.location.href = '/search/' + searchData;
  }
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
      <div className="container">
        <Link className="navbar-brand" to="/"><img src="../applogo.jpeg" height="60" width="150" alt="Start Learning" /></Link>
        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
          <div className="navbar-nav ms-auto ">
            <Form className="d-flex" onSubmit={searchCourse} style={{ height: 40 }} >
              <input className="form-control me-2" onChange={handleChange} type="text" placeholder="Search for a Course" aria-label="Search" />
              <button className="btn btn-outline-success" type="submit" id='btnSearch'>Search</button>
            </Form>
            <Link className="nav-link" aria-current="page" to="/">Home</Link>
            <Link className="nav-link" to="/all-courses">Courses</Link>
            {loginStatus !== 'true' &&

              <>
                  <Link className="nav-link" to="/login">Login</Link>
                  <li className="nav-item dropdown">
                  <p className="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    Register
                  </p>
                  <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
                    <li><Link className="dropdown-item" to="/master-register">As Instructor</Link></li>
                    <li><Link className="dropdown-item" to="/user-register">As Student</Link></li>
                  </ul>
                </li></>
            }
            {loginStatus === 'true' &&
              <li className="nav-item dropdown ms-4">
                <img src='../newuserlogo.png' className='img-thumbnail rounded-circle' data-bs-toggle="dropdown" height={40} width={40} alt='loading' />
                <ul className="dropdown-menu mt-4" aria-labelledby="navbarDropdown">
                  <li><Link className="dropdown-item" to={teacherLoginStatus === 'true' ? '/master-mycourse' : '/user-mycourse'}>My Course</Link></li>
                  <li><Link className="dropdown-item text-danger" to="/master-logout">Logout</Link></li>
                </ul>
              </li>
            }
          </div>
        </div>
      </div>
    </nav >
  )
}

export default Header;